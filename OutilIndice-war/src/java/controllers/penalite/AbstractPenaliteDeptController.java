/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.penalite;

import entities.Penalite;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.PenaliteFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractPenaliteDeptController {

    @EJB
    protected PenaliteFacadeLocal penaliteFacadeLocal;
    protected Penalite penalite = new Penalite();
    protected List<Penalite> penalites = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;

    protected String mode = "";
    protected boolean detail = true;

    public boolean isDetail() {
        return detail;
    }

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Penalite getPenalite() {
        return penalite;
    }

    public void setPenalite(Penalite penalite) {
        this.penalite = penalite;
    }

    public List<Penalite> getPenalites() {
        penalites = penaliteFacadeLocal.findAllService();
        return penalites;
    }

}
