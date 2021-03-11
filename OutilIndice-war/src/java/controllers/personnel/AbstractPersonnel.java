/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.personnel;

import entities.Categorie;
import entities.Personnel;
import entities.Responsabilite;
import entities.Service;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CategorieFacadeLocal;
import sessions.PersonnelFacadeLocal;
import sessions.ResponsabiliteFacadeLocal;
import sessions.ServiceFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractPersonnel {

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    protected Personnel personnel = new Personnel();
    protected List<Personnel> personnels = new ArrayList<>();

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected List<Service> services = new ArrayList<>();

    @EJB
    protected CategorieFacadeLocal categorieFacadeLocal;
    protected List<Categorie> categories = new ArrayList<>();

    @EJB
    protected ResponsabiliteFacadeLocal responsabiliteFacadeLocal;
    protected List<Responsabilite> responsabilites = new ArrayList<>();

    protected Routine routine = new Routine();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Personnel> getPersonnels() {
        personnels = personnelFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
        return personnels;
    }

    public List<Service> getServices() {
        services = serviceFacadeLocal.findAll();
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Categorie> getCategories() {
        categories = categorieFacadeLocal.findAllRangeByCode();
        return categories;
    }

    public List<Responsabilite> getResponsabilites() {
        responsabilites = responsabiliteFacadeLocal.findAll();
        return responsabilites;
    }

}
