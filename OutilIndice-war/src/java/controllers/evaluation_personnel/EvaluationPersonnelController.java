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
import entities.Parametragecritere;
import entities.Penalite;
import entities.Periode;
import entities.Personnel;
import entities.Responsabilite;
import entities.Service;
import entities.Sousperiode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.MappingResultat;
import utils.SessionMBean;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class EvaluationPersonnelController extends AbstractEvaluationPersonnel implements Serializable {

    public EvaluationPersonnelController() {

    }

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        this.initNote();
    }

    private void initNote() {
        note = new Note();
        note.setIdperiode(new Periode());
        note.setIdpersonnel(new Personnel());
        note.setIdsousperiode(new Sousperiode());
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
        this.checkCritere();
        this.initNote();
        sousperiode = new Sousperiode(0);
        listDetailsc.clear();
        evaluationpersonnels.clear();
        evaluationrqntifdepts.clear();
        cibleRqntifs.clear();
        notePi = 0;
        scorePi = 0;
        pointPi = 0;
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
        totalPIncitationPositif = 0;
        totalPIncitationNegatif = 0;
        this.mappingResultats = MappingResultat.getMapping();
        RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').show()");
    }

    public void filterData() {
        notes.clear();
        if (sousperiode.getIdsousperiode() > 0) {
            notes = noteFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
        }

        if (notes.isEmpty()) {
            JsfUtil.addWarningMessage("Aucune donnée trouvée");
        }
    }

    public void prepareEdit(Note n) {
        mode = "Edit";
        this.note = n;
        this.personnel = n.getIdpersonnel();
        this.sousperiode = n.getIdsousperiode();
        message = "";
        this.updateEvaluationData();
        RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').show()");
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

        notePi = this.sommeNotePi();
        RequestContext.getCurrentInstance().execute("PF('EvaluationDetailDialog').show()");
    }

    public void closeDetail() {
        evaluationpersonnels.clear();
        this.initNote();
        RequestContext.getCurrentInstance().execute("PF('ViewEditDialog').hide()");
    }

    private void loadSavedData() {
        this.scorePi = note.getScorePIndiv();
        this.pointPi = note.getPointPIndiv();
        totalPIncitationNegatif = note.getIncitationNegatif();
        totalPIncitationPositif = note.getIncitationPositif();
    }

    public void updateEvaluationData() {
        evaluationpersonnels.clear();
        listDetailsc.clear();
        selectedDetailsc.clear();
        notePi = 0;
        if (personnel.getIdpersonnel() > 0) {
            if (sousperiode.getIdsousperiode() > 0) {
                personnel = personnelFacadeLocal.find(personnel.getIdpersonnel());

                note = new Note(0l);
                note = noteFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                if (note != null) {
                    this.loadSavedData();
                }
                // idCritere = 7
                if (criter7) {
                    parametragecritere = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 7, personnel.getIdcategorie().getIdcategorie());
                    if (parametragecritere != null) {
                        List<Detailsc> listDetail = detailscFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), 7);
                        totalPointPi = sommeTotalSc(listDetail);
                        if (!listDetail.isEmpty()) {
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
                                this.notePi = this.sommeNotePi();
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
                        cibleRqntifs = cibleFacadeLocal.findByIdSousPeriode(personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 4);
                        if (!cibleRqntifs.isEmpty()) {
                            evaluationrqntifdepts = evaluationrqntifdeptFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 4);
                            if (evaluationrqntifdepts.isEmpty()) {
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
                        }
                    } else {
                        parametragecriterePrqn = new Parametragecritere(-1l);
                    }
                }

                // idCritere = 5
                if (criter5) {
                    parametragecriterePrq = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 5, personnel.getIdcategorie().getIdcategorie());
                    if (parametragecriterePrq != null) {
                        evaluationRPrimeQltifDept = evaluationRPrimeQltifDeptFacadeLocal.findByIdService(personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 5);
                        if (evaluationRPrimeQltifDept != null) {
                            evaluationRPrimeQltifPersonnel = evaluationRPrimeQltifPersonnelFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                            if (evaluationRPrimeQltifPersonnel == null) {
                                evaluationRPrimeQltifPersonnel = new EvaluationRPrimeQltifPersonnel(0l);
                                evaluationRPrimeQltifPersonnel.setIdevaluationrprimeqltifdept(evaluationRPrimeQltifDept);
                                evaluationRPrimeQltifPersonnel.setIdpersonnel(personnel);
                                evaluationRPrimeQltifPersonnel.setPoint((parametragecriterePrq.getPoint() * evaluationRPrimeQltifDept.getPourcentage()) / 100);
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
                        cibleBrd = cibleFacadeLocal.findByIdSousPeriodeOneLine(personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 6);
                        if (cibleBrd != null) {
                            evaluationBonusRDeptPersonnel = evaluationBonusRDeptPersonnelFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                            if (evaluationBonusRDeptPersonnel == null) {
                                evaluationBonusRDeptPersonnel = new EvaluationBonusRDeptPersonnel(0l);
                                evaluationBonusRDeptPersonnel.setIdcible(cibleBrd);
                                evaluationBonusRDeptPersonnel.setIdnote(note);
                                evaluationBonusRDeptPersonnel.setPoint((parametragecritereBrd.getPoint() * cibleBrd.getRatio()) / 100);
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
                    evaluationPenaliteDept = evaluationPenaliteDeptFacadeLocal.findByIdService(personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                    if (evaluationPenaliteDept != null) {
                        note.setPenaliteDepartement(evaluationPenaliteDept.getValeur());
                        note.setPointPenaliteDepartement((totalPIncitationPositif * evaluationPenaliteDept.getValeur()) / 100);
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
                        penalites = penaliteFacadeLocal.findAllPersonnel();
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
                        mappingResultats.get(9).setPoint((totalPIncitationPositif * evaluationPenalitePersonnel.getScore()) / 100);
                    }
                }

                if (note.getIdnote() == 0) {
                    this.getTotalIncitation();
                }
            }
        }
    }

    public void updateValue(LignePenalitePersonnel item) {
        int counter = 0;
        for (int i = 0; i < lignePenalitePersonnels.size(); i++) {
            if (lignePenalitePersonnels.get(i).getIdPenalite().getIdpenalite().equals(item.getIdPenalite().getIdpenalite())) {
                counter = i;
                break;
            }
        }
        if (item.isEtat()) {
            lignePenalitePersonnels.get(counter).setValeur(item.getIdPenalite().getPourcentage());
        } else {
            lignePenalitePersonnels.get(counter).setValeur(0);
        }
        this.setDetailPenalitePersonnel();
        this.getTotalIncitation();
    }

    private void setDetailPenalitePersonnel() {
        int result = this.sommeLignePenalite(lignePenalitePersonnels);
        evaluationPenalitePersonnel.setScore(result);
    }

    private int sommeLignePenalite(List<LignePenalitePersonnel> lignePenalitePersonnels) {
        int result = 0;
        for (LignePenalitePersonnel lpp : lignePenalitePersonnels) {
            result += lpp.getValeur();
        }
        return result;
    }

    public void removeLignePenalite(LignePenalitePersonnel l) {
        if (l.getIdlignepenalitepersonnel() != 0l) {
            lignePenalitePersonnels.remove(l);
            lignePenalitePersonnelFacadeLocal.remove(l);
            //listEvaluationPenaliteDepts = evaluationPenaliteDeptFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            penalites.add(l.getIdPenalite());
        } else {
            int conteur = 0;
            for (LignePenalitePersonnel lpp : lignePenalitePersonnels) {
                if (lpp.getIdPenalite().getIdpenalite().equals(l.getIdPenalite().getIdpenalite())) {
                    break;
                }
                conteur++;
            }
            lignePenalitePersonnels.remove(conteur);
            penalites.add(l.getIdPenalite());
        }
        this.setDetailPenalitePersonnel();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void updateFiltreSc() {
        notePi = this.sommeNotePi();
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
            notePi = this.sommeNotePi();
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
            notePi = sommeNotePi();
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void calculHs(String cas, String option) {
        try {
            if (cas.equals("1")) {
                if (option.equals("jour")) {
                    evaluationheuresupp.setPointjour(evaluationheuresupp.getCoefjour() * evaluationheuresupp.getNbheurejour());
                } else {
                    evaluationheuresupp.setPointnuit(evaluationheuresupp.getCoefnuit() * evaluationheuresupp.getNbheurenuit());
                }
                this.mappingResultats.get(1).setPoint(evaluationheuresupp.getPointjour() + evaluationheuresupp.getPointnuit());
                this.getTotalIncitation();
                return;
            }

            if (option.equals("jour")) {
                evaluationheuresuppN.setPointjour(evaluationheuresuppN.getCoefjour() * evaluationheuresuppN.getNbheurejour());
            } else {
                evaluationheuresuppN.setPointnuit(evaluationheuresuppN.getCoefnuit() * evaluationheuresuppN.getNbheurenuit());
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
                if (critereresponsabilite.getIdcritereresponsabilite() != -1l) {
                    if (evaluationresponsabilite.getIdevaluationresponsabilite() == 0l) {
                        evaluationresponsabilite.setIdevaluationresponsabilite(evaluationresponsabiliteFacadeLocal.nextId());
                        evaluationresponsabilite.setIdnote(note);
                        evaluationresponsabilite.setPoint(note.getPointResponsabilite());
                        evaluationresponsabilite.setIdcritere(new Critere(1));
                        evaluationresponsabiliteFacadeLocal.create(evaluationresponsabilite);
                    } else if (evaluationresponsabilite.getIdevaluationresponsabilite() != -1l) {
                        evaluationresponsabiliteFacadeLocal.edit(evaluationresponsabilite);
                    }
                }
            }

            // idCritere = 2
            if (criter2) {
                if (!parametragecritereHsp.getIdparametragecritere().equals(-1l)) {
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
                if (!parametragecritereBpp.getIdparametragecritere().equals(-1l)) {
                    if (evaluationbonuspp.getIdevaluationbonuspp().equals(0l)) {
                        evaluationbonuspp.setIdevaluationbonuspp(evaluationbonusppFacadeLocal.nextId());
                        evaluationbonuspp.setIdnote(note);
                        evaluationbonusppFacadeLocal.create(evaluationbonuspp);
                    } else if (evaluationbonuspp.getIdevaluationbonuspp().equals(0l)) {
                        evaluationbonusppFacadeLocal.edit(evaluationbonuspp);
                    }
                }
            }

            // idCritere = 4
            if (criter4) {
                if (!parametragecriterePrqn.getIdparametragecritere().equals(-1l)) {
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
                if (!parametragecriterePrq.getIdparametragecritere().equals(-1l)) {
                    if (!evaluationRPrimeQltifDept.getIdevaluationrprimeqltifdept().equals(-1l)) {
                        if (evaluationRPrimeQltifPersonnel.getIdevaluationrprimeqltifpersonnel().equals(0l)) {
                            evaluationRPrimeQltifPersonnel.setIdevaluationrprimeqltifpersonnel(evaluationRPrimeQltifPersonnelFacadeLocal.nextId());
                            evaluationRPrimeQltifPersonnel.setIdnote(note);
                            evaluationRPrimeQltifPersonnel.setIdpersonnel(personnel);
                            evaluationRPrimeQltifPersonnelFacadeLocal.create(evaluationRPrimeQltifPersonnel);
                        } else {
                            evaluationRPrimeQltifPersonnelFacadeLocal.edit(evaluationRPrimeQltifPersonnel);
                        }
                    }
                }
            }

            // idCritere = 6
            if (criter6) {
                if (!parametragecritereBrd.getIdparametragecritere().equals(-1l)) {
                    if (!cibleBrd.getIdcible().equals(-1l)) {
                        if (evaluationBonusRDeptPersonnel.getIdevaluationbonusrdeptpersonnel().equals(0l)) {
                            evaluationBonusRDeptPersonnel.setIdevaluationbonusrdeptpersonnel(evaluationBonusRDeptPersonnelFacadeLocal.nextId());
                            evaluationBonusRDeptPersonnel.setIdpersonnel(personnel);
                            evaluationBonusRDeptPersonnelFacadeLocal.create(evaluationBonusRDeptPersonnel);
                        } else {
                            evaluationBonusRDeptPersonnelFacadeLocal.create(evaluationBonusRDeptPersonnel);
                        }
                    }
                }
            }

            // idCritère = 8
            if (criter8) {
                if (!parametragecritereHsn.getIdparametragecritere().equals(-1l)) {
                    if (evaluationheuresuppN.getIdevaluationheuresupp().equals(0l)) {
                        evaluationheuresuppN.setIdevaluationheuresupp(evaluationheuresuppFacadeLocal.nextId());
                        evaluationheuresuppN.setIdnote(note);
                        evaluationheuresuppFacadeLocal.create(evaluationheuresuppN);
                    } else {
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

    private void updateNote(Note note) {
        note.setIncitationNegatif(totalPIncitationNegatif);
        note.setIncitationPositif(totalPIncitationPositif);
        note.setTotalPoint((totalPIncitationPositif - totalPIncitationNegatif));
        note.setPointPIndiv(pointPi);
        note.setScorePIndiv(scorePi);
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
            noteFacadeLocal.remove(n);

            notes.clear();
            if (sousperiode.getIdsousperiode() > 0) {
                notes = noteFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            }
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

        this.notePi = this.calculNotePi();
        this.setDetailPi();
        this.mappingResultats.get(6).setPoint(pointPi);
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
        scorePi = (notePi / totalPointPi) * 100;
        pointPi = (parametragecritere.getPoint() * scorePi) / 100;
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
                note.setPointRqntif((parametragecriterePrqn.getPoint() * ratioPrqnt) / 100);
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
            double res = ((evaluationbonuspp.getRatio() * evaluationbonuspp.getPointMax()) / 100);
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
        mappingResultats.get(8).setPoint((totalPIncitationPositif * evaluationPenaliteDept.getValeur()) / 100);
        mappingResultats.get(9).setPoint((totalPIncitationPositif * evaluationPenalitePersonnel.getScore()) / 100);
    }

    public void calculFinal() {
        if (!mappingResultats.isEmpty()) {
            totalPIncitationNegatif = 0;
            totalPIncitationPositif = 0;
            for (MappingResultat mr : mappingResultats) {
                if (Objects.equals(8, mr.getIdCritere())) {
                    totalPIncitationNegatif += mr.getPoint();
                } else if (!Objects.equals(9, mr.getIdCritere()) && !Objects.equals(10, mr.getIdCritere())) {
                    totalPIncitationPositif += mr.getPoint();
                }
            }

            this.setDataPenalite();

            note.setPointPenaliteDepartement(mappingResultats.get(8).getPoint());
            note.setPointPenalitePersonnel(mappingResultats.get(9).getPoint());
            totalPIncitationNegatif += (mappingResultats.get(8).getPoint() + mappingResultats.get(9).getPoint());
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
        criter1 = criteres.contains(new Critere(1));
        criter2 = criteres.contains(new Critere(2));
        criter3 = criteres.contains(new Critere(3));
        criter4 = criteres.contains(new Critere(4));
        criter5 = criteres.contains(new Critere(5));
        criter6 = criteres.contains(new Critere(6));
        criter7 = criteres.contains(new Critere(7));
        criter8 = criteres.contains(new Critere(8));
        criter9 = criteres.contains(new Critere(9));
        criter10 = criteres.contains(new Critere(10));
    }
}
