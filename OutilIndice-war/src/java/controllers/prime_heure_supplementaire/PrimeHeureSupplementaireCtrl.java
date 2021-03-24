/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_heure_supplementaire;

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
import utils.Utilitaires;

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
        structures.clear();
        structures.add(SessionMBean.getStructure());
        listParametres = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
        parametragecritere = new Parametragecritere();
        parametragecritere.setIdcategorie(new Categorie());

        criterestructure = Utilitaires.findCritereSInSession(2);
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
        denominateurNuit = 500;
        denominateurJour = 1000;
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
            RequestContext.getCurrentInstance().execute("PF('HeureSuppEditDialog').show()");
        }
    }

    public void updateFiltre() {
        categories.clear();
        selectedCategories.clear();
        parametragecriteres.clear();
        List<Parametragecritere> list = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
        categories.addAll(SessionMBean.getCategories());
        if (!list.isEmpty()) {
            parametragecriteres.addAll(list);
            for (Parametragecritere pc : list) {
                selectedCategories.add(pc.getIdcategorie());
            }
            categories.removeAll(selectedCategories);
            selectedCategories.clear();
        }
        RequestContext.getCurrentInstance().execute("PF('HeureSuppCreateDialog').show()");
    }

    public void updateFiltre2() {
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
                pc.setIdcritere(new Critere(2));
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
                pc.setTotal1(Math.ceil(pc.getValeurjournee() * efc.getNombre()));
                pc.setTotal2(Math.ceil(pc.getValeurnuit() * efc.getNombre()));
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
                pc.setIdcritere(new Critere(2));
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
        }
    }

    public void removeCategory(Parametragecritere p) {
        if (p.getIdparametragecritere() != 0l) {
            parametragecritereFacadeLocal.remove(p);
            parametragecriteres.remove(p);
            listParametres = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
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

    public void updateData(String mode) {
        int i = 0;
        this.totalEffectif = 0;
        this.totalPointSaisiJ = 0;
        this.totalPointSaisiN = 0;
        for (Parametragecritere pc : parametragecriteres) {
            if (mode.equals("jour")) {
                pc.setDenominateurjournee(denominateurJour);
                pc.setValeurjournee(pc.getIndice() / denominateurJour);
                pc.setTotal1(Math.ceil(pc.getValeurjournee() * pc.getNombre()));
                parametragecriteres.set(i, pc);
            } else if (mode.equals("nuit")) {
                pc.setDenominateurnuit(denominateurNuit);
                pc.setValeurnuit(pc.getIndice() / denominateurNuit);
                pc.setTotal2(Math.ceil(pc.getValeurnuit() * pc.getNombre()));
                parametragecriteres.set(i, pc);
            } else {
                pc.setValeurjournee(pc.getIndice() / pc.getDenominateurjournee());
                pc.setValeurnuit(pc.getIndice() / pc.getDenominateurnuit());
                pc.setTotal1(Math.ceil(pc.getValeurjournee() * pc.getNombre()));
                pc.setTotal2(Math.ceil(pc.getValeurnuit() * pc.getNombre()));
                parametragecriteres.set(i, pc);
            }

            this.totalEffectif += pc.getNombre();
            totalPointSaisiJ += pc.getTotal1();
            totalPointSaisiN += pc.getTotal2();
            i++;
        }
    }

    public void updateDataLine(String mode) {
        if (mode.equals("indice")) {
            parametragecritere.setValeurjournee(parametragecritere.getIndice() / parametragecritere.getDenominateurjournee());
            parametragecritere.setValeurnuit(parametragecritere.getIndice() / parametragecritere.getDenominateurnuit());
            parametragecritere.setTotal1(Math.ceil(parametragecritere.getValeurjournee() * parametragecritere.getNombre()));
            parametragecritere.setTotal2(Math.ceil(parametragecritere.getValeurnuit() * parametragecritere.getNombre()));
        } else if (mode.equals("jour")) {
            parametragecritere.setValeurjournee(parametragecritere.getIndice() / parametragecritere.getDenominateurjournee());
            parametragecritere.setTotal1(Math.ceil(parametragecritere.getValeurjournee() * parametragecritere.getNombre()));
        } else if (mode.equals("nuit")) {
            parametragecritere.setValeurnuit(parametragecritere.getIndice() / parametragecritere.getDenominateurnuit());
            parametragecritere.setTotal2(Math.ceil(parametragecritere.getValeurnuit() * parametragecritere.getNombre()));
        }
    }

    private void sommeDetail(List<Parametragecritere> list) {
        this.totalPointSaisiJ = 0;
        this.totalPointSaisiN = 0;
        this.totalEffectif = 0;

        for (Parametragecritere pc : list) {
            totalEffectif += pc.getNombre();
            totalPointSaisiJ += pc.getTotal1();
            totalPointSaisiN += pc.getTotal2();
        }
    }

    public void save() {
        try {
            if (parametragecriteres.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            if ((totalPointSaisiJ + totalPointSaisiN) > totalPointMaxCritere) {
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
