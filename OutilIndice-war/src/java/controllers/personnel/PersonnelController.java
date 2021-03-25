/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.personnel;

import controllers.util.JsfUtil;
import entities.Categorie;
import entities.Service;
import entities.Personnel;
import entities.Responsabilite;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;
import utils.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class PersonnelController extends AbstractPersonnel implements Serializable {

    public PersonnelController() {

    }

    @PostConstruct
    public void init() {
        this.initPersonnel();
        services = SessionMBean.getServices();
        categories = SessionMBean.getCategories();
        responsabilites = SessionMBean.getResponsabilites();
    }

    private void initPersonnel() {
        personnel = new Personnel();
        personnel.setIdcategorie(new Categorie(-1));
        personnel.setIdservice(new Service(-1l));
        personnel.setIdresponsabilite(new Responsabilite(-1));
        personnel.setEtat(true);
    }

    public void prepareCreate() {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        this.initPersonnel();
        String matricule = "P0000";
        Long id = personnelFacadeLocal.nextVal(SessionMBean.getStructure().getIdstructure());
        if (id < 10) {
            matricule += "00" + id;
        } else if (id < 100) {
            matricule += "0" + id;
        } else {
            matricule += "" + id;
        }
        personnel.setMatricule(matricule);
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('PersonnelCreateDialog').show()");
    }

    public void prepareEdit(Personnel p) {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        this.personnel = p;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('PersonnelCreateDialog').show()");
    }

    public void save() {
        try {
            if (personnel.getIdservice().getIdservice() == -1) {
                JsfUtil.addErrorMessage("Veuillez sélectionner sélectionner le service");
                return;
            }

            if (personnel.getIdcategorie().getIdcategorie() == -1) {
                JsfUtil.addErrorMessage("Veuillez sélectionner sélectionner la catégorie");
                return;
            }

            if (personnel.getIdresponsabilite().getIdresponsabilite() == -1) {
                JsfUtil.addErrorMessage("Veuillez sélectionner sélectionner la responsabilité");
                return;
            }

            if ("Create".equals(mode)) {
                personnel.setIdpersonnel(personnelFacadeLocal.nextVal());
                personnel.setDateembauche(new Date());
                personnel.setStructure(SessionMBean.getStructure());
                personnelFacadeLocal.create(personnel);
                this.initPersonnel();
                RequestContext.getCurrentInstance().execute("PF('PersonnelCreateDialog').hide()");
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (personnel != null) {
                    personnel.setIdcategorie(categorieFacadeLocal.find(personnel.getIdcategorie().getIdcategorie()));
                    personnel.setIdservice(serviceFacadeLocal.find(personnel.getIdservice().getIdservice()));
                    personnel.setIdresponsabilite(responsabiliteFacadeLocal.find(personnel.getIdresponsabilite().getIdresponsabilite()));
                    personnelFacadeLocal.edit(personnel);

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

    public void delete(Personnel p) {
        try {
            if (!Utilitaires.isAccess(2L)) {
                signalError("acces_refuse");
                return;
            }
            personnelFacadeLocal.remove(p);
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
