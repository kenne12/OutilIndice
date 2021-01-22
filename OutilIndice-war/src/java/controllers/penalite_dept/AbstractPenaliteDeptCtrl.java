/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.penalite_dept;

import entities.EvaluationPenaliteDept;
import entities.Periode;
import entities.Service;
import entities.Sousperiode;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.EvaluationPenaliteDeptFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.SousperiodeFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractPenaliteDeptCtrl {

    @EJB
    protected EvaluationPenaliteDeptFacadeLocal evaluationPenaliteDeptFacadeLocal;
    protected EvaluationPenaliteDept evaluationPenaliteDept = new EvaluationPenaliteDept();
    protected List<EvaluationPenaliteDept> evaluationPenaliteDepts = new ArrayList<>();
    protected List<EvaluationPenaliteDept> listEvaluationPenaliteDepts = new ArrayList<>();

    @EJB
    protected PeriodeFacadeLocal periodeFacadeLocal;
    protected Periode periode = SessionMBean.getPeriode();
    protected List<Periode> periodes = new ArrayList<>();

    @EJB
    protected SousperiodeFacadeLocal sousperiodeFacadeLocal;
    protected Sousperiode sousperiode = new Sousperiode();
    protected List<Sousperiode> sousperiodes = new ArrayList<>();

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected List<Service> services = new ArrayList<>();
    protected List<Service> selectedServices = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = SessionMBean.getStructure();
    protected List<Structure> structures = new ArrayList<>();

    protected Routine routine = new Routine();
    protected String mode = "";

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

    public EvaluationPenaliteDept getEvaluationPenaliteDept() {
        return evaluationPenaliteDept;
    }

    public void setEvaluationPenaliteDept(EvaluationPenaliteDept evaluationPenaliteDept) {
        this.evaluationPenaliteDept = evaluationPenaliteDept;
    }

    public List<EvaluationPenaliteDept> getEvaluationPenaliteDepts() {
        return evaluationPenaliteDepts;
    }

    public List<EvaluationPenaliteDept> getListEvaluationPenaliteDepts() {
        return listEvaluationPenaliteDepts;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public List<Periode> getPeriodes() {
        periodes = periodeFacadeLocal.findAllRange();
        return periodes;
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

    public List<Service> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<Service> selectedServices) {
        this.selectedServices = selectedServices;
    }

}
