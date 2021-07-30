/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime;

import controllers.util.JsfUtil;
import entities.Depense;
import entities.Note;
import entities.Prime;
import entities.Sousperiode;
import entities.TypeSousPeriode;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.Printer;
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
        stateBtn = false;
        validBtn = true;
        if (sousperiode.getIdsousperiode() > 0) {
            primes = primeFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());

            if (primes.isEmpty()) {
                stateBtn = true;
                validBtn = true;
                JsfUtil.addWarningMessage("Aucune information rétrouvée");
            }
        }
    }

    public void updateEvaluationData() {
        this.primes.clear();
        this.selectedNotes.clear();

        if (sousperiode.getIdsousperiode() > 0) {
            this.notes = noteFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), SessionMBean.getPeriode().getIdperiode(), sousperiode.getIdsousperiode());
            if (this.notes.isEmpty()) {
                JsfUtil.addWarningMessage("Les personnel n'ont pas été évalués");
                return;
            }

            if (!this.notes.get(0).isEtat()) {
                this.notes.clear();
                JsfUtil.addWarningMessage("Veuillez valider au préalable le rapport d'évaluation");
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
            notes.forEach(n -> {
                Prime prime = new Prime(0l);
                prime.setIdnote(n);
                prime.setIdperiode(SessionMBean.getPeriode());
                prime.setIdpersonnel(n.getIdpersonnel());
                prime.setIdsousperiode(sousperiode);
                prime.setNotepersonnelle(n.getTotalPoint());
                prime.setMontantglobal(montantPrime);
                prime.setPoint(n.getTotalPoint());
                prime.setPlafond(n.getIdpersonnel().getPlafond());
                this.totalPoint += n.getTotalPoint();
                this.primes.add(prime);
            });

            this.indice = montantPrime / this.totalPoint;

            int counteur = 0;
            for (Prime p : primes) {
                primes.get(counteur).setIndice(indice);
                if ((p.getPoint() * indice) > p.getPlafond()) {
                    primes.get(counteur).setMontant(p.getPlafond());
                    primes.get(counteur).setRelicat((p.getPoint() * indice) - p.getPlafond());
                } else {
                    primes.get(counteur).setMontant(p.getPoint() * indice);
                }
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

    public void validate() {
        primes.forEach(p -> {
            p.setEtat(true);
            primeFacadeLocal.edit(p);
        });
        JsfUtil.addSuccessMessage("Opération réussie");
    }

    public void delete() {
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void printRapport(String option) {
        try {
            Map parameter = new HashMap();
            parameter.put("idStructure", SessionMBean.getStructure().getIdstructure());
            parameter.put("idPeriode", SessionMBean.getPeriode().getIdperiode());
            parameter.put("idSousPeriode", sousperiode.getIdsousperiode());
            if (option.equals("pdf")) {
                Printer.print("/report/rapport_prime.jasper", parameter);
            } else {
                Printer.DOCX("/report/rapport_prime.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
