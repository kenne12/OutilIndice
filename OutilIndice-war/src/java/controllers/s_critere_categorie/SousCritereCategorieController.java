/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.s_critere_categorie;

import controllers.util.JsfUtil;
import entities.Categorie;
import entities.Categoriestructure;
import entities.CategoriestructurePK;
import entities.Criterecategorie;
import entities.Structure;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class SousCritereCategorieController extends AbstractSousCritereCategorie implements Serializable {

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        //detailscs = detailscFacadeLocal.
    }

    public SousCritereCategorieController() {

    }

    public void prepareCreate() {
        structure = SessionMBean.getStructure();
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('CategorieStrCreateDialog').show()");
    }

    public void prepareEdit(Structure s) {
        this.structure = s;
        this.denominateur = 0;
        if (structure != null) {
            mode = "Edit";
            this.updateFiltre();
            RequestContext.getCurrentInstance().execute("PF('CategorieStrCreateDialog').show()");
        }
    }

    public void updateData() {
        if (categoriestructures.isEmpty()) {
            return;
        }

        if ((denominateur == null || denominateur < 1)) {
            return;
        }
        int i = 0;
        for (Categoriestructure cs : this.categoriestructures) {
            this.categoriestructures.get(i).setPointmax(cs.getIndice() / denominateur);
            this.categoriestructures.get(i).setDenominateur(denominateur);
            i++;
        }
    }

    public void updateFiltre() {
        if (categorie != null) {
            List<Criterecategorie> list = souscriterecategorieFacadeLocal.findByIdcategorieIdStructure(categorie.getIdcategorie(), SessionMBean.getStructure().getIdstructure());
            if (list.isEmpty()) {
                
                return;
            }
            
            
        }

        categories.clear();
        selectedCategories.clear();
        categoriestructures.clear();
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            List<Categoriestructure> list = categoriestructureFacadeLocal.findByIdStructure(structure.getIdstructure());
            if (list.isEmpty() || list == null) {
                categories.addAll(categorieFacadeLocal.findAllRangeByCode());
            } else {
                categories.addAll(categorieFacadeLocal.findAllRangeByCode());
                for (Categoriestructure cs : list) {
                    selectedCategories.add(cs.getCategorie());
                }
                categories.removeAll(selectedCategories);
                categoriestructures.addAll(list);
            }
        }
    }

    public void addCategorieToTable() {
        if (!selectedCategories.isEmpty()) {
            for (Categorie c : selectedCategories) {
                if (!checkCategorieInTable(c)) {
                    Categoriestructure cs = new Categoriestructure();
                    cs.setStructure(structure);
                    cs.setIndice(c.getIndice());
                    cs.setPointmax(0);
                    if (denominateur != null && denominateur > 0) {
                        cs.setPointmax(cs.getIndice() / denominateur);
                    }
                    cs.setCategorie(c);
                    categoriestructures.add(cs);
                }
            }
        }
    }

    private boolean checkCategorieInTable(Categorie c) {
        boolean result = false;
        for (Categoriestructure cs : categoriestructures) {
            if (c.getIdcategorie().equals(cs.getCategorie().getIdcategorie())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeCategorie(Categoriestructure item) {
        if (item.getCategorie() != null) {
            categoriestructureFacadeLocal.remove(item);
            categoriestructures.remove(item);
        } else {
            int conteur = 0;
            for (Categoriestructure cs : categoriestructures) {
                if (item.getCategorie().getIdcategorie().equals(cs.getCategorie().getIdcategorie())) {
                    break;
                }
                conteur++;
            }
            categoriestructures.remove(conteur);
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnCategorie(Structure s) {
        String resultat = "";
        if (s.getCategoriestructureCollection() == null || s.getCategoriestructureCollection().isEmpty()) {
            s.setCategoriestructureCollection(categoriestructureFacadeLocal.findByIdStructure(s.getIdstructure()));
        }
        int i = 0;
        for (Categoriestructure c : s.getCategoriestructureCollection()) {
            if (i == 0) {
                resultat = "" + c.getCategorie().getNom() + " (" + c.getPointmax() + " Pts\n)";
            } else {
                resultat += "\n" + c.getCategorie().getNom() + " (" + c.getPointmax() + " Pts)";;
            }
            i++;
        }

        return resultat;
    }

    public void save() {
        try {
            if (categoriestructures.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            categoriestructures.forEach(cs -> {
                Categoriestructure obj = categoriestructureFacadeLocal.findByIdStructureIdCategorie(structure.getIdstructure(), cs.getCategorie().getIdcategorie());
                if (obj == null) {
                    cs.setCategoriestructurePK(new CategoriestructurePK(structure.getIdstructure(), cs.getCategorie().getIdcategorie()));
                    cs.setStructure(structure);
                    categoriestructureFacadeLocal.create(cs);
                } else {
                    categoriestructureFacadeLocal.edit(cs);
                }
            });

            this.categoriestructures.clear();
            this.structure = new Structure();

            RequestContext.getCurrentInstance().execute("PF('CategorieStrCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Categoriestructure cs) {
        try {
            if (sc != null) {
                categoriestructureFacadeLocal.remove(cs);
                categoriestructure = new Categoriestructure();
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
