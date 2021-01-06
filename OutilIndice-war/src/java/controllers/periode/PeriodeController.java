package controllers.periode;

import entities.Periode;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class PeriodeController extends AbstractPeriodeController implements Serializable {
    
    @PostConstruct
    private void init() {
        this.initPeriode();
    }
    
    private void initPeriode() {
        periode = new Periode();
        periode.setIdperiode(null);
    }
    
    public void prepareCreate() {
        try {
            /*if (!Utilitaires.isAccess(2L)) {
             signalError("acces_refuse");
             return;
             }*/
            
            this.mode = "Create";
            this.initPeriode();
            
            RequestContext.getCurrentInstance().execute("PF('PeriodeCreerDialog').show()");
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
            
            if (this.periode == null) {
                signalError("not_row_selected");
                return;
            }
            
            this.mode = "Edit";
            
            RequestContext.getCurrentInstance().execute("PF('PeriodeCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }
    
    public void prepareEdit(Periode r) {
        try {
            /*if (!Utilitaires.isAccess(3L)) {
             signalError("acces_refuse");
             return;
             }*/
            
            this.periode = r;
            
            if (this.periode == null) {
                signalError("not_row_selected");
                return;
            }
            
            this.mode = "Edit";
            
            RequestContext.getCurrentInstance().execute("PF('PeriodeCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }
    
    public void create() {
        try {
            if (this.mode.equals("Create")) {
                
                periode.setIdperiode(periodeFacadeLocal.nextVal());
                periodeFacadeLocal.create(periode);

                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('PeriodeCreerDialog').hide()");
                signalSuccess();
            } else if (this.periode != null) {
                this.periodeFacadeLocal.edit(this.periode);
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('PeriodeCreerDialog').hide()");
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
            if (this.periode != null) {
                if (!Utilitaires.isAccess(4L)) {
                    signalError("acces_refuse");
                    return;
                }
                
                this.periodeFacadeLocal.remove(this.periode);
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }
    
    public void delete(Periode item) {
        try {
            
            this.periode = item;

            /*if (!Utilitaires.isAccess(4L)) {
             signalError("acces_refuse");
             return;
             }*/
            this.periodeFacadeLocal.remove(item);
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
