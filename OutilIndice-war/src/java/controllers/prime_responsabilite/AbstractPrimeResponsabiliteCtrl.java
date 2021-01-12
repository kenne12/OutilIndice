/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_responsabilite;

import entities.Critereresponsabilite;
import entities.Responsabilite;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CritereresponsabiliteFacadeLocal;
import sessions.ResponsabiliteFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractPrimeResponsabiliteCtrl {

    @EJB
    protected CritereresponsabiliteFacadeLocal critereresponsabiliteFacadeLocal;
    protected Critereresponsabilite critereresponsabilite = new Critereresponsabilite();
    protected List<Critereresponsabilite> critereresponsabilites = new ArrayList<>();
    protected List<Critereresponsabilite> listCriteres = new ArrayList<>();

    @EJB
    protected ResponsabiliteFacadeLocal responsabiliteFacadeLocal;
    protected List<Responsabilite> responsabilites = new ArrayList<>();
    protected List<Responsabilite> selectedResponsabilites = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    protected Routine routine = new Routine();
    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
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

}
