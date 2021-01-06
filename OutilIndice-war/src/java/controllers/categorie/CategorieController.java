/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.categorie;

import controllers.util.JsfUtil;
import entities.Categorie;
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
public class CategorieController extends AbstractCategorie implements Serializable {
    
    public CategorieController() {
        
    }
    
    public void prepareCreate() {
        if (!Utilitaires.isAccess(1L)) {
            signalError("acces_refuse");
            return;
        }
        categorie = new Categorie();
        categorie.setPointmax(0);
        categorie.setIndice(0);
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('CategorieCreateDialog').show()");
    }
    
    public void prepareEdit(Categorie c) {
        if (!Utilitaires.isAccess(2L)) {
            signalError("acces_refuse");
            return;
        }
        
        this.categorie = c;
        if (categorie != null) {
            mode = "Edit";
            RequestContext.getCurrentInstance().execute("PF('CategorieCreateDialog').show()");
        }
    }
    
    public void save() {
        try {
            if ("Create".equals(mode)) {
                categorie.setIdcategorie(categorieFacadeLocal.nextVal());
                categorieFacadeLocal.create(categorie);
                categorie = new Categorie();
                detail = true;
                RequestContext.getCurrentInstance().execute("PF('CategorieCreateDialog').hide()");
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else if ("Edit".equals(mode)) {
                if (categorie != null) {
                    categorieFacadeLocal.edit(categorie);
                    categorie = new Categorie();
                    detail = true;
                    JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
                    RequestContext.getCurrentInstance().execute("PF('CategorieCreateDialog').hide()");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne seletionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
    public void delete(Categorie c) {
        try {
            if (!Utilitaires.isAccess(2L)) {
                signalError("acces_refuse");
                return;
            }
            if (c != null) {
                categorieFacadeLocal.remove(c);
                categorie = new Categorie();
                detail = true;
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
    public void activeButton() {
        detail = false;
    }
    
    public void deactiveButton() {
        detail = true;
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
