/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.responsabilite;

import entities.Responsabilite;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.MouchardFacadeLocal;
import sessions.ResponsabiliteFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractResponsabiliteController {

    @EJB
    protected ResponsabiliteFacadeLocal responsabiliteFacadeLocal;
    protected Responsabilite responsabilite = new Responsabilite();
    protected List<Responsabilite> responsabilites = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected String mode = "";

    public Routine getRoutine() {
        return this.routine;
    }

    public String getMode() {
        return this.mode;
    }

    public Responsabilite getResponsabilite() {
        return responsabilite;
    }

    public void setResponsabilite(Responsabilite responsabilite) {
        this.responsabilite = responsabilite;
    }

    public List<Responsabilite> getResponsabilites() {
        responsabilites = responsabiliteFacadeLocal.findAllOrderByCode();
        return responsabilites;
    }

}
