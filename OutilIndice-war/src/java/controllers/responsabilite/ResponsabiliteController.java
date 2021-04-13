/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.responsabilite;

import entities.Responsabilite;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class ResponsabiliteController extends AbstractResponsabiliteController implements Serializable {

    /**
     * Creates a new instance of ResponsabiliteController
     */
    public ResponsabiliteController() {
    }

    public void prepareCreate() {
        try {
            this.mode = "Create";
            responsabilite = new Responsabilite();
            RequestContext.getCurrentInstance().execute("PF('ResponsabiliteCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void prepareEdit(Responsabilite r) {
        try {
            this.responsabilite = r;
            this.mode = "Edit";
            RequestContext.getCurrentInstance().execute("PF('ResponsabiliteCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void create() {
        try {
            if (this.mode.equals("Create")) {
                responsabilite.setIdresponsabilite(responsabiliteFacadeLocal.nextVal());
                responsabiliteFacadeLocal.create(responsabilite);
                RequestContext.getCurrentInstance().execute("PF('ResponsabiliteCreerDialog').hide()");
                signalSuccess();
            } else if (this.responsabilite != null) {
                this.responsabiliteFacadeLocal.edit(this.responsabilite);
                RequestContext.getCurrentInstance().execute("PF('ResponsabiliteCreerDialog').hide()");
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete() {
        try {
            if (this.responsabilite != null) {
                if (!Utilitaires.isAccess(4L)) {
                    signalError("acces_refuse");
                    return;
                }

                this.responsabiliteFacadeLocal.remove(this.responsabilite);              
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete(Responsabilite item) {
        try {
            this.responsabilite = item;
            this.responsabiliteFacadeLocal.remove(item);            
            signalSuccess();
        } catch (Exception e) {
            signalException(e);
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
