/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.effectif_responsabilite;

import entities.Responsabilite;
import entities.EffectifResponsabilite;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.ResponsabiliteFacadeLocal;
import sessions.EffectifResponsabiliteFacadeLocal;
import sessions.PersonnelFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractEffectifResponsabiliteCtrl {

    @EJB
    protected EffectifResponsabiliteFacadeLocal effectifResponsabiliteFacadeLocal;
    protected EffectifResponsabilite effectifResponsabilite = new EffectifResponsabilite();
    protected List<EffectifResponsabilite> effectifResponsabilites = new ArrayList<>();

    @EJB
    protected ResponsabiliteFacadeLocal responsabiliteFacadeLocal;
    protected Responsabilite responsabilite = new Responsabilite();
    protected List<Responsabilite> responsabilites = new ArrayList<>();
    protected List<Responsabilite> selectedResponsabilites = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = SessionMBean.getStructure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;

    protected Routine routine = new Routine();
    protected double effectif = 0;

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Responsabilite getResponsabilite() {
        return responsabilite;
    }

    public void setResponsabilite(Responsabilite responsabilite) {
        this.responsabilite = responsabilite;
    }

    public List<Responsabilite> getResponsabilites() {
        return responsabilites;
    }

    public List<EffectifResponsabilite> getEffectifResponsabilites() {
        return effectifResponsabilites;
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

    public List<Responsabilite> getSelectedResponsabilites() {
        return selectedResponsabilites;
    }

    public void setSelectedResponsabilites(List<Responsabilite> selectedResponsabilites) {
        this.selectedResponsabilites = selectedResponsabilites;
    }

    public double getEffectif() {
        return effectif;
    }

    public void setEffectif(double effectif) {
        this.effectif = effectif;
    }

}
