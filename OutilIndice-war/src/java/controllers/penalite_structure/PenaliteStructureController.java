/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.penalite_structure;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.ParametragePenalite;
import entities.Penalite;
import entities.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class PenaliteStructureController extends AbstractPenaliteStructure implements Serializable {

    /**
     * Creates a new instance of PenaliteStructureController
     */
    public PenaliteStructureController() {
    }

    @PostConstruct
    private void init() {
        listParametragePenalites = parametragePenaliteFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), 10);
    }

    public void prepareCreate(String option) {
        mode = "Create";
        total = 0;
        if (option.equals("2")) {
            this.updateFiltre();
        }
    }

    public void prepareEdit(ParametragePenalite p) {
        this.parametragePenalite = p;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    public void updateFiltre() {
        penalites.clear();
        parametragePenalites.clear();
        selectedPenalites.clear();
        parametragePenalites = parametragePenaliteFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), 10);
        penalites = penaliteFacadeLocal.findAllPersonnel();
        if (!parametragePenalites.isEmpty()) {
            List<Penalite> list = new ArrayList<>();
            for (ParametragePenalite scs : parametragePenalites) {
                list.add(scs.getPenalite());
            }

            if (!penalites.isEmpty()) {
                penalites.removeAll(list);
            }
        }
        total = this.sommePenalites();
        RequestContext.getCurrentInstance().execute("PF('PenaliteCreateDialog').show()");
    }

    public void addPenaliteToTable() {
        if (!selectedPenalites.isEmpty()) {
            for (Penalite p : selectedPenalites) {
                if (!checkPenaliteInTable(p)) {
                    ParametragePenalite obj = new ParametragePenalite(0);
                    obj.setStructure(structure);
                    obj.setDetail(p.getDetail());
                    obj.setPenalite(p);
                    obj.setPourcentage(p.getPourcentage());
                    parametragePenalites.add(obj);
                }
            }
            total = this.sommePenalites();
            penalites.removeAll(selectedPenalites);
            selectedPenalites.clear();
        }
    }

    private boolean checkPenaliteInTable(Penalite p) {
        boolean result = false;
        for (ParametragePenalite scs : parametragePenalites) {
            if (scs.getPenalite().getIdpenalite().equals(p.getIdpenalite())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void prepareEditDetail(ParametragePenalite item) {
        this.parametragePenalite = item;
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').show()");
    }

    public void removePenalite(int index) {
        ParametragePenalite item = parametragePenalites.get(index);
        if (item.getIdParametragePenalite() != 0 && item.getIdParametragePenalite() != null) {
            parametragePenaliteFacadeLocal.remove(item);
        }
        parametragePenalites.remove(index);
        penalites.add(item.getPenalite());
        total = this.sommePenalites();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void editDetail() {
        if (parametragePenalite.getIdParametragePenalite() != null && parametragePenalite.getIdParametragePenalite() > 0) {
            int i = 0;
            for (ParametragePenalite p : parametragePenalites) {
                if (p.getPenalite().equals(parametragePenalite.getPenalite())) {
                    break;
                }
                i++;
            }
            parametragePenalites.set(i, parametragePenalite);
        } else {
            int i = 0;
            for (ParametragePenalite p : parametragePenalites) {
                if (p.getPenalite().equals(parametragePenalite.getPenalite())) {
                    break;
                }
                i++;
            }
            parametragePenalites.set(i, parametragePenalite);
        }
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').hide()");
    }

    public String returnPenalite(Service s) {
        String resultat = "";

        List<ParametragePenalite> list = parametragePenaliteFacadeLocal.findByIdServiceIdCritere(structure.getIdstructure(), s.getIdservice(), 9);

        int i = 0;
        for (ParametragePenalite p : list) {
            if (i == 0) {
                resultat = "" + p.getPenalite().getNom() + " (" + p.getPourcentage() + " %)";
            } else {
                resultat += "\n" + p.getPenalite().getNom() + " (" + p.getPourcentage() + " %)";;
            }
            i++;
        }
        return resultat;
    }

    public void save() {
        try {
            if (parametragePenalites.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            parametragePenalites.forEach(p -> {
                if (p.getIdParametragePenalite() == 0l) {
                    p.setIdParametragePenalite(parametragePenaliteFacadeLocal.nextId());
                    p.setStructure(structure);
                    p.setCritere(new Critere(10));
                    parametragePenaliteFacadeLocal.create(p);
                } else {
                    parametragePenaliteFacadeLocal.edit(p);
                }
            });

            this.parametragePenalites.clear();
            selectedPenalites.clear();
            penalites.clear();

            listParametragePenalites = parametragePenaliteFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), 10);

            RequestContext.getCurrentInstance().execute("PF('PenaliteCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void edit() {

    }

    public void delete(ParametragePenalite p) {
        try {
            parametragePenaliteFacadeLocal.remove(p);
            listParametragePenalites = parametragePenaliteFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), 10);
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private int sommePenalites() {
        if (parametragePenalites.isEmpty()) {
            return 0;
        }
        int resultat = 0;
        for (ParametragePenalite ssc : parametragePenalites) {
            resultat += ssc.getPourcentage();
        }
        return resultat;
    }

}
