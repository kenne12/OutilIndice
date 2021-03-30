/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_srr;

import entities.Sousrubriquerecette;
import entities.TypeStructureSousRubriqueRecette;
import entities.Typestructure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.SousrubriquerecetteFacadeLocal;
import sessions.TypeStructureSousRubriqueRecetteFacadeLocal;
import sessions.TypestructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractTypestructureSrrCtrl {

    @EJB
    protected TypeStructureSousRubriqueRecetteFacadeLocal typeStructureSousRubriqueRecetteFacadeLocal;
    protected TypeStructureSousRubriqueRecette typeStructureSousRubriqueRecette = new TypeStructureSousRubriqueRecette();
    protected List<TypeStructureSousRubriqueRecette> typeStructureSousRubriqueRecettes = new ArrayList<>();

    @EJB
    protected TypestructureFacadeLocal typestructureFacadeLocal;
    protected Typestructure typestructure = new Typestructure();
    protected List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    protected SousrubriquerecetteFacadeLocal sousrubriquedepenseFacadeLocal;
    protected Sousrubriquerecette sousrubriquerecette = new Sousrubriquerecette();
    protected List<Sousrubriquerecette> sousrubriquedepenses = new ArrayList<>();
    protected List<Sousrubriquerecette> selectedSousrubriquerecettes = new ArrayList<>();

    protected Routine routine = new Routine();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
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

    public List<TypeStructureSousRubriqueRecette> getTypeStructureSousRubriqueRecettes() {
        return typeStructureSousRubriqueRecettes;
    }

    public Sousrubriquerecette getService() {
        return sousrubriquerecette;
    }

    public void setService(Sousrubriquerecette sousrubriquerecette) {
        this.sousrubriquerecette = sousrubriquerecette;
    }

    public List<Sousrubriquerecette> getSousrubriquerecettes() {
        return sousrubriquedepenses;
    }

    public void setSousrubriquerecettes(List<Sousrubriquerecette> sousrubriquedepenses) {
        this.sousrubriquedepenses = sousrubriquedepenses;
    }

    public List<Sousrubriquerecette> getSelectedSousrubriquerecettes() {
        return selectedSousrubriquerecettes;
    }

    public void setSelectedSousrubriquerecettes(List<Sousrubriquerecette> selectedSousrubriquerecettes) {
        this.selectedSousrubriquerecettes = selectedSousrubriquerecettes;
    }

}
