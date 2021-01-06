package controllers.utilisateur;

import entities.Personnel;
import entities.Structure;
import entities.Utilisateur;
import entities.Utilisateurstructure;
import entities.UtilisateurstructurePK;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.ShaHash;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class UtilisateurController extends AbstractUtilisateurController implements Serializable {

    @PostConstruct
    private void init() {
        this.utilisateur = new Utilisateur();
        this.templates.clear();
    }

    public void prepareCreate() {
        try {
            /*if (!Utilitaires.isAccess(2L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.mode = "Create";

            this.utilisateur = new Utilisateur();
            this.utilisateur.setActif(true);
            this.utilisateur.setPhoto("user_avatar.png");

            //this.utilisateur.setTemplate((String) this.templates.get(1));
            this.utilisateur.setTheme("hot-sneaks");
            this.personnel = new Personnel();

            this.structures = this.structureFacadeLocal.findAll();
            this.selectedStructures.clear();
            RequestContext.getCurrentInstance().execute("PF('UtilisateurCreerDialog').show()");
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

            if (this.utilisateur == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            List<Utilisateurstructure> listUm = this.utilisateurstructureFacadeLocal.findByIdutilisateur(this.utilisateur.getIdutilisateur());
            if (!listUm.isEmpty()) {
                for (Utilisateurstructure um : listUm) {
                    this.selectedStructures.add(um.getStructure());
                }
            }

            this.structures = this.structureFacadeLocal.findAll();

            RequestContext.getCurrentInstance().execute("PF('UtilisateurCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void prepareEdit(Utilisateur user) {
        try {
            /*if (!Utilitaires.isAccess(3L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.utilisateur = user;

            if (this.utilisateur == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";
            selectedStructures.clear();

            List<Utilisateurstructure> listUs = this.utilisateurstructureFacadeLocal.findByIdutilisateur(this.utilisateur.getIdutilisateur());
            if (!listUs.isEmpty()) {
                for (Utilisateurstructure um : listUs) {
                    this.selectedStructures.add(um.getStructure());
                }
            }

            this.structures = this.structureFacadeLocal.findAll();

            RequestContext.getCurrentInstance().execute("PF('UtilisateurCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void updateMagasin() {
        try {
            if (this.personnel != null) {
                //this.structures.remove(this.personnel.getIdmagasin());
                List localList = this.structures;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (this.mode.equals("Create")) {

                if (!repeatPassword.equals(utilisateur.getPassword())) {
                    signalError("mot_de_passe_non_identiques");
                    return;
                }
                this.utilisateur.setIdutilisateur(this.utilisateurFacadeLocal.nextVal());
                this.utilisateur.setPassword(new ShaHash().hash(this.utilisateur.getPassword()));
                this.utilisateur.setActif(true);
                this.utilisateurFacadeLocal.create(this.utilisateur);

                for (Structure s : this.selectedStructures) {
                    UtilisateurstructurePK usPK = new UtilisateurstructurePK(utilisateur.getIdutilisateur(), s.getIdstructure());
                    Utilisateurstructure us = new Utilisateurstructure();
                    us.setUtilisateurstructurePK(usPK);
                    us.setStructure(s);
                    us.setUtilisateur(this.utilisateur);
                    this.utilisateurstructureFacadeLocal.create(us);
                }

                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'utilisateur : " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());
                this.utilisateur = new Utilisateur();

                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('UtilisateurCreerDialog').hide()");
                signalSuccess();
            } else if (this.utilisateur != null) {

                this.utilisateurFacadeLocal.edit(this.utilisateur);
                for (Structure s : this.selectedStructures) {
                    Utilisateurstructure us = this.utilisateurstructureFacadeLocal.findByIdutilisateurIdstructure(this.utilisateur.getIdutilisateur(), s.getIdstructure());
                    if (us == null) {
                        us = new Utilisateurstructure();
                        UtilisateurstructurePK usPK = new UtilisateurstructurePK(utilisateur.getIdutilisateur(), s.getIdstructure());
                        us.setUtilisateur(this.utilisateur);
                        us.setStructure(s);
                        us.setUtilisateurstructurePK(usPK);
                        this.utilisateurstructureFacadeLocal.create(us);
                    }
                }
                this.modifier = this.detail = this.supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('UtilisateurCreerDialog').hide()");
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void reinitialiseAccount(Utilisateur utilisateur) {
        try {
            if (!Utilitaires.isAccess(47L)) {
                signalError("acces_refuse");
                return;
            }
            utilisateur.setPassword(new ShaHash().hash("123456"));
            this.utilisateurFacadeLocal.edit(utilisateur);
            //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Réinitilisation du compte utilisateur de -> " + utilisateur.getNom() + " " + utilisateur.getPrenom(), SessionMBean.getUserAccount());
            signalSuccess();
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete() {
        try {
            if (this.utilisateur != null) {
                if (!Utilitaires.isAccess(4L)) {
                    signalError("acces_refuse");
                    return;
                }

                mouchardFacadeLocal.deleteByIdutilisateur(utilisateur.getIdutilisateur());
                utilisateurstructureFacadeLocal.deleteByIdutilisateur(utilisateur.getIdutilisateur());
                privilegeFacadeLocal.deleteByIdUtilisateur(utilisateur.getIdutilisateur());
                this.utilisateurFacadeLocal.remove(this.utilisateur);
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'utilisateur : " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete(Utilisateur item) {
        try {

            utilisateur = item;

            /*if (!Utilitaires.isAccess(4L)) {
             signalError("acces_refuse");
             return;
             }*/
            mouchardFacadeLocal.deleteByIdutilisateur(utilisateur.getIdutilisateur());
            utilisateurstructureFacadeLocal.deleteByIdutilisateur(utilisateur.getIdutilisateur());
            privilegeFacadeLocal.deleteByIdUtilisateur(utilisateur.getIdutilisateur());
            this.utilisateurFacadeLocal.remove(this.utilisateur);
            //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'utilisateur : " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());
            signalSuccess();
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void changeStatus(Utilisateur utilisateur, String mode) {
        try {
            /*if (!Utilitaires.isAccess(6L)) {
             signalError("acces_refuse");
             return;
             }*/
            if (mode.equals("activer")) {

                utilisateur.setActif(true);
                this.utilisateurFacadeLocal.edit(utilisateur);
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Activation du compte de l'utilisateur : " + utilisateur.getNom() + " " + utilisateur.getPrenom(), SessionMBean.getUserAccount());
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                utilisateur.setActif(false);
                this.utilisateurFacadeLocal.edit(utilisateur);
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Désativation du compte de l'utilisateur : " + utilisateur.getNom() + " " + utilisateur.getPrenom(), SessionMBean.getUserAccount());
                JsfUtil.addSuccessMessage("Operation réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
