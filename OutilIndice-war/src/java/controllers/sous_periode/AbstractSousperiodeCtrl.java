/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.sous_periode;

import entities.Sousperiode;
import entities.TypeSousPeriode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.MouchardFacadeLocal;
import sessions.SousperiodeFacadeLocal;
import sessions.TypeSousPeriodeFacadeLocal;

import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractSousperiodeCtrl {

    @EJB
    protected SousperiodeFacadeLocal sousperiodeFacadeLocal;
    protected Sousperiode sousperiode = new Sousperiode();
    protected List<Sousperiode> sousperiodes = new ArrayList<>();

    @EJB
    protected TypeSousPeriodeFacadeLocal typeSousPeriodeFacadeLocal;
    protected List<TypeSousPeriode> typeSousPeriodes = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected Boolean detail = true;
    protected Boolean modifier = true;
    protected Boolean consulter = true;
    protected Boolean imprimer = true;
    protected Boolean supprimer = true;

    protected String mode = "";

    public Boolean getDetail() {
        return this.detail;
    }

    public Boolean getModifier() {
        return this.modifier;
    }

    public Boolean getConsulter() {
        return this.consulter;
    }

    public Boolean getImprimer() {
        return this.imprimer;
    }

    public Boolean getSupprimer() {
        return this.supprimer;
    }

    public Routine getRoutine() {
        return this.routine;
    }

    public String getMode() {
        return this.mode;
    }

    public Sousperiode getSousperiode() {
        return sousperiode;
    }

    public void setSousperiode(Sousperiode sousperiode) {
        this.sousperiode = sousperiode;
    }

    public List<Sousperiode> getSousperiodes() {
        sousperiodes = sousperiodeFacadeLocal.findAllRangeCode();
        return sousperiodes;
    }

    public List<TypeSousPeriode> getTypeSousPeriodes() {
        typeSousPeriodes = typeSousPeriodeFacadeLocal.findAll();
        return typeSousPeriodes;
    }

}
