package controllers.rubrique_recette;

import entities.Rubriquerecette;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.MouchardFacadeLocal;
import sessions.RubriquerecetteFacadeLocal;
import utils.Routine;

public class AbstractRubriqueRecetteController {

    @EJB
    protected RubriquerecetteFacadeLocal rubriquerecetteFacadeLocal;
    protected Rubriquerecette rubriquerecette = new Rubriquerecette();
    protected List<Rubriquerecette> rubriquerecettes = new ArrayList<>();

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

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public Boolean getModifier() {
        return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        this.modifier = modifier;
    }

    public Boolean getConsulter() {
        return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        this.consulter = consulter;
    }

    public Boolean getImprimer() {

        return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    public Routine getRoutine() {
        return this.routine;
    }

    public String getMode() {
        return this.mode;
    }

    public Rubriquerecette getRubriquerecette() {
        return rubriquerecette;
    }

    public void setRubriquerecette(Rubriquerecette rubriquerecette) {
        this.rubriquerecette = rubriquerecette;
    }

    public List<Rubriquerecette> getRubriquerecettes() {
        rubriquerecettes = rubriquerecetteFacadeLocal.findAllRangeByCode();
        return rubriquerecettes;
    }

}
