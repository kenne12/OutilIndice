/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.s_critere_service;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.Critereservice;
import entities.CritereservicePK;
import entities.Criterestructure;
import entities.Service;
import entities.Souscritere;
import entities.Souscritereservice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;
import utils.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class SousCritereServiceController extends AbstractSousCritereService implements Serializable {

    public SousCritereServiceController() {

    }

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        this.initDetailSCData();
    }

    private void initDetailSCData() {
        listDetail.clear();
        for (int i = 0; i < 4; i++) {
            listDetail.add(0);
        }
    }

    public void prepareCreate() {
        Criterestructure criterestructure = Utilitaires.findCritereSInSession(5);
        if (criterestructure == null) {
            JsfUtil.addWarningMessage("La prime de resultat qualitatif de departement de fait pas partie des critères de cette structure");
            return;
        }

        criteres.clear();
        souscriteres.clear();
        selectedSouscriteres.clear();
        critereservices.clear();
        critereservice.setCritere(new Critere());
        score = 0;
        mode = "Create";
        message = "";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    public void prepareDuplicate() {
        this.initDetailSCData();
        idServiceDestination = 0;
        idService = 0;
        souscritereservices.clear();
        message = "";
        souscritereservicesTemp.clear();
        RequestContext.getCurrentInstance().execute("PF('DuplicationCritereCreateDialog').show()");
    }

    public void prepareEdit(Service s) {
        this.service = s;
        if (service != null) {
            critereservice = new Critereservice();
            critereservice.setCritere(new Critere());
            mode = "Edit";
            message = "";
            this.updateFiltre();
            RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
        }
    }

    public void updateFiltreDuplication() {
        if (idService != 0) {
            critereservicesTemp.clear();
            souscritereservicesTemp.clear();
            List<Critereservice> list = critereserviceFacadeLocal.findByIdService(idService);
            if (list.isEmpty()) {
                JsfUtil.addErrorMessage("Veuillez définir les criteres pour ce service");
                return;
            }

            listDetail.set(0, list.size());
            critereservicesTemp.addAll(list);
            souscritereservicesTemp = souscritereserviceFacadeLocal.findByIdService(structure.getIdstructure(), idService);

            listDetail.set(1, souscritereservicesTemp.size());
        }

        if (idServiceDestination != 0) {

            if (idServiceDestination == idService) {
                JsfUtil.addErrorMessage("Le service source et celui de destination sont identiques");
                return;
            }

            List<Critereservice> list = critereserviceFacadeLocal.findByIdService(idServiceDestination);
            listDetail.set(2, list.size());

            List<Souscritereservice> listDetailDest = souscritereserviceFacadeLocal.findByIdService(structure.getIdstructure(), idServiceDestination);
            listDetail.set(3, listDetailDest.size());

            if (listDetailDest.isEmpty()) {
                if (!souscritereservicesTemp.isEmpty()) {
                    souscritereservices.clear();
                    for (Souscritereservice ssc : souscritereservicesTemp) {
                        Souscritereservice obj = new Souscritereservice();
                        obj.setIdsouscritereservice(0l);
                        obj.setIdsouscritere(ssc.getIdsouscritere());

                        obj.setIdservice(new Service(idServiceDestination));
                        obj.setPointmax(ssc.getPointmax());
                        obj.setDetail(ssc.getDetail());
                        souscritereservices.add(obj);
                    }
                }

                if (!list.isEmpty()) {
                    souscritereservicesTemp.removeAll(list);
                }

                if (!critereservicesTemp.isEmpty()) {
                    critereservices.clear();
                    for (Critereservice cs : critereservicesTemp) {
                        CritereservicePK scPk = new CritereservicePK(idServiceDestination, cs.getCritere().getIdcritere());

                        Critereservice obj = new Critereservice();
                        obj.setCritereservicePK(scPk);
                        obj.setService(serviceFacadeLocal.find(idServiceDestination));

                        obj.setCritere(cs.getCritere());
                        obj.setPoids(cs.getPoids());
                        obj.setPointmax(cs.getPointmax());
                        critereservices.add(obj);
                    }
                }
            }

        }
        score = this.sommeCritere();
    }

    public void updateFiltre() {
        souscriteres.clear();
        critereservices.clear();
        souscritereservices.clear();
        selectedSouscriteres.clear();

        if (service.getIdservice() != null && service.getIdservice() > 0) {
            souscritereservices = souscritereserviceFacadeLocal.findByIdServiceIdCritere(structure.getIdstructure(), service.getIdservice(), 5);
            if (!souscritereservices.isEmpty()) {
                List<Souscritere> list = new ArrayList<>();
                for (Souscritereservice scs : souscritereservices) {
                    list.add(scs.getIdsouscritere());
                }

                souscriteres = souscritereFacadeLocal.findByIdCritere(5);
                if (!souscriteres.isEmpty()) {
                    souscriteres.removeAll(list);
                }
            } else {
                souscriteres = souscritereFacadeLocal.findByIdCritere(5);
            }
        }

        score = this.sommeCritere();
    }

    public void updateFiltreSc() {
        souscriteres.clear();
        if (critereservice.getCritere().getIdcritere() != null && critereservice.getCritere().getIdcritere() > 0) {
            List<Souscritere> list = souscritereFacadeLocal.findByIdCritere(critereservice.getCritere().getIdcritere());
            if (list.isEmpty()) {
                JsfUtil.addErrorMessage("Veuillez définir les sous - criteres pour cette service");
                return;
            }
            souscriteres.addAll(list);
        }
        score = this.sommeCritere();
    }

    public void addSousCritereToTable() {
        if (!selectedSouscriteres.isEmpty()) {
            for (Souscritere sc : selectedSouscriteres) {
                if (!checkCritereInTable(sc)) {
                    Souscritereservice obj = new Souscritereservice();
                    obj.setIdsouscritereservice(0l);
                    obj.setIdservice(service);
                    obj.setIdsouscritere(sc);
                    obj.setDetail(sc.getDetail());
                    obj.setPointmax(sc.getPointmax());
                    souscritereservices.add(obj);
                }
            }
            score = this.sommeCritere();
            souscriteres.removeAll(selectedSouscriteres);
            selectedSouscriteres.clear();
        }

    }

    private boolean checkCritereInTable(Souscritere sc) {
        boolean result = false;
        for (Souscritereservice scs : souscritereservices) {
            if (scs.getIdsouscritere().getIdsouscritere().equals(sc.getIdsouscritere())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void prepareEditDetail(Souscritereservice item) {
        this.souscritereservice = item;
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').show()");
    }

    public void removeCritere(Souscritereservice item) {
        if (item.getIdsouscritereservice() != 0 && item.getIdsouscritereservice() != null) {
            souscritereserviceFacadeLocal.remove(item);
            souscritereservices.remove(item);
        } else {
            int conteur = 0;
            for (Souscritereservice scs : souscritereservices) {
                if (Objects.equals(item.getIdsouscritere().getIdsouscritere(), scs.getIdsouscritere().getIdsouscritere())) {
                    break;
                }
                conteur++;
            }
            souscritereservices.remove(conteur);
        }
        score = this.sommeCritere();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    private boolean verifyQuantite() {
        boolean result = false;
        for (Critereservice sc : critereservices) {
            double pointMax = 0;
            for (Souscritereservice scs : souscritereservices) {
                if (scs.getIdsouscritere().getIdcritere().equals(sc.getCritere())) {
                    pointMax += scs.getPointmax();
                }
            }

            if (pointMax > sc.getPointmax()) {
                message = "L'ensemble des sous critère de : " + sc.getCritere().getNom() + " Sont supérieur au poid max : " + sc.getPointmax();
                result = true;
                break;
            }
        }
        return result;
    }

    public void editDetail() {
        if (souscritereservice.getIdsouscritereservice() != null && souscritereservice.getIdsouscritereservice() > 0) {
            int i = 0;
            for (Souscritereservice scs : souscritereservices) {
                if (scs.getIdsouscritereservice().equals(scs.getIdsouscritereservice())) {
                    break;
                }
                i++;
            }
            souscritereservices.set(i, souscritereservice);
        } else {
            int i = 0;
            for (Souscritereservice scs : souscritereservices) {
                if (scs.getIdsouscritere().equals(scs.getIdsouscritere())) {
                    break;
                }
                i++;
            }
            souscritereservices.set(i, souscritereservice);
        }
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').hide()");
    }

    public String returnCritere(Service s) {
        String resultat = "";

        if (s.getSouscritereserviceCollection() != null) {
            s.setSouscritereserviceCollection(souscritereserviceFacadeLocal.findByIdService(structure.getIdstructure(), s.getIdservice()));
        }
        int i = 0;
        for (Souscritereservice d : s.getSouscritereserviceCollection()) {
            if (i == 0) {
                resultat = "" + d.getIdsouscritere().getNom() + " (" + d.getPointmax() + " Pts)";
            } else {
                resultat += "\n" + d.getIdsouscritere().getNom() + " (" + d.getPointmax() + " Pts)";;
            }
            i++;
        }
        return resultat;
    }

    public void duplicate() {
        try {
            if (critereservices.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            if (this.verifyQuantite()) {
                JsfUtil.addErrorMessage(message);
                return;
            }

            for (Critereservice cs : critereservices) {
                Critereservice csTemp = critereserviceFacadeLocal.findByIdServiceIdCritere(idServiceDestination, cs.getCritere().getIdcritere());
                if (csTemp == null) {
                    csTemp = new Critereservice(new CritereservicePK(idServiceDestination, cs.getCritere().getIdcritere()));
                    csTemp.setPoids(cs.getPoids());
                    csTemp.setPointmax(cs.getPointmax());
                    csTemp.setCritere(cs.getCritere());
                    critereserviceFacadeLocal.create(csTemp);
                }
            }

            souscritereservices.forEach(ssc -> {
                ssc.setIdsouscritereservice(souscritereserviceFacadeLocal.nextId());
                souscritereserviceFacadeLocal.create(ssc);
            });

            this.souscritereservices.clear();
            critereservices.clear();
            critereservicesTemp.clear();
            this.souscritereservicesTemp.clear();
            this.initDetailSCData();

            RequestContext.getCurrentInstance().execute("PF('DuplicationCritereCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void save() {
        try {
            if (souscritereservices.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            souscritereservices.forEach(scs -> {
                if (scs.getIdsouscritereservice() == 0l) {
                    scs.setIdsouscritereservice(souscritereserviceFacadeLocal.nextId());
                    scs.setStructure(structure);
                    souscritereserviceFacadeLocal.create(scs);
                } else {
                    souscritereserviceFacadeLocal.edit(scs);
                }
            });

            this.souscritereservices.clear();
            critereservices.clear();

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
                souscritereFacadeLocal.remove(sc);
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
        if (souscritereservices.isEmpty()) {
            return 0;
        }
        double resultat = 0;
        for (Souscritereservice ssc : souscritereservices) {
            resultat += ssc.getPointmax();
        }
        return resultat;
    }

}
