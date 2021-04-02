/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.rapport_costing;

import entities.Criterestructure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.CriterestructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractRapportCostingCtrl {

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;
    protected List<Criterestructure> criterestructures = new ArrayList<>();

    protected Routine routine = new Routine();
    protected double poids = 0;
    protected double pointMax = 0;
    protected double pointObtenu;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public List<Criterestructure> getCriterestructures() {
        return criterestructures;
    }

    public void setCriterestructures(List<Criterestructure> criterestructures) {
        this.criterestructures = criterestructures;
    }

    public double getPointMax() {
        return pointMax;
    }

    public double getPoids() {
        return poids;
    }

    public double getPointObtenu() {
        return pointObtenu;
    }

}
