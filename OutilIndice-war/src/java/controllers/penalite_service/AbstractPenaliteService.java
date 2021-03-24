/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.penalite_service;

import entities.ParametragePenalite;
import entities.Penalite;
import entities.Service;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.ParametragePenaliteFacadeLocal;
import sessions.PenaliteFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractPenaliteService {

    @EJB
    protected ParametragePenaliteFacadeLocal parametragePenaliteFacadeLocal;
    protected ParametragePenalite parametragePenalite = new ParametragePenalite();
    protected List<ParametragePenalite> parametragePenalites = new ArrayList<>();

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected Service service = new Service();
    protected List<Service> services = new ArrayList<>();

    @EJB
    protected PenaliteFacadeLocal penaliteFacadeLocal;
    protected List<Penalite> penalites = new ArrayList<>();
    protected List<Penalite> selectedPenalites = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected final Structure structure = SessionMBean.getStructure();

    protected Routine routine = new Routine();
    protected int total = 0;

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
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

    public Structure getStructure() {
        return structure;
    }

    public ParametragePenalite getParametragePenalite() {
        return parametragePenalite;
    }

    public void setParametragePenalite(ParametragePenalite parametragePenalite) {
        this.parametragePenalite = parametragePenalite;
    }

    public List<ParametragePenalite> getParametragePenalites() {
        return parametragePenalites;
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

    public int getTotal() {
        return total;
    }

}
