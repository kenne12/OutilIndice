package depense;

import entities.Depense;
import entities.Recette;
import entities.Sousperiode;
import entities.Sousrubriquedepense;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class DepenseController extends AbstractDepenseController implements Serializable {

    @PostConstruct
    private void init() {
        typeSousPeriodes = SessionMBean.getTypeSousPeriodes();
    }

    private void initDepense() {
        try {
            depenses.clear();
            sousrubriquedepenses.clear();
            selectedSousrubriquedepenses.clear();

            if (sousperiode.getIdsousperiode() != null) {
                List<Recette> listRecette = recetteFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                montant = this.sommeRecette(listRecette);
                if (!listRecette.isEmpty()) {
                    sousrubriquedepenses = SessionMBean.getSousRubriqueDepenses();

                    depenses = depenseFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                    total = this.sommeDepense(depenses);
                    pourcentage = this.sommePourcentage(depenses);
                    if (!depenses.isEmpty()) {
                        for (Depense d : depenses) {
                            selectedSousrubriquedepenses.add(d.getIdsousrubriquedepense());
                        }
                        sousrubriquedepenses.removeAll(selectedSousrubriquedepenses);
                    }
                }
            }
            this.sommeData(depenses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareCreate() {
        try {
            if (!Utilitaires.isAccess(2L)) {
                signalError("acces_refuse");
                return;
            }
            this.initDepense();
            RequestContext.getCurrentInstance().execute("PF('DepenseCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void updateSousPeriode(String option) {
        sousperiodes.clear();
        sousperiode = new Sousperiode(0);
        montant = 0;

        if (option.equals("2")) {
            sousrubriquedepenses.clear();
            selectedSousrubriquedepenses.clear();
            recettes.clear();
            depenses.clear();
        }

        if (typeSousPeriode.getIdTypeSousperiode() != 0) {
            sousperiodes = sousperiodeFacadeLocal.findIdTypeSousPeriode(typeSousPeriode.getIdTypeSousperiode());
        }
    }

    public void updateData() {
        depenses.clear();
        selectedSousrubriquedepenses.clear();
        sousrubriquedepenses.clear();
        montant = 0;
        if (sousperiode.getIdsousperiode() != null) {
            List<Recette> listRecettes = recetteFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
            if (!listRecettes.isEmpty()) {
                System.err.println("recette saisiesaisie");
                this.montant = this.sommeRecette(listRecettes);
                sousrubriquedepenses = SessionMBean.getSousRubriqueDepenses();

                if (sousrubriquedepenses.isEmpty()) {
                    System.err.println("sousrubrique dep empty");
                }

                depenses = depenseFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                if (!depenses.isEmpty()) {
                    this.sommeData(depenses);
                    System.err.println("depenses saisies");

                    for (Depense d : depenses) {
                        selectedSousrubriquedepenses.add(d.getIdsousrubriquedepense());
                    }
                    sousrubriquedepenses.removeAll(selectedSousrubriquedepenses);
                } else {
                    Sousrubriquedepense prime = new Sousrubriquedepense();
                    List<Sousrubriquedepense> listAll = sousrubriquedepenses;
                    List<Sousrubriquedepense> listAll2 = new ArrayList<>();
                    for (Sousrubriquedepense srd : listAll) {
                        if (srd.getSpecial()) {
                            prime = srd;
                        } else {
                            listAll2.add(srd);
                        }
                    }
                    listAll2.add(prime);
                    sousrubriquedepenses.clear();
                    sousrubriquedepenses.addAll(listAll2);
                    selectedSousrubriquedepenses.addAll(sousrubriquedepenses);
                }
            } else {
                JsfUtil.addWarningMessage("Recettes non saisies");
            }
        }
    }

    public boolean activeControle(String option, Depense d) {
        if (d.getIdsousrubriquedepense().getSpecial()) {
            return true;
        } else {
            if (option.equals("montant")) {
                if (calculAuto) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (calculAuto) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private void sommeData(List<Depense> list) {
        this.total = 0;
        this.pourcentage = 0;
        if (list.isEmpty()) {
            return;
        }
        for (Depense d : list) {
            try {
                if (d.getMontant() != null) {
                    total += d.getMontant();
                }
            } catch (Exception e) {
            }

            try {
                if (d.getPourcentage() != null) {
                    pourcentage += d.getPourcentage();
                }
            } catch (Exception e) {
            }
        }
    }

    private double sommeDepense(List<Depense> list) {
        if (list.isEmpty()) {
            return 0;
        }
        double somme = 0;
        for (Depense d : list) {
            try {
                if (d.getMontant() != null) {
                    somme += d.getMontant();
                }
            } catch (Exception e) {
            }
        }
        return somme;
    }

    private double sommePourcentage(List<Depense> list) {
        if (list.isEmpty()) {
            return 0;
        }
        double somme = 0;
        for (Depense r : list) {
            try {
                if (r.getPourcentage() != null) {
                    somme += r.getPourcentage();
                }
            } catch (Exception e) {
            }
        }
        return somme;
    }

    private double sommeRecette(List<Recette> list) {
        if (list.isEmpty()) {
            return 0;
        }
        double somme = 0;
        for (Recette r : list) {
            try {
                if (r.getMontant() != null) {
                    somme += r.getMontant();
                }
            } catch (Exception e) {
            }
        }
        return somme;
    }

    public void updateMode() {
        //montant = this.sommeDepense(depenses);
        //pourcentage = sommePourcentage(depenses);
    }

    public void updateAmount() {
        int i = 0;
        for (Depense d : depenses) {
            if (calculAuto) {
                d.setPourcentage(0d);
                if (d.getMontant() != null && d.getMontant() > 0) {
                    d.setPourcentage((d.getMontant() / montant) * 100);
                }
                depenses.set(i, d);
            } else {
                d.setMontant(0d);
                if (d.getPourcentage() != null && d.getPourcentage() > 0) {
                    d.setMontant((montant * d.getPourcentage()) / 100);
                }
                depenses.set(i, d);
            }
            i++;
        }
        this.updatePrime();
    }

    private void updatePrime() {
        double somme = 0;
        for (Depense d : depenses) {
            if (!d.getIdsousrubriquedepense().getSpecial()) {
                somme += d.getMontant();
            }
        }
        double reste = this.montant - somme;
        depenses.get(depenses.size() - 1).setMontant(reste);
        depenses.get(depenses.size() - 1).setPourcentage((reste / this.montant) * 100);

        total = this.sommeDepense(depenses);
        this.updatePourcentage();
        pourcentage = sommePourcentage(depenses);
    }

    private void updatePourcentage() {
        int i = 0;
        for (Depense d : depenses) {
            d.setPourcentage(0d);
            if (d.getMontant() != null && d.getMontant() > 0) {
                d.setPourcentage((d.getMontant() / this.montant) * 100);
            }
            depenses.set(i, d);
            i++;
        }
    }

    public void addSubRubricToList() {
        try {
            for (Sousrubriquedepense srd : selectedSousrubriquedepenses) {
                if (!checkSubRubricInList(srd, depenses)) {
                    Depense d = new Depense(0l);
                    d.setIdperiode(periode);
                    d.setIdsousperiode(sousperiode);
                    d.setIdsousrubriquedepense(srd);
                    d.setMontant(0d);
                    d.setPourcentage(0d);
                    depenses.add(d);
                }
            }
            sousrubriquedepenses.removeAll(selectedSousrubriquedepenses);
            selectedSousrubriquedepenses.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkSubRubricInList(Sousrubriquedepense ssr, List<Depense> list) {
        boolean result = false;
        for (Depense r : list) {
            if (r.getIdsousrubriquedepense().equals(ssr)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeSubRubric(int index, Depense item) {
        Depense dTemp = depenses.get(index);

        if (dTemp.getIddepense() != 0) {
            depenses.remove(index);
            sousrubriquedepenses.add(item.getIdsousrubriquedepense());
        } else {
            depenses.remove(index);
            sousrubriquedepenses.add(dTemp.getIdsousrubriquedepense());
        }
    }

    public void recherche() {
        try {
            depenses.clear();
            if (sousperiode.getIdsousperiode() != null) {
                depenses = depenseFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                this.sommeData(depenses);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (total != montant) {
                signalError("notification.veuillez_equilibre_les_montant");
                return;
            }

            for (Depense d : depenses) {
                if (d.getIddepense() == 0) {
                    d.setIddepense(depenseFacadeLocal.nextVal());
                    d.setIdperiode(periode);
                    d.setIdsousperiode(sousperiode);
                    d.setIdstructure(structure);
                    depenseFacadeLocal.create(d);
                } else {
                    depenseFacadeLocal.edit(d);
                }
            }
            depenses = depenseFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
            Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement des depense de la structure : " + this.structure.getNom(), SessionMBean.getUserAccount());
            RequestContext.getCurrentInstance().execute("PF('DepenseCreerDialog').hide()");
            signalSuccess();
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete() {
        try {
            if (structure != null) {
                if (!Utilitaires.isAccess(4L)) {
                    signalError("acces_refuse");
                    return;
                }
                this.structureFacadeLocal.remove(this.structure);
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public String convertMoney() {
        return JsfUtil.formaterStringMoney((int) total);
    }

    public double arrondi(double nombre) {
        return JsfUtil.arrondiNDecimales(nombre, 2);
    }

    public void signalError(String chaine) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("information", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(chaine));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void signalSuccess() {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void signalException(Exception e) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.catchException(e, this.routine.localizeMessage("erreur_execution"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}
