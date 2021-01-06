package controllers.utilisateur;

import entities.Structure;
import entities.Personnel;
import entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.StructureFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PersonnelFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import sessions.UtilisateurstructureFacadeLocal;
import utils.Routine;

public class AbstractUtilisateurController {

    @EJB
    protected UtilisateurFacadeLocal utilisateurFacadeLocal;
    protected Utilisateur utilisateur;
    protected List<Utilisateur> utilisateurs = new ArrayList();

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    protected Personnel personnel = new Personnel();
    protected List<Personnel> personnels = new ArrayList();

    protected List<Utilisateur> utilisateurActifs = new ArrayList();
    protected List<Utilisateur> utilisateurInactifs = new ArrayList();

    @EJB
    protected UtilisateurstructureFacadeLocal utilisateurstructureFacadeLocal;

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected List<Structure> structures = new ArrayList();
    protected List<Structure> selectedStructures = new ArrayList();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;
    protected List<String> templates = new ArrayList();

    protected Boolean detail = true;
    protected Boolean modifier = true;
    protected Boolean consulter = true;
    protected Boolean imprimer = true;
    protected Boolean supprimer = true;

    protected boolean buttonActif = false;
    protected boolean buttonInactif = true;

    protected Routine routine = new Routine();

    protected String mode = "";
    protected String repeatPassword = "";

    public Boolean getDetail() {
        return this.detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public Boolean getModifier() {
        return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        this.modifier = modifier;
    }

    public Boolean getConsulter() {
        return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        this.consulter = consulter;
    }

    public Boolean getImprimer() {
        this.imprimer = this.utilisateurFacadeLocal.findAll().isEmpty();
        return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.modifier = this.supprimer = this.detail = utilisateur == null;
        this.utilisateur = utilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        this.utilisateurs = this.utilisateurFacadeLocal.findAll();
        return this.utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public List<Utilisateur> getUtilisateurActifs() {
        this.utilisateurActifs = this.utilisateurFacadeLocal.findByActif(true);
        return this.utilisateurActifs;
    }

    public void setUtilisateurActifs(List<Utilisateur> utilisateurActifs) {
        this.utilisateurActifs = utilisateurActifs;
    }

    public List<Utilisateur> getUtilisateurInactifs() {
        this.utilisateurInactifs = this.utilisateurFacadeLocal.findByActif(false);
        return this.utilisateurInactifs;
    }

    public void setUtilisateurInactifs(List<Utilisateur> utilisateurInactifs) {
        this.utilisateurInactifs = utilisateurInactifs;
    }

    public boolean isButtonActif() {
        return this.buttonActif;
    }

    public void setButtonActif(boolean buttonActif) {
        this.buttonActif = buttonActif;
    }

    public boolean isButtonInactif() {
        return this.buttonInactif;
    }

    public void setButtonInactif(boolean buttonInactif) {
        this.buttonInactif = buttonInactif;
    }

    public List<String> getTemplates() {
        return this.templates;
    }

    public Routine getRoutine() {
        return this.routine;
    }

    public Personnel getPersonnel() {
        return this.personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Personnel> getPersonnels() {
        return this.personnels;
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }

    public List<Structure> getSelectedStructures() {
        return this.selectedStructures;
    }

    public void setSelectedStructures(List<Structure> selectedStructures) {
        this.selectedStructures = selectedStructures;
    }

    public String getMode() {
        return this.mode;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

}
