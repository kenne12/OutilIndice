package controllers.recette;

import entities.Recette;
import entities.Sousrubriquerecette;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class RecetteController extends AbstractRecetteController implements Serializable {

    @PostConstruct
    private void init() {

    }

    private void initRecette() {
        try {
            recettes.clear();
            selectedSousrubriquerecettes.clear();
            if (sousperiode.getIdsousperiode() != null) {
                recettes = recetteFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                this.sommeData(recettes);
                total = montant;

                if (!recettes.isEmpty()) {
                    for (Recette r : recettes) {
                        selectedSousrubriquerecettes.add(r.getIdsousrubriquerecette());
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
            this.initRecette();
            RequestContext.getCurrentInstance().execute("PF('RecetteCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void updateData() {
        recettes.clear();
        selectedSousrubriquerecettes.clear();

        if (sousperiode.getIdsousperiode() != null) {
            sousrubriquerecettes.clear();
            sousrubriquerecettes = sousrubriquerecetteFacadeLocal.findAllRangeByCode();

            recettes = recetteFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());

            if (!recettes.isEmpty()) {
                for (Recette r : recettes) {
                    selectedSousrubriquerecettes.add(r.getIdsousrubriquerecette());
                }
                sousrubriquerecettes.removeAll(selectedSousrubriquerecettes);
            }
        }
        sommeData(recettes);
    }

    private void sommeData(List<Recette> list) {
        this.total = 0;
        this.pourcentage = 0;
        if (list.isEmpty()) {
            return;
        }

        for (Recette r : list) {
            try {
                if (r.getMontant() != null) {
                    total += r.getMontant();
                }
            } catch (Exception e) {
            }

            try {
                if (r.getPourcentage() != null) {
                    pourcentage += r.getPourcentage();
                }
            } catch (Exception e) {
            }
        }
    }

    public void updateMode() {
        this.sommeData(recettes);
    }

    public void updateAmount() {
        int i = 0;
        this.sommeData(recettes);
        for (Recette r : recettes) {
            if (calculAuto) {
                r.setPourcentage(0d);
                if (r.getMontant() != null && r.getMontant() > 0) {
                    r.setPourcentage((r.getMontant() / total) * 100);
                }
                recettes.set(i, r);
            } else {
                if (r.getPourcentage() != null && r.getPourcentage() > 0) {
                    r.setMontant((montant * r.getPourcentage()) / 100);
                }
                recettes.set(i, r);
            }
            i++;
        }
        this.sommeData(recettes);
    }

    public void addSubRubricToList() {
        try {
            List<Sousrubriquerecette> list = new ArrayList<>();
            for (Sousrubriquerecette srr : selectedSousrubriquerecettes) {
                if (!checkSubRubricInList(srr, recettes)) {
                    Recette r = new Recette();
                    r.setIdrecette(0l);
                    r.setIdperiode(periode);
                    r.setIdsousperiode(sousperiode);
                    r.setIdsousrubriquerecette(srr);
                    r.setMontant(0d);
                    r.setPourcentage(0d);
                    recettes.add(r);
                    list.add(srr);
                }
            }
            if (!list.isEmpty()) {
                sousrubriquerecettes.removeAll(list);
                selectedSousrubriquerecettes.removeAll(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeSubRubric(Recette item) {
        if (item.getIdrecette() != 0) {
            recettes.remove(item);
            sousrubriquerecettes.add(item.getIdsousrubriquerecette());
        } else {
            int i = 0;
            for (Recette r : this.recettes) {
                if (r.getIdsousrubriquerecette().equals(item.getIdsousrubriquerecette())) {
                    break;
                }
                i++;
            }
            sousrubriquerecettes.add(this.recettes.get(i).getIdsousrubriquerecette());
            recettes.remove(i);
        }
    }

    private boolean checkSubRubricInList(Sousrubriquerecette ssr, List<Recette> list) {
        boolean result = false;
        for (Recette r : list) {
            if (r.getIdsousrubriquerecette().equals(ssr)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void recherche() {
        try {
            recettes.clear();
            if (sousperiode.getIdsousperiode() != null) {
                recettes = recetteFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
                this.sommeData(recettes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            this.sommeData(recettes);
            int res = (int) pourcentage;
            if (res < 99 || res > 101) {
                signalError("notification.veuillez_corriger_la_saisie");
                return;
            }

            for (Recette r : recettes) {
                if (r.getIdrecette() == 0) {
                    r.setIdrecette(recetteFacadeLocal.nextVal());
                    r.setIdperiode(periode);
                    r.setIdsousperiode(sousperiode);
                    r.setIdstructure(structure);
                    recetteFacadeLocal.create(r);
                } else {
                    recetteFacadeLocal.edit(r);
                }
            }
            recettes = recetteFacadeLocal.findByIdstructureIdperiodeIdSp(structure.getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
            //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
            modifier = detail = supprimer = true;
            RequestContext.getCurrentInstance().execute("PF('RecetteCreerDialog').hide()");
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
