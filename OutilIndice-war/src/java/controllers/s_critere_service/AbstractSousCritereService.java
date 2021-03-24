/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.s_critere_service;

import entities.Critere;
import entities.Critereservice;
import entities.Service;
import entities.Souscritere;
import entities.Souscritereservice;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.CritereFacadeLocal;
import sessions.CritereserviceFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.SouscritereFacadeLocal;
import sessions.SouscritereserviceFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractSousCritereService {

    @EJB
    protected SouscritereserviceFacadeLocal souscritereserviceFacadeLocal;
    protected Souscritereservice souscritereservice = new Souscritereservice();
    protected List<Souscritereservice> souscritereservices = new ArrayList<>();
    protected List<Souscritereservice> souscritereservicesTemp = new ArrayList<>();

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected Service service = new Service();
    protected List<Service> services = new ArrayList<>();

    @EJB
    protected CritereserviceFacadeLocal critereserviceFacadeLocal;
    protected Critereservice critereservice = new Critereservice();
    protected List<Critereservice> critereservices = new ArrayList<>();
    protected List<Critereservice> critereservicesTemp = new ArrayList<>();

    @EJB
    protected SouscritereFacadeLocal souscritereFacadeLocal;
    protected List<Souscritere> souscriteres = new ArrayList<>();
    protected List<Souscritere> selectedSouscriteres = new ArrayList<>();

    @EJB
    protected CritereFacadeLocal critereFacadeLocal;
    protected List<Critere> criteres = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected final Structure structure = SessionMBean.getStructure();
    protected List<Structure> structures = new ArrayList<>();

    protected Routine routine = new Routine();
    protected static final double scoreMax = 100;
    protected double score = 0;

    protected long idService;
    protected long idServiceDestination;

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

    public Structure getStructure() {
        return structure;
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public double getScore() {
        return score;
    }

    public List<Souscritere> getSelectedSouscriteres() {
        return selectedSouscriteres;
    }

    public void setSelectedSouscriteres(List<Souscritere> selectedSouscriteres) {
        this.selectedSouscriteres = selectedSouscriteres;
    }

    public String getMessage() {
        return message;
    }

    public long getIdService() {
        return idService;
    }

    public void setIdService(long idService) {
        this.idService = idService;
    }

    public long getIdServiceDestination() {
        return idServiceDestination;
    }

    public void setIdServiceDestination(long idServiceDestination) {
        this.idServiceDestination = idServiceDestination;
    }

    public List<Integer> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<Integer> listDetail) {
        this.listDetail = listDetail;
    }

    public List<Souscritereservice> getSouscritereservices() {
        return souscritereservices;
    }

    public void setSouscritereservices(List<Souscritereservice> souscritereservices) {
        this.souscritereservices = souscritereservices;
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

    public Critereservice getCritereservice() {
        return critereservice;
    }

    public void setCritereservice(Critereservice critereservice) {
        this.critereservice = critereservice;
    }

    public List<Critereservice> getCritereservices() {
        return critereservices;
    }

    public Souscritereservice getSouscritereservice() {
        return souscritereservice;
    }

    public void setSouscritereservice(Souscritereservice souscritereservice) {
        this.souscritereservice = souscritereservice;
    }

}
