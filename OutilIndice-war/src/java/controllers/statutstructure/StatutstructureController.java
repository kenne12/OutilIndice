package controllers.statutstructure;

import entities.Statutstructure;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class StatutstructureController extends AbstractStatutstructureController implements Serializable {

    @PostConstruct
    private void init() {

        this.initTypeStructure();
    }

    private void initTypeStructure() {
        statutstructure = new Statutstructure();
        statutstructure.setEtat("Actif");
    }

    public void prepareCreate() {
        try {
            /*if (!Utilitaires.isAccess(2L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.mode = "Create";
            this.initTypeStructure();

            RequestContext.getCurrentInstance().execute("PF('StatutstructureCreerDialog').show()");
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

            if (this.statutstructure == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            RequestContext.getCurrentInstance().execute("PF('StatutstructureCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void prepareEdit(Statutstructure r) {
        try {
            /*if (!Utilitaires.isAccess(3L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.statutstructure = r;

            if (this.statutstructure == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            RequestContext.getCurrentInstance().execute("PF('StatutstructureCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void create() {
        try {
            if (this.mode.equals("Create")) {

                statutstructure.setIdstatutstructure(statutstructureFacadeLocal.nextVal());
                statutstructureFacadeLocal.create(statutstructure);

                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('StatutstructureCreerDialog').hide()");
                signalSuccess();
            } else if (this.statutstructure != null) {
                this.statutstructureFacadeLocal.edit(this.statutstructure);
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('StatutstructureCreerDialog').hide()");
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
            if (this.statutstructure != null) {
                if (!Utilitaires.isAccess(4L)) {
                    signalError("acces_refuse");
                    return;
                }

                this.statutstructureFacadeLocal.remove(this.statutstructure);
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete(Statutstructure item) {
        try {

            this.statutstructure = item;

            /*if (!Utilitaires.isAccess(4L)) {
             signalError("acces_refuse");
             return;
             }*/
            this.statutstructureFacadeLocal.remove(item);
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
