package depense;

import entities.Depense;
import entities.Periode;
import entities.Recette;
import entities.Sousperiode;
import entities.Sousrubriquedepense;
import entities.Structure;
import entities.TypeSousPeriode;
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
import sessions.TypeSousPeriodeFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

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
    protected final Periode periode = SessionMBean.getPeriode();

    @EJB
    protected SousperiodeFacadeLocal sousperiodeFacadeLocal;
    protected Sousperiode sousperiode = new Sousperiode();
    protected List<Sousperiode> sousperiodes = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = SessionMBean.getStructure();

    @EJB
    protected TypeSousPeriodeFacadeLocal typeSousPeriodeFacadeLocal;
    protected TypeSousPeriode typeSousPeriode = new TypeSousPeriode();
    protected List<TypeSousPeriode> typeSousPeriodes = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected boolean calculAuto = true;
    protected double montant = 0;
    protected double total = 0;
    protected double pourcentage = 0;

    protected String mode = "";

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

    public Periode getPeriode() {
        return periode;
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

    public List<Sousrubriquedepense> getSousrubriquedepenses() {
        return sousrubriquedepenses;
    }

    public List<Sousrubriquedepense> getSelectedSousrubriquedepenses() {
        return selectedSousrubriquedepenses;
    }

    public void setSelectedSousrubriquedepenses(List<Sousrubriquedepense> selectedSousrubriquedepenses) {
        this.selectedSousrubriquedepenses = selectedSousrubriquedepenses;
    }

    public TypeSousPeriode getTypeSousPeriode() {
        return typeSousPeriode;
    }

    public void setTypeSousPeriode(TypeSousPeriode typeSousPeriode) {
        this.typeSousPeriode = typeSousPeriode;
    }

    public List<TypeSousPeriode> getTypeSousPeriodes() {
        return typeSousPeriodes;
    }

}
