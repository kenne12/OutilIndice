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
import entities.TypeSousPeriode;
import java.io.Serializable;
import java.util.List;
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
        typeSousPeriodes = SessionMBean.getTypeSousPeriodes();
    }

    public PrimeController() {

    }

    public void prepareCreate() {
        sousperiode = new Sousperiode(0);
        typeSousPeriode = new TypeSousPeriode(0);

        notes.clear();
        selectedNotes.clear();

        mode = "Create";
        message = "";
        montantPrime = 0;
        RequestContext.getCurrentInstance().execute("PF('PrimeCreateDialog').show()");
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
        RequestContext.getCurrentInstance().execute("PF('PrimeCreateDialog').show()");
    }

    public void prepareView(Prime p) {
        this.prime = p;
        evaluationpersonnels = evaluationpersonnelFacadeLocal.findByPersonnel(p.getIdpersonnel().getIdpersonnel(), p.getIdperiode().getIdperiode(), p.getIdsousperiode().getIdsousperiode());
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

            this.totalPoint = 0;
            for (Note n : notes) {
                Prime prime = new Prime(0l);
                prime.setIdnote(n);
                prime.setIdperiode(SessionMBean.getPeriode());
                prime.setIdpersonnel(n.getIdpersonnel());
                prime.setIdsousperiode(sousperiode);
                prime.setNotepersonnelle(n.getTotalPoint());
                prime.setMontantglobal(montantPrime);
                prime.setPoint(n.getTotalPoint());
                this.totalPoint += n.getTotalPoint();
                this.primes.add(prime);
            }

            this.indice = montantPrime / this.totalPoint;

            int counteur = 0;
            for (Prime p : primes) {
                primes.get(counteur).setIndice(indice);
                primes.get(counteur).setMontant(p.getPoint() * indice);
                counteur += 1;
            }
            notes.clear();
        }
    }

    public void updateSousPeriode(String option) {
        sousperiodes.clear();
        sousperiode = new Sousperiode(0);
        if (option.equals("1")) {
            primes.clear();
        }

        if (typeSousPeriode.getIdTypeSousperiode() != 0) {
            sousperiodes = sousperiodeFacadeLocal.findIdTypeSousPeriode(typeSousPeriode.getIdTypeSousperiode());
        }
    }

    public void addNoteToTable() {
        if (!selectedNotes.isEmpty()) {
            for (Note n : selectedNotes) {
                if (!checkCritereInTable(n)) {
                    Prime obj = new Prime(0l);
                    obj.setIdnote(n);
                    obj.setIdperiode(n.getIdperiode());
                    obj.setIdpersonnel(n.getIdpersonnel());
                    obj.setIdsousperiode(n.getIdsousperiode());
                    obj.setNotepersonnelle(n.getTotalPoint());
                    primes.add(obj);
                }
            }
            notes.removeAll(selectedNotes);
        }
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

    public void removePrime(int index) {
        Prime item = primes.get(index);
        if (item.getIdprime() != 0 && item.getIdprime() != null) {
            primeFacadeLocal.remove(item);
        }
        primes.remove(index);
        notes.add(item.getIdnote());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void save() {
        try {
            if (primes.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
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
            RequestContext.getCurrentInstance().execute("PF('PrimeCreateDialog').hide()");
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
