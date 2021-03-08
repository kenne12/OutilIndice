/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_r_qltif_dept;

import entities.Categorie;
import entities.Criterestructure;
import entities.Parametragecritere;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CategorieFacadeLocal;
import sessions.CriterestructureFacadeLocal;
import sessions.ParametragecritereFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractPrimeRQltifDeptCtrl {

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;
    protected Criterestructure criterestructure = new Criterestructure();

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
    protected Structure structure = SessionMBean.getStructure();
    protected List<Structure> structures = new ArrayList<>();

    protected Routine routine = new Routine();
    protected String mode = "";

    protected double denominateur = 5;

    protected double totalPointMaxCritere;
    protected int totalEffectif;
    protected double totalPointSaisi;

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

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<Structure> getStructures() {
        return structures;
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

}
