/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.penalite_dept;

import controllers.util.JsfUtil;
import entities.EvaluationPenaliteDept;
import entities.Service;
import entities.Sousperiode;
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
public class PenaliteDeptCtrl extends AbstractPenaliteDeptCtrl {

    /**
     * Creates a new instance of PenaliteDeptCtrl
     */
    public PenaliteDeptCtrl() {
    }

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        evaluationPenaliteDept = new EvaluationPenaliteDept();
        evaluationPenaliteDept.setIdservice(new Service());
    }

    public void prepareCreate() {
        mode = "Create";
        sousperiode = new Sousperiode();
        evaluationPenaliteDept = new EvaluationPenaliteDept();
        evaluationPenaliteDept.setIdservice(new Service());
        evaluationPenaliteDepts.clear();
        RequestContext.getCurrentInstance().execute("PF('PenaliteDeptCreateDialog').show()");
    }

    public void prepareEdit(EvaluationPenaliteDept c) {
        this.evaluationPenaliteDept = c;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('PenaliteDeptEditDialog').show()");
    }

    public void updateFiltre() {
        evaluationPenaliteDepts.clear();
        services.clear();
        selectedServices.clear();
        if (sousperiode.getIdsousperiode() != null && sousperiode.getIdsousperiode() > 0) {
            List<EvaluationPenaliteDept> list = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
            if (list.isEmpty() || list == null) {
                services.addAll(serviceFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure()));
            } else {
                evaluationPenaliteDepts.addAll(list);
                services.addAll(serviceFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure()));

                for (EvaluationPenaliteDept e : list) {
                    selectedServices.add(e.getIdservice());
                }

                services.removeAll(selectedServices);
                selectedServices.clear();
            }
        }
    }

    public void searchData() {
        try {
            listEvaluationPenaliteDepts.clear();
            if (sousperiode.getIdsousperiode() != null) {
                listEvaluationPenaliteDepts = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addServiceToTable() {
        if (!selectedServices.isEmpty()) {
            for (Service s : selectedServices) {
                EvaluationPenaliteDept e = new EvaluationPenaliteDept();
                e.setIdevaluationpenalitedept(0l);
                e.setIdperiode(periode);
                e.setIdservice(s);
                e.setIdsousperiode(sousperiode);
                e.setValeur(0);
                evaluationPenaliteDepts.add(e);
            }
            services.removeAll(selectedServices);
        }
    }

    public void removeService(EvaluationPenaliteDept e) {
        if (e.getIdevaluationpenalitedept() != 0l) {
            evaluationPenaliteDeptFacadeLocal.remove(e);
            evaluationPenaliteDepts.remove(e);
            listEvaluationPenaliteDepts = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
            services.add(e.getIdservice());
        } else {
            int conteur = 0;
            for (EvaluationPenaliteDept ev : evaluationPenaliteDepts) {
                if (ev.getIdservice().getIdservice().equals(e.getIdservice().getIdservice())) {
                    break;
                }
                conteur++;
            }
            evaluationPenaliteDepts.remove(conteur);
            services.add(e.getIdservice());
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void save() {
        try {
            if (evaluationPenaliteDepts.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            for (EvaluationPenaliteDept c : evaluationPenaliteDepts) {
                if (c.getIdevaluationpenalitedept() == 0l) {
                    c.setIdevaluationpenalitedept(evaluationPenaliteDeptFacadeLocal.nextId());
                    evaluationPenaliteDeptFacadeLocal.create(c);
                } else {
                    evaluationPenaliteDeptFacadeLocal.edit(c);
                }
            }
            listEvaluationPenaliteDepts = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
            this.evaluationPenaliteDepts.clear();
            evaluationPenaliteDept = new EvaluationPenaliteDept();
            evaluationPenaliteDept.setIdservice(new Service());
            RequestContext.getCurrentInstance().execute("PF('PenaliteDeptCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void edit() {
        try {
            evaluationPenaliteDeptFacadeLocal.edit(evaluationPenaliteDept);
            evaluationPenaliteDept = new EvaluationPenaliteDept();
            evaluationPenaliteDept.setIdservice(new Service());
            listEvaluationPenaliteDepts = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
            RequestContext.getCurrentInstance().execute("PF('PenaliteDeptEditDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(EvaluationPenaliteDept c) {
        try {
            evaluationPenaliteDeptFacadeLocal.remove(c);
            listEvaluationPenaliteDepts = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode());
            evaluationPenaliteDept = new EvaluationPenaliteDept();
            evaluationPenaliteDept.setIdservice(new Service());
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
