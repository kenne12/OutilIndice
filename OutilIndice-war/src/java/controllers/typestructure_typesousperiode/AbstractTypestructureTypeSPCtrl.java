/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_typesousperiode;

import entities.TypeSousPeriode;
import entities.Typestructure;
import entities.TypestructureTypeSousperiode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.TypeSousPeriodeFacadeLocal;
import sessions.TypestructureFacadeLocal;
import sessions.TypestructureTypeSousperiodeFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractTypestructureTypeSPCtrl {

    @EJB
    protected TypestructureTypeSousperiodeFacadeLocal typestructureTypeSousperiodeFacadeLocal;
    protected TypestructureTypeSousperiode typestructureTypeSousperiode = new TypestructureTypeSousperiode();
    protected List<TypestructureTypeSousperiode> typestructureTypeSousperiodes = new ArrayList<>();

    @EJB
    protected TypestructureFacadeLocal typestructureFacadeLocal;
    protected Typestructure typestructure = new Typestructure();
    protected List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    protected TypeSousPeriodeFacadeLocal typeSousPeriodeFacadeLocal;
    protected TypeSousPeriode typeSousPeriode = new TypeSousPeriode();
    protected List<TypeSousPeriode> typeSousPeriodes = new ArrayList<>();
    protected List<TypeSousPeriode> selectedTypeSousPeriodes = new ArrayList<>();

    protected Routine routine = new Routine();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
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

    public void setTypeSousPeriodes(List<TypeSousPeriode> typeSousPeriodes) {
        this.typeSousPeriodes = typeSousPeriodes;
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

    public List<TypestructureTypeSousperiode> getTypestructureTypeSousperiodes() {
        return typestructureTypeSousperiodes;
    }

    public List<TypeSousPeriode> getSelectedTypeSousPeriodes() {
        return selectedTypeSousPeriodes;
    }

    public void setSelectedTypeSousPeriodes(List<TypeSousPeriode> selectedTypeSousPeriodes) {
        this.selectedTypeSousPeriodes = selectedTypeSousPeriodes;
    }

}
