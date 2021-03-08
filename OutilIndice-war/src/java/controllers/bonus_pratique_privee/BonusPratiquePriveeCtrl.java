/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.bonus_pratique_privee;

import controllers.util.JsfUtil;
import entities.Categorie;
import entities.Critere;
import entities.EffectifCategorie;
import entities.Parametragecritere;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
@ManagedBean
@SessionScoped
public class BonusPratiquePriveeCtrl extends AbstractBonusPratiquePriveeCtrl implements Serializable {

    /**
     * Creates a new instance of BonusPratiquePriveeCtrl
     */
    public BonusPratiquePriveeCtrl() {
    }

    @PostConstruct
    private void init() {
        structure = SessionMBean.getStructure();
        structures.clear();
        structures.add(SessionMBean.getStructure());
        listParametres = parametragecritereFacadeLocal.findByIdStructurePp(SessionMBean.getStructure().getIdstructure(), 3, true);
        parametragecritere = new Parametragecritere();
        parametragecritere.setIdcategorie(new Categorie());

        criterestructure = criterestructureFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), 2);
        if (criterestructure != null) {
            totalPointMaxCritere = criterestructure.getResultat();
        }
    }

    public void prepareCreate(String option) {

        if (criterestructure == null) {
            JsfUtil.addWarningMessage("Cette structure ne traite pas du bonus d'heure supplementaire");
            return;
        }

        mode = "Create";
        if (option.equals("1")) {
            this.updateFiltre();
        } else {
            this.updateFiltre2();
        }
    }

    public void prepareEdit(Parametragecritere p) {
        this.parametragecritere = p;
        if (structure != null) {
            mode = "Edit";
            RequestContext.getCurrentInstance().execute("PF('BonusPPriveeCreateDialog').show()");
        }
    }

    public void updateFiltre() {
        categories.clear();
        selectedCategories.clear();
        parametragecriteres.clear();

        List<Parametragecritere> list = parametragecritereFacadeLocal.findByIdStructurePp(SessionMBean.getStructure().getIdstructure(), 3, true);
        if (list.isEmpty() || list == null) {
            categories.addAll(categorieFacadeLocal.findAllRangeByCode());
        } else {
            parametragecriteres.addAll(list);
            categories.addAll(categorieFacadeLocal.findAllRangeByCode());
            for (Parametragecritere pc : list) {
                selectedCategories.add(pc.getIdcategorie());
            }
            categories.removeAll(selectedCategories);
            selectedCategories.clear();
        }
    }

    private void updateFiltre2() {
        parametragecriteres.clear();
        List<Parametragecritere> list = parametragecritereFacadeLocal.findByIdStructurePp(SessionMBean.getStructure().getIdstructure(), 3, true);
        if (list.isEmpty()) {

            effectifCategories = effectifCategorieFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
            if (effectifCategories.isEmpty()) {
                JsfUtil.addWarningMessage("Veuillez définir les effectifs par catégorie pour cette structure");
                return;
            }
            for (EffectifCategorie efc : effectifCategories) {
                Parametragecritere pc = new Parametragecritere();
                pc.setIdparametragecritere(0l);
                pc.setIdstructure(structure);
                pc.setIdcritere(new Critere(3));
                pc.setIndice(efc.getCategorie().getIndice());
                pc.setDenominateurjournee(0);
                pc.setDenominateurnuit(0);
                pc.setValeurjournee(0);
                pc.setValeurnuit(0);
                pc.setPoint(0d);
                pc.setIdcategorie(efc.getCategorie());
                pc.setHeuresupp(false);
                pc.setHeuresupn(false);
                pc.setPratiqueprivee(false);
                pc.setPerformanceindividuelle(false);
                pc.setResultatqualitatifdept(false);
                pc.setBonusrevenudept(false);
                pc.setPratiqueprivee(true);
                pc.setNombre(efc.getNombre());
                pc.setTotal1(0);
                parametragecriteres.add(pc);
            }
        } else {
            parametragecriteres.addAll(list);
        }
        this.sommeDetail(parametragecriteres);
        RequestContext.getCurrentInstance().execute("PF('BonusPPriveeCreateDialog').show()");
    }

    public void updateSaisieLine(int index) {
        try {
            parametragecriteres.get(index).setTotal1(parametragecriteres.get(index).getPoint() * parametragecriteres.get(index).getNombre());
            this.sommeDetail(parametragecriteres);
        } catch (Exception e) {
            parametragecriteres.get(index).setTotal1(0);
        }
    }

    private void sommeDetail(List<Parametragecritere> list) {
        this.totalPointSaisi = 0;
        this.totalEffectif = 0;

        for (Parametragecritere pc : list) {
            totalEffectif += pc.getNombre();
            totalPointSaisi += pc.getTotal1();
        }
    }

    public void addCategoriesToTable() {
        if (!selectedCategories.isEmpty()) {
            for (Categorie c : selectedCategories) {
                Parametragecritere pc = new Parametragecritere();
                pc.setIdparametragecritere(0l);
                pc.setIdstructure(structure);
                pc.setIdcritere(new Critere(3));
                pc.setIndice(c.getIndice());
                pc.setDenominateurjournee(0);
                pc.setDenominateurnuit(0);
                pc.setValeurjournee(0);
                pc.setValeurnuit(0);
                pc.setPoint(0d);
                pc.setIdcategorie(c);
                pc.setHeuresupp(false);
                pc.setHeuresupn(false);
                pc.setPratiqueprivee(true);
                pc.setPerformanceindividuelle(false);
                pc.setResultatqualitatifdept(false);
                pc.setBonusrevenudept(false);
                parametragecriteres.add(pc);
            }
            categories.removeAll(selectedCategories);
        }
    }

    public void removeCategory(Parametragecritere p) {
        if (p.getIdparametragecritere() != 0l) {
            parametragecritereFacadeLocal.remove(p);
            parametragecriteres.remove(p);
            listParametres = parametragecritereFacadeLocal.findByIdStructurePp(SessionMBean.getStructure().getIdstructure(), 3, true);
        } else {
            int conteur = 0;
            for (Parametragecritere pc : parametragecriteres) {
                if (pc.getIdcategorie().getIdcategorie().equals(p.getIdcategorie().getIdcategorie())) {
                    break;
                }
                conteur++;
            }
            parametragecriteres.remove(conteur);
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void save() {
        try {
            if (parametragecriteres.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            if ((totalPointSaisi) > totalPointMaxCritere) {
                JsfUtil.addErrorMessage("Le total saisi depasse le total point max possible");
                return;
            }

            for (Parametragecritere pc : parametragecriteres) {
                if (pc.getIdparametragecritere() == 0l) {
                    pc.setIdparametragecritere(parametragecritereFacadeLocal.nextId());
                    parametragecritereFacadeLocal.create(pc);
                } else {
                    parametragecritereFacadeLocal.edit(pc);
                }
            }
            listParametres = parametragecritereFacadeLocal.findByIdStructurePp(SessionMBean.getStructure().getIdstructure(), 3, true);
            this.parametragecriteres.clear();

            RequestContext.getCurrentInstance().execute("PF('BonusPPriveeCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void edit() {
        try {
            parametragecritereFacadeLocal.edit(parametragecritere);
            parametragecritere = new Parametragecritere();
            parametragecritere.setIdcategorie(new Categorie());
            listParametres = parametragecritereFacadeLocal.findByIdStructurePp(SessionMBean.getStructure().getIdstructure(), 3, true);
            RequestContext.getCurrentInstance().execute("PF('BonusPPriveeCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Parametragecritere p) {
        try {
            parametragecritereFacadeLocal.remove(p);
            listParametres = parametragecritereFacadeLocal.findByIdStructurePp(SessionMBean.getStructure().getIdstructure(), 3, true);
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
