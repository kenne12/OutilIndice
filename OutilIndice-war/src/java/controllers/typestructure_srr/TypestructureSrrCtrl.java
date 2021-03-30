/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_srr;

import controllers.util.JsfUtil;
import entities.Sousrubriquerecette;
import entities.TypeStructureSousRubriqueRecette;
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
public class TypestructureSrrCtrl extends AbstractTypestructureSrrCtrl implements Serializable {

    public TypestructureSrrCtrl() {

    }

    public void prepareCreate() {
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('TypestructureSrrCreateDialog').show()");
    }

    public void prepareEdit(Typestructure s) {
        this.typestructure = s;
        mode = "Edit";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('TypestructureSrrCreateDialog').show()");
    }

    public void updateFiltre() {
        sousrubriquedepenses.clear();
        selectedSousrubriquerecettes.clear();
        typeStructureSousRubriqueRecettes.clear();
        if (typestructure.getIdtypestructure() != null && typestructure.getIdtypestructure() > 0) {
            List<TypeStructureSousRubriqueRecette> list = typeStructureSousRubriqueRecetteFacadeLocal.findByIdTypestructure(typestructure.getIdtypestructure());

            sousrubriquedepenses.addAll(sousrubriquedepenseFacadeLocal.findAllRangeByCode());
            if (!list.isEmpty()) {
                for (TypeStructureSousRubriqueRecette trsd : list) {
                    selectedSousrubriquerecettes.add(trsd.getSousrubriquerecette());
                }
                sousrubriquedepenses.removeAll(selectedSousrubriquerecettes);
                typeStructureSousRubriqueRecettes.addAll(list);
                selectedSousrubriquerecettes.clear();
            }
        }
    }

    public void addSRubriqueToTable() {
        if (!selectedSousrubriquerecettes.isEmpty()) {

            for (Sousrubriquerecette s : selectedSousrubriquerecettes) {
                if (!checkSousRubriqueInTable(s)) {
                    typeStructureSousRubriqueRecettes.add(new TypeStructureSousRubriqueRecette(0, typestructure, s));
                }
            }
            sousrubriquedepenses.removeAll(selectedSousrubriquerecettes);
            selectedSousrubriquerecettes.clear();
        }
    }

    private boolean checkSousRubriqueInTable(Sousrubriquerecette s) {
        boolean result = false;
        for (TypeStructureSousRubriqueRecette ts : typeStructureSousRubriqueRecettes) {
            if (s.getIdsousrubriquerecette().equals(ts.getSousrubriquerecette().getIdsousrubriquerecette())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeSousRubrique(int index) {
        TypeStructureSousRubriqueRecette item = typeStructureSousRubriqueRecettes.get(index);
        if (item.getId() != 0) {
            typeStructureSousRubriqueRecetteFacadeLocal.remove(item);
        }
        typeStructureSousRubriqueRecettes.remove(index);
        sousrubriquedepenses.add(item.getSousrubriquerecette());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnSousRubrique(Typestructure t) {
        String resultat = "";
        if (t.getTypeStructureSRRecetteCollection().isEmpty()) {
            t.setTypeStructureSRRecetteCollection(typeStructureSousRubriqueRecetteFacadeLocal.findByIdTypestructure(t.getIdtypestructure()));
        }
        if (t.getTypeStructureSRRecetteCollection() != null) {
            int i = 0;
            for (TypeStructureSousRubriqueRecette ts : t.getTypeStructureSRRecetteCollection()) {
                if (i == 0) {
                    resultat = "" + ts.getSousrubriquerecette().getNom() + " ";
                } else {
                    resultat += "\n / " + ts.getSousrubriquerecette().getNom() + " ";
                }
                i++;
            }
        }
        return resultat;
    }

    @Transactional
    public void save() {
        try {
            if (typeStructureSousRubriqueRecettes.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            typeStructureSousRubriqueRecettes.forEach(ts -> {
                if (ts.getId() == 0) {
                    ts.setId(typeStructureSousRubriqueRecetteFacadeLocal.nextId());
                    typeStructureSousRubriqueRecetteFacadeLocal.create(ts);
                } else {
                    typeStructureSousRubriqueRecetteFacadeLocal.edit(ts);
                }
            });

            this.typeStructureSousRubriqueRecettes.clear();
            RequestContext.getCurrentInstance().execute("PF('TypestructureSrrCreateDialog').hide()");
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
