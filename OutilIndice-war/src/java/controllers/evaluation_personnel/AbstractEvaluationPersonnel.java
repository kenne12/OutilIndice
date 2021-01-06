/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.evaluation_personnel;

import entities.Categorie;
import entities.Categoriestructure;
import entities.Criterestructure;
import entities.Detailsc;
import entities.Evaluationpersonnel;
import entities.Note;
import entities.Personnel;
import entities.Sousperiode;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.CategorieFacadeLocal;
import sessions.CategoriestructureFacadeLocal;
import sessions.CriterestructureFacadeLocal;
import sessions.DetailscFacadeLocal;
import sessions.ElementReponseFacadeLocal;
import sessions.EvaluationpersonnelFacadeLocal;
import sessions.NoteFacadeLocal;
import sessions.NoteserviceFacadeLocal;
import sessions.PersonnelFacadeLocal;
import sessions.SousperiodeFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractEvaluationPersonnel {

    @EJB
    protected EvaluationpersonnelFacadeLocal evaluationpersonnelFacadeLocal;
    protected Evaluationpersonnel evaluationpersonnel = new Evaluationpersonnel();
    protected List<Evaluationpersonnel> evaluationpersonnels = new ArrayList<>();
    protected List<Evaluationpersonnel> evaluationpersonnelIps = new ArrayList<>();
    protected List<Evaluationpersonnel> evaluationpersonnelIns = new ArrayList<>();

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    protected Personnel personnel = new Personnel();
    protected List<Personnel> personnels = new ArrayList<>();

    @EJB
    protected CategorieFacadeLocal categorieFacadeLocal;
    protected Categorie categorie = new Categorie();
    protected List<Categorie> categories = new ArrayList<>();

    @EJB
    protected SousperiodeFacadeLocal sousperiodeFacadeLocal;
    protected Sousperiode sousperiode = new Sousperiode();
    protected List<Sousperiode> sousperiodes = new ArrayList<>();

    @EJB
    protected DetailscFacadeLocal detailscFacadeLocal;
    protected List<Detailsc> selectedDetailsc = new ArrayList<>();
    protected List<Detailsc> listDetailsc = new ArrayList<>();

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;

    protected List<Criterestructure> criterestructures = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected CategoriestructureFacadeLocal categoriestructureFacadeLocal;
    protected Categoriestructure categoriestructure = new Categoriestructure();

    @EJB
    protected NoteFacadeLocal noteFacadeLocal;
    protected Note note = new Note();
    protected List<Note> notes = new ArrayList<>();

    @EJB
    protected NoteserviceFacadeLocal noteserviceFacadeLocal;

    @EJB
    protected ElementReponseFacadeLocal elementReponseFacadeLocal;

    protected Routine routine = new Routine();
    protected static final double scoreMax = 100;
    protected double score = 0;
    protected double score_1 = 0;
    protected double score_2 = 0;
    protected double scoreIndice = 0;

    protected long idStructureSource;
    protected long idStructureDestination;

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

    public String getMessage() {
        return message;
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

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Personnel> getPersonnels() {
        personnels = personnelFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
        return personnels;
    }

    public Sousperiode getSousperiode() {
        return sousperiode;
    }

    public void setSousperiode(Sousperiode sousperiode) {
        this.sousperiode = sousperiode;
    }

    public List<Sousperiode> getSousperiodes() {
        sousperiodes = sousperiodeFacadeLocal.findAllRangeCode();
        return sousperiodes;
    }

    public List<Detailsc> getSelectedDetailsc() {
        return selectedDetailsc;
    }

    public void setSelectedDetailsc(List<Detailsc> selectedDetailsc) {
        this.selectedDetailsc = selectedDetailsc;
    }

    public List<Evaluationpersonnel> getEvaluationpersonnels() {
        return evaluationpersonnels;
    }

    public Evaluationpersonnel getEvaluationpersonnel() {
        return evaluationpersonnel;
    }

    public void setEvaluationpersonnel(Evaluationpersonnel evaluationpersonnel) {
        this.evaluationpersonnel = evaluationpersonnel;
    }

    public Categoriestructure getCategoriestructure() {
        return categoriestructure;
    }

    public void setCategoriestructure(Categoriestructure categoriestructure) {
        this.categoriestructure = categoriestructure;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<Evaluationpersonnel> getEvaluationpersonnelIps() {
        return evaluationpersonnelIps;
    }

    public List<Evaluationpersonnel> getEvaluationpersonnelIns() {
        return evaluationpersonnelIns;
    }

    public double getScore_1() {
        return score_1;
    }

    public double getScore_2() {
        return score_2;
    }

    public double getScoreIndice() {
        return scoreIndice;
    }

}
