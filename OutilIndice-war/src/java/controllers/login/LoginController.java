/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.login;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.Criterestructure;
import utils.SessionMBean;
import entities.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sessions.UtilisateurFacadeLocal;
import utils.ShaHash;
import entities.Menu;
import entities.Periode;
import entities.Privilege;
import entities.Structure;
import entities.Utilisateurstructure;
import org.primefaces.context.RequestContext;
import sessions.CriterestructureFacadeLocal;
import sessions.MenuFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.UtilisateurstructureFacadeLocal;
import utils.Routine;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;

    @EJB
    private MenuFacadeLocal menuBFacadeLocal;

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    private Utilisateur utilisateur = new Utilisateur();

    @EJB
    private PrivilegeFacadeLocal privilegeFacadeLocal;

    @EJB
    private StructureFacadeLocal structureFacadeLocal;
    private Structure structure = new Structure();
    private List<Structure> structures = new ArrayList<>();

    @EJB
    UtilisateurstructureFacadeLocal utilisateurstructureFacadeLocal;

    private String confirmPassword = "";

    private String filename = "logo.jpeg";
    private String filenameInstitution = "logo1.png";

    private boolean showSessionPanel = true;
    private Routine routine = new Routine();

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    @EJB
    private PeriodeFacadeLocal periodeFacadeLocal;
    private Periode periode = new Periode();
    private List<Periode> periodes = new ArrayList<>();

    //@EJB
    //private ParametrageFacadeLocal parametrageFacadeLocal;
    private String language = "fr";

    public LoginController() {

    }

    public void login() {
        try {

            Utilisateur usr = utilisateurFacade.login(utilisateur.getLogin(), new ShaHash().hash(utilisateur.getPassword()));

            if (usr != null) {

                utilisateur = usr;
                HttpSession session = SessionMBean.getSession();

                session.setAttribute("user", usr);

                periodes = periodeFacadeLocal.findByEtat(true);
                if (!periodes.isEmpty()) {
                    periode = periodes.get(0);
                }

                List<Utilisateurstructure> us = utilisateurstructureFacadeLocal.findByIdutilisateur(utilisateur.getIdutilisateur());
                if (!us.isEmpty()) {
                    structures.clear();
                    structure = us.get(0).getStructure();
                    for (Utilisateurstructure obj : us) {
                        structures.add(obj.getStructure());
                    }
                }

                List<Menu> menu_mapped = menuBFacadeLocal.findAll();
                List<String> menu_all_mapped = new ArrayList<>();

                for (Menu m : menu_mapped) {
                    String[] menus = m.getRessource().split(";");
                    for (String temp : menus) {
                        if (!menu_all_mapped.contains(temp)) {
                            menu_all_mapped.add(temp);
                        }
                    }
                }

                List<Privilege> privilegesTemp = privilegeFacadeLocal.findByUser(utilisateur.getIdutilisateur());
                List<Long> accesses = new ArrayList<>();
                List<String> access = new ArrayList<>();

                for (Privilege p : privilegesTemp) {
                    accesses.add(p.getIdmenu().getIdmenu().longValue());
                    String[] menus = p.getIdmenu().getRessource().split(";");
                    for (String temp : menus) {
                        if (!access.contains(temp)) {
                            access.add(temp);
                        }
                    }
                }

                session.setAttribute("user_access_id", accesses);
                session.setAttribute("user_all_menu", access);
                session.setAttribute("system_all_menu", menu_all_mapped);

                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/index.html");
            } else {
                System.err.println("echec d'authentification");
                utilisateur = new Utilisateur();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login et mot de passe incorrets", "Please enter correct username and Password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            JsfUtil.addErrorMessage("Echec de l'opération");
            utilisateur = new Utilisateur();
        }
    }

    public void initSession() {
        try {
            HttpSession session = SessionMBean.getSession();
            try {
                //filename = institution.getPhoto();
                //filenameInstitution = institution.getPhotoInstitutionMere();
                structure = structureFacadeLocal.find(structure.getIdstructure());
                session.setAttribute("structure", structure);

                periode = periodeFacadeLocal.find(periode.getIdperiode());
                session.setAttribute("periode", periode);

                List<Criterestructure> list = criterestructureFacadeLocal.findByIdStructure(structure.getIdstructure());
                session.setAttribute("criterestructures", list);
                List<Critere> criteres = new ArrayList<>();
                if (!list.isEmpty()) {
                    for (Criterestructure c : list) {
                        criteres.add(c.getCritere());
                    }
                }
                session.setAttribute("criteres", criteres);
            } catch (Exception e) {
            }
            showSessionPanel = false;
        } catch (Exception e) {
            e.printStackTrace();
            showSessionPanel = true;
        }
    }

    public void updateCompte() {
        if ((!this.utilisateur.getPassword().equals("")) || (!this.utilisateur.getPassword().equals(null))) {
            if (utilisateur.getPassword().equals(this.confirmPassword)) {
                utilisateur.setPassword(new ShaHash().hash(this.confirmPassword));
                utilisateurFacade.edit(utilisateur);
                confirmPassword = "";
                RequestContext.getCurrentInstance().execute("PF('Modifier_compteDialog').hide()");
                this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
                RequestContext.getCurrentInstance().execute("PF('NotifyDialog').show()");
            } else {
                utilisateur = utilisateurFacade.find(this.utilisateur.getIdutilisateur());
                confirmPassword = "";
                routine.feedBack("avertissement", "/resources/tool_images/error.png", routine.localizeMessage("mot_de_passe_non_identic"));
                RequestContext.getCurrentInstance().execute("PF('NotifyDialog').show()");
            }
        } else {
            utilisateur = utilisateurFacade.find(utilisateur.getIdutilisateur());
            confirmPassword = "";
            this.routine.feedBack("avertissement", "/resources/tool_images/error.png", this.routine.localizeMessage("saisir_mot_de_passe"));
            RequestContext.getCurrentInstance().execute("PF('NotifyDialog').show()");
        }
    }

    public void logout() {
        HttpSession session = SessionMBean.getSession();
        Utilisateur usr = SessionMBean.getUserAccount();
        session.invalidate();
        String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void watcher() {
        try {
            if (SessionMBean.getUserAccount() == null) {
                String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String switchFr() {
        language = "fr";
        return null;
    }

    public String switchEn() {
        language = "en";
        return null;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isShowSessionPanel() {
        return showSessionPanel;
    }

    public void setShowSessionPanel(boolean showSessionPanel) {
        this.showSessionPanel = showSessionPanel;
    }

    public String getFilename() {
        return filename;
    }

    public String getFilenameInstitution() {
        return filenameInstitution;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Routine getRoutine() {
        return routine;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public List<Periode> getPeriodes() {
        return periodes;
    }

    public void setPeriodes(List<Periode> periodes) {
        this.periodes = periodes;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }

}
