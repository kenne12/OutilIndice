/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.penalite_dept;

import entities.EvaluationPenaliteDept;
import entities.LignePenaliteDept;
import entities.Penalite;
import entities.Service;
import entities.Sousperiode;
import entities.Structure;
import entities.TypeSousPeriode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CriterestructureFacadeLocal;
import sessions.EvaluationPenaliteDeptFacadeLocal;
import sessions.LignePenaliteDeptFacadeLocal;
import sessions.ParametragePenaliteFacadeLocal;
import sessions.PenaliteFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.SousperiodeFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypeSousPeriodeFacadeLocal;
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

    @EJB
    protected ParametragePenaliteFacadeLocal parametragePenaliteFacadeLocal;

    @EJB
    protected PenaliteFacadeLocal penaliteFacadeLocal;
    protected List<Penalite> penalites = new ArrayList<>();
    protected List<Penalite> selectedPenalites = new ArrayList<>();

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;

    @EJB
    protected LignePenaliteDeptFacadeLocal lignePenaliteDeptFacadeLocal;
    protected List<LignePenaliteDept> lignePenaliteDepts = new ArrayList<>();

    @EJB
    protected SousperiodeFacadeLocal sousperiodeFacadeLocal;
    protected Sousperiode sousperiode = new Sousperiode();
    protected List<Sousperiode> sousperiodes = new ArrayList<>();

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected Service service = new Service();
    protected List<Service> services = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = SessionMBean.getStructure();

    @EJB
    protected TypeSousPeriodeFacadeLocal typeSousPeriodeFacadeLocal;
    protected TypeSousPeriode typeSousPeriode = new TypeSousPeriode();
    protected List<TypeSousPeriode> typeSousPeriodes = new ArrayList<>();

    protected Routine routine = new Routine();
    protected String mode = "";

    protected boolean stateBtn = true;

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
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
        return sousperiodes;
    }

    public List<Penalite> getPenalites() {
        return penalites;
    }

    public List<Penalite> getSelectedPenalites() {
        return selectedPenalites;
    }

    public void setSelectedPenalites(List<Penalite> selectedPenalites) {
        this.selectedPenalites = selectedPenalites;
    }

    public List<LignePenaliteDept> getLignePenaliteDepts() {
        return lignePenaliteDepts;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public TypeSousPeriode getTypeSousPeriode() {
        return typeSousPeriode;
    }

    public void setTypeSousPeriode(TypeSousPeriode typeSousPeriode) {
        this.typeSousPeriode = typeSousPeriode;
    }

    public List<TypeSousPeriode> getTypeSousPeriodes() {
        return typeSousPeriodes;
    }

    public boolean isStateBtn() {
        return stateBtn;
    }

}
