package depense;

import entities.Depense;
import entities.Recette;
import entities.Sousrubriquedepense;
import java.io.Serializable;
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
        structure = SessionMBean.getStructure();
        periode = SessionMBean.getPeriode();
    }

    private void initDepense() {
        try {
            depenses.clear();
            sousrubriquedepenses.clear();
            selectedSousrubriquedepenses.clear();
            if (structure.getIdstructure() != null) {
                if (periode.getIdperiode() != null) {
                    if (sousperiode.getIdsousperiode() != null) {

                        sousrubriquedepenses = sousrubriquedepenseFacadeLocal.findAllEtatPrime(false);
                        sousrubriquedepenses.addAll(sousrubriquedepenseFacadeLocal.findAllEtatPrime(true));

                        depenses = depenseFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                        total = this.sommeDepense(depenses);
                        pourcentage = this.sommePourcentage(depenses);

                        List<Recette> listRecette = recetteFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                        montant = this.sommeRecette(listRecette);

                        if (!depenses.isEmpty()) {
                            for (Depense d : depenses) {
                                selectedSousrubriquedepenses.add(d.getIdsousrubriquedepense());
                            }
                        }
                    }
                }
            }
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

    public void updateData() {
        depenses.clear();
        if (structure.getIdstructure() != null) {
            if (periode.getIdperiode() != null) {
                if (sousperiode.getIdsousperiode() != null) {
                    depenses = depenseFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                    total = this.sommeDepense(depenses);

                    List<Recette> listRecette = recetteFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                    this.montant = this.sommeRecette(listRecette);

                    sousrubriquedepenses = sousrubriquedepenseFacadeLocal.findAllEtatPrime(false);
                    sousrubriquedepenses.addAll(sousrubriquedepenseFacadeLocal.findAllEtatPrime(true));
                }
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
                    Depense d = new Depense();
                    d.setIddepense(0l);
                    d.setIdperiode(periode);
                    d.setIdsousperiode(sousperiode);
                    d.setIdsousrubriquedepense(srd);
                    d.setMontant(0d);
                    d.setPourcentage(0d);
                    depenses.add(d);
                }
            }
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

    public void recherche() {
        try {
            depenses.clear();
            if (structure.getIdstructure() != null) {
                if (periode.getIdperiode() != null) {
                    if (sousperiode.getIdsousperiode() != null) {
                        depenses = depenseFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                        total = this.sommeDepense(depenses);
                        pourcentage = this.sommePourcentage(depenses);
                    }
                }
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
            modifier = detail = supprimer = true;
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
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
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
