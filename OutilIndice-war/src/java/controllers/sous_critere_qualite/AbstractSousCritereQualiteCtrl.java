/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.sous_critere_qualite;

import entities.Souscritere;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.ElementReponseFacadeLocal;
import sessions.SouscritereFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractSousCritereQualiteCtrl {

    @EJB
    protected SouscritereFacadeLocal souscritereFacadeLocal;
    protected Souscritere souscritere = new Souscritere();
    protected List<Souscritere> souscriteres = new ArrayList<>();

    @EJB
    protected ElementReponseFacadeLocal elementReponseFacadeLocal;

    protected Routine routine = new Routine();

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
        souscriteres = souscritereFacadeLocal.findByIdCritere(5);
        return souscriteres;
    }

}
