/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.sous_critere_qualite;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.Elementreponse;
import entities.Rubriquesc;
import entities.Souscritere;
import java.util.List;
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
public class SousCritereQualiteCtrl extends AbstractSousCritereQualiteCtrl {

    /**
     * Creates a new instance of SousCritereQualiteCtrl
     */
    public SousCritereQualiteCtrl() {
    }

    public void prepareCreate() {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        souscritere = new Souscritere();
        souscritere.setPointmax(0);
        souscritere.setDetail("-");
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('SousCritereQualiteCreateDialog').show()");
    }

    public void prepareEdit(Souscritere sc) {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        this.souscritere = sc;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('SousCritereQualiteCreateDialog').show()");
    }

    public void save() {
        try {
            if ("Create".equals(mode)) {
                souscritere.setIdsouscritere(souscritereFacadeLocal.nextVal());
                souscritere.setIdcritere(new Critere(5));
                souscritere.setIdrubriquesc(new Rubriquesc(5));
                souscritereFacadeLocal.create(souscritere);
                souscritere = new Souscritere();
                RequestContext.getCurrentInstance().execute("PF('SousCritereQualiteCreateDialog').hide()");
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (souscritere != null) {
                    souscritereFacadeLocal.edit(souscritere);
                    souscritere = new Souscritere();
                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                    RequestContext.getCurrentInstance().execute("PF('SousCritereQualiteCreateDialog').hide()");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne seletionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Souscritere sc) {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        try {
            List<Elementreponse> listEr = elementReponseFacadeLocal.findByIdSousCritere(sc.getIdsouscritere());
            if (!listEr.isEmpty()) {
                JsfUtil.addSuccessMessage("Ce sous - critère comporte plusieurs éléments de reponse");
                return;
            }

            souscritereFacadeLocal.remove(sc);
            souscritere = new Souscritere();
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
