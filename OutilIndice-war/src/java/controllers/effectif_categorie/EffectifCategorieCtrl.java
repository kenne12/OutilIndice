/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.effectif_categorie;

import controllers.util.JsfUtil;
import entities.Categorie;
import entities.EffectifCategorie;
import entities.Personnel;
import entities.Structure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class EffectifCategorieCtrl extends AbstractEffectifCategorieCtrl implements Serializable {

    /**
     * Creates a new instance of EffectifCategorieCtrl
     */
    public EffectifCategorieCtrl() {
    }

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
    }

    public void prepareCreate() {
        structure = SessionMBean.getStructure();
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('EffectifCategorieCreateDialog').show()");
    }

    public void prepareEdit(Structure s) {
        this.structure = s;
        if (structure != null) {
            mode = "Edit";
            this.updateFiltre();
            RequestContext.getCurrentInstance().execute("PF('EffectifCategorieCreateDialog').show()");
        }
    }

    public void updateFiltre() {
        categories.clear();
        selectedCategories.clear();
        effectifCategories.clear();
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            List<EffectifCategorie> list = effectifCategorieFacadeLocal.findByIdStructure(structure.getIdstructure());
            categories.addAll(SessionMBean.getCategories());
            if (!list.isEmpty()) {
                for (EffectifCategorie cs : list) {
                    selectedCategories.add(cs.getCategorie());
                }
                categories.removeAll(selectedCategories);
                effectifCategories.addAll(list);
            }
        }
        this.effectif = this.sommeData();
    }

    public void addCategorieToTable() {
        if (!selectedCategories.isEmpty()) {
            List<Categorie> list = new ArrayList<>();
            List<Personnel> personnels = personnelFacadeLocal.findByIdStructure(structure.getIdstructure(), true);
            for (Categorie c : selectedCategories) {
                if (!checkCategorieInTable(c)) {
                    List<Personnel> listPc = new ArrayList<>();
                    for (Personnel p : personnels) {
                        if (p.getIdcategorie().getIdcategorie().equals(c.getIdcategorie())) {
                            listPc.add(p);
                        }
                    }

                    EffectifCategorie cs = new EffectifCategorie(0l);
                    cs.setStructure(structure);
                    cs.setCategorie(c);
                    cs.setNombre(listPc.size());
                    effectifCategories.add(cs);
                    list.add(c);

                    if (!listPc.isEmpty()) {
                        personnels.removeAll(listPc);
                    }
                }
            }

            this.categories.removeAll(list);
            this.selectedCategories.removeAll(list);
            this.sommeData();
        }
    }

    private boolean checkCategorieInTable(Categorie c) {
        boolean result = false;
        for (EffectifCategorie cs : effectifCategories) {
            if (c.getIdcategorie().equals(cs.getCategorie().getIdcategorie())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeCategorie(EffectifCategorie item) {
        if (item.getCategorie() != null) {
            effectifCategorieFacadeLocal.remove(item);
            effectifCategories.remove(item);
        } else {
            int conteur = 0;
            for (EffectifCategorie cs : effectifCategories) {
                if (item.getCategorie().getIdcategorie().equals(cs.getCategorie().getIdcategorie())) {
                    break;
                }
                conteur++;
            }
            effectifCategories.remove(conteur);
        }
        this.effectif = this.sommeData();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnCategorie(Structure s) {
        String resultat = "";
        if (s.getEffectifCategorieCollection() != null) {
            int i = 0;
            for (EffectifCategorie c : s.getEffectifCategorieCollection()) {
                if (i == 0) {
                    resultat = "" + c.getCategorie().getNom() + " (" + c.getNombre() + ")";
                } else {
                    resultat += "\n" + c.getCategorie().getNom() + " (" + c.getNombre() + ")";;
                }
                i++;
            }
        }
        return resultat;
    }

    @Transactional
    public void save() {
        try {
            if (effectifCategories.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            effectifCategories.forEach(efc -> {
                if (efc.getIdEffectifCategorie().equals(0l)) {
                    efc.setIdEffectifCategorie(effectifCategorieFacadeLocal.nextId());
                    effectifCategorieFacadeLocal.create(efc);
                } else {
                    effectifCategorieFacadeLocal.edit(efc);
                }
            });

            this.effectifCategories.clear();
            this.structure = new Structure();

            RequestContext.getCurrentInstance().execute("PF('EffectifCategorieCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Structure s) {
        try {
            effectifCategorieFacadeLocal.deleteByIdStructure(s.getIdstructure());
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private double sommeData() {
        this.effectif = 0;
        if (effectifCategories.isEmpty()) {
            return 0;
        }

        for (EffectifCategorie eff : effectifCategories) {
            try {
                effectif += eff.getNombre();
            } catch (Exception e) {
            }
        }
        return this.effectif;
    }

    public void updateSaisie() {
        this.sommeData();
    }
}
