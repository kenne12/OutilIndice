/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_responsabilite;

import entities.Responsabilite;
import entities.Typestructure;
import entities.TypestructureResponsabilite;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.ResponsabiliteFacadeLocal;
import sessions.TypestructureFacadeLocal;
import sessions.TypestructureResponsabiliteFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractTypestructureResponsabiliteCtrl {

    @EJB
    protected TypestructureResponsabiliteFacadeLocal typestructureResponsabiliteFacadeLocal;
    protected TypestructureResponsabilite typestructureResponsabilite = new TypestructureResponsabilite();
    protected List<TypestructureResponsabilite> typestructureResponsabilites = new ArrayList<>();

    @EJB
    protected TypestructureFacadeLocal typestructureFacadeLocal;
    protected Typestructure typestructure = new Typestructure();
    protected List<Typestructure> typestructures = new ArrayList<>();

    @EJB
    protected ResponsabiliteFacadeLocal responsabiliteFacadeLocal;
    protected Responsabilite responsabilite = new Responsabilite();
    protected List<Responsabilite> responsabilites = new ArrayList<>();
    protected List<Responsabilite> selectedResponsabilites = new ArrayList<>();

    protected Routine routine = new Routine();

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

    public List<TypestructureResponsabilite> getTypestructureResponsabilites() {
        return typestructureResponsabilites;
    }

    public List<Responsabilite> getSelectedResponsabilites() {
        return selectedResponsabilites;
    }

    public void setSelectedResponsabilites(List<Responsabilite> selectedResponsabilites) {
        this.selectedResponsabilites = selectedResponsabilites;
    }
}
