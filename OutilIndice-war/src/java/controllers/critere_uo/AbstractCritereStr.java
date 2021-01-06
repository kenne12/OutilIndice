/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.critere_uo;

import entities.Critere;
import entities.Criterestructure;
import entities.Souscritere;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.CritereFacadeLocal;
import sessions.CriterestructureFacadeLocal;
import sessions.SouscritereFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractCritereStr {

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;
    protected Criterestructure criterestructure = new Criterestructure();
    protected List<Criterestructure> criterestructures = new ArrayList<>();

    @EJB
    protected SouscritereFacadeLocal souscritereFacadeLocal;
    protected Souscritere souscritere = new Souscritere();
    protected List<Souscritere> souscriteres = new ArrayList<>();

    @EJB
    protected CritereFacadeLocal critereFacadeLocal;
    protected Critere critere = new Critere();
    protected List<Critere> criteres = new ArrayList<>();
    protected List<Critere> selectedCriteres = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    protected Routine routine = new Routine();
    protected static final double scoreMax = 100;
    protected double score = 0;
    protected double pointMax = 0;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Critere getCritere() {
        return critere;
    }

    public void setCritere(Critere critere) {
        this.critere = critere;
    }

    public List<Critere> getCriteres() {
        return criteres;
    }

    public Souscritere getSouscritere() {
        return souscritere;
    }

    public void setSouscritere(Souscritere souscritere) {
        this.souscritere = souscritere;
    }

    public List<Souscritere> getSouscriteres() {
        souscriteres = souscritereFacadeLocal.findAllRangeByCode();
        return souscriteres;
    }

    public List<Criterestructure> getCriterestructures() {
        return criterestructures;
    }

    public void setCriterestructures(List<Criterestructure> criterestructures) {
        this.criterestructures = criterestructures;
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

    public void setStructures(List<Structure> structures) {
        this.structures = structures;
    }

    public List<Critere> getSelectedCriteres() {
        return selectedCriteres;
    }

    public void setSelectedCriteres(List<Critere> selectedCriteres) {
        this.selectedCriteres = selectedCriteres;
    }

    public double getScore() {
        return score;
    }

    public double getPointMax() {
        return pointMax;
    }

}
