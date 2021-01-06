/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.critere_service;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.Critereservice;
import entities.CritereservicePK;
import entities.Criterestructure;
import entities.Service;
import entities.Souscritere;
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
public class CritereServiceController extends AbstractCritereService implements Serializable {

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        structure = SessionMBean.getStructure();
    }

    public CritereServiceController() {

    }

    public void prepareCreate() {        
        service = new Service();
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    public void prepareEdit(Service s) {
        this.service = s;
        if (s != null) {
            mode = "Edit";
            this.updateFiltre();
            RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
        }
    }

    public void updateFiltre() {
        criteres.clear();
        selectedCriteres.clear();
        critereservices.clear();
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            if (service.getIdservice() != null && service.getIdservice() > 0) {

                List<Criterestructure> list = criterestructureFacadeLocal.findByIdStructure(structure.getIdstructure());
                if (!list.isEmpty() || list != null) {
                    List<Critereservice> listCs = critereserviceFacadeLocal.findByIdService(service.getIdservice());
                    if (listCs.isEmpty()) {
                        criteres.addAll(critereFacadeLocal.findAllRangeByCode());
                    } else {
                        criteres.addAll(critereFacadeLocal.findAllRangeByCode());
                        for (Critereservice cs : listCs) {
                            selectedCriteres.add(cs.getCritere());
                        }
                        criteres.removeAll(selectedCriteres);
                        critereservices.addAll(listCs);
                    }
                }
            }
        }
        score = this.sommeCritere();
    }

    public void addCritereToTable() {
        if (!selectedCriteres.isEmpty()) {
            for (Critere c : selectedCriteres) {
                if (!checkCritereInTable(c)) {
                    Critereservice cs = new Critereservice();
                    cs.setService(service);
                    cs.setCritere(c);
                    cs.setPoids(c.getPoids());
                    cs.setPointmax(0d);
                    critereservices.add(cs);
                }
            }
        }
        score = this.sommeCritere();
    }

    private boolean checkCritereInTable(Critere c) {
        boolean result = false;
        for (Critereservice cs : critereservices) {
            if (c.getIdcritere().equals(cs.getCritere().getIdcritere())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeCritere(Critereservice item) {
        if (item.getCritere() != null) {
            critereserviceFacadeLocal.remove(item);
            critereservices.remove(item);
        } else {
            int conteur = 0;
            for (Critereservice cs : critereservices) {
                if (item.getCritere().getIdcritere().equals(cs.getCritere().getIdcritere())) {
                    break;
                }
                conteur++;
            }
            critereservices.remove(conteur);
        }
        score = this.sommeCritere();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnCritere(Service s) {
        String resultat = "";
        if (s.getCritereserviceCollection() != null) {
            int i = 0;
            for (Critereservice c : s.getCritereserviceCollection()) {
                if (i == 0) {
                    resultat = "" + c.getCritere().getNom() + " (" + c.getPointmax() + " Pts)";
                } else {
                    resultat += "\n" + c.getCritere().getNom() + " (" + c.getPointmax() + " Pts)";;
                }
                i++;
            }
        }
        return resultat;
    }

    public void save() {
        try {
            if (critereservices.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            if (this.sommeCritere() > scoreMax) {
                JsfUtil.addErrorMessage(routine.localizeMessage("notification.erreur_poids"));
                return;
            }

            critereservices.forEach(cs -> {
                Critereservice obj = critereserviceFacadeLocal.findByIdServiceIdCritere(service.getIdservice(), cs.getCritere().getIdcritere());
                if (obj == null) {
                    cs.setCritereservicePK(new CritereservicePK(service.getIdservice(), cs.getCritere().getIdcritere()));
                    critereserviceFacadeLocal.create(cs);
                } else {
                    critereserviceFacadeLocal.edit(cs);
                }
            });

            this.critereservices.clear();
            this.service = new Service();

            RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Souscritere sc) {
        try {
            if (sc != null) {

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
        this.pointMax = 0;
        if (critereservices.isEmpty()) {
            return 0;
        }
        double resultat = 0;
        double pointMax = 0;
        for (Critereservice cs : critereservices) {
            resultat += cs.getPoids();
            pointMax += cs.getPointmax();
        }
        this.pointMax = pointMax;
        return resultat;
    }

}
