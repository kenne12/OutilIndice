/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.s_critere_service;

import controllers.util.JsfUtil;
import entities.Service;
import entities.Souscritere;
import entities.Souscritereservice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author USER
 */
@ManagedBean
@SessionScoped
public class SousCritereServiceController extends AbstractSousCritereService implements Serializable {

    @PostConstruct
    private void init() {

    }

    public void prepareCreate() {
        this.souscriteres.clear();
        this.selectedSouscriteres.clear();
        this.score = 0.0;
        this.mode = "Create";
        this.message = "";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    public void prepareEdit(Service s) {
        this.service = s;
        this.mode = "Edit";
        this.message = "";
        updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    public void updateFiltre() {
        this.souscriteres.clear();
        this.souscritereservices.clear();
        this.selectedSouscriteres.clear();
        if (this.service.getIdservice() != null && this.service.getIdservice() > 0L) {
            this.souscritereservices = this.souscritereserviceFacadeLocal.findByIdServiceIdCritere(this.structure.getIdstructure(), this.service.getIdservice(), 5);
            if (!this.souscritereservices.isEmpty()) {
                List<Souscritere> list = new ArrayList<>();
                for (Souscritereservice scs : this.souscritereservices) {
                    list.add(scs.getIdsouscritere());
                }
                this.souscriteres = this.souscritereFacadeLocal.findByIdCritere(5);
                if (!this.souscriteres.isEmpty()) {
                    this.souscriteres.removeAll(list);
                }
            } else {
                this.souscriteres = this.souscritereFacadeLocal.findByIdCritere(5);
            }
        }
        this.score = sommeCritere();
    }

    public void addSousCritereToTable() {
        if (!this.selectedSouscriteres.isEmpty()) {
            for (Souscritere sc : this.selectedSouscriteres) {
                if (!checkCritereInTable(sc)) {
                    Souscritereservice obj = new Souscritereservice();
                    obj.setIdsouscritereservice((0L));
                    obj.setIdservice(this.service);
                    obj.setIdsouscritere(sc);
                    obj.setDetail(sc.getDetail());
                    obj.setPointmax(sc.getPointmax());
                    this.souscritereservices.add(obj);
                }
            }
            this.score = sommeCritere();
            this.souscriteres.removeAll(this.selectedSouscriteres);
            this.selectedSouscriteres.clear();
        }
    }

    private boolean checkCritereInTable(Souscritere sc) {
        boolean result = false;
        for (Souscritereservice scs : this.souscritereservices) {
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

    public void removeCritere(int index) {
        Souscritereservice item = souscritereservices.get(index);
        if (item.getIdsouscritereservice() != 0L && item.getIdsouscritereservice() != null) {
            this.souscritereserviceFacadeLocal.remove(item);

        }
        this.souscritereservices.remove(item);
        this.score = sommeCritere();
        souscriteres.add(item.getIdsouscritere());
        JsfUtil.addSuccessMessage(this.routine.localizeMessage("notification.operation_reussie"));
    }

    public void editDetail() {
        if (this.souscritereservice.getIdsouscritereservice() != null && this.souscritereservice.getIdsouscritereservice() > 0L) {
            int i = 0;
            for (Souscritereservice scs : this.souscritereservices) {
                if (scs.getIdsouscritereservice().equals(scs.getIdsouscritereservice())) {
                    break;
                }
                i++;
            }
            this.souscritereservices.set(i, this.souscritereservice);
        } else {
            int i = 0;
            for (Souscritereservice scs : this.souscritereservices) {
                if (scs.getIdsouscritere().equals(scs.getIdsouscritere())) {
                    break;
                }
                i++;
            }
            this.souscritereservices.set(i, this.souscritereservice);
        }
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').hide()");
    }

    public String returnCritere(Service s) {
        String resultat = "";
        if (s.getSouscritereserviceCollection() != null) {
            s.setSouscritereserviceCollection(this.souscritereserviceFacadeLocal.findByIdService(this.structure.getIdstructure(), s.getIdservice()));
        }
        int i = 0;
        for (Souscritereservice d : s.getSouscritereserviceCollection()) {
            if (i == 0) {
                resultat = "" + d.getIdsouscritere().getNom() + " (" + d.getPointmax() + " Pts)";
            } else {
                resultat = resultat + "\n" + d.getIdsouscritere().getNom() + " (" + d.getPointmax() + " Pts)";
            }
            i++;
        }
        return resultat;
    }

    public void save() {
        try {
            if (this.souscritereservices.isEmpty()) {
                JsfUtil.addErrorMessage(this.routine.localizeMessage("common.tableau_vide"));
                return;
            }
            this.souscritereservices.forEach(scs -> {
                if (scs.getIdsouscritereservice() == 0L) {
                    scs.setIdsouscritereservice(this.souscritereserviceFacadeLocal.nextId());
                    scs.setStructure(this.structure);
                    this.souscritereserviceFacadeLocal.create(scs);
                } else {
                    this.souscritereserviceFacadeLocal.edit(scs);
                }
            });
            this.souscritereservices.clear();
            RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').hide()");
            JsfUtil.addSuccessMessage(this.routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Souscritere sc) {
        try {
            if (sc != null) {
                this.souscritereFacadeLocal.remove(sc);
                JsfUtil.addSuccessMessage(this.routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionn");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private double sommeCritere() {
        if (this.souscritereservices.isEmpty()) {
            return 0.0;
        }
        double resultat = 0.0;
        for (Souscritereservice ssc : this.souscritereservices) {
            resultat += ssc.getPointmax();
        }
        return resultat;
    }

}
