/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cible_bonus_revenu_dept;

import entities.Cible;
import entities.Indicateur;
import entities.Periode;
import entities.Service;
import entities.Sousperiode;
import entities.Structure;
import entities.TypeSousPeriode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CibleFacadeLocal;
import sessions.CriterestructureFacadeLocal;
import sessions.IndicateurFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.SousperiodeFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypeSousPeriodeFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractCibleBonusRDeptCtrl {

    @EJB
    protected CibleFacadeLocal cibleFacadeLocal;
    protected Cible cible = new Cible();
    protected List<Cible> cibles = new ArrayList<>();
    protected List<Cible> listCibles = new ArrayList<>();

    @EJB
    protected IndicateurFacadeLocal indicateurFacadeLocal;
    protected Indicateur indicateur = new Indicateur();
    protected List<Indicateur> indicateurs = new ArrayList<>();

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
    protected List<Service> services = new ArrayList<>();
    protected List<Service> selectedServices = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;

    @EJB
    protected TypeSousPeriodeFacadeLocal typeSousPeriodeFacadeLocal;
    protected TypeSousPeriode typeSousPeriode = new TypeSousPeriode();
    protected List<TypeSousPeriode> typeSousPeriodes = new ArrayList<>();

    protected double totalCible;

    protected Routine routine = new Routine();
    protected String mode = "";

    protected boolean stateBtn = true;

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
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
        indicateurs = indicateurFacadeLocal.findAll();
        return indicateurs;
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
        return services;
    }

    public Sousperiode getSousperiode() {
        return sousperiode;
    }

    public void setSousperiode(Sousperiode sousperiode) {
        this.sousperiode = sousperiode;
    }

    public List<Sousperiode> getSousperiodes() {
        return sousperiodes;
    }

    public List<Service> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<Service> selectedServices) {
        this.selectedServices = selectedServices;
    }

    public Indicateur getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }

    public double getTotalCible() {
        return totalCible;
    }

    public void setTotalCible(double totalCible) {
        this.totalCible = totalCible;
    }

    public TypeSousPeriode getTypeSousPeriode() {
        return typeSousPeriode;
    }

    public void setTypeSousPeriode(TypeSousPeriode typeSousPeriode) {
        this.typeSousPeriode = typeSousPeriode;
    }

    public List<TypeSousPeriode> getTypeSousPeriodes() {
        return typeSousPeriodes;
    }

    public boolean isStateBtn() {
        return stateBtn;
    }

}
