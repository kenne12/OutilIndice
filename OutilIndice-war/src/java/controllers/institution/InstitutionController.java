package controllers.institution;

import entities.Institution;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class InstitutionController extends AbstractInstitutionController implements Serializable {

    @PostConstruct
    private void init() {
        this.institution = new Institution();

    }

    public void prepareCreate() {
        try {
            /*if (!Utilitaires.isAccess(2L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.mode = "Create";

            institution = new Institution();
            this.institution.setChapitre("-");
            this.institution.setChoixstrategique("-");
            this.institution.setSigle("-");
            this.institution.setEtat("Actif");
            

            //this.institution.setTemplate((String) this.templates.get(1));
            RequestContext.getCurrentInstance().execute("PF('InstitutionCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void prepareEdit() {
        try {
            if (!Utilitaires.isAccess(3L)) {
                signalError("acces_refuse");
                return;
            }

            if (this.institution == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            RequestContext.getCurrentInstance().execute("PF('InstitutionCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void prepareEdit(Institution institution) {
        try {
            /*if (!Utilitaires.isAccess(3L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.institution = institution;

            if (this.institution == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            RequestContext.getCurrentInstance().execute("PF('InstitutionCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void create() {
        try {
            if (this.mode.equals("Create")) {

                this.institution.setIdinstitution(this.institutionFacadeLocal.nextVal());

                this.institution.setEtat("Actif");
                this.institutionFacadeLocal.create(this.institution);

                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'institution : " + this.institution.getNom() + " " + this.institution.getPrenom(), SessionMBean.getUserAccount());
                this.institution = new Institution();

                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('InstitutionCreerDialog').hide()");
                signalSuccess();
            } else if (this.institution != null) {

                this.institutionFacadeLocal.edit(this.institution);

                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('InstitutionCreerDialog').hide()");
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
            if (this.institution != null) {
                if (!Utilitaires.isAccess(4L)) {
                    signalError("acces_refuse");
                    return;
                }

                this.institutionFacadeLocal.remove(this.institution);
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'institution : " + this.institution.getNom() + " " + this.institution.getPrenom(), SessionMBean.getUserAccount());
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete(Institution item) {
        try {

            institution = item;

            /*if (!Utilitaires.isAccess(4L)) {
             signalError("acces_refuse");
             return;
             }*/
            this.institutionFacadeLocal.remove(this.institution);
            //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'institution : " + this.institution.getNom() + " " + this.institution.getPrenom(), SessionMBean.getUserAccount());
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
