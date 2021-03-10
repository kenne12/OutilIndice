package controllers.recette;

import entities.Periode;
import entities.Recette;
import entities.Sousperiode;
import entities.Sousrubriquerecette;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.MouchardFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.RecetteFacadeLocal;
import sessions.SousperiodeFacadeLocal;
import sessions.SousrubriquerecetteFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractRecetteController {

    @EJB
    protected RecetteFacadeLocal recetteFacadeLocal;
    protected Recette recette = new Recette();
    protected List<Recette> recettes = new ArrayList<>();

    @EJB
    protected PeriodeFacadeLocal periodeFacadeLocal;
    protected Periode periode = SessionMBean.getPeriode();

    @EJB
    protected SousperiodeFacadeLocal sousperiodeFacadeLocal;
    protected Sousperiode sousperiode = new Sousperiode();
    protected List<Sousperiode> sousperiodes = new ArrayList<>();

    @EJB
    protected SousrubriquerecetteFacadeLocal sousrubriquerecetteFacadeLocal;
    protected List<Sousrubriquerecette> sousrubriquerecettes = new ArrayList<>();
    protected List<Sousrubriquerecette> selectedSousrubriquerecettes = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = SessionMBean.getStructure();
    protected List<Structure> structures = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected boolean calculAuto = true;
    protected double montant = 0;
    protected double total;
    protected double pourcentage;

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
        this.imprimer = recettes.isEmpty();
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

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Routine getRoutine() {
        return this.routine;
    }

    public String getMode() {
        return this.mode;
    }

    public List<Structure> getStructures() {
        structures = structureFacadeLocal.findAll();
        return structures;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public List<Sousrubriquerecette> getSousrubriquerecettes() {

        return sousrubriquerecettes;
    }

    public List<Sousrubriquerecette> getSelectedSousrubriquerecettes() {
        return selectedSousrubriquerecettes;
    }

    public void setSelectedSousrubriquerecettes(List<Sousrubriquerecette> selectedSousrubriquerecettes) {
        this.selectedSousrubriquerecettes = selectedSousrubriquerecettes;
    }

    public List<Recette> getRecettes() {
        return recettes;
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

    public boolean isCalculAuto() {
        return calculAuto;
    }

    public void setCalculAuto(boolean calculAuto) {
        this.calculAuto = calculAuto;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public double getTotal() {
        return total;
    }

}
