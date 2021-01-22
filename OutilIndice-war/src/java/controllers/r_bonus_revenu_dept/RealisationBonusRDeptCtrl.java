/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.r_bonus_revenu_dept;

import controllers.util.JsfUtil;
import entities.Cible;
import entities.Critere;
import entities.Indicateur;
import entities.Service;
import entities.Sousperiode;
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
public class RealisationBonusRDeptCtrl extends AbstractRealisationBonusRDeptCtrl {

    /**
     * Creates a new instance of RealisationBonusRDeptCtrl
     */
    public RealisationBonusRDeptCtrl() {
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
        RequestContext.getCurrentInstance().execute("PF('PrimeRQntifCreateDialog').show()");
    }
    
    public void prepareEdit(Cible c) {
        this.cible = c;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('PrimeRQntifEditDialog').show()");
    }
    
    public void updateFiltre() {
        cibles.clear();
        if (sousperiode.getIdsousperiode() != null && sousperiode.getIdsousperiode() > 0) {
            List<Cible> list = cibleFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 6);
            if (list.isEmpty() || list == null) {
                JsfUtil.addWarningMessage("Cibles non programmées");
            } else {
                cibles.addAll(list);
                this.getTotals(cibles);
            }
        }
    }
    
    public void searchData() {
        try {
            listCibles.clear();
            if (sousperiode.getIdsousperiode() != null) {
                listCibles = cibleFacadeLocal.findByIdStructureSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 6);
                this.getTotals2(listCibles);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            listCibles = cibleFacadeLocal.findByIdSousPeriode(SessionMBean.getStructure().getIdstructure(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 6);
            this.cibles.clear();
            cible = new Cible();
            cible.setIdservice(new Service());
            RequestContext.getCurrentInstance().execute("PF('PrimeRQntifCreateDialog').hide()");
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
            this.getTotals2(listCibles);
            RequestContext.getCurrentInstance().execute("PF('PrimeRQntifEditDialog').hide()");
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
    
    public void getTotals(List<Cible> list) {
        try {
            this.totalCible = 0;
            this.totalRealisation = 0;
            this.totalRatio = 0;
            int counter = 0;
            for (Cible c : list) {
                totalCible += c.getValeurcible();
                try {
                    totalRealisation += c.getValeurrealisee();
                    list.get(counter).setRatio((c.getValeurrealisee() / c.getValeurcible()) * 100);
                } catch (Exception e) {
                    list.get(counter).setRatio(0);
                }
                counter++;
            }
            totalRatio = (totalRealisation / totalCible) * 100;
        } catch (Exception e) {
            totalRatio = 0;
        }
    }
    
    public void getTotals2(List<Cible> list) {
        try {
            this.totalCible = 0;
            this.totalRealisation = 0;
            this.totalRatio = 0;
            for (Cible c : list) {
                totalCible += c.getValeurcible();
                totalRealisation += c.getValeurrealisee();
            }
            totalRatio = (totalRealisation / totalCible) * 100;
        } catch (Exception e) {
            totalRatio = 0;
        }
    }
    
    public void updateLine() {
        try {
            cible.setRatio((cible.getValeurrealisee() / cible.getValeurcible()) * 100);
        } catch (Exception e) {
            cible.setRatio(0);
        }
    }
}
