/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_responsabilite;

import entities.Critereresponsabilite;
import entities.Criterestructure;
import entities.EffectifResponsabilite;
import entities.Responsabilite;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CritereresponsabiliteFacadeLocal;
import sessions.CriterestructureFacadeLocal;
import sessions.EffectifResponsabiliteFacadeLocal;
import sessions.ResponsabiliteFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;
import utils.Utilitaires;

/**
 *
 * @author USER
 */
public class AbstractPrimeResponsabiliteCtrl {

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;
    protected Criterestructure criterestructure = Utilitaires.findCritereSInSession(1);
    protected List<Criterestructure> criterestructures = SessionMBean.getCritereStructures();

    @EJB
    protected CritereresponsabiliteFacadeLocal critereresponsabiliteFacadeLocal;
    protected Critereresponsabilite critereresponsabilite = new Critereresponsabilite();
    protected List<Critereresponsabilite> critereresponsabilites = new ArrayList<>();
    protected List<Critereresponsabilite> listCriteres = new ArrayList<>();

    @EJB
    protected EffectifResponsabiliteFacadeLocal effectifResponsabiliteFacadeLocal;
    protected List<EffectifResponsabilite> effectifResponsabilites = new ArrayList<>();

    @EJB
    protected ResponsabiliteFacadeLocal responsabiliteFacadeLocal;
    protected List<Responsabilite> responsabilites = new ArrayList<>();
    protected List<Responsabilite> selectedResponsabilites = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected final Structure structure = SessionMBean.getStructure();

    protected Routine routine = new Routine();
    protected String mode = "";
    protected double totalPointMaxCritere;
    protected double totalPointSaisi;
    protected int totalEffectifs;

    protected int indexCritere;

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Structure getStructure() {
        return structure;
    }

    public List<Responsabilite> getResponsabilites() {
        return responsabilites;
    }

    public List<Responsabilite> getSelectedResponsabilites() {
        return selectedResponsabilites;
    }

    public void setSelectedResponsabilites(List<Responsabilite> selectedResponsabilites) {
        this.selectedResponsabilites = selectedResponsabilites;
    }

    public List<Critereresponsabilite> getCritereresponsabilites() {
        return critereresponsabilites;
    }

    public List<Critereresponsabilite> getListCriteres() {
        return listCriteres;
    }

    public Critereresponsabilite getCritereresponsabilite() {
        return critereresponsabilite;
    }

    public void setCritereresponsabilite(Critereresponsabilite critereresponsabilite) {
        this.critereresponsabilite = critereresponsabilite;
    }

    public List<EffectifResponsabilite> getEffectifResponsabilites() {
        return effectifResponsabilites;
    }

    public double getTotalPointMaxCritere() {
        return totalPointMaxCritere;
    }

    public double getTotalPointSaisi() {
        return totalPointSaisi;
    }

    public int getTotalEffectifs() {
        return totalEffectifs;
    }

    public Criterestructure getCriterestructure() {
        return criterestructure;
    }

}
