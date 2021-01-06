package controllers.sousRubriqueDepense;

import entities.Sousrubriquedepense;
import entities.Rubriquedepense;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class SRubriqueDepenseController extends AbstractSRubriqueDepenseController implements Serializable {
    
    @PostConstruct
    private void init() {
        this.rubriquedepense = new Rubriquedepense();
        this.initSousrubriquedepense();
    }
    
    private void initSousrubriquedepense() {
        rubriquedepense = new Rubriquedepense();
        sousrubriquedepense = new Sousrubriquedepense();
        sousrubriquedepense.setIdrubriquedepense(new Rubriquedepense());
        sousrubriquedepense.setSpecial(false);
    }
    
    public void prepareCreate() {
        try {
            /*if (!Utilitaires.isAccess(2L)) {
             signalError("acces_refuse");
             return;
             }*/
            
            this.mode = "Create";
            this.initSousrubriquedepense();
            RequestContext.getCurrentInstance().execute("PF('SRubriqueDepenseCreerDialog').show()");
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
            
            if (this.rubriquedepense == null) {
                signalError("not_row_selected");
                return;
            }
            
            this.mode = "Edit";
            
            RequestContext.getCurrentInstance().execute("PF('SRubriqueDepenseCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }
    
    public void prepareEdit(Sousrubriquedepense sousrubriquedepense) {
        try {
            /*if (!Utilitaires.isAccess(3L)) {
             signalError("acces_refuse");
             return;
             }*/
            
            this.sousrubriquedepense = sousrubriquedepense;
            
            if (this.rubriquedepense == null) {
                signalError("not_row_selected");
                return;
            }
            
            this.mode = "Edit";
            
            RequestContext.getCurrentInstance().execute("PF('SRubriqueDepenseCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }
    
    public void create() {
        try {
            if (this.mode.equals("Create")) {
                
                sousrubriquedepense.setIdsousrubriquedepense(sousrubriquedepenseFacadeLocal.nextVal());
                sousrubriquedepenseFacadeLocal.create(sousrubriquedepense);

                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'rubriquedepense : " + this.rubriquedepense.getNom() + " " + this.rubriquedepense.getPrenom(), SessionMBean.getUserAccount());
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('SRubriqueDepenseCreerDialog').hide()");
                signalSuccess();
            } else if (sousrubriquedepense != null) {
                sousrubriquedepense.setIdrubriquedepense(rubriquedepenseFacadeLocal.find(sousrubriquedepense.getIdrubriquedepense().getIdrubriquedepense()));
                sousrubriquedepenseFacadeLocal.edit(this.sousrubriquedepense);
                modifier = detail = supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('SRubriqueDepenseCreerDialog').hide()");
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
            if (this.sousrubriquedepense != null) {
                if (!Utilitaires.isAccess(4L)) {
                    signalError("acces_refuse");
                    return;
                }
                
                this.sousrubriquedepenseFacadeLocal.remove(this.sousrubriquedepense);
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'rubriquedepense : " + this.rubriquedepense.getNom() + " " + this.rubriquedepense.getPrenom(), SessionMBean.getUserAccount());
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }
    
    public void delete(Sousrubriquedepense item) {
        try {
            
            this.sousrubriquedepense = item;

            /*if (!Utilitaires.isAccess(4L)) {
             signalError("acces_refuse");
             return;
             }*/
            this.sousrubriquedepenseFacadeLocal.remove(item);
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
