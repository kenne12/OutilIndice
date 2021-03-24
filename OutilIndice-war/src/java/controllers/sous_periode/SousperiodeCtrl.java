/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.sous_periode;

import entities.Sousperiode;
import entities.TypeSousPeriode;
import java.io.Serializable;
import javax.annotation.PostConstruct;
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
public class SousperiodeCtrl extends AbstractSousperiodeCtrl implements Serializable {

    @PostConstruct
    private void init() {
        this.initPeriode();
    }

    private void initPeriode() {
        sousperiode = new Sousperiode();
        sousperiode.setTypeSousPeriode(new TypeSousPeriode(0));
    }

    public void prepareCreate() {
        try {
            this.mode = "Create";
            this.initPeriode();
            RequestContext.getCurrentInstance().execute("PF('SousPeriodeCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void prepareEdit(Sousperiode s) {
        try {
            if (!Utilitaires.isAccess(3L)) {
                signalError("acces_refuse");
                return;
            }

            this.sousperiode = s;
            this.mode = "Edit";
            RequestContext.getCurrentInstance().execute("PF('SousPeriodeCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void save() {
        try {

            if (sousperiode.getTypeSousPeriode().getIdTypeSousperiode() == null) {
                signalError("veuillez_selectionner_le_type");
                return;
            }

            if (this.mode.equals("Create")) {
                sousperiode.setIdsousperiode(sousperiodeFacadeLocal.nextVal());
                sousperiodeFacadeLocal.create(sousperiode);

                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('SousPeriodeCreerDialog').hide()");
                signalSuccess();
            } else if (this.sousperiode != null) {
                this.sousperiode.setTypeSousPeriode(typeSousPeriodeFacadeLocal.find(sousperiode.getTypeSousPeriode().getIdTypeSousperiode()));
                this.sousperiodeFacadeLocal.edit(this.sousperiode);
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('SousPeriodeCreerDialog').hide()");
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete(Sousperiode item) {
        try {
            this.sousperiodeFacadeLocal.remove(item);
            //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
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
