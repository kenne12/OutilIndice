/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_qntif_dept;

import entities.Cible;
import entities.Indicateur;
import entities.Periode;
import entities.Service;
import entities.Sousperiode;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CibleFacadeLocal;
import sessions.IndicateurFacadeLocal;
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
public class AbstractPrimeQntifDeptCtrl {

    @EJB
    protected CibleFacadeLocal cibleFacadeLocal;
    protected Cible cible = new Cible();
    protected List<Cible> cibles = new ArrayList<>();
    protected List<Cible> listCibles = new ArrayList<>();

    @EJB
    protected IndicateurFacadeLocal indicateurFacadeLocal;
    protected List<Indicateur> indicateurs = new ArrayList<>();
    protected List<Indicateur> selectedIndicateurs = new ArrayList<>();

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
    protected Service service = new Service();
    protected List<Service> services = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = SessionMBean.getStructure();
    protected List<Structure> structures = new ArrayList<>();

    protected double totalCible;

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

    public Cible getCible() {
        return cible;
    }

    public void setCible(Cible cible) {
        this.cible = cible;
    }

    public List<Cible> getCibles() {
        return cibles;
    }

    public List<Cible> getListCibles() {
        return listCibles;
    }

    public List<Indicateur> getIndicateurs() {
        return indicateurs;
    }

    public List<Indicateur> getSelectedIndicateurs() {
        return selectedIndicateurs;
    }

    public void setSelectedIndicateurs(List<Indicateur> selectedIndicateurs) {
        this.selectedIndicateurs = selectedIndicateurs;
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
        services = serviceFacadeLocal.findAllOrderByCode();
        return services;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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

    public double getTotalCible() {
        return totalCible;
    }

}
