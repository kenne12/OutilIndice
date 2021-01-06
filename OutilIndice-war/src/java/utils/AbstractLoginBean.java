package utils;

import entities.Mouchard;

import java.util.Date;
import javax.ejb.EJB;
import sessions.MenuFacadeLocal;
import sessions.MouchardFacadeLocal;

import sessions.PrivilegeFacadeLocal;

public class AbstractLoginBean {

    @EJB
    protected MenuFacadeLocal menuFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;
    protected Mouchard traceur;
    protected Routine routine = new Routine();

    protected Date date = new Date();

    protected String confirmPassword = "";
    protected boolean gestionPersonnel;
    protected boolean gestionNote;
    protected boolean gestionPrivilege;
    protected boolean gestionDiscipline;
    protected boolean gestionInscription;
    protected boolean gestionEtat;
    protected boolean parametrage;
    protected boolean bibliotheque;
    protected String gestionPersonnelVisible = "hidden";
    protected String gestionNoteVisible = "hidden";
    protected String gestionPrivilegeVisible = "hidden";
    protected String gestionDisciplineVisible = "hidden";
    protected String gestionInscriptionVisible = "hidden";
    protected String gestionEtatVisible = "hidden";
    protected String parametrageVisible = "hidden";
    protected String bibliothequeVisible = "hidden";

    protected boolean showHibernatePanel = false;
    protected String hibernatePassword = "";

    protected boolean showSessionPanel = true;

    protected boolean connected = false;

    protected String connectionVisible = "visible";

    public boolean isGestionPersonnel() {
        return this.gestionPersonnel;
    }

    public void setGestionPersonnel(boolean gestionPersonnel) {
        this.gestionPersonnel = gestionPersonnel;
    }

    public boolean isGestionNote() {
        return this.gestionNote;
    }

    public void setGestionNote(boolean gestionNote) {
        this.gestionNote = gestionNote;
    }

    public boolean isGestionPrivilege() {
        return this.gestionPrivilege;
    }

    public void setGestionPrivilege(boolean gestionPrivilege) {
        this.gestionPrivilege = gestionPrivilege;
    }

    public boolean isGestionDiscipline() {
        return this.gestionDiscipline;
    }

    public void setGestionDiscipline(boolean gestionDiscipline) {
        this.gestionDiscipline = gestionDiscipline;
    }

    public boolean isGestionInscription() {
        return this.gestionInscription;
    }

    public void setGestionInscription(boolean gestionInscription) {
        this.gestionInscription = gestionInscription;
    }

    public boolean isGestionEtat() {
        return this.gestionEtat;
    }

    public void setGestionEtat(boolean gestionEtat) {
        this.gestionEtat = gestionEtat;
    }

    public boolean isParametrage() {
        /* 121 */ return this.parametrage;
    }

    public void setParametrage(boolean parametrage) {
        /* 125 */ this.parametrage = parametrage;
    }

    public boolean isBibliotheque() {
        /* 129 */ return this.bibliotheque;
    }

    public void setBibliotheque(boolean bibliotheque) {
        /* 133 */ this.bibliotheque = bibliotheque;
    }

    public String getGestionPersonnelVisible() {
        /* 138 */ return this.gestionPersonnelVisible;
    }

    public void setGestionPersonnelVisible(String gestionPersonnelVisible) {
        /* 142 */ this.gestionPersonnelVisible = gestionPersonnelVisible;
    }

    public String getGestionNoteVisible() {
        /* 146 */ return this.gestionNoteVisible;
    }

    public void setGestionNoteVisible(String gestionNoteVisible) {
        /* 150 */ this.gestionNoteVisible = gestionNoteVisible;
    }

    public String getGestionPrivilegeVisible() {
        /* 154 */ return this.gestionPrivilegeVisible;
    }

    public void setGestionPrivilegeVisible(String gestionPrivilegeVisible) {
        /* 158 */ this.gestionPrivilegeVisible = gestionPrivilegeVisible;
    }

    public String getGestionDisciplineVisible() {
        /* 162 */ return this.gestionDisciplineVisible;
    }

    public void setGestionDisciplineVisible(String gestionDisciplineVisible) {
        /* 166 */ this.gestionDisciplineVisible = gestionDisciplineVisible;
    }

    public String getGestionInscriptionVisible() {
        /* 170 */ return this.gestionInscriptionVisible;
    }

    public void setGestionInscriptionVisible(String gestionInscriptionVisible) {
        /* 174 */ this.gestionInscriptionVisible = gestionInscriptionVisible;
    }

    public String getGestionEtatVisible() {
        /* 178 */ return this.gestionEtatVisible;
    }

    public void setGestionEtatVisible(String gestionEtatVisible) {
        /* 182 */ this.gestionEtatVisible = gestionEtatVisible;
    }

    public String getParametrageVisible() {
        /* 186 */ return this.parametrageVisible;
    }

    public void setParametrageVisible(String parametrageVisible) {
        /* 190 */ this.parametrageVisible = parametrageVisible;
    }

    public String getBibliothequeVisible() {
        /* 194 */ return this.bibliothequeVisible;
    }

    public void setBibliothequeVisible(String bibliothequeVisible) {
        /* 198 */ this.bibliothequeVisible = bibliothequeVisible;
    }

    public boolean isShowHibernatePanel() {
        /* 202 */ return this.showHibernatePanel;
    }

    public void setShowHibernatePanel(boolean showHibernatePanel) {
        /* 206 */ this.showHibernatePanel = showHibernatePanel;
    }

    public String getHibernatePassword() {
        /* 210 */ return this.hibernatePassword;
    }

    public void setHibernatePassword(String hibernatePassword) {
        /* 214 */ this.hibernatePassword = hibernatePassword;
    }

    public Mouchard getTraceur() {
        /* 218 */ return this.traceur;
    }

    public void setTraceur(Mouchard traceur) {
        /* 222 */ this.traceur = traceur;
    }

    public boolean isShowSessionPanel() {
        /* 230 */ return this.showSessionPanel;
    }

    public Date getDate() {
        /* 234 */ return this.date;
    }

    public void setDate(Date date) {
        /* 238 */ this.date = date;
    }

    public Routine getRoutine() {
        /* 242 */ return this.routine;
    }

    public String getConfirmPassword() {
        /* 246 */ return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        /* 250 */ this.confirmPassword = confirmPassword;
    }
}
