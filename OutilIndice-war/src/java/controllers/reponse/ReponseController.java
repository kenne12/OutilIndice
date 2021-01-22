/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reponse;

import controllers.util.JsfUtil;
import entities.Elementreponse;
import entities.Souscritere;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class ReponseController extends AbstractElementResponse implements Serializable {

    public ReponseController() {

    }

    public void prepareCreate() {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }

        souscritere = new Souscritere();
        souscritere.setIdsouscritere(-1);
        elementReponse = new Elementreponse();

        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('SousCritereCreateDialog').show()");
    }

    public void prepareEdit(Elementreponse er) {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        this.elementReponse = er;
        souscritere = er.getIdsouscritere();

        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('SousCritereCreateDialog').show()");
    }

    public void save() {
        try {
            if (souscritere.getIdsouscritere() == -1) {
                JsfUtil.addErrorMessage("Veuillez sélectionner sélectionner le sous-critères");
                return;
            }
            if ("Create".equals(mode)) {
                elementReponse.setIdelementreponse(elementReponseFacadeLocal.nextVal());
                elementReponse.setIdsouscritere(souscritere);
                elementReponseFacadeLocal.create(elementReponse);
                souscritere = new Souscritere();
                elementReponse = new Elementreponse();

                RequestContext.getCurrentInstance().execute("PF('SousCritereCreateDialog').hide()");
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (elementReponse != null) {
                    elementReponse.setIdsouscritere(souscritereFacadeLocal.find(souscritere.getIdsouscritere()));

                    elementReponseFacadeLocal.edit(elementReponse);
                    elementReponse = new Elementreponse();
                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                    RequestContext.getCurrentInstance().execute("PF('SousCritereCreateDialog').hide()");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne seletionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Elementreponse er) {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        try {
            elementReponseFacadeLocal.remove(er);
            elementReponse = new Elementreponse();
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
