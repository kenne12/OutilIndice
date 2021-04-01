/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_heure_supplementaire;

import controllers.util.JsfUtil;
import entities.Categorie;
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
public class PrimeHeureSupplementaireCtrl extends AbstractPrimeHeureSupplementaireCtrl implements Serializable {

    /**
     * Creates a new instance of PrimeHeureSupplementaireCtrl
     */
    public PrimeHeureSupplementaireCtrl() {
    }

    @PostConstruct
    private void init() {
        listParametres = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
        parametragecritere = new Parametragecritere();
        parametragecritere.setIdcategorie(new Categorie());
    }

    public void prepareCreate(String option) {
        mode = "Create";
        denominateurNuit = 500;
        denominateurJour = 1000;
        if (option.equals("2")) {
            this.updateFiltre();
        }
    }

    public void prepareEdit(Parametragecritere p) {
        this.parametragecritere = p;
        if (structure != null) {
            mode = "Edit";
            RequestContext.getCurrentInstance().execute("PF('HeureSuppEditDialog').show()");
        }
    }

    public void updateFiltre() {
        parametragecriteres.clear();
        List<Parametragecritere> list = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
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
                pc.setDenominateurjournee(denominateurJour);
                pc.setDenominateurnuit(denominateurNuit);
                pc.setValeurjournee(pc.getIndice() / denominateurJour);
                pc.setValeurnuit(pc.getIndice() / denominateurNuit);
                pc.setPoint(0d);
                pc.setIdcategorie(efc.getCategorie());
                pc.setHeuresupp(true);
                pc.setHeuresupn(false);
                pc.setPratiqueprivee(false);
                pc.setPerformanceindividuelle(false);
                pc.setResultatqualitatifdept(false);
                pc.setBonusrevenudept(false);
                pc.setNombre(efc.getNombre());
                parametragecriteres.add(pc);
            }
        } else {
            parametragecriteres.addAll(list);
        }
        this.sommeDetail(parametragecriteres);
        RequestContext.getCurrentInstance().execute("PF('HeureSuppCreateDialog').show()");
    }

    public void addCategoriesToTable() {
        if (!selectedCategories.isEmpty()) {
            for (Categorie c : selectedCategories) {
                Parametragecritere pc = new Parametragecritere(0l);
                pc.setIdstructure(structure);
                pc.setIdcritere(criterestructure.getCritere());
                pc.setIndice(c.getIndice());
                pc.setDenominateurjournee(denominateurJour);
                pc.setDenominateurnuit(denominateurNuit);
                pc.setValeurjournee(pc.getIndice() / denominateurJour);
                pc.setValeurnuit(pc.getIndice() / denominateurNuit);
                pc.setPoint(0d);
                pc.setIdcategorie(c);
                pc.setHeuresupp(true);
                pc.setHeuresupn(false);
                pc.setPratiqueprivee(false);
                pc.setPerformanceindividuelle(false);
                pc.setResultatqualitatifdept(false);
                pc.setBonusrevenudept(false);
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
            listParametres = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
        }
        parametragecriteres.remove(index);
        categories.add(p.getIdcategorie());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void updateData(String mode) {
        int i = 0;
        this.totalEffectif = 0;

        for (Parametragecritere pc : parametragecriteres) {
            if (mode.equals("jour")) {
                pc.setDenominateurjournee(denominateurJour);
                pc.setValeurjournee(pc.getIndice() / denominateurJour);
                parametragecriteres.set(i, pc);
            } else if (mode.equals("nuit")) {
                pc.setDenominateurnuit(denominateurNuit);
                pc.setValeurnuit(pc.getIndice() / denominateurNuit);
                parametragecriteres.set(i, pc);
            } else {
                pc.setValeurjournee(pc.getIndice() / pc.getDenominateurjournee());
                pc.setValeurnuit(pc.getIndice() / pc.getDenominateurnuit());
                parametragecriteres.set(i, pc);
            }

            this.totalEffectif += pc.getNombre();
            i++;
        }
    }

    public void updateDataLine(String mode) {
        if (mode.equals("indice")) {
            parametragecritere.setValeurjournee(parametragecritere.getIndice() / parametragecritere.getDenominateurjournee());
            parametragecritere.setValeurnuit(parametragecritere.getIndice() / parametragecritere.getDenominateurnuit());
        } else if (mode.equals("jour")) {
            parametragecritere.setValeurjournee(parametragecritere.getIndice() / parametragecritere.getDenominateurjournee());
        } else if (mode.equals("nuit")) {
            parametragecritere.setValeurnuit(parametragecritere.getIndice() / parametragecritere.getDenominateurnuit());
        }
    }

    private void sommeDetail(List<Parametragecritere> list) {
        this.totalEffectif = 0;
        for (Parametragecritere pc : list) {
            totalEffectif += pc.getNombre();
        }
    }

    public void save() {
        try {
            if (parametragecriteres.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
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
            listParametres = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
            this.parametragecriteres.clear();

            RequestContext.getCurrentInstance().execute("PF('HeureSuppCreateDialog').hide()");
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
            listParametres = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
            RequestContext.getCurrentInstance().execute("PF('HeureSuppEditDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Parametragecritere p) {
        try {
            parametragecritereFacadeLocal.remove(p);
            listParametres = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
