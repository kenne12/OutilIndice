/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.indicateurqte_service;

import entities.Indicateur;
import entities.IndicateurQteService;
import entities.Service;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.IndicateurFacadeLocal;
import sessions.IndicateurQteServiceFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractIndicateurQteService {

    @EJB
    protected IndicateurQteServiceFacadeLocal indicateurQteServiceFacadeLocal;
    protected IndicateurQteService indicateurQteService = new IndicateurQteService();
    protected List<IndicateurQteService> indicateurQteServices = new ArrayList<>();

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected Service service = new Service();
    protected List<Service> services = new ArrayList<>();

    @EJB
    protected IndicateurFacadeLocal indicateurFacadeLocal;
    protected List<Indicateur> indicateurs = new ArrayList<>();
    protected List<Indicateur> selectedindicateurs = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected final Structure structure = SessionMBean.getStructure();

    protected Routine routine = new Routine();

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

    public List<Indicateur> getIndicateurs() {
        return indicateurs;
    }

    public List<Indicateur> getSelectedindicateurs() {
        return selectedindicateurs;
    }

    public void setSelectedindicateurs(List<Indicateur> selectedindicateurs) {
        this.selectedindicateurs = selectedindicateurs;
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
        services = serviceFacadeLocal.findAllOrderByCode();
        return services;
    }

    public IndicateurQteService getIndicateurQteService() {
        return indicateurQteService;
    }

    public void setIndicateurQteService(IndicateurQteService indicateurQteService) {
        this.indicateurQteService = indicateurQteService;
    }

    public List<IndicateurQteService> getIndicateurQteServices() {
        return indicateurQteServices;
    }

}
