package controllers.privilege;

import entities.Menu;
import entities.Privilege;
import entities.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class PrivilegeController extends AbstractPrivilegeController implements Serializable {

    @PostConstruct
    private void initAcces() {
        this.utilisateur = new Utilisateur();
    }

    public void prepareCreate() {
        try {
            /* if (!Utilitaires.isAccess(5L)) {
             JsfUtil.addErrorMessage("Vous n'avez pas le droit d'attribuer les privilèges aux utilisateurs");
             return;
             }*/

            this.dualMenu.getSource().clear();
            this.dualMenu.getTarget().clear();
            this.utilisateur = new Utilisateur();
            RequestContext.getCurrentInstance().execute("PF('AccesCreerDialog').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewAccess(Utilisateur utilisateur) {
        try {
            this.utilisateur = utilisateur;
            this.privileges = this.privilegeFacadeLocal.findByUser(utilisateur.getIdutilisateur());
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Echec");
        }
    }

    public void handleUserChange() {
        this.dualMenu.getSource().clear();
        this.dualMenu.getTarget().clear();
        try {
            if (this.utilisateur.getIdutilisateur() != null) {
                this.utilisateur = this.utilisateurFacadeLocal.find(this.utilisateur.getIdutilisateur());

                List<Privilege> privilegeTemp = this.privilegeFacadeLocal.findByUser(this.utilisateur.getIdutilisateur());
                if (privilegeTemp.isEmpty()) {
                    this.dualMenu.setSource(this.menuFacadeLocal.findAll());
                } else {
                    List menusTarget = new ArrayList();

                    for (Privilege p : privilegeTemp) {
                        menusTarget.add(p.getIdmenu());
                    }

                    this.dualMenu.getTarget().addAll(menusTarget);
                    this.dualMenu.getSource().addAll(this.menuFacadeLocal.findAll());
                    this.dualMenu.getSource().removeAll(menusTarget);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (this.utilisateur.getIdutilisateur() == null) {
                dualMenu.getSource().clear();
                dualMenu.getTarget().clear();
                return;
            }
            this.utilisateur = this.utilisateurFacadeLocal.find(this.utilisateur.getIdutilisateur());

            for (Menu m : dualMenu.getSource()) {
                Privilege p = this.privilegeFacadeLocal.findByUser(this.utilisateur.getIdutilisateur().intValue(), m.getIdmenu().intValue());
                if (p != null) {
                    this.privilegeFacadeLocal.remove(p);
                    Utilitaires.saveOperation(this.mouchardFacadeLocal, "RETRAIT DU PRIVILEGE -> " + m.getNom() + " à l'utilisateur -> " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());
                }
            }

            Boolean flag = false;
            for (Menu m : this.dualMenu.getTarget()) {
                if (!flag) {
                    if (m.getIdmenu() == 1) {
                        flag = true;
                        Privilege p = this.privilegeFacadeLocal.findByUser(this.utilisateur.getIdutilisateur().intValue(), 1);
                        if (p == null) {
                            p = new Privilege();
                            p.setIdprivilege(this.privilegeFacadeLocal.nextVal());
                            p.setIdmenu(m);
                            p.setIdutilisateur(this.utilisateur);
                            this.privilegeFacadeLocal.create(p);
                            Utilitaires.saveOperation(this.mouchardFacadeLocal, "ATTRIBUTION DU PRIVILEGE : SUPER ADMINISTRATEUR à l'utilisateur -> " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());
                            break;
                        }
                        JsfUtil.addSuccessMessage("Vous disposez dejà du privilège SUPER ADMINISTRATEUR");
                        break;
                    }

                    Privilege p = this.privilegeFacadeLocal.findByUser(this.utilisateur.getIdutilisateur().intValue(), m.getIdmenu().intValue());
                    if (p == null) {
                        p = new Privilege();
                        p.setIdprivilege(this.privilegeFacadeLocal.nextVal());
                        p.setIdmenu(m);
                        p.setIdutilisateur(this.utilisateur);
                        this.privilegeFacadeLocal.create(p);
                        Utilitaires.saveOperation(this.mouchardFacadeLocal, "ATTRIBUTION DU PRIVILEGE -> " + m.getNom() + " à l'utilisateur -> " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());
                    }
                }
            }
            RequestContext.getCurrentInstance().execute("PF('AccesCreerDialog').hide()");
            signalSuccess();

        } catch (Exception e) {
            e.printStackTrace();
            signalError("erreur_execution");
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
