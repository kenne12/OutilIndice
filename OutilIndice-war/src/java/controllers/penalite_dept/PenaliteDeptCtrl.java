/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.penalite_dept;

import controllers.util.JsfUtil;
import entities.EvaluationPenaliteDept;
import entities.LignePenaliteDept;
import entities.ParametragePenalite;
import entities.Penalite;
import entities.Service;
import entities.Sousperiode;
import entities.TypeSousPeriode;
import java.io.Serializable;
import java.util.ArrayList;
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
public class PenaliteDeptCtrl extends AbstractPenaliteDeptCtrl implements Serializable {

    /**
     * Creates a new instance of PenaliteDeptCtrl
     */
    public PenaliteDeptCtrl() {
    }

    @PostConstruct
    private void init() {
        services = SessionMBean.getServices();
        typeSousPeriodes = SessionMBean.getTypeSousPeriodes();
        if (criterestructureFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), true).isEmpty()) {
            JsfUtil.addErrorMessage("Veuillez valider les critères d'évaluation");
            return;
        }
        stateBtn = false;
    }

    public void prepareCreate() {
        mode = "Create";
        sousperiode = new Sousperiode();
        typeSousPeriode = new TypeSousPeriode(0);
        service = new Service();
        lignePenaliteDepts.clear();
        evaluationPenaliteDept = new EvaluationPenaliteDept();
        selectedPenalites.clear();
        RequestContext.getCurrentInstance().execute("PF('PenaliteDeptCreateDialog').show()");
    }

    public void prepareEdit(EvaluationPenaliteDept e) {
        this.evaluationPenaliteDept = e;
        this.service = e.getIdservice();
        this.sousperiode = e.getIdsousperiode();
        mode = "Edit";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('PenaliteDeptCreateDialog').show()");
    }

    private List<Penalite> extractPenaliteInParam(List<ParametragePenalite> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<Penalite> listPenalite = new ArrayList<>();
        for (ParametragePenalite p : list) {
            listPenalite.add(p.getPenalite());
        }
        return listPenalite;
    }

    public void updateFiltre() {
        penalites.clear();
        selectedPenalites.clear();
        lignePenaliteDepts.clear();
        if (sousperiode.getIdsousperiode() != null && sousperiode.getIdsousperiode() > 0) {
            if (service.getIdservice() != null && service.getIdservice() > 0) {
                if (mode.equals("Create")) {
                    evaluationPenaliteDept = evaluationPenaliteDeptFacadeLocal.findByIdService(structure.getIdstructure(), service.getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                }

                if (evaluationPenaliteDept != null) {
                    lignePenaliteDepts = lignePenaliteDeptFacadeLocal.findByIdEvaluationPenaliteDept(evaluationPenaliteDept.getIdevaluationpenalitedept());

                    List<ParametragePenalite> parametragePenalites = parametragePenaliteFacadeLocal.findByIdServiceIdCritere(structure.getIdstructure(), service.getIdservice(), 9);
                    penalites = this.extractPenaliteInParam(parametragePenalites);
                    if (!lignePenaliteDepts.isEmpty()) {
                        for (LignePenaliteDept lpd : lignePenaliteDepts) {
                            selectedPenalites.add(lpd.getIdpenalite());
                        }
                        penalites.removeAll(selectedPenalites);
                        selectedPenalites.clear();
                    } else {
                        if (!penalites.isEmpty()) {
                            for (Penalite p : penalites) {
                                LignePenaliteDept lpd = new LignePenaliteDept(0l);
                                lpd.setEtat(false);
                                lpd.setValeur(0);
                                lpd.setIdpenalite(p);
                                lignePenaliteDepts.add(lpd);
                            }
                            penalites.clear();
                        }
                    }
                } else {
                    evaluationPenaliteDept = new EvaluationPenaliteDept(0l);
                    evaluationPenaliteDept.setIdservice(service);
                    evaluationPenaliteDept.setIdperiode(SessionMBean.getPeriode());
                    evaluationPenaliteDept.setIdsousperiode(sousperiode);

                    List<ParametragePenalite> parametragePenalites = parametragePenaliteFacadeLocal.findByIdServiceIdCritere(structure.getIdstructure(), service.getIdservice(), 9);
                    penalites = this.extractPenaliteInParam(parametragePenalites);

                    if (!penalites.isEmpty()) {
                        for (Penalite p : penalites) {
                            LignePenaliteDept lpd = new LignePenaliteDept(0l);
                            lpd.setEtat(false);
                            lpd.setValeur(0);
                            lpd.setIdpenalite(p);
                            lignePenaliteDepts.add(lpd);
                        }
                    }
                    penalites.clear();
                }
            }
        }
    }

    public void updateValue(LignePenaliteDept item) {
        int counter = 0;
        for (int i = 0; i < lignePenaliteDepts.size(); i++) {
            if (lignePenaliteDepts.get(i).getIdpenalite().getIdpenalite().equals(item.getIdpenalite().getIdpenalite())) {
                counter = i;
                break;
            }
        }
        if (item.isEtat()) {
            lignePenaliteDepts.get(counter).setValeur(item.getIdpenalite().getPourcentage());
        } else {
            lignePenaliteDepts.get(counter).setValeur(0);
        }
    }

    public void updateSousPeriode(String option) {
        sousperiodes.clear();
        sousperiode = new Sousperiode(0);

        if (option.equals("2")) {
            penalites.clear();
            lignePenaliteDepts.clear();
        }

        if (option.equals("1")) {
            evaluationPenaliteDepts.clear();
        }

        if (typeSousPeriode.getIdTypeSousperiode() != 0) {
            sousperiodes = sousperiodeFacadeLocal.findIdTypeSousPeriode(typeSousPeriode.getIdTypeSousperiode());
        }
    }

    public void searchData() {
        try {
            evaluationPenaliteDepts.clear();
            if (sousperiode.getIdsousperiode() != null) {
                evaluationPenaliteDepts = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPenaliteToTable() {
        if (!selectedPenalites.isEmpty()) {
            for (Penalite p : selectedPenalites) {
                LignePenaliteDept lpd = new LignePenaliteDept(0l);
                lpd.setEtat(false);
                lpd.setIdpenalite(p);
                lpd.setValeur(0);
                lignePenaliteDepts.add(lpd);
            }
            penalites.removeAll(selectedPenalites);
            selectedPenalites.clear();
        }
    }

    public void removePenalite(int index) {
        LignePenaliteDept l = lignePenaliteDepts.get(index);
        if (l.getIdlignepenalitedept() != 0l) {
            lignePenaliteDeptFacadeLocal.remove(l);
        }
        lignePenaliteDepts.remove(index);
        penalites.add(l.getIdpenalite());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void save() {
        try {
            this.calculTotal();
            if (evaluationPenaliteDept.getIdevaluationpenalitedept() == 0) {
                evaluationPenaliteDept.setIdevaluationpenalitedept(evaluationPenaliteDeptFacadeLocal.nextId());
                evaluationPenaliteDept.setStructure(structure);
                evaluationPenaliteDeptFacadeLocal.create(evaluationPenaliteDept);
            } else {
                evaluationPenaliteDeptFacadeLocal.edit(evaluationPenaliteDept);
            }
            for (LignePenaliteDept lpd : lignePenaliteDepts) {
                if (lpd.getIdlignepenalitedept() == 0l) {
                    lpd.setIdlignepenalitedept(lignePenaliteDeptFacadeLocal.nextId());
                    lpd.setIdevaluationpenalitedept(evaluationPenaliteDept);
                    lignePenaliteDeptFacadeLocal.create(lpd);
                } else {
                    lignePenaliteDeptFacadeLocal.edit(lpd);
                }
            }
            evaluationPenaliteDepts = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            evaluationPenaliteDept = new EvaluationPenaliteDept();
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
            evaluationPenaliteDepts = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            RequestContext.getCurrentInstance().execute("PF('PenaliteDeptEditDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(EvaluationPenaliteDept e) {
        try {
            evaluationPenaliteDeptFacadeLocal.remove(e);
            lignePenaliteDeptFacadeLocal.deleteByIdEvaluation(e.getIdevaluationpenalitedept());
            evaluationPenaliteDepts = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception ex) {
            ex.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void calculTotal() {
        if (!lignePenaliteDepts.isEmpty()) {
            int valeur = 0;
            for (LignePenaliteDept lpd : lignePenaliteDepts) {
                valeur += lpd.getValeur();
            }
            evaluationPenaliteDept.setValeur(valeur);
            return;
        }
        evaluationPenaliteDept.setValeur(0);
    }

}
