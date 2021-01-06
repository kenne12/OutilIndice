package depense;

import entities.Depense;
import entities.Periode;
import entities.Recette;
import entities.Sousperiode;
import entities.Sousrubriquedepense;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.DepenseFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.RecetteFacadeLocal;
import sessions.SousperiodeFacadeLocal;
import sessions.SousrubriquedepenseFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.Routine;

public class AbstractDepenseController {

    @EJB
    protected DepenseFacadeLocal depenseFacadeLocal;
    protected Depense depense = new Depense();
    protected List<Depense> depenses = new ArrayList<>();

    @EJB
    protected SousrubriquedepenseFacadeLocal sousrubriquedepenseFacadeLocal;
    protected List<Sousrubriquedepense> sousrubriquedepenses = new ArrayList<>();
    protected List<Sousrubriquedepense> selectedSousrubriquedepenses = new ArrayList<>();

    @EJB
    protected RecetteFacadeLocal recetteFacadeLocal;

    protected List<Recette> recettes = new ArrayList<>();

    @EJB
    protected PeriodeFacadeLocal periodeFacadeLocal;
    protected Periode periode = new Periode();
    protected List<Periode> periodes = new ArrayList<>();

    @EJB
    protected SousperiodeFacadeLocal sousperiodeFacadeLocal;
    protected Sousperiode sousperiode = new Sousperiode();
    protected List<Sousperiode> sousperiodes = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected boolean calculAuto = true;
    protected double montant = 0;
    protected double total = 0;
    protected double pourcentage = 0;

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

    public List<Periode> getPeriodes() {
        periodes = periodeFacadeLocal.findAllRange();
        return periodes;
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

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public double getTotal() {
        return total;
    }

    public Depense getDepense() {
        return depense;
    }

    public void setDepense(Depense depense) {
        this.depense = depense;
    }

    public List<Depense> getDepenses() {
        return depenses;
    }

    public void setDepenses(List<Depense> depenses) {
        this.depenses = depenses;
    }

    public List<Sousrubriquedepense> getSousrubriquedepenses() {   
        return sousrubriquedepenses;
    }

    public List<Sousrubriquedepense> getSelectedSousrubriquedepenses() {
        return selectedSousrubriquedepenses;
    }

    public void setSelectedSousrubriquedepenses(List<Sousrubriquedepense> selectedSousrubriquedepenses) {
        this.selectedSousrubriquedepenses = selectedSousrubriquedepenses;
    }

}
