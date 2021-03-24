/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_categorie;

import controllers.util.JsfUtil;
import entities.Categorie;
import entities.Typestructure;
import entities.TypestructureCategorie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.Transactional;
import org.primefaces.context.RequestContext;

/**
 *
 * @author USER
 */
@ManagedBean
@SessionScoped
public class TypestructureCategorieCtrl extends AbstractTypestructureCategorieCtrl implements Serializable {

    public TypestructureCategorieCtrl() {

    }

    public void prepareCreate() {
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('TypestructureCategorieCreateDialog').show()");
    }

    public void prepareEdit(Typestructure s) {
        this.typestructure = s;
        mode = "Edit";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('TypestructureCategorieCreateDialog').show()");
    }

    public void updateFiltre() {
        categories.clear();
        selectedCategories.clear();
        typestructureCategories.clear();
        if (typestructure.getIdtypestructure() != null && typestructure.getIdtypestructure() > 0) {
            List<TypestructureCategorie> list = typestructureCategorieFacadeLocal.findByIdTypestructure(typestructure.getIdtypestructure());

            categories.addAll(categorieFacadeLocal.findAllRangeByCode());
            if (!list.isEmpty()) {
                for (TypestructureCategorie cs : list) {
                    selectedCategories.add(cs.getCategorie());
                }
                categories.removeAll(selectedCategories);
                typestructureCategories.addAll(list);
                selectedCategories.clear();
            }
        }
    }

    public void addCategorieToTable() {
        if (!selectedCategories.isEmpty()) {
            List<Categorie> list = new ArrayList();
            for (Categorie c : selectedCategories) {
                if (!checkCategorieInTable(c)) {
                    typestructureCategories.add(new TypestructureCategorie(0, typestructure, c));
                    list.add(c);
                }
            }
            categories.removeAll(list);
            selectedCategories.clear();
        }
    }

    private boolean checkCategorieInTable(Categorie c) {
        boolean result = false;
        for (TypestructureCategorie ts : typestructureCategories) {
            if (c.getIdcategorie().equals(ts.getCategorie().getIdcategorie())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeCategorie(int index, TypestructureCategorie item) {
        if (item.getIdTypestructureCategorie() != 0) {
            typestructureCategorieFacadeLocal.remove(item);
            typestructureCategories.remove(item);
        } else {
            typestructureCategories.remove(index);
        }
        categories.add(item.getCategorie());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnCategorie(Typestructure t) {
        String resultat = "";
        if (t.getTypestructureCategorieCollection().isEmpty()) {
            t.setTypestructureCategorieCollection(typestructureCategorieFacadeLocal.findByIdTypestructure(t.getIdtypestructure()));
        }
        if (t.getTypestructureCategorieCollection() != null) {
            int i = 0;
            for (TypestructureCategorie ts : t.getTypestructureCategorieCollection()) {
                if (i == 0) {
                    resultat = "" + ts.getCategorie().getNom() + "";
                } else {
                    resultat += "\n / " + ts.getCategorie().getNom() + " ";
                }
                i++;
            }
        }
        return resultat;
    }

    @Transactional
    public void save() {
        try {
            if (typestructureCategories.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            typestructureCategories.forEach(ts -> {
                if (ts.getIdTypestructureCategorie() == 0) {
                    ts.setIdTypestructureCategorie(typestructureCategorieFacadeLocal.nextId());
                    typestructureCategorieFacadeLocal.create(ts);
                } else {
                    typestructureCategorieFacadeLocal.edit(ts);
                }
            });

            this.typestructureCategories.clear();
            RequestContext.getCurrentInstance().execute("PF('TypestructureCategorieCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Typestructure ts) {
        try {
            /*if (sc != null) {
             souscritereFacadeLocal.remove(sc);
             souscritere = new Souscritere();
             JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
             } else {
             JsfUtil.addErrorMessage("Aucune ligne seletionnée");
             }*/
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
