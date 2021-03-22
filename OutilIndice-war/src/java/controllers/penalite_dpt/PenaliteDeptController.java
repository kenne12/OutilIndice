/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.penalite_dpt;

import controllers.util.JsfUtil;
import entities.Penalite;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

/**
 *
 * @author USER
 */
@ManagedBean
@SessionScoped
public class PenaliteDeptController extends AbstractPenaliteDeptController implements Serializable {

    public PenaliteDeptController() {

    }

    public void load() {
        penalites = penaliteFacadeLocal.findAllService();
    }

    public void prepareCreate() {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        mode = "Create";
        penalite = new Penalite();
        penalite.setDetail("-");
        penalite.setService(true);
        penalite.setPersonnel(false);
        penalite.setCode("00" + penaliteFacadeLocal.nextValService("service"));

        RequestContext.getCurrentInstance().execute("PF('PenaliteCreateDialog').show()");
    }

    public void prepareEdit(Penalite c) {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        this.penalite = c;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('PenaliteCreateDialog').show()");
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                penalite.setIdpenalite(penaliteFacadeLocal.nextVal());
                penaliteFacadeLocal.create(penalite);
                this.load();
                penalite = new Penalite();
                detail = true;
                RequestContext.getCurrentInstance().execute("PF('PenaliteCreateDialog').hide()");
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (penalite != null) {
                    penaliteFacadeLocal.edit(penalite);
                    this.load();
                    penalite = new Penalite();
                    detail = true;
                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                    RequestContext.getCurrentInstance().execute("PF('PenaliteCreateDialog').hide()");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne seletionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Penalite i) {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        try {
            penaliteFacadeLocal.remove(i);
            this.load();
            penalite = new Penalite();
            detail = true;
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void signalError(String chaine) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("information", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(chaine));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void signalSuccess() {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void signalException(Exception e) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.catchException(e, this.routine.localizeMessage("erreur_execution"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}
