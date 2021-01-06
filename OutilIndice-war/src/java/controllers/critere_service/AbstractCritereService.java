/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.critere_service;

import entities.Critere;
import entities.Critereservice;
import entities.Service;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.CritereFacadeLocal;
import sessions.CritereserviceFacadeLocal;
import sessions.CriterestructureFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractCritereService {

    @EJB
    protected CritereserviceFacadeLocal critereserviceFacadeLocal;
    protected Critereservice critereservice = new Critereservice();
    protected List<Critereservice> critereservices = new ArrayList<>();

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected Service service = new Service();
    protected List<Service> services = new ArrayList<>();

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;

    @EJB
    protected CritereFacadeLocal critereFacadeLocal;
    protected Critere critere = new Critere();
    protected List<Critere> criteres = new ArrayList<>();
    protected List<Critere> selectedCriteres = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    protected Routine routine = new Routine();
    protected static final double scoreMax = 100;
    protected double score = 0;
    protected double pointMax = 0;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Critere getCritere() {
        return critere;
    }

    public void setCritere(Critere critere) {
        this.critere = critere;
    }

    public List<Critere> getCriteres() {
        return criteres;
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

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }

    public List<Critere> getSelectedCriteres() {
        return selectedCriteres;
    }

    public void setSelectedCriteres(List<Critere> selectedCriteres) {
        this.selectedCriteres = selectedCriteres;
    }

    public double getScore() {
        return score;
    }

    public double getPointMax() {
        return pointMax;
    }

    public Critereservice getCritereservice() {
        return critereservice;
    }

    public void setCritereservice(Critereservice critereservice) {
        this.critereservice = critereservice;
    }

    public List<Critereservice> getCritereservices() {
        return critereservices;
    }

    public void setCritereservices(List<Critereservice> critereservices) {
        this.critereservices = critereservices;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        services = serviceFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

}
