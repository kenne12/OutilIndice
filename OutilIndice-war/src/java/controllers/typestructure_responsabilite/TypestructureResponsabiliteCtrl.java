/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_responsabilite;

import controllers.util.JsfUtil;
import entities.Responsabilite;
import entities.Typestructure;
import entities.TypestructureResponsabilite;
import entities.TypestructureService;
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
public class TypestructureResponsabiliteCtrl extends AbstractTypestructureResponsabiliteCtrl implements Serializable {

    public void prepareCreate() {
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('TypestructureResponsabiliteCreateDialog').show()");
    }

    public void prepareEdit(Typestructure s) {
        this.typestructure = s;
        mode = "Edit";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('TypestructureResponsabiliteCreateDialog').show()");
    }

    public void updateFiltre() {
        responsabilites.clear();
        selectedResponsabilites.clear();
        typestructureResponsabilites.clear();
        if (typestructure.getIdtypestructure() != null && typestructure.getIdtypestructure() > 0) {
            List<TypestructureResponsabilite> list = typestructureResponsabiliteFacadeLocal.findByIdTypestructure(typestructure.getIdtypestructure());

            responsabilites.addAll(responsabiliteFacadeLocal.findAll());
            if (!list.isEmpty()) {
                for (TypestructureResponsabilite tsr : list) {
                    selectedResponsabilites.add(tsr.getResponsabilite());
                }
                responsabilites.removeAll(selectedResponsabilites);
                typestructureResponsabilites.addAll(list);
                selectedResponsabilites.clear();
            }
        }
    }

    public void addResponsabiliteToTable() {
        if (!selectedResponsabilites.isEmpty()) {
            List<Responsabilite> list = new ArrayList();
            for (Responsabilite r : selectedResponsabilites) {
                if (!checkResponsabiliteInTable(r)) {
                    typestructureResponsabilites.add(new TypestructureResponsabilite(0, typestructure, r));
                    list.add(r);
                }
            }
            responsabilites.removeAll(list);
            selectedResponsabilites.clear();
        }
    }

    private boolean checkResponsabiliteInTable(Responsabilite r) {
        boolean result = false;
        for (TypestructureResponsabilite tsr : typestructureResponsabilites) {
            if (r.getIdresponsabilite().equals(tsr.getResponsabilite().getIdresponsabilite())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeResponsabilite(int index, TypestructureResponsabilite item) {
        if (item.getIdTypestructureResponsabilite() != 0) {
            typestructureResponsabiliteFacadeLocal.remove(item);
            typestructureResponsabilites.remove(item);
        } else {
            typestructureResponsabilites.remove(index);
        }
        responsabilites.add(item.getResponsabilite());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnResponsabilite(Typestructure t) {
        String resultat = "";
        if (t.getTypestructureResponsabiliteCollection().isEmpty()) {
            t.setTypestructureResponsabiliteCollection(typestructureResponsabiliteFacadeLocal.findByIdTypestructure(t.getIdtypestructure()));
        }
        if (t.getTypestructureResponsabiliteCollection() != null) {
            int i = 0;
            for (TypestructureResponsabilite ts : t.getTypestructureResponsabiliteCollection()) {
                if (i == 0) {
                    resultat = "" + ts.getResponsabilite().getNom() + " ";
                } else {
                    resultat += "\n / " + ts.getResponsabilite().getNom() + " ";
                }
                i++;
            }
        }
        return resultat;
    }

    @Transactional
    public void save() {
        try {
            if (typestructureResponsabilites.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            typestructureResponsabilites.forEach(ts -> {
                if (ts.getIdTypestructureResponsabilite() == 0) {
                    ts.setIdTypestructureResponsabilite(typestructureResponsabiliteFacadeLocal.nextId());
                    typestructureResponsabiliteFacadeLocal.create(ts);
                } else {
                    typestructureResponsabiliteFacadeLocal.edit(ts);
                }
            });

            this.typestructureResponsabilites.clear();
            RequestContext.getCurrentInstance().execute("PF('TypestructureResponsabiliteCreateDialog').hide()");
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
