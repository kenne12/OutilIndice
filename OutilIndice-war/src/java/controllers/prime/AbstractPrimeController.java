/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime;

import entities.Evaluationpersonnel;
import entities.Note;
import entities.Prime;
import entities.Sousperiode;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.DepenseFacadeLocal;
import sessions.EvaluationpersonnelFacadeLocal;
import sessions.NoteFacadeLocal;
import sessions.NoteserviceFacadeLocal;
import sessions.PrimeFacadeLocal;
import sessions.SousperiodeFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractPrimeController {

    @EJB
    protected PrimeFacadeLocal primeFacadeLocal;
    protected Prime prime = new Prime();
    protected List<Prime> primes = new ArrayList<>();

    @EJB
    protected EvaluationpersonnelFacadeLocal evaluationpersonnelFacadeLocal;
    protected Evaluationpersonnel evaluationpersonnel = new Evaluationpersonnel();
    protected List<Evaluationpersonnel> evaluationpersonnels = new ArrayList<>();

    @EJB
    protected SousperiodeFacadeLocal sousperiodeFacadeLocal;
    protected Sousperiode sousperiode = new Sousperiode();
    protected List<Sousperiode> sousperiodes = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected NoteFacadeLocal noteFacadeLocal;
    protected List<Note> notes = new ArrayList<>();
    protected List<Note> selectedNotes = new ArrayList<>();

    @EJB
    protected NoteserviceFacadeLocal noteserviceFacadeLocal;

    protected Routine routine = new Routine();
    protected static final double scoreMax = 100;
    protected double score = 0;

    @EJB
    protected DepenseFacadeLocal depenseFacadeLocal;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";
    protected String message = "";
    protected double montantPrime = 0;

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
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

    public String getMessage() {
        return message;
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

    public List<Evaluationpersonnel> getEvaluationpersonnels() {
        return evaluationpersonnels;
    }

    public Evaluationpersonnel getEvaluationpersonnel() {
        return evaluationpersonnel;
    }

    public void setEvaluationpersonnel(Evaluationpersonnel evaluationpersonnel) {
        this.evaluationpersonnel = evaluationpersonnel;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<Prime> getPrimes() {
        return primes;
    }

    public double getMontantPrime() {
        return montantPrime;
    }

    public void setMontantPrime(double montantPrime) {
        this.montantPrime = montantPrime;
    }

    public List<Note> getSelectedNotes() {
        return selectedNotes;
    }

    public void setSelectedNotes(List<Note> selectedNotes) {
        this.selectedNotes = selectedNotes;
    }

    public Prime getPrime() {
        return prime;
    }

    public void setPrime(Prime prime) {
        this.prime = prime;
    }

}
