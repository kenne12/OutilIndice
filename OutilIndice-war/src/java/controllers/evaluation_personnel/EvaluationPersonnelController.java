/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.evaluation_personnel;

import controllers.util.JsfUtil;
import entities.Categorie;
import entities.Categoriestructure;
import entities.Critere;
import entities.Criterestructure;
import entities.Detailsc;
import entities.Elementreponse;
import entities.Evaluationpersonnel;
import entities.Note;
import entities.Noteservice;
import entities.Periode;
import entities.Personnel;
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

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        structure = SessionMBean.getStructure();
        this.initNote();
    }

    private void initNote() {
        note = new Note();
        note.setIdperiode(new Periode());
        note.setIdpersonnel(new Personnel());
        note.setIdsousperiode(new Sousperiode());
    }

    public EvaluationPersonnelController() {

    }

    public void prepareCreate() {
        sousperiode = new Sousperiode();
        sousperiode.setIdsousperiode(0);
        personnel = new Personnel();
        personnel.setIdpersonnel(0l);
        categorie = new Categorie();
        listDetailsc.clear();
        evaluationpersonnels.clear();
        score = 0;
        mode = "Create";
        message = "";
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
        this.evaluationpersonnelIps.clear();
        this.evaluationpersonnelIns.clear();
        List<Evaluationpersonnel> list = evaluationpersonnelFacadeLocal.findByPersonnel(n.getIdpersonnel().getIdpersonnel(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode());

        if (!list.isEmpty()) {
            for (Evaluationpersonnel evp : list) {
                if (!evp.getIddetailsc().getIdsouscritere().getIncitatif()) {
                    this.evaluationpersonnels.add(evp);
                } else if (evp.getIddetailsc().getIdsouscritere().getSigne().equals("+")) {
                    this.evaluationpersonnelIps.add(evp);
                } else {
                    this.evaluationpersonnelIns.add(evp);
                }
            }
        }

        score = this.sommeNote();
        this.scoreIndice = sommeIndice(evaluationpersonnels);
        this.score_1 = this.sommeNote2(evaluationpersonnelIps);
        this.score_2 = this.sommeNote2(evaluationpersonnelIns);
        RequestContext.getCurrentInstance().execute("PF('EvaluationDetailDialog').show()");
    }

    public void closeDetail() {
        evaluationpersonnels.clear();
        this.initNote();
        RequestContext.getCurrentInstance().execute("PF('ViewEditDialog').hide()");
    }

    public void updateEvaluationData() {
        evaluationpersonnels.clear();
        evaluationpersonnelIns.clear();
        evaluationpersonnelIps.clear();

        listDetailsc.clear();
        selectedDetailsc.clear();

        categoriestructure = new Categoriestructure();
        score = 0;
        scoreIndice = 0;
        if (personnel.getIdpersonnel() > 0) {
            if (sousperiode.getIdsousperiode() > 0) {
                personnel = personnelFacadeLocal.find(personnel.getIdpersonnel());

                /*categoriestructure = categoriestructureFacadeLocal.findByIdStructureIdCategorie(SessionMBean.getStructure().getIdstructure(), personnel.getIdcategorie().getIdcategorie());
                 if (categoriestructure == null) {
                 JsfUtil.addErrorMessage("Veuillez définir le barème d'évaluation  pour cette catégorie");
                 return;
                 }*/
                List<Detailsc> listDetail = detailscFacadeLocal.findByIdStructure(structure.getIdstructure(), true);

                if (listDetail.isEmpty()) {
                    JsfUtil.addErrorMessage("Veuillez définir les sous critère d'évaluation pour cette catégorie");
                    return;
                }

                criterestructures = criterestructureFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());

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
                        if (d.getIdsouscritere().getIncitatif()) {
                            ev.setMultiplicateur(d.getIdsouscritere().getMultiplicateur());
                        } else {
                            ev.setMultiplicateur(0);
                        }
                        ev.setIddetailsc(d);
                        ev.setIdpersonnel(personnel);
                        ev.setPointmaxindice(d.getPointmaxindice());

                        ev.setObservation("---");
                        if (!ev.getIddetailsc().getIdsouscritere().getIncitatif()) {
                            evaluationpersonnels.add(ev);
                            scoreIndice += d.getPointmaxindice();
                        } else if (ev.getIddetailsc().getIdsouscritere().isPositif()) {
                            evaluationpersonnelIps.add(ev);
                        } else {
                            evaluationpersonnelIns.add(ev);
                        }
                        adds.add(d);
                    }
                    this.listDetailsc.removeAll(adds);
                } else {

                    for (Evaluationpersonnel evp : list) {
                        if (!evp.getIddetailsc().getIdsouscritere().getIncitatif()) {
                            evaluationpersonnels.add(evp);
                        } else if (evp.getIddetailsc().getIdsouscritere().isPositif()) {
                            evaluationpersonnelIps.add(evp);
                        } else {
                            evaluationpersonnelIns.add(evp);
                        }
                        adds.add(evp.getIddetailsc());
                    }

                    listDetailsc.removeAll(adds);

                    this.score = this.sommeNote();
                    this.scoreIndice = this.sommeIndice(list);
                    this.score_1 = this.sommeNote2(evaluationpersonnelIps);
                    this.score_2 = this.sommeNote2(evaluationpersonnelIns);
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
                    evp.setPointmaxindice(dsc.getPointmaxindice());

                    if (!dsc.getIdsouscritere().getIncitatif()) {
                        evaluationpersonnels.add(evp);
                        scoreIndice += dsc.getPointmaxindice();
                    } else if (dsc.getIdsouscritere().isPositif()) {
                        evaluationpersonnelIps.add(evp);
                    } else {
                        evaluationpersonnelIns.add(evp);
                    }

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
            scoreIndice = sommeIndice(list);
        }

        if (table.equals("2")) {
            score_1 = sommeNote2(list);
        }

        if (table.equals("3")) {
            score_2 = sommeNote2(list);
        }

        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
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
            this.saveEvaluation(evaluationpersonnelIns);
            this.saveEvaluation(evaluationpersonnelIps);
            double noteFinale = (score + score_1) - score_2;

            this.updateNote(personnel.getIdpersonnel(), personnel.getIdservice().getIdservice(), noteFinale, sousperiode.getIdsousperiode());

            notes.clear();
            if (sousperiode.getIdsousperiode() > 0) {
                notes = noteFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            }

            this.listDetailsc.clear();
            criterestructures.clear();
            RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private void updateNote(long idPersonnel, long idService, double note, int idSousPeriode) {
        Note n = noteFacadeLocal.findByIdPersonnel(idPersonnel, SessionMBean.getPeriode().getIdperiode(), idSousPeriode);
        Noteservice noteservice = noteserviceFacadeLocal.findByIdService(idService, SessionMBean.getPeriode().getIdperiode(), idSousPeriode);
        if (n == null) {
            n = new Note();
            n.setIdnote(noteFacadeLocal.nextId());
            n.setIdperiode(SessionMBean.getPeriode());
            n.setIdpersonnel(personnel);
            n.setIdsousperiode(sousperiode);
            n.setNotepersonnelle(score);
            n.setNotein(this.sommeNote2(evaluationpersonnelIns));
            n.setNoteip(this.sommeNote2(evaluationpersonnelIps));
            n.setNotefinale(note);
            n.setSommeindice(scoreIndice);
            n.setPoidpersonnel(this.sommeDetailCritere());
            n.setNoteservice(0d);
            n.setPoidsservice(0d);
            if (noteservice != null) {
                n.setNoteservice(noteservice.getNote());
                n.setPoidsservice(noteservice.getPoids());
            }
            noteFacadeLocal.create(n);
        } else {
            n.setNotepersonnelle(score);
            n.setNotein(this.sommeNote2(evaluationpersonnelIns));
            n.setNoteip(this.sommeNote2(evaluationpersonnelIps));
            n.setNotefinale(note);
            n.setSommeindice(scoreIndice);
            n.setPoidpersonnel(this.sommeDetailCritere());
            if (noteservice != null) {
                n.setNoteservice(noteservice.getNote());
                n.setPoidsservice(noteservice.getPoids());
            }
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

    private double sommeIndice(List<Evaluationpersonnel> list) {
        if (list.isEmpty()) {
            return 0;
        }
        double resultat = 0;
        for (Evaluationpersonnel dsc : list) {
            resultat += dsc.getPointmaxindice();
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
        } else if (table.equals("2")) {
            this.score_1 = score;
        } else if (table.equals("3")) {
            this.score_2 = score;
        }
    }

    public void updateIndice() {
        this.scoreIndice = sommeIndice(evaluationpersonnels);
    }

}
