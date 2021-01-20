/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.r_prime_r_qltif_dept;

import entities.EvaluationRPrimeQltifDept;
import entities.Periode;
import entities.Service;
import entities.Sousperiode;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.EvaluationRPrimeQltifDeptFacadeLocal;
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
public class AbstractRealisationPrimeRQltifDeptCtrl {

    @EJB
    protected EvaluationRPrimeQltifDeptFacadeLocal evaluationRPrimeQltifDeptFacadeLocal;
    protected EvaluationRPrimeQltifDept evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept();
    protected List<EvaluationRPrimeQltifDept> evaluationRPrimeQltifDepts = new ArrayList<>();
    protected List<EvaluationRPrimeQltifDept> listEvaluationRPrimeQltifDepts = new ArrayList<>();

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

    public EvaluationRPrimeQltifDept getEvaluationRPrimeQltifDept() {
        return evaluationRPrimeQltifDept;
    }

    public void setEvaluationRPrimeQltifDept(EvaluationRPrimeQltifDept evaluationRPrimeQltifDept) {
        this.evaluationRPrimeQltifDept = evaluationRPrimeQltifDept;
    }

    public List<EvaluationRPrimeQltifDept> getEvaluationRPrimeQltifDepts() {
        return evaluationRPrimeQltifDepts;
    }

    public void setEvaluationRPrimeQltifDepts(List<EvaluationRPrimeQltifDept> evaluationRPrimeQltifDepts) {
        this.evaluationRPrimeQltifDepts = evaluationRPrimeQltifDepts;
    }

    public List<EvaluationRPrimeQltifDept> getListEvaluationRPrimeQltifDepts() {
        return listEvaluationRPrimeQltifDepts;
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
