/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_srd;

import controllers.util.JsfUtil;
import entities.Sousrubriquedepense;
import entities.TypeStructureSousRubriqueDepense;
import entities.Typestructure;
import java.io.Serializable;
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
public class TypestructureSrdCtrl extends AbstractTypestructureSrdCtrl implements Serializable {

    public TypestructureSrdCtrl() {

    }

    public void prepareCreate() {
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('TypestructureSrdCreateDialog').show()");
    }

    public void prepareEdit(Typestructure s) {
        this.typestructure = s;
        mode = "Edit";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('TypestructureSrdCreateDialog').show()");
    }

    public void updateFiltre() {
        sousrubriquedepenses.clear();
        selectedSousrubriquedepenses.clear();
        typeStructureSousRubriqueDepenses.clear();
        if (typestructure.getIdtypestructure() != null && typestructure.getIdtypestructure() > 0) {
            List<TypeStructureSousRubriqueDepense> list = typeStructureSousRubriqueDepenseFacadeLocal.findByIdTypestructure(typestructure.getIdtypestructure());

            sousrubriquedepenses.addAll(sousrubriquedepenseFacadeLocal.findAllRangeByCode());
            if (!list.isEmpty()) {
                for (TypeStructureSousRubriqueDepense trsd : list) {
                    selectedSousrubriquedepenses.add(trsd.getSousrubriquedepense());
                }
                sousrubriquedepenses.removeAll(selectedSousrubriquedepenses);
                typeStructureSousRubriqueDepenses.addAll(list);
                selectedSousrubriquedepenses.clear();
            }
        }
    }

    public void addSRubriqueToTable() {
        if (!selectedSousrubriquedepenses.isEmpty()) {

            for (Sousrubriquedepense s : selectedSousrubriquedepenses) {
                if (!checkSousRubriqueInTable(s)) {
                    typeStructureSousRubriqueDepenses.add(new TypeStructureSousRubriqueDepense(0, typestructure, s));
                }
            }
            sousrubriquedepenses.removeAll(selectedSousrubriquedepenses);
            selectedSousrubriquedepenses.clear();
        }
    }

    private boolean checkSousRubriqueInTable(Sousrubriquedepense s) {
        boolean result = false;
        for (TypeStructureSousRubriqueDepense ts : typeStructureSousRubriqueDepenses) {
            if (s.getIdsousrubriquedepense().equals(ts.getSousrubriquedepense().getIdsousrubriquedepense())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeSousRubrique(int index) {
        TypeStructureSousRubriqueDepense item = typeStructureSousRubriqueDepenses.get(index);
        if (item.getId() != 0) {
            typeStructureSousRubriqueDepenseFacadeLocal.remove(item);
        }
        typeStructureSousRubriqueDepenses.remove(index);
        sousrubriquedepenses.add(item.getSousrubriquedepense());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnSousRubrique(Typestructure t) {
        String resultat = "";
        if (t.getTypeStructureSRDepenseCollection().isEmpty()) {
            t.setTypeStructureSRDepenseCollection(typeStructureSousRubriqueDepenseFacadeLocal.findByIdTypestructure(t.getIdtypestructure()));
        }
        if (t.getTypeStructureSRDepenseCollection() != null) {
            int i = 0;
            for (TypeStructureSousRubriqueDepense ts : t.getTypeStructureSRDepenseCollection()) {
                if (i == 0) {
                    resultat = "" + ts.getSousrubriquedepense().getNom() + " ";
                } else {
                    resultat += "\n / " + ts.getSousrubriquedepense().getNom() + " ";
                }
                i++;
            }
        }
        return resultat;
    }

    @Transactional
    public void save() {
        try {
            if (typeStructureSousRubriqueDepenses.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            typeStructureSousRubriqueDepenses.forEach(ts -> {
                if (ts.getId() == 0) {
                    ts.setId(typeStructureSousRubriqueDepenseFacadeLocal.nextId());
                    typeStructureSousRubriqueDepenseFacadeLocal.create(ts);
                } else {
                    typeStructureSousRubriqueDepenseFacadeLocal.edit(ts);
                }
            });

            this.typeStructureSousRubriqueDepenses.clear();
            RequestContext.getCurrentInstance().execute("PF('TypestructureSrdCreateDialog').hide()");
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
