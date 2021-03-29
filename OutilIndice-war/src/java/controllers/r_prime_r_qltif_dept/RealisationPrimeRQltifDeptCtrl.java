/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.r_prime_r_qltif_dept;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.EvaluationRPrimeQltifDept;
import entities.LignePrimeQualiteDept;
import entities.Service;
import entities.Souscritereservice;
import entities.Sousperiode;
import java.io.Serializable;
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
public class RealisationPrimeRQltifDeptCtrl extends AbstractRealisationPrimeRQltifDeptCtrl implements Serializable {

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
        services = SessionMBean.getServices();
        typeSousPeriodes = SessionMBean.getTypeSousPeriodes();
    }

    public void prepareCreate() {
        mode = "Create";
        sousperiode = new Sousperiode();
        service = new Service();
        evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept();
        lignePrimeQualiteDepts.clear();
        RequestContext.getCurrentInstance().execute("PF('RPrimeRQLtifCreateDialog').show()");
    }

    public void prepareEdit(EvaluationRPrimeQltifDept e) {
        this.evaluationRPrimeQltifDept = e;
        this.service = e.getIdservice();
        this.sousperiode = e.getIdsousperiode();
        mode = "Edit";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('RPrimeRQLtifEditDialog').show()");
    }

    public void updateFiltre() {
        souscritereservices.clear();
        selectedSouscritereservices.clear();
        lignePrimeQualiteDepts.clear();

        if (sousperiode.getIdsousperiode() != null && sousperiode.getIdsousperiode() > 0) {
            if (service.getIdservice() != null && service.getIdservice() > 0) {

                souscritereservices.addAll(souscritereserviceFacadeLocal.findByIdServiceIdCritere(structure.getIdstructure(), service.getIdservice(), 5));

                if (mode.equals("Create")) {
                    evaluationRPrimeQltifDept = evaluationRPrimeQltifDeptFacadeLocal.findByIdService(structure.getIdstructure(), service.getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 5);
                }
                if (evaluationRPrimeQltifDept != null) {
                    lignePrimeQualiteDepts = lignePrimeQualiteDeptFacadeLocal.findByIdEvaluation(evaluationRPrimeQltifDept.getIdevaluationrprimeqltifdept());
                    for (LignePrimeQualiteDept lpq : lignePrimeQualiteDepts) {
                        selectedSouscritereservices.add(lpq.getIdsouscritereservice());
                    }
                    souscritereservices.removeAll(selectedSouscritereservices);
                    selectedSouscritereservices.clear();
                } else {
                    evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept(0l);
                    evaluationRPrimeQltifDept.setIdservice(service);
                    evaluationRPrimeQltifDept.setIdperiode(SessionMBean.getPeriode());
                    evaluationRPrimeQltifDept.setIdsousperiode(sousperiode);
                    evaluationRPrimeQltifDept.setIdcritere(new Critere(5));
                }
            }
        }
    }

    public void addServiceToTable() {
        if (!selectedSouscritereservices.isEmpty()) {

            for (Souscritereservice s : selectedSouscritereservices) {
                LignePrimeQualiteDept lpq = new LignePrimeQualiteDept(0l);
                lpq.setIdsouscritereservice(s);
                lpq.setValeurCible(s.getPointmax());
                lpq.setValeurRealisee(0);
                lpq.setRatio(0);
                evaluationRPrimeQltifDept.setCible(evaluationRPrimeQltifDept.getCible() + s.getPointmax());
                lignePrimeQualiteDepts.add(lpq);
            }
            souscritereservices.removeAll(selectedSouscritereservices);
            selectedSouscritereservices.clear();
        }
    }

    public void removeSousCritere(int index) {
        LignePrimeQualiteDept lTemp = lignePrimeQualiteDepts.get(index);

        if (lTemp.getIdligneprimequalitedept() != 0l) {
            lignePrimeQualiteDeptFacadeLocal.remove(lTemp);
            lignePrimeQualiteDepts.remove(index);
            evaluationRPrimeQltifDepts = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 5);
            souscritereservices.add(lTemp.getIdsouscritereservice());
        } else {

            lignePrimeQualiteDepts.remove(index);
            souscritereservices.add(lTemp.getIdsouscritereservice());
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void calculData(String mode) {
        evaluationRPrimeQltifDept.setScore(0);
        evaluationRPrimeQltifDept.setPourcentage(0);
        if (!lignePrimeQualiteDepts.isEmpty()) {
            int i = 0;
            double sommeRatio = 0;
            int sommeRealisee = 0;
            for (LignePrimeQualiteDept lpq : lignePrimeQualiteDepts) {
                try {
                    if (mode.equals("valeur")) {
                        if (lpq.getValeurRealisee() > lpq.getValeurCible()) {
                            lpq.setValeurRealisee(lpq.getValeurCible());
                        }
                        Double r = ((Double.valueOf(lpq.getValeurRealisee()) / lpq.getValeurCible()) * 100);
                        lpq.setRatio(r);
                    } else if (mode.equals("ratio")) {
                        if (lpq.getRatio() > 100) {
                            lpq.setRatio(100);
                        }
                        Double r = ((Double.valueOf(lpq.getRatio() * lpq.getValeurCible())) / 100);
                        lpq.setValeurRealisee(r.intValue());
                    }
                    sommeRatio += lpq.getRatio();
                    sommeRealisee += lpq.getValeurRealisee();
                    lignePrimeQualiteDepts.set(i, lpq);
                } catch (Exception e) {
                    lignePrimeQualiteDepts.get(i).setRatio(0);
                }
                i++;
            }
            evaluationRPrimeQltifDept.setScore(sommeRealisee);
            if (sommeRatio > 0) {
                evaluationRPrimeQltifDept.setPourcentage(sommeRatio / lignePrimeQualiteDepts.size());
            }
        }
    }

    public void updateSousPeriode(String option) {
        sousperiodes.clear();
        sousperiode = new Sousperiode(0);

        if (option.equals("2")) {
            souscritereservices.clear();
            lignePrimeQualiteDepts.clear();
        }

        if (option.equals("1")) {
            evaluationRPrimeQltifDepts.clear();
        }

        if (typeSousPeriode.getIdTypeSousperiode() != 0) {
            sousperiodes = sousperiodeFacadeLocal.findIdTypeSousPeriode(typeSousPeriode.getIdTypeSousperiode());
        }
    }

    public void searchData() {
        evaluationRPrimeQltifDepts.clear();
        if (sousperiode.getIdsousperiode() != null) {
            evaluationRPrimeQltifDepts = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 5);
        }
    }

    public void save() {
        try {
            if (lignePrimeQualiteDepts.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            if (evaluationRPrimeQltifDept.getIdevaluationrprimeqltifdept() == 0) {
                evaluationRPrimeQltifDept.setIdevaluationrprimeqltifdept(evaluationRPrimeQltifDeptFacadeLocal.nextId());
                evaluationRPrimeQltifDept.setStructure(structure);
                evaluationRPrimeQltifDeptFacadeLocal.create(evaluationRPrimeQltifDept);
            } else {
                evaluationRPrimeQltifDeptFacadeLocal.edit(evaluationRPrimeQltifDept);
            }

            for (LignePrimeQualiteDept lpq : lignePrimeQualiteDepts) {
                if (lpq.getIdligneprimequalitedept() == 0l) {
                    lpq.setIdligneprimequalitedept(lignePrimeQualiteDeptFacadeLocal.nextId());
                    lpq.setIdEvaluationRPrimeQltifDept(evaluationRPrimeQltifDept);
                    lignePrimeQualiteDeptFacadeLocal.create(lpq);
                } else {
                    lignePrimeQualiteDeptFacadeLocal.edit(lpq);
                }
            }
            evaluationRPrimeQltifDepts = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 5);
            evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept();
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
            evaluationRPrimeQltifDepts = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 5);
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
            evaluationRPrimeQltifDepts = evaluationRPrimeQltifDeptFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 5);
            evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept();
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
