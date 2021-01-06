/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.s_critere_uo;

import entities.Categorie;
import entities.Critere;
import entities.Criterestructure;
import entities.Detailsc;
import entities.Souscritere;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.CategorieFacadeLocal;
import sessions.CritereFacadeLocal;
import sessions.CriterestructureFacadeLocal;
import sessions.DetailscFacadeLocal;
import sessions.SouscritereFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractSousCritereStr {

    @EJB
    protected DetailscFacadeLocal detailscFacadeLocal;
    protected Detailsc detailsc = new Detailsc();
    protected List<Detailsc> listDetailsc = new ArrayList<>();
    protected List<Detailsc> listDetailscTemp = new ArrayList<>();

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;
    protected Criterestructure criterestructure = new Criterestructure();
    protected List<Criterestructure> criterestructures = new ArrayList<>();
    protected List<Criterestructure> criterestructuresTemp = new ArrayList<>();

    @EJB
    protected SouscritereFacadeLocal souscritereFacadeLocal;
    protected List<Souscritere> souscriteres = new ArrayList<>();
    protected List<Souscritere> souscritereRestants = new ArrayList<>();
    protected List<Souscritere> selectedSouscriteres = new ArrayList<>();

    @EJB
    protected CritereFacadeLocal critereFacadeLocal;
    protected List<Critere> criteres = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected CategorieFacadeLocal categorieFacadeLocal;
    protected Categorie categorie = new Categorie();
    protected List<Categorie> categories = new ArrayList<>();

    protected Routine routine = new Routine();
    protected static final double scoreMax = 100;
    protected double score = 0;
    protected double scoreIndice = 0;

    protected long idStructureSource;
    protected long idStructureDestination;

    protected int denominateur = 0;
    protected int indice = 0;

    protected List<Integer> listDetail = new ArrayList<>();

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";
    protected String message = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public List<Critere> getCriteres() {
        return criteres;
    }

    public List<Souscritere> getSouscriteres() {
        return souscriteres;
    }

    public List<Criterestructure> getCriterestructures() {
        return criterestructures;
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

    public double getScore() {
        return score;
    }

    public List<Detailsc> getListDetailsc() {
        return listDetailsc;
    }

    public List<Souscritere> getSelectedSouscriteres() {
        return selectedSouscriteres;
    }

    public void setSelectedSouscriteres(List<Souscritere> selectedSouscriteres) {
        this.selectedSouscriteres = selectedSouscriteres;
    }

    public Criterestructure getCriterestructure() {
        return criterestructure;
    }

    public void setCriterestructure(Criterestructure criterestructure) {
        this.criterestructure = criterestructure;
    }

    public String getMessage() {
        return message;
    }

    public Detailsc getDetailsc() {
        return detailsc;
    }

    public void setDetailsc(Detailsc detailsc) {
        this.detailsc = detailsc;
    }

    public long getIdStructureSource() {
        return idStructureSource;
    }

    public void setIdStructureSource(long idStructureSource) {
        this.idStructureSource = idStructureSource;
    }

    public long getIdStructureDestination() {
        return idStructureDestination;
    }

    public void setIdStructureDestination(long idStructureDestination) {
        this.idStructureDestination = idStructureDestination;
    }

    public List<Integer> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<Integer> listDetail) {
        this.listDetail = listDetail;
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

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public List<Souscritere> getSouscritereRestants() {
        return souscritereRestants;
    }

    public void setSouscritereRestants(List<Souscritere> souscritereRestants) {
        this.souscritereRestants = souscritereRestants;
    }

    public int getDenominateur() {
        return denominateur;
    }

    public void setDenominateur(int denominateur) {
        this.denominateur = denominateur;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public double getScoreIndice() {
        return scoreIndice;
    }

    public void setScoreIndice(double scoreIndice) {
        this.scoreIndice = scoreIndice;
    }

}
