/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.reponse;

import entities.Elementreponse;
import entities.Souscritere;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessions.ElementReponseFacadeLocal;
import sessions.SouscritereFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractElementResponse {

    @EJB
    protected ElementReponseFacadeLocal elementReponseFacadeLocal;
    protected Elementreponse elementReponse = new Elementreponse();
    protected List<Elementreponse> elementReponses = new ArrayList<>();

    @EJB
    protected SouscritereFacadeLocal souscritereFacadeLocal;
    protected Souscritere souscritere = new Souscritere();
    protected List<Souscritere> souscriteres = new ArrayList<>();

    protected Routine routine = new Routine();

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    protected String mode = "";

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
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

    public Elementreponse getElementReponse() {
        return elementReponse;
    }

    public void setElementReponse(Elementreponse elementReponse) {
        this.elementReponse = elementReponse;
    }

    public List<Elementreponse> getElementReponses() {
        elementReponses = elementReponseFacadeLocal.findAllRange();
        return elementReponses;
    }

}
