/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.login;

import controllers.util.JsfUtil;
import entities.Categorie;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utils.ShaHash;
import entities.Menu;
import entities.Periode;
import entities.Privilege;
import entities.Responsabilite;
import entities.Service;
import entities.Structure;
import entities.TypeSousPeriode;
import entities.TypestructureCategorie;
import entities.TypestructureResponsabilite;
import entities.TypestructureService;
import entities.TypestructureTypeSousperiode;
import entities.Utilisateurstructure;
import org.primefaces.context.RequestContext;
import utils.Routine;

@ManagedBean
@SessionScoped
public class LoginController extends AbstractLoginController implements Serializable {

    public LoginController() {

    }

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

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
                utilisateur = new Utilisateur();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login et mot de passe incorrets", "Please enter correct username and Password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Echec de l'opération");
            utilisateur = new Utilisateur();
        }
    }

    public void initSession() {
        HttpSession session = SessionMBean.getSession();
        try {
            structure = structureFacadeLocal.find(structure.getIdstructure());
            session.setAttribute("structure", structure);

            periode = periodeFacadeLocal.find(periode.getIdperiode());
            session.setAttribute("periode", periode);

            List<Criterestructure> list = criterestructureFacadeLocal.findByIdStructure(structure.getIdstructure());
            session.setAttribute("criterestructures", list);
            List<Critere> criteres = new ArrayList<>();
            if (!list.isEmpty()) {
                list.forEach(c -> {
                    criteres.add(c.getCritere());
                });
            }
            session.setAttribute("criteres", criteres);

            List<TypestructureCategorie> typestructureCategories = typestructureCategorieFacadeLocal.findByIdTypestructure(structure.getIdtypestructure().getIdtypestructure());
            List<Categorie> categories = new ArrayList<>();
            if (!typestructureCategories.isEmpty()) {
                typestructureCategories.forEach(tsc -> {
                    categories.add(tsc.getCategorie());
                });
            }
            session.setAttribute("categories", categories);
            session.setAttribute("ts_categories", typestructureCategories);

            List<TypestructureService> typestructureServices = typestructureServiceFacadeLocal.findByIdTypestructure(structure.getIdtypestructure().getIdtypestructure());
            List<Service> services = new ArrayList<>();

            typestructureServices.forEach(tss -> {
                services.add(tss.getService());
            });

            session.setAttribute("services", services);
            session.setAttribute("ts_services", typestructureServices);

            List<TypestructureTypeSousperiode> typestructureTypeSousperiodes = typestructureTypeSousperiodeFacadeLocal.findByIdTypestructure(structure.getIdtypestructure().getIdtypestructure());
            List<TypeSousPeriode> typeSousPeriodes = new ArrayList<>();
            typestructureTypeSousperiodes.forEach(tsp -> {
                typeSousPeriodes.add(tsp.getTypeSousPeriode());
            });

            session.setAttribute("type_sousperiodes", typeSousPeriodes);
            session.setAttribute("ts_sousperiodes", typestructureTypeSousperiodes);

            List<TypestructureResponsabilite> typestructureResponsabilites = typestructureResponsabiliteFacadeLocal.findByIdTypestructure(structure.getIdtypestructure().getIdtypestructure());
            List<Responsabilite> responsabilites = new ArrayList<>();
            typestructureResponsabilites.forEach(tsr -> {
                responsabilites.add(tsr.getResponsabilite());
            });

            session.setAttribute("responsabilites", responsabilites);
            session.setAttribute("ts_responsabilites", typestructureResponsabilites);

            showSessionPanel = false;
        } catch (Exception e) {
            showSessionPanel = true;
            JsfUtil.addErrorMessage("Exception");
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

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<Structure> getStructures() {
        return structures;
    }

}
