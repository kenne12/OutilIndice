/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.evaluation_service;

import controllers.util.JsfUtil;
import entities.Evaluationservice;
import entities.Note;
import entities.Noteservice;
import entities.Periode;
import entities.Service;
import entities.Souscritereservice;
import entities.Sousperiode;
import java.io.Serializable;
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
public class EvaluationServiceController extends AbstractEvaluationService implements Serializable {

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        structure = SessionMBean.getStructure();
        this.initNote();
    }

    private void initNote() {
        noteservice = new Noteservice();
        noteservice.setIdperiode(new Periode());
        noteservice.setIdservice(new Service());
        noteservice.setIdsousperiode(new Sousperiode());
    }

    public EvaluationServiceController() {

    }

    public void prepareCreate() {
        sousperiode = new Sousperiode();
        sousperiode.setIdsousperiode(0);
        service = new Service();
        service.setIdservice(0l);

        listSouscritereservice.clear();
        score = 0;
        mode = "Create";
        message = "";
        services = serviceFacadeLocal.findAllOrderByCode();
        RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').show()");
    }

    public void prepareEdit(Noteservice n) {
        this.noteservice = n;
        service = n.getIdservice();
        sousperiode = n.getIdsousperiode();
        mode = "Edit";
        message = "";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').show()");
    }

    public void prepareView(Noteservice n) {
        this.noteservice = n;
        evaluationservices = evaluationserviceFacadeLocal.findByService(n.getIdservice().getIdservice(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode());
        score = this.sommeCritere();
        RequestContext.getCurrentInstance().execute("PF('ViewEditDialog').show()");
    }

    public void closeDetail() {
        evaluationservices.clear();
        this.initNote();
        RequestContext.getCurrentInstance().execute("PF('ViewEditDialog').hide()");
    }

    public void filterData() {
        noteservices.clear();
        if (sousperiode.getIdsousperiode() > 0) {
            noteservices = noteserviceFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
        }

        if (noteservices.isEmpty()) {
            JsfUtil.addWarningMessage("Aucune");
        }
    }

    public void updateFiltre() {

    }

    public void updateEvaluationData() {
        listSouscritereservice.clear();
        evaluationservices.clear();
        selectedSouscritereservice.clear();

        if (service.getIdservice() > 0) {
            if (sousperiode.getIdsousperiode() > 0) {
                service = serviceFacadeLocal.find(service.getIdservice());

                List<Souscritereservice> listSousCritereService = souscritereserviceFacadeLocal.findByIdService(structure.getIdstructure(), service.getIdservice());

                if (listSousCritereService.isEmpty()) {
                    JsfUtil.addErrorMessage("Veuillez définir les sous - critère d'évaluation pour ce service");
                    return;
                }

                critereservices = critereserviceFacadeLocal.findByIdService(service.getIdservice());

                this.listSouscritereservice.clear();
                this.listSouscritereservice.addAll(listSousCritereService);

                List<Evaluationservice> list = evaluationserviceFacadeLocal.findByService(structure.getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
                if (list.isEmpty()) {
                    evaluationservices.clear();

                    for (Souscritereservice s : listSousCritereService) {
                        Evaluationservice ev = new Evaluationservice();
                        ev.setIdevaluationservice(0l);
                        ev.setIdsouscritereservice(s);
                        ev.setNote(0d);
                        ev.setObservation("--------");
                        evaluationservices.add(ev);
                    }

                    this.selectedSouscritereservice.clear();
                } else {
                    evaluationservices.clear();
                    evaluationservices.addAll(list);

                    for (Evaluationservice ev : list) {
                        selectedSouscritereservice.add(ev.getIdsouscritereservice());
                    }
                }
            }
        }
    }

    public void updateFiltreSc() {
        score = this.sommeCritere();
    }

    public void addCritereToTable() {
        if (!selectedSouscritereservice.isEmpty()) {
            for (Souscritereservice scs : selectedSouscritereservice) {
                if (!checkCritereInTable(scs)) {
                    Evaluationservice evp = new Evaluationservice();
                    evp.setIdevaluationservice(0l);
                    evp.setIdsouscritereservice(scs);
                    evp.setIdsousperiode(sousperiode);
                    evp.setIdperiode(SessionMBean.getPeriode());
                    evp.setNote(0d);
                    evaluationservices.add(evp);
                }
            }
        }
        score = this.sommeCritere();
    }

    private boolean checkCritereInTable(Souscritereservice scs) {
        boolean result = false;
        for (Evaluationservice evs : evaluationservices) {
            if (evs.getIdsouscritereservice().equals(scs)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void prepareEditDetail(Evaluationservice item) {
        this.evaluationservice = item;
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').show()");
    }

    public void removeCritere(Evaluationservice item) {
        if (item.getIdevaluationservice() != 0 && item.getIdevaluationservice() != null) {
            evaluationserviceFacadeLocal.remove(item);
            evaluationservices.remove(item);
        } else {
            int conteur = 0;
            for (Evaluationservice evs : evaluationservices) {
                if (Objects.equals(item.getIdsouscritereservice(), evs.getIdsouscritereservice())) {
                    break;
                }
                conteur++;
            }
            evaluationservices.remove(conteur);
        }
        score = this.sommeCritere();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    private boolean verifyQuantite() {
        message = "";
        boolean result = false;

        for (Evaluationservice ev : evaluationservices) {
            if (ev.getNote() > ev.getIdsouscritereservice().getPointmax()) {
                message = "La valeur du sous - critère : " + ev.getIdsouscritereservice().getIdsouscritere().getNom() + " :  " + ev.getNote() + " est supérieur au score max : " + ev.getIdsouscritereservice().getPointmax();
                result = true;
                break;
            }
        }
        return result;
    }

    public void editDetail() {
        if (evaluationservice.getIdevaluationservice() != null && evaluationservice.getIdevaluationservice() > 0) {
            int i = 0;
            for (Evaluationservice evs : evaluationservices) {
                if (evs.getIdsouscritereservice().equals(evaluationservice.getIdsouscritereservice())) {
                    break;
                }
                i++;
            }
            evaluationservices.set(i, evaluationservice);
        } else {
            int i = 0;
            for (Evaluationservice evp : evaluationservices) {
                if (evp.getIdsouscritereservice().equals(evaluationservice.getIdsouscritereservice())) {
                    break;
                }
                i++;
            }
            evaluationservices.set(i, evaluationservice);
        }
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').hide()");
    }

    public void save() {
        try {
            if (evaluationservices.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            if (this.verifyQuantite()) {
                JsfUtil.addErrorMessage(message);
                return;
            }

            evaluationservices.forEach(evp -> {
                if (evp.getIdevaluationservice() == 0l) {
                    evp.setIdevaluationservice(evaluationserviceFacadeLocal.nextId());
                    evp.setIdsousperiode(sousperiode);
                    evp.setIdperiode(SessionMBean.getPeriode());
                    evaluationserviceFacadeLocal.create(evp);
                } else {
                    evaluationserviceFacadeLocal.edit(evp);
                }
            });

            this.updateNote(service.getIdservice(), sousperiode.getIdsousperiode(), this.sommeCritere());
            noteservices = noteserviceFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            this.listSouscritereservice.clear();
            critereservices.clear();
            evaluationservices.clear();
            RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private void updateNote(long idService, int idSousPeriode, double note) {
        Noteservice n = noteserviceFacadeLocal.findByIdService(idService, SessionMBean.getPeriode().getIdperiode(), idSousPeriode);
        if (n == null) {
            n = new Noteservice();
            n.setIdnoteservice(noteserviceFacadeLocal.nextId());
            n.setIdperiode(SessionMBean.getPeriode());
            n.setIdservice(new Service(idService));
            n.setIdsousperiode(new Sousperiode(idSousPeriode));
            n.setNote(note);
            n.setPoids(this.sommeDetailCritere());
            noteserviceFacadeLocal.create(n);
        } else {
            n.setNote(note);
            noteserviceFacadeLocal.edit(n);
        }

        List<Note> listNote = noteFacadeLocal.findByIdService(structure.getIdstructure(), idService, SessionMBean.getPeriode().getIdperiode(), idSousPeriode);
        if (!listNote.isEmpty()) {
            for (Note nTemp : listNote) {
                noteFacadeLocal.edit(nTemp);
            }
        }
    }

    private double sommeDetailCritere() {
        if (evaluationservices.isEmpty()) {
            return 0;
        }
        double somme = 0;
        for (Evaluationservice dsc : evaluationservices) {
            somme += dsc.getIdsouscritereservice().getPointmax();
        }
        return somme;
    }

    public void delete(Noteservice ns) {
        try {
            evaluationserviceFacadeLocal.deleteData(ns.getIdservice().getIdservice(), ns.getIdperiode().getIdperiode(), ns.getIdsousperiode().getIdsousperiode());
            noteserviceFacadeLocal.remove(ns);
            noteservices = noteserviceFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private double sommeCritere() {
        if (evaluationservices.isEmpty()) {
            return 0;
        }
        double resultat = 0;
        for (Evaluationservice dsc : evaluationservices) {
            resultat += dsc.getNote();
        }
        return resultat;
    }

}
