/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_service;

import entities.Service;
import entities.TypestructureService;
import entities.Typestructure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.ServiceFacadeLocal;
import sessions.TypestructureServiceFacadeLocal;
import sessions.TypestructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractTypestructServiceCtrl {

    @EJB
    protected TypestructureServiceFacadeLocal typestructureServiceFacadeLocal;
    protected TypestructureService typestructureService = new TypestructureService();
    protected List<TypestructureService> typestructureServices = new ArrayList<>();

    @EJB
    protected TypestructureFacadeLocal typestructureFacadeLocal;
    protected Typestructure typestructure = new Typestructure();
    protected List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected Service service = new Service();
    protected List<Service> services = new ArrayList<>();
    protected List<Service> selectedServices = new ArrayList<>();

    protected Routine routine = new Routine();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
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

    public Typestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }

    public List<Typestructure> getTypestructures() {
        typestructures = typestructureFacadeLocal.findAllRangeByNom();
        return typestructures;
    }

    public List<TypestructureService> getTypestructureServices() {
        return typestructureServices;
    }

    public List<Service> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<Service> selectedServices) {
        this.selectedServices = selectedServices;
    }

}
