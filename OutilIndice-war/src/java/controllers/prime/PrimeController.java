/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime;

import controllers.util.JsfUtil;
import entities.Depense;
import entities.Evaluationpersonnel;
import entities.Note;
import entities.Prime;
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
public class PrimeController extends AbstractPrimeController implements Serializable {

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        structure = SessionMBean.getStructure();
        this.initNote();
    }

    private void initNote() {

    }

    public PrimeController() {

    }

    public void prepareCreate() {
        sousperiode = new Sousperiode();
        sousperiode.setIdsousperiode(0);

        notes.clear();
        selectedNotes.clear();

        score = 0;
        mode = "Create";
        message = "";
        montantPrime = 0;

        RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').show()");
    }

    public void filterData() {
        primes.clear();
        if (sousperiode.getIdsousperiode() > 0) {
            primes = primeFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
        }

        if (notes.isEmpty()) {
            JsfUtil.addWarningMessage("Aucune");
        }
    }

    public void prepareEdit(Note n) {
        this.sousperiode = n.getIdsousperiode();
        mode = "Edit";
        message = "";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').show()");
    }

    public void prepareView(Prime p) {
        this.prime = p;
        evaluationpersonnels = evaluationpersonnelFacadeLocal.findByPersonnel(p.getIdpersonnel().getIdpersonnel(), p.getIdperiode().getIdperiode(), p.getIdsousperiode().getIdsousperiode());
        score = this.sommeCritere();
        RequestContext.getCurrentInstance().execute("PF('ViewEditDialog').show()");
    }

    public void closeDetail() {
        evaluationpersonnels.clear();
        RequestContext.getCurrentInstance().execute("PF('ViewEditDialog').hide()");
    }

    public void updateFiltre() {

    }

    public void updateEvaluationData() {
        evaluationpersonnels.clear();
        this.primes.clear();
        this.selectedNotes.clear();

        if (sousperiode.getIdsousperiode() > 0) {
            this.notes = noteFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            if (this.notes.isEmpty()) {
                JsfUtil.addWarningMessage("Les personnel n'ont pas été évalués");
                return;
            }

            Depense depense = depenseFacadeLocal.findByIdstructureIdperiodeIdSpPrime(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode(), true);
            if (depense == null) {
                JsfUtil.addWarningMessage("Aucune rubrique de prime définie à cette période");
                return;
            }

            montantPrime = depense.getMontant();

            List<Prime> listPrimes = primeFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            if (!listPrimes.isEmpty()) {
                for (Prime p : listPrimes) {
                    selectedNotes.add(p.getIdnote());
                }
                this.primes.addAll(listPrimes);
                return;
            }

            double poidPersonnel = 75;
            double poidService = 25;

            double pointTotal = 0;
            for (Note n : notes) {
                Prime prime = new Prime();
                prime.setIdprime(0l);
                prime.setIdnote(n);
                prime.setIdperiode(SessionMBean.getPeriode());
                prime.setIdpersonnel(n.getIdpersonnel());
                prime.setIdsousperiode(sousperiode);
                prime.setNotepersonnelle(n.getNotepersonnelle());
                prime.setMontantglobal(montantPrime);
                //double point = ((n.getNotepersonnelle() * poidPersonnel) / 100) + ((n.getNoteservice() * poidService) / 100);
                //prime.setPoint(point);
                //pointTotal += point;
                this.primes.add(prime);
            }

            double indice = montantPrime / pointTotal;

            int counteur = 0;
            for (Prime p : primes) {
                primes.get(counteur).setIndice(indice);
                primes.get(counteur).setMontant(p.getPoint() * indice);
                counteur += 1;
            }
        }
    }

    public void updateFiltreSc() {
        score = this.sommeCritere();
    }

    public void addNoteToTable() {
        if (!selectedNotes.isEmpty()) {
            for (Note n : selectedNotes) {
                if (!checkCritereInTable(n)) {
                    Prime obj = new Prime();
                    obj.setIdprime(0l);
                    obj.setIdnote(n);
                    obj.setIdperiode(n.getIdperiode());
                    obj.setIdpersonnel(n.getIdpersonnel());
                    obj.setIdsousperiode(n.getIdsousperiode());
                    obj.setNotepersonnelle(n.getNotepersonnelle());
                    //obj.setNoteservice(n.getNoteservice());
                    primes.add(obj);
                }
            }
        }
        score = this.sommeCritere();
    }

    private boolean checkCritereInTable(Note n) {
        boolean result = false;
        for (Prime obj : primes) {
            if (obj.getIdnote().equals(n)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void prepareEditDetail(Prime item) {
        this.prime = item;
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').show()");
    }

    public void removeCritere(Prime item) {
        if (item.getIdprime() != 0 && item.getIdprime() != null) {
            primeFacadeLocal.remove(item);
            primes.remove(item);
        } else {
            int conteur = 0;
            for (Prime p : primes) {
                if (Objects.equals(item.getIdnote(), p.getIdnote())) {
                    break;
                }
                conteur++;
            }
            primes.remove(conteur);
        }
        score = this.sommeCritere();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    private boolean verifyQuantite() {
        message = "";
        boolean result = false;
        double pointMax = 0;
        for (Evaluationpersonnel ev : evaluationpersonnels) {
            pointMax += ev.getNote();
        }

        /*if (pointMax > categoriestructure.getPointmax()) {
         message = "L'ensemble des valeurs des sous critères  : " + pointMax + " Sont supérieur au poid max : " + categoriestructure.getPointmax();
         result = true;
         }*/

        /*for (Criterestructure sc : criterestructures) {
         double pointMax = 0;
         for (Detailsc dsc : listDetailsc) {
         if (dsc.getIdsouscritere().getIdcritere().equals(sc.getCritere())) {
         pointMax += dsc.getPointMax();
         }
         }

         if (pointMax > sc.getPointmax()) {
         message = "L'ensemble des sous critère de : " + sc.getCritere().getNom() + " Sont supérieur au poid max : " + sc.getPointmax();
         result = true;
         break;
         }
         }*/
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

    public void save() {
        try {
            if (primes.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            if (this.verifyQuantite()) {
                JsfUtil.addErrorMessage(message);
                return;
            }

            primes.forEach(obj -> {
                if (obj.getIdprime() == 0l) {
                    obj.setIdprime(primeFacadeLocal.nextId());
                    obj.setIdperiode(SessionMBean.getPeriode());
                    primeFacadeLocal.create(obj);
                } else {
                    primeFacadeLocal.edit(obj);
                }
            });

            this.primes.clear();
            RequestContext.getCurrentInstance().execute("PF('EvaluationCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Note n) {
        try {
            if (n != null) {
                evaluationpersonnelFacadeLocal.deleteData(n.getIdpersonnel().getIdpersonnel(), n.getIdperiode().getIdperiode(), n.getIdsousperiode().getIdsousperiode());
                noteFacadeLocal.remove(n);
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private double sommeCritere() {
        if (evaluationpersonnels.isEmpty()) {
            return 0;
        }
        double resultat = 0;
        for (Evaluationpersonnel dsc : evaluationpersonnels) {
            resultat += dsc.getNote();
        }
        return resultat;
    }

}
