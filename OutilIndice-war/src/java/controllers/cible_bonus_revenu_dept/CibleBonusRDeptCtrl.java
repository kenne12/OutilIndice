/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.cible_bonus_revenu_dept;

import controllers.util.JsfUtil;
import entities.Cible;
import entities.Critere;
import entities.Indicateur;
import entities.Service;
import entities.Sousperiode;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
@ManagedBean
@SessionScoped
public class CibleBonusRDeptCtrl extends AbstractCibleBonusRDeptCtrl implements Serializable {

    /**
     * Creates a new instance of CibleBonusRDeptCtrl
     */
    public CibleBonusRDeptCtrl() {
    }

    @PostConstruct
    private void init() {
        structure = SessionMBean.getStructure();
        structures.clear();
        structures.add(SessionMBean.getStructure());
        cible = new Cible();
        cible.setIdservice(new Service());
    }

    public void prepareCreate() {
        mode = "Create";
        sousperiode = new Sousperiode();
        cible = new Cible();
        cible.setIdservice(new Service());
        cibles.clear();
        RequestContext.getCurrentInstance().execute("PF('CibleBonusRDeptCreateDialog').show()");
    }

    public void prepareEdit(Cible c) {
        this.cible = c;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('CibleBonusRDeptEditDialog').show()");
    }

    public void updateFiltre() {
        services.clear();
        selectedServices.clear();
        cibles.clear();
        this.totalCible = 0;

        if (sousperiode.getIdsousperiode() != null && sousperiode.getIdsousperiode() > 0) {
            List<Cible> list = cibleFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 6);
            services.addAll(SessionMBean.getServices());
            if (!list.isEmpty()) {
                cibles.addAll(list);

                for (Cible c : list) {
                    selectedServices.add(c.getIdservice());
                }

                services.removeAll(selectedServices);
                selectedServices.clear();
                this.sommeDetail(list);
            }
        }
    }

    public void addServiceToTable() {
        if (!selectedServices.isEmpty()) {
            for (Service s : selectedServices) {
                Cible c = new Cible(0l);
                c.setIdcritere(new Critere(6));
                c.setIdstructure(structure);
                c.setIdperiode(periode);
                c.setIdsousperiode(sousperiode);
                c.setIdservice(s);
                c.setIdindicateur(indicateur);
                c.setPrimeresultatquant(false);
                c.setBonusrevenudept(true);
                c.setValeurcible(0);
                cibles.add(c);
            }
            services.removeAll(selectedServices);
        }
    }

    public void removeIndicator(Cible c) {
        if (c.getIdcible() != 0l) {
            cibleFacadeLocal.remove(c);
            cibles.remove(c);
            listCibles = cibleFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 6);
            services.add(c.getIdservice());
        } else {
            int conteur = 0;
            for (Cible cible : cibles) {
                if (cible.getIdservice().getIdservice().equals(c.getIdservice().getIdservice())) {
                    break;
                }
                conteur++;
            }
            cibles.remove(conteur);
            services.add(c.getIdservice());
        }
        this.sommeDetail(cibles);
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void searchData() {
        listCibles.clear();
        if (sousperiode.getIdsousperiode() != null) {
            listCibles = cibleFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 6);
        }
    }

    public double sommeDetail(List<Cible> cibles) {
        this.totalCible = 0;
        if (cibles.isEmpty()) {
            return totalCible;
        }
        for (Cible c : cibles) {
            this.totalCible += c.getValeurcible();
        }
        return totalCible;
    }

    public void updateSasie() {
        this.sommeDetail(cibles);
    }

    public void save() {
        try {
            if (cibles.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            for (Cible c : cibles) {
                if (c.getIdcible() == 0l) {
                    c.setIdcible(cibleFacadeLocal.nextId());
                    cibleFacadeLocal.create(c);
                } else {
                    cibleFacadeLocal.edit(c);
                }
            }
            listCibles = cibleFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 6);
            this.cibles.clear();
            cible = new Cible();
            cible.setIdservice(new Service());
            RequestContext.getCurrentInstance().execute("PF('CibleBonusRDeptCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void edit() {
        try {
            cibleFacadeLocal.edit(cible);
            cible = new Cible();
            cible.setIdindicateur(new Indicateur());
            listCibles = cibleFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 6);
            RequestContext.getCurrentInstance().execute("PF('CibleBonusRDeptEditDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Cible c) {
        try {
            cibleFacadeLocal.remove(c);
            listCibles = cibleFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 6);
            cible = new Cible();
            cible.setIdservice(new Service());
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
}
