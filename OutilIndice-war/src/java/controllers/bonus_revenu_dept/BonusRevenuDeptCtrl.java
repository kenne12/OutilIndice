/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.bonus_revenu_dept;

import controllers.util.JsfUtil;
import entities.Categorie;
import entities.Critere;
import entities.Parametragecritere;
import entities.Categorie;
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
public class BonusRevenuDeptCtrl extends AbstractBonusRevenuDeptCtrl implements Serializable {

    /**
     * Creates a new instance of BonusRevenuDeptCtrl
     */
    public BonusRevenuDeptCtrl() {
    }
    
    @PostConstruct
    private void init() {
        structure = SessionMBean.getStructure();
        structures.clear();
        structures.add(SessionMBean.getStructure());
        listParametres = parametragecritereFacadeLocal.findByIdStructureBrd(SessionMBean.getStructure().getIdstructure(), 6, true);
        parametragecritere = new Parametragecritere();
        parametragecritere.setIdcategorie(new Categorie());
    }
    
    public void prepareCreate() {
        this.denominateur = 5;
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('BonusRevenuDeptCreateDialog').show()");
    }
    
    public void prepareEdit(Parametragecritere p) {
        this.parametragecritere = p;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('BonusRevenuDeptEditDialog').show()");
    }
    
    public void updateFiltre() {
        categories.clear();
        selectedCategories.clear();
        parametragecriteres.clear();
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            List<Parametragecritere> list = parametragecritereFacadeLocal.findByIdStructureBrd(SessionMBean.getStructure().getIdstructure(), 6, true);
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
                pc.setIdcritere(new Critere(6));
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
        }
    }
    
    public void removeCategory(Parametragecritere p) {
        if (p.getIdparametragecritere() != 0l) {
            parametragecritereFacadeLocal.remove(p);
            parametragecriteres.remove(p);
            listParametres = parametragecritereFacadeLocal.findByIdStructureBrd(SessionMBean.getStructure().getIdstructure(), 6, true);
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
            pc.setDenominateur((int) denominateur);
            pc.setPoint(pc.getIndice() / denominateur);
            parametragecriteres.set(i, pc);
            i++;
        }
    }
    
    public void updateData2(String mode) {
        int i = 0;
        for (Parametragecritere pc : parametragecriteres) {
            pc.setPoint(pc.getIndice() / pc.getDenominateur());
            parametragecriteres.set(i, pc);
            i++;
        }
    }
    
    public void updateDataLine(String mode) {
        try {
            parametragecritere.setPoint(parametragecritere.getIndice() / parametragecritere.getDenominateur());
        } catch (Exception e) {
            parametragecritere.setPoint(0);
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
            listParametres = parametragecritereFacadeLocal.findByIdStructureBrd(SessionMBean.getStructure().getIdstructure(), 6, true);
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
