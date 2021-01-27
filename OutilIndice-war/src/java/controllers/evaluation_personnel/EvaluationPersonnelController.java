/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.evaluation_personnel;

import controllers.util.JsfUtil;
import entities.Categorie;
import entities.Cible;
import entities.Critereresponsabilite;
import entities.Detailsc;
import entities.Elementreponse;
import entities.EvaluationBonusRDeptPersonnel;
import entities.EvaluationPenaliteDept;
import entities.EvaluationRPrimeQltifPersonnel;
import entities.Evaluationbonuspp;
import entities.Evaluationheuresupp;
import entities.Evaluationpersonnel;
import entities.Evaluationresponsabilite;
import entities.Evaluationrqntifdept;
import entities.Note;
import entities.Parametragecritere;
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
        this.initNote();
        sousperiode = new Sousperiode();
        sousperiode.setIdsousperiode(0);
        listDetailsc.clear();
        evaluationpersonnels.clear();
        score = 0;
        mode = "Create";
        message = "";
        evaluationheuresupp = new Evaluationheuresupp();
        evaluationheuresuppN = new Evaluationheuresupp();
        critereresponsabilite = new Critereresponsabilite();
        parametragecritereBpp = new Parametragecritere();
        evaluationbonuspp = new Evaluationbonuspp();
        evaluationPenaliteDept = new EvaluationPenaliteDept();
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
        this.note = n;
        this.personnel = n.getIdpersonnel();
        this.sousperiode = n.getIdsousperiode();
        mode = "Edit";
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

        score = this.sommeNote();
        RequestContext.getCurrentInstance().execute("PF('EvaluationDetailDialog').show()");
    }

    public void closeDetail() {
        evaluationpersonnels.clear();
        this.initNote();
        RequestContext.getCurrentInstance().execute("PF('ViewEditDialog').hide()");
    }

    public void updateEvaluationData() {
        evaluationpersonnels.clear();
        listDetailsc.clear();
        selectedDetailsc.clear();
        score = 0;
        if (personnel.getIdpersonnel() > 0) {
            if (sousperiode.getIdsousperiode() > 0) {
                personnel = personnelFacadeLocal.find(personnel.getIdpersonnel());

                note = noteFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                if (note == null) {
                    note = new Note();
                }

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
                                ev.setIdpersonnel(personnel);
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
                            this.score = this.sommeNote();
                        }
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez définir le barème (Point Max) d'évaluation  pour cette catégorie");
                }

                parametragecritereHsp = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 2, personnel.getIdcategorie().getIdcategorie());
                if (parametragecritereHsp != null) {
                    evaluationheuresupp = evaluationheuresuppFacadeLocal.findByIdPersonnel(SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), personnel.getIdpersonnel(), 2);
                    if (evaluationheuresupp == null) {
                        evaluationheuresupp = new Evaluationheuresupp();
                        evaluationheuresupp.setIdcritere(parametragecritereHsp.getIdcritere());
                        evaluationheuresupp.setCoefnuit(parametragecritereHsp.getValeurnuit());
                        evaluationheuresupp.setCoefjour(parametragecritereHsp.getValeurjournee());
                    }
                } else {
                    parametragecritereHsp = new Parametragecritere();
                    parametragecritereHsp.setIdparametragecritere(0l);
                    JsfUtil.addErrorMessage("Veuillez définir le barème d'évaluation pour cette catégorie");
                }

                critereresponsabilite = critereresponsabiliteFacadeLocal.findByIdResponsabilite(SessionMBean.getStructure().getIdstructure(), personnel.getIdresponsabilite().getIdresponsabilite(), 1);
                if (critereresponsabilite != null) {
                    evaluationresponsabilite = evaluationresponsabiliteFacadeLocal.findByIdPersonnel(SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), personnel.getIdpersonnel(), 1);
                    if (evaluationresponsabilite == null) {
                        evaluationresponsabilite = new Evaluationresponsabilite();
                        evaluationresponsabilite.setPoint(critereresponsabilite.getPoint());
                    }
                } else {
                    critereresponsabilite = new Critereresponsabilite();
                    critereresponsabilite.setIdcritereresponsabilite(0l);
                    JsfUtil.addErrorMessage("Veuillez définir le point max cette responsabilité");
                }

                parametragecritereBpp = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 3, personnel.getIdcategorie().getIdcategorie());
                if (parametragecritereBpp != null) {
                    evaluationbonuspp = evaluationbonusppFacadeLocal.findByIdPersonnel(SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), personnel.getIdpersonnel(), 3);
                    if (evaluationbonuspp == null) {
                        evaluationbonuspp = new Evaluationbonuspp();
                        evaluationbonuspp.setPoint(parametragecritereBpp.getPoint());
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez définir le point max du bonus pour ne pas détourner les patients pour le privé");
                }

                parametragecriterePrqn = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 4, personnel.getIdcategorie().getIdcategorie());
                if (parametragecriterePrqn != null) {
                    cibleRqntifs = cibleFacadeLocal.findByIdSousPeriode(personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 4);
                    if (!cibleRqntifs.isEmpty()) {
                        evaluationrqntifdepts = evaluationrqntifdeptFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 4);
                        if (evaluationrqntifdepts.isEmpty()) {
                            for (Cible c : cibleRqntifs) {
                                Evaluationrqntifdept ev = new Evaluationrqntifdept();
                                ev.setCible(c.getValeurcible());
                                ev.setRealisation(0);
                                ev.setRatio(0);
                                ev.setIdcible(c);
                                ev.setIdpersonnel(personnel);
                                evaluationrqntifdepts.add(ev);
                            }
                        }
                    } else {
                        JsfUtil.addErrorMessage("Veuillez définir les cibles pour le revenu des quantitatifs de département");
                    }
                } else {
                    parametragecriterePrqn = new Parametragecritere();
                }

                parametragecriterePrq = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 5, personnel.getIdcategorie().getIdcategorie());
                if (parametragecriterePrq != null) {
                    evaluationRPrimeQltifDept = evaluationRPrimeQltifDeptFacadeLocal.findByIdService(personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 5);
                    if (evaluationRPrimeQltifDept != null) {
                        evaluationRPrimeQltifPersonnel = evaluationRPrimeQltifPersonnelFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                        if (evaluationRPrimeQltifPersonnel == null) {
                            evaluationRPrimeQltifPersonnel = new EvaluationRPrimeQltifPersonnel();
                            evaluationRPrimeQltifPersonnel.setIdevaluationrprimeqltifdept(evaluationRPrimeQltifDept);
                            evaluationRPrimeQltifPersonnel.setPoint((evaluationRPrimeQltifDept.getScore() * parametragecriterePrq.getPoint()) / 100);
                        }
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez définir le score max par catégorie de la prime du résultat qualitatif du département");
                }

                parametragecritereBrd = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 6, personnel.getIdcategorie().getIdcategorie());
                if (parametragecritereBrd != null) {
                    cibleBrd = cibleFacadeLocal.findByIdSousPeriodeOneLine(personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), 6);
                    if (cibleBrd != null) {
                        evaluationBonusRDeptPersonnel = evaluationBonusRDeptPersonnelFacadeLocal.findByIdPersonnel(personnel.getIdpersonnel(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                        if (evaluationBonusRDeptPersonnel == null) {
                            evaluationBonusRDeptPersonnel = new EvaluationBonusRDeptPersonnel();
                            evaluationBonusRDeptPersonnel.setIdcible(cibleBrd);
                            evaluationBonusRDeptPersonnel.setPoint((parametragecritereBrd.getPoint() * cibleBrd.getRatio()) / 100);
                        }
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez définir le point max par catégorie de la prime du résultat revenu du département");
                }

                parametragecritereHsn = parametragecritereFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), 8, personnel.getIdcategorie().getIdcategorie());
                if (parametragecritereHsn != null) {
                    evaluationheuresuppN = evaluationheuresuppFacadeLocal.findByIdPersonnel(SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), personnel.getIdpersonnel(), 8);
                    if (evaluationheuresuppN == null) {
                        evaluationheuresuppN = new Evaluationheuresupp();
                        evaluationheuresuppN.setIdcritere(parametragecritereHsn.getIdcritere());
                        evaluationheuresuppN.setCoefnuit(parametragecritereHsn.getValeurnuit());
                        evaluationheuresuppN.setCoefjour(parametragecritereHsn.getValeurjournee());
                    }
                } else {
                    JsfUtil.addErrorMessage("Veuillez définir les coefficient pour les heures perdues");
                }

                evaluationPenaliteDept = evaluationPenaliteDeptFacadeLocal.findByIdService(personnel.getIdservice().getIdservice(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                if (evaluationPenaliteDept != null) {
                    note.setPenaliteDepartement(evaluationPenaliteDept.getValeur());
                } else {
                    evaluationPenaliteDept = new EvaluationPenaliteDept();
                }
            }
        }
    }

    public void updateFiltreSc() {
        score = this.sommeNote();
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
                    evp.setIdpersonnel(personnel);
                    evp.setIdsousperiode(sousperiode);
                    evp.setIdperiode(SessionMBean.getPeriode());
                    evp.setNote(0d);
                    evaluationpersonnels.add(evp);

                    list.add(dsc);
                }
            }

            selectedDetailsc.removeAll(list);
            listDetailsc.removeAll(list);
        }
        score = this.sommeNote();
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
            score = sommeNote2(list);
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
                return;
            }

            if (option.equals("jour")) {
                evaluationheuresuppN.setPointjour(evaluationheuresuppN.getCoefjour() * evaluationheuresuppN.getNbheurejour());
            } else {
                evaluationheuresuppN.setPointnuit(evaluationheuresuppN.getCoefnuit() * evaluationheuresuppN.getNbheurenuit());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean verifyQuantite() {
        boolean result = false;
        message = "";
        for (Evaluationpersonnel ev : evaluationpersonnels) {
            if (ev.getNote() > ev.getIddetailsc().getPointMax()) {
                message = "La valeur du sous - critère  : " + ev.getIddetailsc().getIdsouscritere().getNom() + " : " + ev.getNote() + " est supérieur au score max : " + ev.getIddetailsc().getPointMax();
                result = true;
                break;
            }
        }
        return result;
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
                evp.setIdperiode(SessionMBean.getPeriode());
                evp.setIdsousperiode(sousperiode);
                evp.setIdpersonnel(personnel);
                evaluationpersonnelFacadeLocal.create(evp);
            } else {
                evaluationpersonnelFacadeLocal.edit(evp);
            }
        });
    }

    public void save() {
        try {
            if (evaluationpersonnels.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            if (this.verifyQuantite()) {
                JsfUtil.addErrorMessage(message);
                return;
            }

            this.saveEvaluation(evaluationpersonnels);

            double noteFinale = (score);

            this.updateNote(personnel.getIdpersonnel(), personnel.getIdservice().getIdservice(), noteFinale, sousperiode.getIdsousperiode());

            notes.clear();
            if (sousperiode.getIdsousperiode() > 0) {
                notes = noteFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            }

            this.listDetailsc.clear();
            RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private void updateNote(long idPersonnel, long idService, double note, int idSousPeriode) {
        Note n = noteFacadeLocal.findByIdPersonnel(idPersonnel, SessionMBean.getPeriode().getIdperiode(), idSousPeriode);
        if (n == null) {
            n = new Note();
            n.setIdnote(noteFacadeLocal.nextId());
            n.setIdperiode(SessionMBean.getPeriode());
            n.setIdpersonnel(personnel);
            n.setIdsousperiode(sousperiode);
            n.setNotepersonnelle(score);
            n.setNotefinale(note);
            noteFacadeLocal.create(n);
        } else {
            n.setNotepersonnelle(score);
            n.setNotefinale(note);
            noteFacadeLocal.edit(n);
        }
    }

    public void delete(Note n) {
        try {
            if (n != null) {
                evaluationpersonnelFacadeLocal.deleteData(n.getIdpersonnel().getIdpersonnel(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode());
                noteFacadeLocal.remove(n);

                notes.clear();
                if (sousperiode.getIdsousperiode() > 0) {
                    notes = noteFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                }
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private double sommeNote() {
        if (evaluationpersonnels.isEmpty()) {
            return 0;
        }
        double resultat = 0;
        for (Evaluationpersonnel dsc : evaluationpersonnels) {
            resultat += dsc.getNote();
        }
        return resultat;
    }

    private double sommeNote2(List<Evaluationpersonnel> list) {
        if (list.isEmpty()) {
            return 0;
        }
        double resultat = 0;
        for (Evaluationpersonnel dsc : list) {
            resultat += dsc.getNote();
        }
        return resultat;
    }

    private double sommeDetailCritere() {
        if (evaluationpersonnels.isEmpty()) {
            return 0;
        }
        double somme = 0;
        for (Evaluationpersonnel dsc : evaluationpersonnels) {
            somme += dsc.getIddetailsc().getPointMax();
        }
        return somme;
    }

    public void setResponse(String table, int index, List<Evaluationpersonnel> evaluationpersonnels) {
        Evaluationpersonnel ev = evaluationpersonnels.get(index);
        ev.setNote(ev.getIdelementreponse().getValeur());
        evaluationpersonnels.set(index, ev);

        double score = 0;
        for (Evaluationpersonnel evp : evaluationpersonnels) {
            score += evp.getNote();
        }

        if (table.equals("1")) {
            this.score = score;
        }
    }

    public void updateIndice() {

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
            } catch (Exception e) {
            }
        }
    }
}
