/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_typesousperiode;

import controllers.util.JsfUtil;
import entities.TypeSousPeriode;
import entities.Typestructure;
import entities.TypestructureTypeSousperiode;
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
public class TypestructureTypeSPCtrl extends AbstractTypestructureTypeSPCtrl implements Serializable {

    public TypestructureTypeSPCtrl() {

    }

    public void prepareCreate() {
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('TypestructureTspCreateDialog').show()");
    }

    public void prepareEdit(Typestructure s) {
        this.typestructure = s;
        mode = "Edit";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('TypestructureTspCreateDialog').show()");
    }

    public void updateFiltre() {
        typeSousPeriodes.clear();
        selectedTypeSousPeriodes.clear();
        typestructureTypeSousperiodes.clear();
        if (typestructure.getIdtypestructure() != null && typestructure.getIdtypestructure() > 0) {
            List<TypestructureTypeSousperiode> list = typestructureTypeSousperiodeFacadeLocal.findByIdTypestructure(typestructure.getIdtypestructure());

            typeSousPeriodes.addAll(typeSousPeriodeFacadeLocal.findAllOrderByCode());
            if (!list.isEmpty()) {
                for (TypestructureTypeSousperiode cs : list) {
                    selectedTypeSousPeriodes.add(cs.getTypeSousPeriode());
                }
                typeSousPeriodes.removeAll(selectedTypeSousPeriodes);
                typestructureTypeSousperiodes.addAll(list);
                selectedTypeSousPeriodes.clear();
            }
        }
    }

    public void addTypeSousPeriodeToTable() {
        if (!selectedTypeSousPeriodes.isEmpty()) {
            List<TypeSousPeriode> list = new ArrayList();
            for (TypeSousPeriode t : selectedTypeSousPeriodes) {
                if (!checkTypeSousPeriodeInTable(t)) {                    
                    typestructureTypeSousperiodes.add(new TypestructureTypeSousperiode(0, typestructure, t));
                    list.add(t);
                }
            }
            typeSousPeriodes.removeAll(list);
            selectedTypeSousPeriodes.clear();
        }
    }

    private boolean checkTypeSousPeriodeInTable(TypeSousPeriode s) {
        boolean result = false;
        for (TypestructureTypeSousperiode ts : typestructureTypeSousperiodes) {
            if (s.getIdTypeSousperiode().equals(ts.getTypeSousPeriode().getIdTypeSousperiode())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeTypeSousPeriode(int index, TypestructureTypeSousperiode item) {
        if (item.getIdTypestructureTypesousperiode() != 0) {
            typestructureTypeSousperiodeFacadeLocal.remove(item);
            typestructureTypeSousperiodes.remove(item);
        } else {
            typestructureTypeSousperiodes.remove(index);
        }
        typeSousPeriodes.add(item.getTypeSousPeriode());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnTypeSousPeriode(Typestructure t) {
        String resultat = "";
        if (t.getTypestructureTypeSousperiodeCollection().isEmpty()) {
            t.setTypestructureTypeSousperiodeCollection(typestructureTypeSousperiodeFacadeLocal.findByIdTypestructure(t.getIdtypestructure()));
        }
        if (t.getTypestructureTypeSousperiodeCollection() != null) {
            int i = 0;
            for (TypestructureTypeSousperiode ts : t.getTypestructureTypeSousperiodeCollection()) {
                if (i == 0) {
                    resultat = "" + ts.getTypeSousPeriode().getNom() + " ";
                } else {
                    resultat += "\n" + ts.getTypeSousPeriode().getNom() + " ";
                }
                i++;
            }
        }
        return resultat;
    }

    @Transactional
    public void save() {
        try {
            if (typestructureTypeSousperiodes.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            typestructureTypeSousperiodes.forEach(ts -> {
                if (ts.getIdTypestructureTypesousperiode() == 0) {
                    ts.setIdTypestructureTypesousperiode(typestructureTypeSousperiodeFacadeLocal.nextId());
                    typestructureTypeSousperiodeFacadeLocal.create(ts);
                } else {
                    typestructureTypeSousperiodeFacadeLocal.edit(ts);
                }
            });

            this.typestructureTypeSousperiodes.clear();
            RequestContext.getCurrentInstance().execute("PF('TypestructureTspCreateDialog').hide()");
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
