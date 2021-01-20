/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.r_prime_r_qltif_dept;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.EvaluationRPrimeQltifDept;
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
public class RealisationPrimeRQltifDeptCtrl extends AbstractRealisationPrimeRQltifDeptCtrl {

    /**
     * Creates a new instance of RealisationPrimeRQltifDeptCtrl
     */
    public RealisationPrimeRQltifDeptCtrl() {
    }

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept();
        evaluationRPrimeQltifDept.setIdservice(new Service());
    }

    public void prepareCreate() {
        mode = "Create";
        sousperiode = new Sousperiode();
        evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept();
        evaluationRPrimeQltifDept.setIdservice(new Service());
        evaluationRPrimeQltifDepts.clear();
        RequestContext.getCurrentInstance().execute("PF('RPrimeRQLtifCreateDialog').show()");
    }

    public void prepareEdit(EvaluationRPrimeQltifDept e) {
        this.evaluationRPrimeQltifDept = e;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('RPrimeRQLtifEditDialog').show()");
    }

    public void updateFiltre() {
        services.clear();
        selectedServices.clear();
        evaluationRPrimeQltifDepts.clear();

        if (sousperiode.getIdsousperiode() != null && sousperiode.getIdsousperiode() > 0) {
            List<EvaluationRPrimeQltifDept> list = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 5);
            if (list.isEmpty() || list == null) {
                services.addAll(serviceFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure()));
            } else {
                evaluationRPrimeQltifDepts.addAll(list);
                services.addAll(serviceFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure()));

                for (EvaluationRPrimeQltifDept e : list) {
                    selectedServices.add(e.getIdservice());
                }

                services.removeAll(selectedServices);
                selectedServices.clear();
            }
        }
    }

    public void addServiceToTable() {
        if (!selectedServices.isEmpty()) {
            for (Service s : selectedServices) {
                EvaluationRPrimeQltifDept e = new EvaluationRPrimeQltifDept();
                e.setIdevaluationrprimeqltifdept(0l);
                e.setIdcritere(new Critere(5));

                e.setIdperiode(periode);
                e.setIdsousperiode(sousperiode);
                e.setIdservice(s);
                e.setScore(0);
                evaluationRPrimeQltifDepts.add(e);
            }
            services.removeAll(selectedServices);
        }
    }

    public void removeIndicator(EvaluationRPrimeQltifDept e) {
        if (e.getIdevaluationrprimeqltifdept() != 0l) {
            evaluationRPrimeQltifDeptFacadeLocal.remove(e);
            evaluationRPrimeQltifDepts.remove(e);
            listEvaluationRPrimeQltifDepts = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 5);
            services.add(e.getIdservice());
        } else {
            int conteur = 0;
            for (EvaluationRPrimeQltifDept ev : evaluationRPrimeQltifDepts) {
                if (ev.getIdservice().getIdservice().equals(e.getIdservice().getIdservice())) {
                    break;
                }
                conteur++;
            }
            evaluationRPrimeQltifDepts.remove(conteur);
            services.add(e.getIdservice());
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void searchData() {
        listEvaluationRPrimeQltifDepts.clear();
        if (structure.getIdstructure() != null) {
            if (sousperiode.getIdsousperiode() != null) {
                listEvaluationRPrimeQltifDepts = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 5);
            }
        }
    }

    public void save() {
        try {
            if (evaluationRPrimeQltifDepts.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            for (EvaluationRPrimeQltifDept ev : evaluationRPrimeQltifDepts) {
                if (ev.getIdevaluationrprimeqltifdept() == 0l) {
                    ev.setIdevaluationrprimeqltifdept(evaluationRPrimeQltifDeptFacadeLocal.nextId());
                    evaluationRPrimeQltifDeptFacadeLocal.create(ev);
                } else {
                    evaluationRPrimeQltifDeptFacadeLocal.edit(ev);
                }
            }
            listEvaluationRPrimeQltifDepts = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 5);
            this.evaluationRPrimeQltifDepts.clear();
            evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept();
            evaluationRPrimeQltifDept.setIdservice(new Service());
            RequestContext.getCurrentInstance().execute("PF('RPrimeRQLtifCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void edit() {
        try {
            evaluationRPrimeQltifDeptFacadeLocal.edit(evaluationRPrimeQltifDept);
            evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept();
            evaluationRPrimeQltifDept.setIdservice(new Service());
            listEvaluationRPrimeQltifDepts = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 5);
            RequestContext.getCurrentInstance().execute("PF('RPrimeRQLtifEditDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(EvaluationRPrimeQltifDept ev) {
        try {
            evaluationRPrimeQltifDeptFacadeLocal.remove(ev);
            listEvaluationRPrimeQltifDepts = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 5);
            evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept();
            evaluationRPrimeQltifDept.setIdservice(new Service());
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
