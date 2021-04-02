/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.rapport_costing;

import controllers.util.JsfUtil;
import entities.Criterestructure;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class RapportCostingCtrl extends AbstractRapportCostingCtrl implements Serializable {

    @PostConstruct
    private void init() {
        criterestructures = SessionMBean.getCritereStructures();
        this.sommeCritere();
    }

    public void prepareCreate() {
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    @Transactional
    public void save() {
        try {
            if (criterestructures.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            this.sommeCritere();

            criterestructures.forEach(cs -> {
                criterestructureFacadeLocal.edit(cs);
            });

            this.criterestructures.clear();
            RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private void sommeCritere() {
        this.pointMax = 0;
        this.poids = 0.0;
        this.pointObtenu = 0.0;
        if (criterestructures.isEmpty()) {
            return;
        }
        for (Criterestructure cs : criterestructures) {
            poids += cs.getPoids();
            pointMax += cs.getPointMax();
            pointObtenu += cs.getPoidsfinal();
        }
    }

}
