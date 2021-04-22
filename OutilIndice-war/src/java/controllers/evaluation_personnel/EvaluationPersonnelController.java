/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.evaluation_personnel;

import controllers.util.JsfUtil;
import entities.Categorie;
import entities.Cible;
import entities.Critere;
import entities.Critereresponsabilite;
import entities.Detailsc;
import entities.Elementreponse;
import entities.EvaluationBonusRDeptPersonnel;
import entities.EvaluationPenaliteDept;
import entities.EvaluationPenalitePersonnel;
import entities.EvaluationRPrimeQltifDept;
import entities.EvaluationRPrimeQltifPersonnel;
import entities.Evaluationbonuspp;
import entities.Evaluationheuresupp;
import entities.Evaluationpersonnel;
import entities.Evaluationresponsabilite;
import entities.Evaluationrqntifdept;
import entities.LignePenalitePersonnel;
import entities.Note;
import entities.ParametragePenalite;
import entities.Parametragecritere;
import entities.Penalite;
import entities.Periode;
import entities.Personnel;
import entities.Responsabilite;
import entities.Service;
import entities.Sousperiode;
import entities.TypeSousPeriode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.MappingResultat;
import utils.Printer;
import utils.SessionMBean;
import utils.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class EvaluationPersonnelController extends AbstractEvaluationPersonnel implements Serializable {
    
    public EvaluationPersonnelController() {
        
    }
    
    @PostConstruct
    private void init() {
        this.initNote();
        if (criterestructureFacade.findByIdStructure(SessionMBean.getStructure().getIdstructure(), true).isEmpty()) {
            JsfUtil.addErrorMessage("Veuillez valider les critères d'évaluation");
            return;
        }
        stateBtn = false;
        this.checkCritere();
        typeSousPeriodes = SessionMBean.getTypeSousPeriodes();
    }
    
    private void initNote() {
        note = new Note(0l);
        note.setIdperiode(new Periode());
        note.setIdpersonnel(new Personnel());
        note.setIdsousperiode(new Sousperiode());
    }
    
    private void initPersonnel() {
        personnel = new Personnel();
        personnel.setIdpersonnel(0l);
        personnel.setIdcategorie(new Categorie());
        personnel.setIdservice(new Service());
        personnel.setIdresponsabilite(new Responsabilite());
    }
    
    public void prepareCreate() {
        if (criteres.isEmpty()) {
            signalError("definir_les_critere_evaluation");
            return;
        }
        mode = "Create";
        this.initNote();
        this.initPersonnel();
        sousperiode = new Sousperiode(0);
        typeSousPeriode = new TypeSousPeriode(0);
        listDetailsc.clear();
        evaluationpersonnels.clear();
        evaluationrqntifdepts.clear();
        cibleRqntifs.clear();
        note.setNotePi(0);
        note.setScorePIndiv(0);
        note.setPointPIndiv(0);
        message = "";
        evaluationheuresupp = new Evaluationheuresupp();
        evaluationheuresuppN = new Evaluationheuresupp();
        critereresponsabilite = new Critereresponsabilite();
        parametragecritereBpp = new Parametragecritere();
        evaluationbonuspp = new Evaluationbonuspp();
        evaluationPenaliteDept = new EvaluationPenaliteDept();
        evaluationPenalitePersonnel = new EvaluationPenalitePersonnel();
        evaluationresponsabilite = new Evaluationresponsabilite();
        evaluationbonuspp = new Evaluationbonuspp();
        lignePenalitePersonnels.clear();
        note.setIncitationPositif(0);
        note.setIncitationNegatif(0);
        this.mappingResultats = MappingResultat.getMapping();
        RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').show()");
    }
    
    public void filterData() {
        notes.clear();
        statePrintBtn = false;
        validBtn = false;
        if (sousperiode.getIdsousperiode() > 0) {
            notes = noteFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            
            if (notes.isEmpty()) {
                statePrintBtn = true;
                JsfUtil.addWarningMessage("Aucune donnée trouvée");
                return;
            }
            if (notes.get(0).isEtat()) {
                validBtn = false;
            }
        }
    }
    
    public void updateSousPeriode(String option) {
        sousperiodes.clear();
        sousperiode = new Sousperiode(0);
        if (option.equals("1")) {
            notes.clear();
        }
        
        if (typeSousPeriode.getIdTypeSousperiode() != 0) {
            sousperiodes = sousperiodeFacadeLocal.findIdTypeSousPeriode(typeSousPeriode.getIdTypeSousperiode());
        }
    }
    
    private void prepareEditData(Note n) {
        mode = "Edit";
        note = n;
        personnel = n.getIdpersonnel();
        sousperiode = n.getIdsousperiode();
        message = "";
        mappingResultats = MappingResultat.getMapping();
        this.setResult(mappingResultats, n);
        
        if (criter1) {
            evaluationresponsabilite = evaluationresponsabiliteFacadeLocal.findByIdPersonnel(n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode(), n.getIdpersonnel().getIdpersonnel(), 1);
            critereresponsabilite = critereresponsabiliteFacadeLocal.findByIdResponsabilite(SessionMBean.getStructure().getIdstructure(), personnel.getIdresponsabilite().getIdresponsabilite(), 1);
        }
        
        if (criter2) {
            evaluationheuresupp = evaluationheuresuppFacadeLocal.findByIdPersonnel(n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode(), n.getIdpersonnel().getIdpersonnel(), 2);
            parametragecritereHsp = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 2, personnel.getIdcategorie().getIdcategorie());
        }
        
        if (criter3) {
            evaluationbonuspp = evaluationbonusppFacadeLocal.findByIdPersonnel(n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode(), n.getIdpersonnel().getIdpersonnel(), 3);
            parametragecritereBpp = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 3, personnel.getIdcategorie().getIdcategorie());
        }
        
        if (criter4) {
            evaluationrqntifdepts = evaluationrqntifdeptFacadeLocal.findByIdPersonnel(n.getIdpersonnel().getIdpersonnel(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode(), 4);
            sommeDetailEvRqnDept(evaluationrqntifdepts);
            parametragecriterePrqn = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 4, personnel.getIdcategorie().getIdcategorie());
        }
        
        if (criter5) {
            evaluationRPrimeQltifDept = evaluationRPrimeQltifDeptFacadeLocal.findByIdService(n.getIdpersonnel().getStructure().getIdstructure(), n.getIdpersonnel().getIdservice().getIdservice(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode(), 5);
            evaluationRPrimeQltifPersonnel = evaluationRPrimeQltifPersonnelFacadeLocal.findByIdPersonnel(n.getIdpersonnel().getIdpersonnel(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode());
            parametragecriterePrq = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 5, personnel.getIdcategorie().getIdcategorie());
        }
        
        if (criter6) {
            cibleBrd = cibleFacadeLocal.findByIdSousPeriodeOneLine(n.getIdpersonnel().getStructure().getIdstructure(), n.getIdpersonnel().getIdservice().getIdservice(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode(), 6);
            evaluationBonusRDeptPersonnel = evaluationBonusRDeptPersonnelFacadeLocal.findByIdPersonnel(n.getIdpersonnel().getIdpersonnel(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode());
            parametragecritereBrd = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 6, personnel.getIdcategorie().getIdcategorie());
        }
        
        if (criter7) {
            evaluationpersonnels = evaluationpersonnelFacadeLocal.findByPersonnel(n.getIdpersonnel().getIdpersonnel(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode());
            List<Detailsc> listDetail = detailscFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), 7);
            totalPointPi = sommeTotalSc(listDetail);
            parametragecritere = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 7, personnel.getIdcategorie().getIdcategorie());
        }
        
        if (criter8) {
            evaluationheuresuppN = evaluationheuresuppFacadeLocal.findByIdPersonnel(n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode(), n.getIdpersonnel().getIdpersonnel(), 8);
            parametragecritereHsn = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 8, personnel.getIdcategorie().getIdcategorie());
        }
        
        if (criter9) {
            evaluationPenaliteDept = evaluationPenaliteDeptFacadeLocal.findByIdService(n.getIdpersonnel().getStructure().getIdstructure(), n.getIdpersonnel().getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), n.getIdsousperiode().getIdsousperiode());
        }
        
        if (criter10) {
            evaluationPenalitePersonnel = evaluationPenalitePersonnelFacade.findIdPersonnelIdPeriode(n.getIdpersonnel().getIdpersonnel(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode());
            if (evaluationPenalitePersonnel != null) {
                lignePenalitePersonnels = lignePenalitePersonnelFacadeLocal.findByIdEvaluation(evaluationPenalitePersonnel.getIdevaluationpenalitepersonnel());
            }
        }
    }
    
    public void prepareEdit(Note n) {
        this.prepareEditData(n);
        RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').show()");
    }
    
    private void setResult(List<MappingResultat> mappingResultats, Note n) {
        mappingResultats.get(0).setPoint(n.getPointResponsabilite());
        mappingResultats.get(1).setPoint(n.getPointHeureSupp());
        mappingResultats.get(2).setPoint(n.getPointPratiqueP());
        mappingResultats.get(3).setPoint(n.getPointRqntif());
        mappingResultats.get(4).setPoint(n.getPointRQltifDept());
        mappingResultats.get(5).setPoint(n.getPointBonusRDept());
        mappingResultats.get(6).setPoint(n.getPointPIndiv());
        mappingResultats.get(7).setPoint(n.getIncitationNHP());
        mappingResultats.get(8).setPoint(n.getPointPenaliteDepartement());
        mappingResultats.get(9).setPoint(n.getPointPenalitePersonnel());
    }
    
    public void prepareView(Note n) {
        this.note = n;
        this.evaluationpersonnels.clear();
        List<Evaluationpersonnel> list = evaluationpersonnelFacadeLocal.findByPersonnel(n.getIdpersonnel().getIdpersonnel(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode());
        
        if (!list.isEmpty()) {
            for (Evaluationpersonnel evp : list) {
                this.evaluationpersonnels.add(evp);
            }
        }
        
        this.sommeNotePi();
        RequestContext.getCurrentInstance().execute("PF('EvaluationDetailDialog').show()");
    }
    
    public void closeDetail() {
        evaluationpersonnels.clear();
        this.initNote();
        RequestContext.getCurrentInstance().execute("PF('ViewEditDialog').hide()");
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
    
    public void updateEvaluationData() {
        evaluationpersonnels.clear();
        listDetailsc.clear();
        selectedDetailsc.clear();
        note.setNotePi(0);
        if (personnel.getIdpersonnel() > 0) {
            if (sousperiode.getIdsousperiode() > 0) {
                personnel = personnelFacadeLocal.find(personnel.getIdpersonnel());
                
                Note noteTemp = noteFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                if (noteTemp == null) {
                    this.initNote();

                    // idCritere = 7
                    if (criter7) {
                        parametragecritere = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 7, personnel.getIdcategorie().getIdcategorie());
                        if (parametragecritere != null) {
                            List<Detailsc> listDetail = detailscFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), 7);
                            totalPointPi = sommeTotalSc(listDetail);
                            if (!listDetail.isEmpty()) {
                                note.setPointMaxPi(parametragecritere.getPoint());
                                this.listDetailsc.clear();
                                this.listDetailsc.addAll(listDetail);
                                
                                List<Evaluationpersonnel> list = evaluationpersonnelFacadeLocal.findByPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                                List<Detailsc> adds = new ArrayList<>();
                                if (list.isEmpty()) {
                                    for (Detailsc d : listDetail) {
                                        Evaluationpersonnel ev = new Evaluationpersonnel();
                                        ev.setIdelementreponse(new Elementreponse());
                                        d.getIdsouscritere().setElementreponseCollection(elementReponseFacadeLocal.findByIdSousCritere(d.getIdsouscritere().getIdsouscritere()));
                                        
                                        if (!d.getIdsouscritere().getElementreponseCollection().isEmpty()) {
                                            List<Elementreponse> ers = (List) d.getIdsouscritere().getElementreponseCollection();
                                            ev.setIdelementreponse(ers.get(0));
                                            ev.setNote(0d);
                                        }
                                        
                                        ev.setIdevaluationpersonnel(0l);
                                        ev.setIddetailsc(d);
                                        ev.setObservation("---");
                                        evaluationpersonnels.add(ev);
                                        adds.add(d);
                                    }
                                    this.listDetailsc.removeAll(adds);
                                } else {
                                    for (Evaluationpersonnel evp : list) {
                                        evaluationpersonnels.add(evp);
                                        adds.add(evp.getIddetailsc());
                                    }
                                    listDetailsc.removeAll(adds);
                                }
                            }
                        } else {
                            parametragecritere = new Parametragecritere(-1l);
                        }
                    }

                    // idCritere = 1
                    if (criter1) {
                        critereresponsabilite = critereresponsabiliteFacadeLocal.findByIdResponsabilite(SessionMBean.getStructure().getIdstructure(), personnel.getIdresponsabilite().getIdresponsabilite(), 1);
                        if (critereresponsabilite != null) {
                            evaluationresponsabilite = evaluationresponsabiliteFacadeLocal.findByIdPersonnel(SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), personnel.getIdpersonnel(), 1);
                            if (evaluationresponsabilite == null) {
                                evaluationresponsabilite = new Evaluationresponsabilite(0l);
                                evaluationresponsabilite.setIdcritere(critereresponsabilite.getIdcritere());
                                evaluationresponsabilite.setPointMax(critereresponsabilite.getPoint());
                            }
                        } else {
                            critereresponsabilite.setIdcritereresponsabilite(-1l);
                        }
                    }

                    // idCritere = 2
                    if (criter2) {
                        parametragecritereHsp = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 2, personnel.getIdcategorie().getIdcategorie());
                        if (parametragecritereHsp != null) {
                            evaluationheuresupp = evaluationheuresuppFacadeLocal.findByIdPersonnel(SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), personnel.getIdpersonnel(), 2);
                            if (evaluationheuresupp == null) {
                                evaluationheuresupp = new Evaluationheuresupp(0l);
                                evaluationheuresupp.setIdcritere(parametragecritereHsp.getIdcritere());
                                evaluationheuresupp.setCoefnuit(parametragecritereHsp.getValeurnuit());
                                evaluationheuresupp.setCoefjour(parametragecritereHsp.getValeurjournee());
                            }
                        } else {
                            parametragecritereHsp = new Parametragecritere(-1l);
                        }
                    }

                    // idCritère = 3
                    if (criter3) {
                        parametragecritereBpp = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 3, personnel.getIdcategorie().getIdcategorie());
                        if (parametragecritereBpp != null) {
                            evaluationbonuspp = evaluationbonusppFacadeLocal.findByIdPersonnel(SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), personnel.getIdpersonnel(), 3);
                            if (evaluationbonuspp == null) {
                                evaluationbonuspp = new Evaluationbonuspp(0l);
                                evaluationbonuspp.setIdcritere(parametragecritereBpp.getIdcritere());
                                evaluationbonuspp.setPointMax(parametragecritereBpp.getPoint());
                            }
                        } else {
                            parametragecritereBpp.setIdparametragecritere(-1l);
                        }
                    }

                    // idCritere 4
                    if (criter4) {
                        parametragecriterePrqn = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 4, personnel.getIdcategorie().getIdcategorie());
                        if (parametragecriterePrqn != null) {
                            cibleRqntifs = cibleFacadeLocal.findByIdSousPeriode(structure.getIdstructure(), personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 4);
                            if (!cibleRqntifs.isEmpty()) {
                                evaluationrqntifdepts = evaluationrqntifdeptFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 4);
                                if (evaluationrqntifdepts.isEmpty()) {
                                    note.setPointMaxRQntif(parametragecriterePrqn.getPoint());
                                    for (Cible c : cibleRqntifs) {
                                        Evaluationrqntifdept ev = new Evaluationrqntifdept(0l);
                                        ev.setCible(c.getValeurcible());
                                        ev.setRealisation(0);
                                        ev.setRatio(0);
                                        ev.setIdcible(c);
                                        ev.setIdpersonnel(personnel);
                                        evaluationrqntifdepts.add(ev);
                                    }
                                }
                                sommeDetailEvRqnDept(evaluationrqntifdepts);
                            }
                        } else {
                            parametragecriterePrqn = new Parametragecritere(-1l);
                        }
                    }

                    // idCritere = 5
                    if (criter5) {
                        parametragecriterePrq = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 5, personnel.getIdcategorie().getIdcategorie());
                        if (parametragecriterePrq != null) {
                            evaluationRPrimeQltifDept = evaluationRPrimeQltifDeptFacadeLocal.findByIdService(structure.getIdstructure(), personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 5);
                            if (evaluationRPrimeQltifDept != null) {
                                evaluationRPrimeQltifPersonnel = evaluationRPrimeQltifPersonnelFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                                if (evaluationRPrimeQltifPersonnel == null) {
                                    evaluationRPrimeQltifPersonnel = new EvaluationRPrimeQltifPersonnel(0l);
                                    evaluationRPrimeQltifPersonnel.setIdevaluationrprimeqltifdept(evaluationRPrimeQltifDept);
                                    evaluationRPrimeQltifPersonnel.setIdpersonnel(personnel);
                                    evaluationRPrimeQltifPersonnel.setPoint(Math.ceil((parametragecriterePrq.getPoint() * evaluationRPrimeQltifDept.getPourcentage()) / 100));
                                    evaluationRPrimeQltifPersonnel.setPointMax(parametragecriterePrq.getPoint());
                                    note.setPointMaxPrQltif(parametragecriterePrq.getPoint());
                                    note.setScorePrQltif(evaluationRPrimeQltifDept.getPourcentage());
                                }
                                mappingResultats.get(4).setPoint(evaluationRPrimeQltifPersonnel.getPoint());
                            } else {
                                evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept(-1l);
                            }
                        } else {
                            parametragecriterePrq = new Parametragecritere(-1l);
                        }
                    }

                    // idCritere = 6
                    if (criter6) {
                        parametragecritereBrd = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 6, personnel.getIdcategorie().getIdcategorie());
                        if (parametragecritereBrd != null) {
                            note.setPointMaxBrd(parametragecritereBrd.getPoint());
                            cibleBrd = cibleFacadeLocal.findByIdSousPeriodeOneLine(structure.getIdstructure(), personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 6);
                            if (cibleBrd != null) {
                                evaluationBonusRDeptPersonnel = evaluationBonusRDeptPersonnelFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                                if (evaluationBonusRDeptPersonnel == null) {
                                    evaluationBonusRDeptPersonnel = new EvaluationBonusRDeptPersonnel(0l);
                                    evaluationBonusRDeptPersonnel.setIdcible(cibleBrd);
                                    evaluationBonusRDeptPersonnel.setIdnote(note);
                                    evaluationBonusRDeptPersonnel.setPoint(Math.ceil((parametragecritereBrd.getPoint() * cibleBrd.getRatio()) / 100));
                                    evaluationBonusRDeptPersonnel.setPointMax(parametragecritereBrd.getPoint());
                                    note.setScoreBrd(cibleBrd.getRatio());
                                }
                                mappingResultats.get(5).setPoint(evaluationBonusRDeptPersonnel.getPoint());
                            } else {
                                cibleBrd = new Cible(-1l);
                            }
                        } else {
                            parametragecritereBrd = new Parametragecritere(-1l);
                        }
                    }

                    // idCritere = 8
                    if (criter8) {
                        parametragecritereHsn = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 8, personnel.getIdcategorie().getIdcategorie());
                        if (parametragecritereHsn != null) {
                            evaluationheuresuppN = evaluationheuresuppFacadeLocal.findByIdPersonnel(SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), personnel.getIdpersonnel(), 8);
                            if (evaluationheuresuppN == null) {
                                evaluationheuresuppN = new Evaluationheuresupp(0l);
                                evaluationheuresuppN.setIdcritere(parametragecritereHsn.getIdcritere());
                                evaluationheuresuppN.setCoefnuit(parametragecritereHsn.getValeurnuit());
                                evaluationheuresuppN.setCoefjour(parametragecritereHsn.getValeurjournee());
                            } else {
                                mappingResultats.get(7).setPoint(evaluationheuresuppN.getPointnuit() + evaluationheuresuppN.getPointjour());
                            }
                        } else {
                            parametragecritereHsn = new Parametragecritere(-1l);
                            mappingResultats.get(7).setPoint(0);
                        }
                    }

                    // idCritere = 9
                    if (criter9) {
                        evaluationPenaliteDept = evaluationPenaliteDeptFacadeLocal.findByIdService(structure.getIdstructure(), personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                        if (evaluationPenaliteDept != null) {
                            note.setPenaliteDepartement(evaluationPenaliteDept.getValeur());
                            note.setPointPenaliteDepartement(Math.ceil((note.getIncitationPositif() * evaluationPenaliteDept.getValeur()) / 100));
                            mappingResultats.get(8).setPoint(note.getPointPenaliteDepartement());
                        } else {
                            evaluationPenaliteDept = new EvaluationPenaliteDept();
                            mappingResultats.get(8).setPoint(0);
                        }
                    }

                    // idCritere = 10
                    if (criter10) {
                        evaluationPenalitePersonnel = evaluationPenalitePersonnelFacade.findIdPersonnelIdPeriode(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                        if (evaluationPenalitePersonnel != null) {
                            lignePenalitePersonnels = lignePenalitePersonnelFacadeLocal.findByIdEvaluation(evaluationPenalitePersonnel.getIdevaluationpenalitepersonnel());
                        } else {
                            evaluationPenalitePersonnel = new EvaluationPenalitePersonnel(0l);
                            evaluationPenalitePersonnel.setCible(0);
                            
                            List<ParametragePenalite> parametragePenalites = parametragePenaliteFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), 10);
                            penalites = this.extractPenaliteInParam(parametragePenalites);
                            
                            int sommeCible = 0;
                            for (Penalite p : penalites) {
                                LignePenalitePersonnel lpp = new LignePenalitePersonnel();
                                lpp.setIdlignepenalitepersonnel(0l);
                                lpp.setIdPenalite(p);
                                lpp.setCible(p.getPourcentage());
                                sommeCible += p.getPourcentage();
                                lpp.setEtat(false);
                                lignePenalitePersonnels.add(lpp);
                            }
                            evaluationPenalitePersonnel.setCible(sommeCible);
                        }
                        if (note.getIdnote().equals(0l)) {
                            mappingResultats.get(9).setPoint(Math.ceil((note.getIncitationPositif() * evaluationPenalitePersonnel.getScore()) / 100));
                        }
                    }
                    this.getTotalIncitation();
                } else {
                    this.prepareEditData(noteTemp);
                }
            }
        }
    }
    
    private void sommeDetailEvRqnDept(List<Evaluationrqntifdept> evaluationrqntifdepts) {
        ciblePrqnt = 0;
        realisationPrqnt = 0;
        ratioPrqnt = 0;
        if (!evaluationrqntifdepts.isEmpty()) {
            evaluationrqntifdepts.forEach(evr -> {
                ciblePrqnt += evr.getCible();
                realisationPrqnt += evr.getRealisation();
            });
            ratioPrqnt = (realisationPrqnt / ciblePrqnt) * 100;
        }
    }
    
    public void updateValue(int index, LignePenalitePersonnel item) {
        if (item.isEtat()) {
            lignePenalitePersonnels.get(index).setValeur(item.getIdPenalite().getPourcentage());
        } else {
            lignePenalitePersonnels.get(index).setValeur(0);
        }
        this.setDetailPenalitePersonnel();
        this.getTotalIncitation();
    }
    
    private void setDetailPenalitePersonnel() {
        int result = this.sommeLignePenalite(lignePenalitePersonnels);
        evaluationPenalitePersonnel.setScore(result);
        note.setPenalitePersonnel(result);
    }
    
    private int sommeLignePenalite(List<LignePenalitePersonnel> lignePenalitePersonnels) {
        int result = 0;
        for (LignePenalitePersonnel lpp : lignePenalitePersonnels) {
            result += lpp.getValeur();
        }
        return result;
    }
    
    public void removeLignePenalite(int index) {
        LignePenalitePersonnel l = lignePenalitePersonnels.get(index);
        if (l.getIdlignepenalitepersonnel() != 0l) {
            lignePenalitePersonnelFacadeLocal.remove(l);
        }
        lignePenalitePersonnels.remove(index);
        penalites.add(l.getIdPenalite());
        this.setDetailPenalitePersonnel();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }
    
    public void updateFiltreSc() {
        note.setNotePi(this.sommeNotePi());
    }
    
    public void addCritereToTable() {
        if (!selectedDetailsc.isEmpty()) {
            
            List<Detailsc> list = new ArrayList<>();
            for (Detailsc dsc : selectedDetailsc) {
                if (!checkCritereInTable(dsc)) {
                    Evaluationpersonnel evp = new Evaluationpersonnel();
                    
                    dsc.getIdsouscritere().setElementreponseCollection(elementReponseFacadeLocal.findByIdSousCritere(dsc.getIdsouscritere().getIdsouscritere()));
                    if (!dsc.getIdsouscritere().getElementreponseCollection().isEmpty()) {
                        List<Elementreponse> ers = (List) dsc.getIdsouscritere().getElementreponseCollection();
                        evp.setIdelementreponse(ers.get(0));
                    }
                    
                    evp.setIdevaluationpersonnel(0l);
                    evp.setIddetailsc(dsc);
                    evp.setNote(0d);
                    evaluationpersonnels.add(evp);
                    list.add(dsc);
                }
            }
            
            selectedDetailsc.removeAll(list);
            listDetailsc.removeAll(list);
            note.setNotePi(this.sommeNotePi());
        }
        
    }
    
    private boolean checkCritereInTable(Detailsc dsc) {
        boolean result = false;
        for (Evaluationpersonnel evp : evaluationpersonnels) {
            if (evp.getIddetailsc().getIddetailsc().equals(dsc.getIddetailsc())) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public void prepareEditDetail(Evaluationpersonnel item) {
        this.evaluationpersonnel = item;
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').show()");
    }
    
    public void removeCritere(String table, Evaluationpersonnel item, List<Evaluationpersonnel> list) {
        if (item.getIdevaluationpersonnel() != 0 && item.getIdevaluationpersonnel() != null) {
            evaluationpersonnelFacadeLocal.remove(item);
            list.remove(item);
        } else {
            int conteur = 0;
            for (Evaluationpersonnel evp : list) {
                if (Objects.equals(item.getIddetailsc().getIddetailsc(), evp.getIddetailsc().getIddetailsc())) {
                    break;
                }
                conteur++;
            }
            list.remove(conteur);
        }
        
        listDetailsc.add(item.getIddetailsc());
        if (table.equals("1")) {
            note.setNotePi(sommeNotePi());
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }
    
    public void calculHs(String cas, String option) {
        try {
            if (cas.equals("1")) {
                if (option.equals("jour")) {
                    evaluationheuresupp.setPointjour(Math.ceil(evaluationheuresupp.getCoefjour() * evaluationheuresupp.getNbheurejour()));
                } else {
                    evaluationheuresupp.setPointnuit(Math.ceil(evaluationheuresupp.getCoefnuit() * evaluationheuresupp.getNbheurenuit()));
                }
                this.mappingResultats.get(1).setPoint(evaluationheuresupp.getPointjour() + evaluationheuresupp.getPointnuit());
                this.getTotalIncitation();
                return;
            }
            
            if (option.equals("jour")) {
                evaluationheuresuppN.setPointjour(Math.ceil(evaluationheuresuppN.getCoefjour() * evaluationheuresuppN.getNbheurejour()));
            } else {
                evaluationheuresuppN.setPointnuit(Math.ceil(evaluationheuresuppN.getCoefnuit() * evaluationheuresuppN.getNbheurenuit()));
            }
            this.mappingResultats.get(7).setPoint(evaluationheuresuppN.getPointjour() + evaluationheuresuppN.getPointnuit());
            this.getTotalIncitation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void editDetail() {
        if (evaluationpersonnel.getIdevaluationpersonnel() != null && evaluationpersonnel.getIdevaluationpersonnel() > 0) {
            int i = 0;
            for (Evaluationpersonnel evp : evaluationpersonnels) {
                if (evp.getIddetailsc().equals(evaluationpersonnel.getIddetailsc())) {
                    break;
                }
                i++;
            }
            evaluationpersonnels.set(i, evaluationpersonnel);
        } else {
            int i = 0;
            for (Evaluationpersonnel evp : evaluationpersonnels) {
                if (evp.getIddetailsc().equals(evaluationpersonnel.getIddetailsc())) {
                    break;
                }
                i++;
            }
            evaluationpersonnels.set(i, evaluationpersonnel);
        }
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').hide()");
    }
    
    private void saveEvaluation(List<Evaluationpersonnel> list) {
        list.forEach(evp -> {
            if (evp.getIdevaluationpersonnel() == 0l) {
                evp.setIdevaluationpersonnel(evaluationpersonnelFacadeLocal.nextId());
                evp.setIdnote(note);
                evaluationpersonnelFacadeLocal.create(evp);
            } else {
                evaluationpersonnelFacadeLocal.edit(evp);
            }
        });
    }
    
    public void save() {
        try {
            
            ut.begin();
            
            this.updateNote(note);

            // idCritère = 7
            if (criter7) {
                this.saveEvaluation(evaluationpersonnels);
            }

            // idCritere = 1
            if (criter1) {
                if ((critereresponsabilite != null && !critereresponsabilite.getIdcritereresponsabilite().equals(-1l))) {
                    if (evaluationresponsabilite.getIdevaluationresponsabilite() == 0l) {
                        evaluationresponsabilite.setIdevaluationresponsabilite(evaluationresponsabiliteFacadeLocal.nextId());
                        evaluationresponsabilite.setIdnote(note);
                        evaluationresponsabilite.setPoint(note.getPointResponsabilite());
                        evaluationresponsabilite.setIdcritere(new Critere(1));
                        evaluationresponsabiliteFacadeLocal.create(evaluationresponsabilite);
                    } else if (!Objects.equals(evaluationresponsabilite.getIdevaluationresponsabilite(), -1l)) {
                        evaluationresponsabiliteFacadeLocal.edit(evaluationresponsabilite);
                    }
                }
            }

            // idCritere = 2
            if (criter2) {
                if ((parametragecritereHsp != null && !parametragecritereHsp.getIdparametragecritere().equals(-1l))) {
                    if (evaluationheuresupp.getIdevaluationheuresupp().equals(0l)) {
                        evaluationheuresupp.setIdcritere(new Critere(2));
                        evaluationheuresupp.setIdevaluationheuresupp(evaluationheuresuppFacadeLocal.nextId());
                        evaluationheuresupp.setIdnote(note);
                        evaluationheuresuppFacadeLocal.create(evaluationheuresupp);
                    } else if (!evaluationheuresupp.getIdevaluationheuresupp().equals(-1l)) {
                        evaluationheuresuppFacadeLocal.edit(evaluationheuresupp);
                    }
                }
            }

            // idCritere = 3
            if (criter3) {
                if ((parametragecritereBpp != null && !parametragecritereBpp.getIdparametragecritere().equals(-1l))) {
                    if (evaluationbonuspp.getIdevaluationbonuspp().equals(0l)) {
                        evaluationbonuspp.setIdevaluationbonuspp(evaluationbonusppFacadeLocal.nextId());
                        evaluationbonuspp.setIdnote(note);
                        evaluationbonusppFacadeLocal.create(evaluationbonuspp);
                    } else if (!evaluationbonuspp.getIdevaluationbonuspp().equals(-1l)) {
                        evaluationbonusppFacadeLocal.edit(evaluationbonuspp);
                    }
                }
            }

            // idCritere = 4
            if (criter4) {
                if ((parametragecriterePrqn != null && !parametragecriterePrqn.getIdparametragecritere().equals(-1l))) {
                    if (!evaluationrqntifdepts.isEmpty()) {
                        for (Evaluationrqntifdept ligne : evaluationrqntifdepts) {
                            if (ligne.getIdevaluationrqntifdept().equals(0l)) {
                                ligne.setIdevaluationrqntifdept(evaluationrqntifdeptFacadeLocal.nextId());
                                ligne.setIdnote(note);
                                ligne.setIdpersonnel(personnel);
                                evaluationrqntifdeptFacadeLocal.create(ligne);
                            } else {
                                evaluationrqntifdeptFacadeLocal.edit(ligne);
                            }
                        }
                    }
                }
            }

            // idCritere = 5
            if (criter5) {
                if ((parametragecriterePrq != null && !parametragecriterePrq.getIdparametragecritere().equals(-1l))) {
                    if ((evaluationRPrimeQltifDept != null && !evaluationRPrimeQltifDept.getIdevaluationrprimeqltifdept().equals(-1l))) {
                        if (evaluationRPrimeQltifPersonnel.getIdevaluationrprimeqltifpersonnel().equals(0l)) {
                            evaluationRPrimeQltifPersonnel.setIdevaluationrprimeqltifpersonnel(evaluationRPrimeQltifPersonnelFacadeLocal.nextId());
                            evaluationRPrimeQltifPersonnel.setIdnote(note);
                            evaluationRPrimeQltifPersonnel.setIdpersonnel(personnel);
                            evaluationRPrimeQltifPersonnelFacadeLocal.create(evaluationRPrimeQltifPersonnel);
                        } else if (!Objects.equals(evaluationRPrimeQltifPersonnel.getIdevaluationrprimeqltifpersonnel(), -1l)) {
                            evaluationRPrimeQltifPersonnelFacadeLocal.edit(evaluationRPrimeQltifPersonnel);
                        }
                    }
                }
            }

            // idCritere = 6
            if (criter6) {
                if ((parametragecritereBrd != null && !parametragecritereBrd.getIdparametragecritere().equals(-1l))) {
                    if (!cibleBrd.getIdcible().equals(-1l)) {
                        if (evaluationBonusRDeptPersonnel.getIdevaluationbonusrdeptpersonnel().equals(0l)) {
                            evaluationBonusRDeptPersonnel.setIdevaluationbonusrdeptpersonnel(evaluationBonusRDeptPersonnelFacadeLocal.nextId());
                            evaluationBonusRDeptPersonnel.setIdpersonnel(personnel);
                            evaluationBonusRDeptPersonnelFacadeLocal.create(evaluationBonusRDeptPersonnel);
                        } else if (!Objects.equals(evaluationBonusRDeptPersonnel.getIdevaluationbonusrdeptpersonnel(), -1l)) {
                            evaluationBonusRDeptPersonnelFacadeLocal.edit(evaluationBonusRDeptPersonnel);
                        }
                    }
                }
            }

            // idCritère = 8
            if (criter8) {
                if ((parametragecritereHsn != null && !parametragecritereHsn.getIdparametragecritere().equals(-1l))) {
                    if (evaluationheuresuppN.getIdevaluationheuresupp().equals(0l)) {
                        evaluationheuresuppN.setIdevaluationheuresupp(evaluationheuresuppFacadeLocal.nextId());
                        evaluationheuresuppN.setIdnote(note);
                        evaluationheuresuppFacadeLocal.create(evaluationheuresuppN);
                    } else if (!Objects.equals(evaluationheuresuppN.getIdevaluationheuresupp(), -1l)) {
                        evaluationheuresuppFacadeLocal.edit(evaluationheuresuppN);
                    }
                }
            }

            // idCritere = 9 it is done
            // idCritère = 10
            if (criter10) {
                if (evaluationPenalitePersonnel.getIdevaluationpenalitepersonnel().equals(0l)) {
                    evaluationPenalitePersonnel.setIdevaluationpenalitepersonnel(evaluationPenalitePersonnelFacade.nextId());
                    evaluationPenalitePersonnel.setIdnote(note);
                    evaluationPenalitePersonnelFacade.create(evaluationPenalitePersonnel);
                } else if (!Objects.equals(evaluationPenalitePersonnel, -1l)) {
                    evaluationPenalitePersonnelFacade.edit(evaluationPenalitePersonnel);
                }
                
                for (LignePenalitePersonnel ligne : lignePenalitePersonnels) {
                    if (ligne.getIdlignepenalitepersonnel().equals(0l)) {
                        ligne.setIdEvaluationPenalitePersonnel(evaluationPenalitePersonnel);
                        ligne.setIdlignepenalitepersonnel(lignePenalitePersonnelFacadeLocal.nextId());
                        lignePenalitePersonnelFacadeLocal.create(ligne);
                    } else {
                        lignePenalitePersonnelFacadeLocal.edit(ligne);
                    }
                }
            }
            
            ut.commit();
            
            notes.clear();
            if (sousperiode.getIdsousperiode() > 0) {
                notes = noteFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            }
            
            this.listDetailsc.clear();
            this.signalSuccess();
            RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').hide()");
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
    public void validate() {
        notes.forEach(n -> {
            n.setEtat(true);
            noteFacadeLocal.edit(n);            
        });
        JsfUtil.addSuccessMessage("Opération réussie");
    }
    
    private void updateNote(Note note) {
        note.setTotalPoint((note.getIncitationPositif() - note.getIncitationNegatif()));
        note.setPointHeureSupp(evaluationheuresupp.getPointjour() + evaluationheuresupp.getPointnuit());
        note.setIncitationNHP(evaluationheuresuppN.getPointjour() + evaluationheuresuppN.getPointnuit());
        note.setPointResponsabilite(evaluationresponsabilite.getPoint());
        note.setPointPratiqueP(evaluationbonuspp.getPoint());
        note.setPointRQltifDept(evaluationRPrimeQltifPersonnel.getPoint());
        note.setPointBonusRDept(evaluationBonusRDeptPersonnel.getPoint());
        note.setIdperiode(SessionMBean.getPeriode());
        note.setIdsousperiode(sousperiode);
        note.setIdpersonnel(personnel);
        
        if (note.getIdnote() == 0l) {
            note.setIdnote(noteFacadeLocal.nextId());
            noteFacadeLocal.create(note);
        } else {
            noteFacadeLocal.edit(note);
        }
    }
    
    public void delete(Note n) {
        try {
            ut.begin();
            evaluationpersonnelFacadeLocal.deleteData(n.getIdnote());
            lignePenalitePersonnelFacadeLocal.deleteByIdNote(n.getIdnote());
            evaluationPenalitePersonnelFacade.deleteByIdNote(n.getIdnote());
            evaluationheuresuppFacadeLocal.deleteByIdNote(n.getIdnote());
            evaluationbonusppFacadeLocal.deleteByIdNote(n.getIdnote());
            evaluationresponsabiliteFacadeLocal.deleteByIdNote(n.getIdnote());
            evaluationbonusppFacadeLocal.deleteByIdNote(n.getIdnote());
            evaluationrqntifdeptFacadeLocal.deleteByIdNote(n.getIdnote());
            evaluationRPrimeQltifPersonnelFacadeLocal.deleteByIdNote(n.getIdnote());
            evaluationBonusRDeptPersonnelFacadeLocal.deleteByIdNote(n.getIdnote());
            primeFacadeLocal.deleteByIdNote(n.getIdnote());
            noteFacadeLocal.remove(n);
            
            notes.clear();
            if (sousperiode.getIdsousperiode() > 0) {
                notes = noteFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            }
            ut.commit();
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
    private double sommeNotePi() {
        if (evaluationpersonnels.isEmpty()) {
            return 0;
        }
        double resultat = 0;
        for (Evaluationpersonnel dsc : evaluationpersonnels) {
            resultat += dsc.getNote();
        }
        return resultat;
    }
    
    public void setResponse(int index, List<Evaluationpersonnel> evaluationpersonnels) {
        Evaluationpersonnel ev = evaluationpersonnels.get(index);
        ev.setNote(ev.getIdelementreponse().getValeur());
        evaluationpersonnels.set(index, ev);
        
        this.note.setNotePi(this.calculNotePi());
        this.setDetailPi();
        this.mappingResultats.get(6).setPoint(note.getPointPIndiv());
        this.getTotalIncitation();
    }
    
    private double calculNotePi() {
        if (evaluationpersonnels.isEmpty()) {
            return 0;
        }
        double note = 0;
        for (Evaluationpersonnel evp : evaluationpersonnels) {
            note += evp.getNote();
        }
        return note;
    }
    
    private void setDetailPi() {
        note.setScorePIndiv((note.getNotePi() / totalPointPi) * 100);
        note.setPointPIndiv(Math.ceil((parametragecritere.getPoint() * note.getScorePIndiv()) / 100));
    }
    
    private double sommeTotalSc(List<Detailsc> list) {
        if (list.isEmpty()) {
            return 0;
        }
        double somme = 0;
        for (Detailsc d : list) {
            somme += d.getPointMax();
        }
        return somme;
    }
    
    public void calculPrimeQtif(String mode) {
        if (mode.equals("1")) {
            int i = 0;
            double cible = 0;
            double realisation = 0;
            for (Evaluationrqntifdept ev : evaluationrqntifdepts) {
                try {
                    ev.setRatio((ev.getRealisation() / ev.getCible()) * 100);
                    evaluationrqntifdepts.set(i, ev);
                    cible += ev.getCible();
                    realisation += ev.getRealisation();
                    i++;
                } catch (Exception e) {
                }
            }
            
            this.ratioPrqnt = (realisation / cible) * 100;
            this.ciblePrqnt = cible;
            this.realisationPrqnt = realisation;
            note.setPoucentageRQntif(ratioPrqnt);
            try {
                note.setPointRqntif(Math.ceil((parametragecriterePrqn.getPoint() * ratioPrqnt) / 100));
                mappingResultats.get(3).setPoint(note.getPointRqntif());
            } catch (Exception e) {
                mappingResultats.get(3).setPoint(0);
                note.setPointRqntif(0);
            }
            this.getTotalIncitation();
        }
    }
    
    public void updatePointResponsabilite() {
        try {
            double res = ((evaluationresponsabilite.getRatio() * evaluationresponsabilite.getPointMax()) / 100);
            evaluationresponsabilite.setPoint(res);
            mappingResultats.get(0).setPoint(res);
        } catch (Exception e) {
            evaluationresponsabilite.setPoint(0);
            mappingResultats.get(0).setPoint(0);
        }
        this.getTotalIncitation();
    }
    
    public void updatePointPratiquePrivee() {
        try {
            double res = (evaluationbonuspp.getRatio() * evaluationbonuspp.getPointMax()) / 100;
            evaluationbonuspp.setPoint(res);
            mappingResultats.get(2).setPoint(res);
        } catch (Exception e) {
            evaluationbonuspp.setPoint(0);
            mappingResultats.get(2).setPoint(0);
        }
        this.getTotalIncitation();
    }
    
    private void getTotalIncitation() {
        this.calculFinal();
    }
    
    private void setDataPenalite() {
        mappingResultats.get(8).setPoint(Math.ceil((note.getIncitationPositif() * evaluationPenaliteDept.getValeur()) / 100));
        mappingResultats.get(9).setPoint(Math.ceil((note.getIncitationPositif() * evaluationPenalitePersonnel.getScore()) / 100));
    }
    
    public void calculFinal() {
        if (!mappingResultats.isEmpty()) {
            note.setIncitationNegatif(0);
            note.setIncitationPositif(0);
            for (MappingResultat mr : mappingResultats) {
                if (Objects.equals(8, mr.getIdCritere())) {
                    note.setIncitationNegatif(note.getIncitationNegatif() + mr.getPoint());
                } else if (!Objects.equals(9, mr.getIdCritere()) && !Objects.equals(10, mr.getIdCritere())) {
                    note.setIncitationPositif(note.getIncitationPositif() + mr.getPoint());
                }
            }
            
            this.setDataPenalite();
            
            note.setPointPenaliteDepartement(mappingResultats.get(8).getPoint());
            note.setPointPenalitePersonnel(mappingResultats.get(9).getPoint());
            note.setIncitationNegatif(note.getIncitationNegatif() + (mappingResultats.get(8).getPoint() + mappingResultats.get(9).getPoint()));
        }
    }
    
    public void signalError(String chaine) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("information", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(chaine));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
    
    public void signalSuccess() {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
    
    public void signalException(Exception e) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.catchException(e, this.routine.localizeMessage("erreur_execution"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
    
    public boolean checkCritere(Critere critere) {
        if (criteres.contains(critere)) {
            return true;
        }
        return false;
    }
    
    private void checkCritere() {
        criter1 = Utilitaires.findCritereInSession(1) != null;
        criter2 = Utilitaires.findCritereInSession(2) != null;
        criter3 = Utilitaires.findCritereInSession(3) != null;
        criter4 = Utilitaires.findCritereInSession(4) != null;
        criter5 = Utilitaires.findCritereInSession(5) != null;
        criter6 = Utilitaires.findCritereInSession(6) != null;
        criter7 = Utilitaires.findCritereInSession(7) != null;
        criter8 = Utilitaires.findCritereInSession(8) != null;
        criter9 = Utilitaires.findCritereInSession(9) != null;
        criter10 = Utilitaires.findCritereInSession(10) != null;
    }
    
    public void printFiche(Note item, String option) {
        try {
            Map parameter = new HashMap();
            parameter.put("idPersonnel", item.getIdpersonnel().getIdpersonnel());
            parameter.put("idPeriode", item.getIdperiode().getIdperiode());
            parameter.put("idSousPeriode", item.getIdsousperiode().getIdsousperiode());
            if (option.equals("pdf")) {
                Printer.print("/report/fiche_evaluation_individuelle.jasper", parameter);
            } else {
                Printer.DOCX("/report/fiche_evaluation_individuelle.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void printRapport(String option) {
        try {
            Map parameter = new HashMap();
            parameter.put("idStructure", SessionMBean.getStructure().getIdstructure());
            parameter.put("idPeriode", SessionMBean.getPeriode().getIdperiode());
            parameter.put("idSousPeriode", sousperiode.getIdsousperiode());
            if (option.equals("pdf")) {
                Printer.print("/report/rapport_evaluation.jasper", parameter);
            } else {
                Printer.DOCX("/report/rapport_evaluation.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
