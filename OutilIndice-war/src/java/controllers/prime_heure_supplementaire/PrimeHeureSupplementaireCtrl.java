/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_heure_supplementaire;

import controllers.util.JsfUtil;
import entities.Categorie;
import entities.Critere;
import entities.Parametragecritere;
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
public class PrimeHeureSupplementaireCtrl extends AbstractPrimeHeureSupplementaireCtrl {

    /**
     * Creates a new instance of PrimeHeureSupplementaireCtrl
     */
    public PrimeHeureSupplementaireCtrl() {
    }
    
    @PostConstruct
    private void init() {
        structure = SessionMBean.getStructure();
        structures.clear();
        structures.add(SessionMBean.getStructure());
        listParametres = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
        parametragecritere = new Parametragecritere();
        parametragecritere.setIdcategorie(new Categorie());
    }
    
    public void prepareCreate() {
        this.updateFiltre();
        mode = "Create";
        denominateurNuit = 500;
        denominateurJour = 1000;
        RequestContext.getCurrentInstance().execute("PF('HeureSuppCreateDialog').show()");
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
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            List<Parametragecritere> list = parametragecritereFacadeLocal.findByIdStructureHs(SessionMBean.getStructure().getIdstructure(), 2, true);
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
    }
    
    public void addCategoriesToTable() {
        if (!selectedCategories.isEmpty()) {
            for (Categorie c : selectedCategories) {
                Parametragecritere pc = new Parametragecritere();
                pc.setIdparametragecritere(0l);
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
