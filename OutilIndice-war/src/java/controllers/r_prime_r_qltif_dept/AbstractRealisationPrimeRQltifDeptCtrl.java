/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.r_prime_r_qltif_dept;

import entities.EvaluationRPrimeQltifDept;
import entities.LignePrimeQualiteDept;
import entities.Service;
import entities.Souscritereservice;
import entities.Sousperiode;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.EvaluationRPrimeQltifDeptFacadeLocal;
import sessions.LignePrimeQualiteDeptFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.SouscritereserviceFacadeLocal;
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

    @EJB
    protected SouscritereserviceFacadeLocal souscritereserviceFacadeLocal;
    protected List<Souscritereservice> souscritereservices = new ArrayList<>();
    protected List<Souscritereservice> selectedSouscritereservices = new ArrayList<>();

    @EJB
    protected LignePrimeQualiteDeptFacadeLocal lignePrimeQualiteDeptFacadeLocal;
    protected List<LignePrimeQualiteDept> lignePrimeQualiteDepts = new ArrayList<>();

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

    public List<Service> getServices() {
        services = serviceFacadeLocal.findAllOrderByCode();
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Souscritereservice> getSouscritereservices() {
        return souscritereservices;
    }

    public List<Souscritereservice> getSelectedSouscritereservices() {
        return selectedSouscritereservices;
    }

    public void setSelectedSouscritereservices(List<Souscritereservice> selectedSouscritereservices) {
        this.selectedSouscritereservices = selectedSouscritereservices;
    }

    public List<LignePrimeQualiteDept> getLignePrimeQualiteDepts() {
        return lignePrimeQualiteDepts;
    }

}
