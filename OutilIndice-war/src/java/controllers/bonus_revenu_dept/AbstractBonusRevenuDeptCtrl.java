/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.bonus_revenu_dept;

import entities.Parametragecritere;
import entities.Service;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.ParametragecritereFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractBonusRevenuDeptCtrl {

    @EJB
    protected ParametragecritereFacadeLocal parametragecritereFacadeLocal;
    protected Parametragecritere parametragecritere = new Parametragecritere();
    protected List<Parametragecritere> parametragecriteres = new ArrayList<>();
    protected List<Parametragecritere> listParametres = new ArrayList<>();

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected Service service = new Service();
    protected List<Service> services = new ArrayList<>();
    protected List<Service> selectedServices = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    protected Routine routine = new Routine();
    protected String mode = "";

    protected double denominateur = 5;

    public Parametragecritere getParametragecritere() {
        return parametragecritere;
    }

    public void setParametragecritere(Parametragecritere parametragecritere) {
        this.parametragecritere = parametragecritere;
    }

    public List<Parametragecritere> getParametragecriteres() {
        return parametragecriteres;
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

    public List<Service> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<Service> selectedServices) {
        this.selectedServices = selectedServices;
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

    public Routine getRoutine() {
        return routine;
    }

    public List<Parametragecritere> getListParametres() {
        return listParametres;
    }

    public double getDenominateur() {
        return denominateur;
    }

    public void setDenominateur(double denominateur) {
        this.denominateur = denominateur;
    }

}
