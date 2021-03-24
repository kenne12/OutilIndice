/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.penalite_service;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.Criterestructure;
import entities.Service;
import entities.Penalite;
import entities.ParametragePenalite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;
import utils.Utilitaires;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class PenaliteServiceController extends AbstractPenaliteService implements Serializable {

    /**
     * Creates a new instance of PenaliteServiceController
     */
    public PenaliteServiceController() {
    }

    @PostConstruct
    private void init() {
        services = SessionMBean.getServices();
    }

    public void prepareCreate() {
        Criterestructure criterestructure = Utilitaires.findCritereSInSession(9);
        if (criterestructure == null) {
            JsfUtil.addWarningMessage("La prime de resultat qualitatif de departement de fait pas partie des critères de cette structure");
            return;
        }

        penalites.clear();
        selectedPenalites.clear();
        total = 0;
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    public void prepareEdit(Service s) {
        this.service = s;
        mode = "Edit";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    public void updateFiltre() {
        penalites.clear();
        parametragePenalites.clear();
        selectedPenalites.clear();
        parametragePenalites = parametragePenaliteFacadeLocal.findByIdServiceIdCritere(structure.getIdstructure(), service.getIdservice(), 9);
        penalites = penaliteFacadeLocal.findAllService();
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
    }

    public void addSousCritereToTable() {
        if (!selectedPenalites.isEmpty()) {
            for (Penalite p : selectedPenalites) {
                if (!checkCritereInTable(p)) {
                    ParametragePenalite obj = new ParametragePenalite(0);
                    obj.setService(service);
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

    private boolean checkCritereInTable(Penalite p) {
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

    public void removeCritere(ParametragePenalite item) {
        if (item.getIdParametragePenalite() != 0 && item.getIdParametragePenalite() != null) {
            parametragePenaliteFacadeLocal.remove(item);
            parametragePenalites.remove(item);
        } else {
            int conteur = 0;
            for (ParametragePenalite p : parametragePenalites) {
                if (Objects.equals(item.getPenalite().getIdpenalite(), p.getPenalite().getIdpenalite())) {
                    break;
                }
                conteur++;
            }
            parametragePenalites.remove(conteur);
        }
        total = this.sommePenalites();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void editDetail() {
        if (parametragePenalite.getIdParametragePenalite() != null && parametragePenalite.getIdParametragePenalite() > 0) {
            int i = 0;
            for (ParametragePenalite p : parametragePenalites) {
                if (p.getIdParametragePenalite().equals(parametragePenalite.getIdParametragePenalite())) {
                    break;
                }
                i++;
            }
            parametragePenalites.set(i, parametragePenalite);
        } else {
            int i = 0;
            for (ParametragePenalite p : parametragePenalites) {
                if (p.getIdParametragePenalite().equals(parametragePenalite.getIdParametragePenalite())) {
                    break;
                }
                i++;
            }
            parametragePenalites.set(i, parametragePenalite);
        }
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').hide()");
    }

    public String returnCritere(Service s) {
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
                    p.setCritere(new Critere(9));
                    parametragePenaliteFacadeLocal.create(p);
                } else {
                    parametragePenaliteFacadeLocal.edit(p);
                }
            });

            this.parametragePenalites.clear();
            selectedPenalites.clear();
            penalites.clear();

            RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Penalite sc) {
        try {
            penaliteFacadeLocal.remove(sc);
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
