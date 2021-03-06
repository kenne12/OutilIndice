package controllers.rubrique_recette;

import entities.Rubriquerecette;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class RubriqueRecetteController extends AbstractRubriqueRecetteController implements Serializable {

    @PostConstruct
    private void init() {

        this.initService();
    }

    private void initService() {
        rubriquerecette = new Rubriquerecette();
    }

    public void prepareCreate() {
        try {
            /*if (!Utilitaires.isAccess(2L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.mode = "Create";

            this.initService();

            RequestContext.getCurrentInstance().execute("PF('RubriqueRecetteCreerDialog').show()");
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

            if (this.rubriquerecette == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            RequestContext.getCurrentInstance().execute("PF('RubriqueRecetteCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void prepareEdit(Rubriquerecette r) {
        try {
            /*if (!Utilitaires.isAccess(3L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.rubriquerecette = r;

            if (this.rubriquerecette == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            RequestContext.getCurrentInstance().execute("PF('RubriqueRecetteCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void create() {
        try {
            if (this.mode.equals("Create")) {

                rubriquerecette.setIdrubriquerecette(rubriquerecetteFacadeLocal.nextVal());
                rubriquerecetteFacadeLocal.create(rubriquerecette);

                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('RubriqueRecetteCreerDialog').hide()");
                signalSuccess();
            } else if (this.rubriquerecette != null) {
                this.rubriquerecetteFacadeLocal.edit(this.rubriquerecette);
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('RubriqueRecetteCreerDialog').hide()");
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
            if (this.rubriquerecette != null) {
                if (!Utilitaires.isAccess(4L)) {
                    signalError("acces_refuse");
                    return;
                }

                this.rubriquerecetteFacadeLocal.remove(this.rubriquerecette);
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete(Rubriquerecette item) {
        try {

            this.rubriquerecette = item;

            /*if (!Utilitaires.isAccess(4L)) {
             signalError("acces_refuse");
             return;
             }*/
            this.rubriquerecetteFacadeLocal.remove(item);
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
