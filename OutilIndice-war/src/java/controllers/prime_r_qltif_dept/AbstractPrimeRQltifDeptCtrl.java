/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_r_qltif_dept;

import entities.Categorie;
import entities.Criterestructure;
import entities.EffectifCategorie;
import entities.Parametragecritere;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CategorieFacadeLocal;
import sessions.CriterestructureFacadeLocal;
import sessions.EffectifCategorieFacadeLocal;
import sessions.ParametragecritereFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;
import utils.Utilitaires;

/**
 *
 * @author USER
 */
public class AbstractPrimeRQltifDeptCtrl {

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;
    protected Criterestructure criterestructure = Utilitaires.findCritereSInSession(5);
    protected List<Criterestructure> criterestructures = SessionMBean.getCritereStructures();

    @EJB
    protected ParametragecritereFacadeLocal parametragecritereFacadeLocal;
    protected Parametragecritere parametragecritere = new Parametragecritere();
    protected List<Parametragecritere> parametragecriteres = new ArrayList<>();
    protected List<Parametragecritere> listParametres = new ArrayList<>();

    @EJB
    protected CategorieFacadeLocal categorieFacadeLocal;
    protected Categorie categorie = new Categorie();
    protected List<Categorie> categories = new ArrayList<>();
    protected List<Categorie> selectedCategories = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected final Structure structure = SessionMBean.getStructure();

    @EJB
    protected EffectifCategorieFacadeLocal effectifCategorieFacadeLocal;
    protected List<EffectifCategorie> effectifCategories = new ArrayList<>();

    protected Routine routine = new Routine();
    protected String mode = "";

    protected double denominateur = 5;

    protected double totalPointMaxCritere;
    protected int totalEffectif;
    protected double totalPointSaisi;
    protected int indexCritere;

    public Parametragecritere getParametragecritere() {
        return parametragecritere;
    }

    public void setParametragecritere(Parametragecritere parametragecritere) {
        this.parametragecritere = parametragecritere;
    }

    public List<Parametragecritere> getParametragecriteres() {
        return parametragecriteres;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public List<Categorie> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(List<Categorie> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public Structure getStructure() {
        return structure;
    }

    public Routine getRoutine() {
        return routine;
    }

    public List<Parametragecritere> getListParametres() {
        return listParametres;
    }

    public double getDenominateur() {
        return denominateur;
    }

    public void setDenominateur(double denominateur) {
        this.denominateur = denominateur;
    }

    public double getTotalPointMaxCritere() {
        return totalPointMaxCritere;
    }

    public int getTotalEffectif() {
        return totalEffectif;
    }

    public double getTotalPointSaisi() {
        return totalPointSaisi;
    }

    public Criterestructure getCriterestructure() {
        return criterestructure;
    }

}
