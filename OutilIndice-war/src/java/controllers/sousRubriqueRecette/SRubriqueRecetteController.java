package controllers.sousRubriqueRecette;

import entities.Rubriquerecette;
import entities.Sousrubriquerecette;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class SRubriqueRecetteController extends AbstractSRubriqueRecetteController implements Serializable {

    @PostConstruct
    private void init() {
        this.rubriquerecette = new Rubriquerecette();
        this.initSousrubriquerecette();
    }

    private void initSousrubriquerecette() {
        rubriquerecette = new Rubriquerecette();
        sousrubriquerecette = new Sousrubriquerecette();
        sousrubriquerecette.setIdrubriquerecette(new Rubriquerecette());
    }

    public void prepareCreate() {
        try {
            /*if (!Utilitaires.isAccess(2L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.mode = "Create";
            this.initSousrubriquerecette();
            RequestContext.getCurrentInstance().execute("PF('SRubriqueRecetteCreerDialog').show()");
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

            if (this.sousrubriquerecette == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            RequestContext.getCurrentInstance().execute("PF('SRubriqueRecetteCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void prepareEdit(Sousrubriquerecette sousrubriquerecette) {
        try {
            /*if (!Utilitaires.isAccess(3L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.sousrubriquerecette = sousrubriquerecette;

            if (this.sousrubriquerecette == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            RequestContext.getCurrentInstance().execute("PF('SRubriqueRecetteCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void create() {
        try {
            if (this.mode.equals("Create")) {

                sousrubriquerecette.setIdsousrubriquerecette(sousrubriquerecetteFacadeLocal.nextVal());
                sousrubriquerecetteFacadeLocal.create(sousrubriquerecette);

                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'rubriquedepense : " + this.rubriquedepense.getNom() + " " + this.rubriquedepense.getPrenom(), SessionMBean.getUserAccount());
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('SRubriqueRecetteCreerDialog').hide()");
                signalSuccess();
            } else if (sousrubriquerecette != null) {
                sousrubriquerecette.setIdrubriquerecette(rubriquerecetteFacadeLocal.find(sousrubriquerecette.getIdrubriquerecette().getIdrubriquerecette()));
                sousrubriquerecetteFacadeLocal.edit(this.sousrubriquerecette);
                modifier = detail = supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('SRubriqueRecetteCreerDialog').hide()");
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
            if (this.sousrubriquerecette != null) {
                if (!Utilitaires.isAccess(4L)) {
                    signalError("acces_refuse");
                    return;
                }

                this.sousrubriquerecetteFacadeLocal.remove(this.sousrubriquerecette);
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'rubriquedepense : " + this.rubriquedepense.getNom() + " " + this.rubriquedepense.getPrenom(), SessionMBean.getUserAccount());
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete(Sousrubriquerecette item) {
        try {

            this.sousrubriquerecette = item;

            /*if (!Utilitaires.isAccess(4L)) {
             signalError("acces_refuse");
             return;
             }*/
            this.sousrubriquerecetteFacadeLocal.remove(item);
            //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'rubriquedepense : " + this.rubriquedepense.getNom() + " " + this.rubriquedepense.getPrenom(), SessionMBean.getUserAccount());
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
