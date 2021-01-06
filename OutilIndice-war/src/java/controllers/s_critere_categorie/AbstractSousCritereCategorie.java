/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.s_critere_categorie;

import entities.Categorie;
import entities.Categoriestructure;
import entities.Detailsc;
import entities.Criterecategorie;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.CategorieFacadeLocal;
import sessions.CategoriestructureFacadeLocal;
import sessions.DetailscFacadeLocal;
import sessions.SouscriterecategorieFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractSousCritereCategorie {

    @EJB
    protected SouscriterecategorieFacadeLocal souscriterecategorieFacadeLocal;
    protected Criterecategorie souscriterecategorie = new Criterecategorie();
    protected List<Criterecategorie> souscriterecategories = new ArrayList<>();

    @EJB
    protected DetailscFacadeLocal detailscFacadeLocal;
    protected Detailsc detailsc = new Detailsc();
    protected List<Detailsc> detailscs = new ArrayList<>();

    @EJB
    protected CategoriestructureFacadeLocal categoriestructureFacadeLocal;
    protected Categoriestructure categoriestructure = new Categoriestructure();
    protected List<Categoriestructure> categoriestructures = new ArrayList();

    @EJB
    protected CategorieFacadeLocal categorieFacadeLocal;
    protected Categorie categorie = new Categorie();
    protected List<Categorie> categories = new ArrayList<>();
    protected List<Categorie> selectedCategories = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    protected Routine routine = new Routine();
    protected static final double scoreMax = 100;
    protected double score = 0;
    protected double pointMax = 0;
    protected Integer denominateur = 0;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Categorie> getCategories() {
        categories = categorieFacadeLocal.findAllRangeByCode();
        return categories;
    }

    public List<Categoriestructure> getCategoriestructures() {
        return categoriestructures;
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

    public List<Categorie> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(List<Categorie> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public double getScore() {
        return score;
    }

    public double getPointMax() {
        return pointMax;
    }

    public Categoriestructure getCategoriestructure() {
        return categoriestructure;
    }

    public void setCategoriestructure(Categoriestructure categoriestructure) {
        this.categoriestructure = categoriestructure;
    }

    public int getDenominateur() {
        return denominateur;
    }

    public void setDenominateur(int denominateur) {
        this.denominateur = denominateur;
    }

}
