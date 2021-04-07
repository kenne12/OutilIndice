/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.bonus_revenu_dept;

import controllers.util.JsfUtil;
import entities.Parametragecritere;
import entities.Categorie;
import entities.EffectifCategorie;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class BonusRevenuDeptCtrl extends AbstractBonusRevenuDeptCtrl implements Serializable {

    /**
     * Creates a new instance of BonusRevenuDeptCtrl
     */
    public BonusRevenuDeptCtrl() {
    }

    @PostConstruct
    private void init() {
        listParametres = parametragecritereFacadeLocal.findByIdStructureBrd(SessionMBean.getStructure().getIdstructure(), 6, true);
        parametragecritere = new Parametragecritere();
        parametragecritere.setIdcategorie(new Categorie());
        criterestructure = criterestructureFacadeLocal.findByIdStructureIdCritere(SessionMBean.getStructure().getIdstructure(), 6);
        criterestructures = criterestructureFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
        totalPointMaxCritere = criterestructure.getResultat();
        indexCritere = criterestructures.indexOf(criterestructure);
    }

    public void prepareCreate(String option) {
        this.denominateur = 5;
        mode = "Create";
        if (option.equals("2")) {
            this.updateFiltre();
        }
    }

    public void prepareEdit(Parametragecritere p) {
        this.parametragecritere = p;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('BonusRevenuDeptEditDialog').show()");
    }

    private void updateFiltre() {
        parametragecriteres.clear();
        List<Parametragecritere> list = parametragecritereFacadeLocal.findByIdStructureBrd(SessionMBean.getStructure().getIdstructure(), 6, true);
        if (list.isEmpty()) {

            effectifCategories = effectifCategorieFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
            if (effectifCategories.isEmpty()) {
                JsfUtil.addWarningMessage("Veuillez définir les effectifs par catégorie pour cette structure");
                return;
            }
            for (EffectifCategorie efc : effectifCategories) {
                Parametragecritere pc = new Parametragecritere(0l);
                pc.setIdstructure(structure);
                pc.setIdcritere(criterestructure.getCritere());
                pc.setIndice(efc.getCategorie().getIndice());
                pc.setIdcategorie(efc.getCategorie());
                pc.setNombre(efc.getNombre());
                pc.setDenominateurjournee(0);
                pc.setDenominateurnuit(0);
                pc.setValeurjournee(0);
                pc.setValeurnuit(0);
                pc.setPoint(0d);
                pc.setHeuresupp(false);
                pc.setHeuresupn(false);
                pc.setPratiqueprivee(false);
                pc.setPerformanceindividuelle(false);
                pc.setResultatqualitatifdept(false);
                pc.setBonusrevenudept(true);
                pc.setPratiqueprivee(false);
                if (denominateur > 0) {
                    pc.setDenominateur((int) denominateur);
                    pc.setPoint(pc.getIndice() / denominateur);
                }
                pc.setTotal1(Math.ceil(pc.getPoint() * pc.getNombre()));
                parametragecriteres.add(pc);
            }
        } else {
            parametragecriteres.addAll(list);
        }
        this.sommeDetail(parametragecriteres);
        RequestContext.getCurrentInstance().execute("PF('BonusRevenuDeptCreateDialog').show()");
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
                Parametragecritere pc = new Parametragecritere(0l);
                pc.setIdstructure(structure);
                pc.setIdcritere(criterestructure.getCritere());
                pc.setIndice(c.getIndice());
                pc.setDenominateurjournee(0);
                pc.setDenominateurnuit(0);
                pc.setValeurjournee(0);
                pc.setValeurnuit(0);
                pc.setPoint(0d);
                if (denominateur > 0) {
                    pc.setDenominateur((int) denominateur);
                    pc.setPoint(pc.getIndice() / denominateur);
                }
                pc.setIdcategorie(c);
                pc.setHeuresupp(false);
                pc.setHeuresupn(false);
                pc.setPratiqueprivee(false);
                pc.setPerformanceindividuelle(false);
                pc.setResultatqualitatifdept(false);
                pc.setBonusrevenudept(true);
                parametragecriteres.add(pc);
            }
            categories.removeAll(selectedCategories);
            selectedCategories.clear();
        }
    }

    public void removeCategory(int index) {
        Parametragecritere p = parametragecriteres.get(index);
        if (p.getIdparametragecritere() != 0l) {
            parametragecritereFacadeLocal.remove(p);
            listParametres = parametragecritereFacadeLocal.findByIdStructureBrd(SessionMBean.getStructure().getIdstructure(), 6, true);
        }
        parametragecriteres.remove(p);
        categories.add(p.getIdcategorie());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void refreshCosting() {
        criterestructure.setResultatfinal(totalPointSaisi);
        criterestructures.set(indexCritere, criterestructure);
        double somme = 0;
        for (int i = 0; i < criterestructures.size(); i++) {
            if (criterestructures.get(i).getPoids() != null && criterestructures.get(i).getPoids() > 0) {
                somme += criterestructures.get(i).getResultatfinal();
            }
        }
        for (int i = 0; i < criterestructures.size(); i++) {
            if (criterestructures.get(i).getPoids() != null && criterestructures.get(i).getPoids() > 0) {
                criterestructures.get(i).setPoidsfinal((criterestructures.get(i).getResultatfinal() / somme) * 100);
                criterestructures.get(i).setEcart(criterestructures.get(i).getPoidsfinal() - criterestructures.get(i).getPoids());
            }
        }
        criterestructure = criterestructures.get(indexCritere);
    }

    public void updateData(String mode) {
        int i = 0;
        this.totalPointSaisi = 0;
        this.totalEffectif = 0;

        for (Parametragecritere pc : parametragecriteres) {
            pc.setDenominateur((int) denominateur);
            pc.setPoint(pc.getIndice() / denominateur);
            pc.setTotal1(Math.ceil(pc.getPoint() * pc.getNombre()));
            parametragecriteres.set(i, pc);

            this.totalPointSaisi += pc.getTotal1();
            this.totalEffectif += pc.getNombre();
            i++;
        }
    }

    public void updateDataLine(int index) {
        try {
            parametragecriteres.get(index).setPoint(parametragecriteres.get(index).getIndice() / parametragecriteres.get(index).getDenominateur());
            parametragecriteres.get(index).setTotal1(Math.ceil(parametragecriteres.get(index).getPoint() * parametragecriteres.get(index).getNombre()));
        } catch (Exception e) {
            parametragecriteres.get(index).setPoint(0);
            parametragecriteres.get(index).setTotal1(0);
        }
        this.sommeDetail(parametragecriteres);
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
            listParametres = parametragecritereFacadeLocal.findByIdStructureBrd(SessionMBean.getStructure().getIdstructure(), 6, true);

            criterestructures.forEach(cs -> {
                criterestructureFacadeLocal.edit(cs);
            });
            this.parametragecriteres.clear();

            RequestContext.getCurrentInstance().execute("PF('BonusRevenuDeptCreateDialog').hide()");
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
            listParametres = parametragecritereFacadeLocal.findByIdStructureBrd(SessionMBean.getStructure().getIdstructure(), 6, true);
            RequestContext.getCurrentInstance().execute("PF('BonusRevenuDeptEditDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Parametragecritere p) {
        try {
            parametragecritereFacadeLocal.remove(p);
            listParametres = parametragecritereFacadeLocal.findByIdStructureBrd(SessionMBean.getStructure().getIdstructure(), 6, true);
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
