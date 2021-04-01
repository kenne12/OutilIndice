/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.s_critere_service;

import entities.Service;
import entities.Souscritere;
import entities.Souscritereservice;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
    protected List<Service> services = SessionMBean.getServices();

    @EJB
    protected SouscritereFacadeLocal souscritereFacadeLocal;
    protected List<Souscritere> souscriteres = new ArrayList<>();
    protected List<Souscritere> selectedSouscriteres = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected final Structure structure = SessionMBean.getStructure();

    protected Routine routine = new Routine();

    protected double score = 0.0;

    protected String mode = "";
    protected String message = "";

    public Routine getRoutine() {
        return this.routine;
    }

    public String getMode() {
        return this.mode;
    }

    public List<Souscritere> getSouscriteres() {
        return this.souscriteres;
    }

    public Structure getStructure() {
        return this.structure;
    }

    public double getScore() {
        return this.score;
    }

    public List<Souscritere> getSelectedSouscriteres() {
        return this.selectedSouscriteres;
    }

    public void setSelectedSouscriteres(List<Souscritere> selectedSouscriteres) {
        this.selectedSouscriteres = selectedSouscriteres;
    }

    public String getMessage() {
        return this.message;
    }

    public List<Souscritereservice> getSouscritereservices() {
        return this.souscritereservices;
    }

    public void setSouscritereservices(List<Souscritereservice> souscritereservices) {
        this.souscritereservices = souscritereservices;
    }

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public Souscritereservice getSouscritereservice() {
        return this.souscritereservice;
    }

    public void setSouscritereservice(Souscritereservice souscritereservice) {
        this.souscritereservice = souscritereservice;
    }

}
