/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_srd;

import entities.Sousrubriquedepense;
import entities.Typestructure;
import entities.TypeStructureSousRubriqueDepense;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.SousrubriquedepenseFacadeLocal;
import sessions.TypestructureFacadeLocal;
import sessions.TypeStructureSousRubriqueDepenseFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractTypestructureSrdCtrl {

    @EJB
    protected TypeStructureSousRubriqueDepenseFacadeLocal typeStructureSousRubriqueDepenseFacadeLocal;
    protected TypeStructureSousRubriqueDepense typeStructureSousRubriqueDepense = new TypeStructureSousRubriqueDepense();
    protected List<TypeStructureSousRubriqueDepense> typeStructureSousRubriqueDepenses = new ArrayList<>();

    @EJB
    protected TypestructureFacadeLocal typestructureFacadeLocal;
    protected Typestructure typestructure = new Typestructure();
    protected List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    protected SousrubriquedepenseFacadeLocal sousrubriquedepenseFacadeLocal;
    protected Sousrubriquedepense service = new Sousrubriquedepense();
    protected List<Sousrubriquedepense> sousrubriquedepenses = new ArrayList<>();
    protected List<Sousrubriquedepense> selectedSousrubriquedepenses = new ArrayList<>();

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

    public List<TypeStructureSousRubriqueDepense> getTypeStructureSousRubriqueDepenses() {
        return typeStructureSousRubriqueDepenses;
    }

    public Sousrubriquedepense getService() {
        return service;
    }

    public void setService(Sousrubriquedepense service) {
        this.service = service;
    }

    public List<Sousrubriquedepense> getSousrubriquedepenses() {
        return sousrubriquedepenses;
    }

    public void setSousrubriquedepenses(List<Sousrubriquedepense> sousrubriquedepenses) {
        this.sousrubriquedepenses = sousrubriquedepenses;
    }

    public List<Sousrubriquedepense> getSelectedSousrubriquedepenses() {
        return selectedSousrubriquedepenses;
    }

    public void setSelectedSousrubriquedepenses(List<Sousrubriquedepense> selectedSousrubriquedepenses) {
        this.selectedSousrubriquedepenses = selectedSousrubriquedepenses;
    }

}
