/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.evaluation_service;

import entities.Critereservice;
import entities.Evaluationservice;
import entities.Noteservice;
import entities.Service;
import entities.Souscritereservice;
import entities.Sousperiode;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CritereserviceFacadeLocal;
import sessions.SouscritereserviceFacadeLocal;
import sessions.EvaluationserviceFacadeLocal;
import sessions.NoteFacadeLocal;
import sessions.NoteserviceFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.SousperiodeFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractEvaluationService {

    @EJB
    protected EvaluationserviceFacadeLocal evaluationserviceFacadeLocal;
    protected Evaluationservice evaluationservice = new Evaluationservice();
    protected List<Evaluationservice> evaluationservices = new ArrayList<>();

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected Service service = new Service();
    protected List<Service> services = new ArrayList<>();

    @EJB
    protected SousperiodeFacadeLocal sousperiodeFacadeLocal;
    protected Sousperiode sousperiode = new Sousperiode();
    protected List<Sousperiode> sousperiodes = new ArrayList<>();

    @EJB
    protected SouscritereserviceFacadeLocal souscritereserviceFacadeLocal;
    protected List<Souscritereservice> selectedSouscritereservice = new ArrayList<>();
    protected List<Souscritereservice> listSouscritereservice = new ArrayList<>();

    @EJB
    protected CritereserviceFacadeLocal critereserviceFacadeLocal;
    protected List<Critereservice> critereservices = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected NoteserviceFacadeLocal noteserviceFacadeLocal;
    protected Noteservice noteservice = new Noteservice();
    protected List<Noteservice> noteservices = new ArrayList<>();

    @EJB
    protected NoteFacadeLocal noteFacadeLocal;

    protected Routine routine = new Routine();
    protected static final double scoreMax = 100;
    protected double score = 0;

    protected String mode = "";
    protected String message = "";

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

    public List<Souscritereservice> getListSouscritereservice() {
        return listSouscritereservice;
    }

    public String getMessage() {
        return message;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        return services;
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

    public List<Souscritereservice> getSelectedSouscritereservice() {
        return selectedSouscritereservice;
    }

    public void setSelectedSouscritereservice(List<Souscritereservice> selectedSouscritereservice) {
        this.selectedSouscritereservice = selectedSouscritereservice;
    }

    public List<Evaluationservice> getEvaluationservices() {
        return evaluationservices;
    }

    public Evaluationservice getEvaluationservice() {
        return evaluationservice;
    }

    public void setEvaluationservice(Evaluationservice evaluationservice) {
        this.evaluationservice = evaluationservice;
    }

    public Noteservice getNoteservice() {
        return noteservice;
    }

    public void setNoteservice(Noteservice noteservice) {
        this.noteservice = noteservice;
    }

    public List<Noteservice> getNoteservices() {
        return noteservices;
    }

}
