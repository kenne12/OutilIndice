/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_qntif_dept;

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
public class PrimeQntifDeptCtrl extends AbstractPrimeQntifDeptCtrl implements Serializable {

    /**
     * Creates a new instance of PrimeQntifDeptCtrl
     */
    public PrimeQntifDeptCtrl() {
    }

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        cible = new Cible();
        cible.setIdindicateur(new Indicateur());
    }

    public void prepareCreate() {
        mode = "Create";
        sousperiode = new Sousperiode();
        service = new Service();
        cible = new Cible();
        cible.setIdindicateur(new Indicateur());
        cibles.clear();
        RequestContext.getCurrentInstance().execute("PF('PrimeRQntifCreateDialog').show()");
    }

    public void prepareEdit(Cible c) {
        this.cible = c;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('PrimeRQntifEditDialog').show()");
    }

    public void updateFiltre() {
        indicateurs.clear();
        selectedIndicateurs.clear();
        cibles.clear();
        totalCible = 0;
        if (service.getIdservice() != null && service.getIdservice() > 0) {
            if (sousperiode.getIdsousperiode() != null && sousperiode.getIdsousperiode() > 0) {
                List<Cible> list = cibleFacadeLocal.findByIdSousPeriode(service.getIdservice(), service.getIdservice(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 4);
                if (list.isEmpty() || list == null) {
                    indicateurs.addAll(indicateurFacadeLocal.findAll());
                } else {
                    cibles.addAll(list);
                    indicateurs.addAll(indicateurFacadeLocal.findAll());

                    for (Cible c : list) {
                        selectedIndicateurs.add(c.getIdindicateur());
                    }

                    indicateurs.removeAll(selectedIndicateurs);
                    selectedIndicateurs.clear();
                    this.sommeDetail(list);
                }
            }
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

    public void addIndicatorToTable() {
        if (!selectedIndicateurs.isEmpty()) {
            for (Indicateur i : selectedIndicateurs) {
                Cible c = new Cible();
                c.setIdcible(0l);
                c.setIdcritere(new Critere(4));
                c.setIdstructure(structure);
                c.setIdperiode(periode);
                c.setIdsousperiode(sousperiode);
                c.setIdservice(service);
                c.setIdindicateur(i);
                c.setPrimeresultatquant(true);
                c.setBonusrevenudept(false);
                c.setValeurcible(0);
                cibles.add(c);
            }
            indicateurs.removeAll(selectedIndicateurs);
        }
    }

    public void removeIndicator(Cible c) {
        if (c.getIdcible() != 0l) {
            cibleFacadeLocal.remove(c);
            cibles.remove(c);
            listCibles = cibleFacadeLocal.findByIdSousPeriode(structure.getIdstructure(), service.getIdservice(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 4);
            indicateurs.add(c.getIdindicateur());
        } else {
            int conteur = 0;
            for (Cible cible : cibles) {
                if (cible.getIdindicateur().getIdindicateur().equals(c.getIdindicateur().getIdindicateur())) {
                    break;
                }
                conteur++;
            }
            cibles.remove(conteur);
            indicateurs.add(c.getIdindicateur());
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void searchData() {
        listCibles.clear();
        if (service.getIdservice() != null) {
            if (sousperiode.getIdsousperiode() != null) {
                listCibles = cibleFacadeLocal.findByIdSousPeriode(structure.getIdstructure(), service.getIdservice(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 4);
            }
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
            listCibles = cibleFacadeLocal.findByIdSousPeriode(structure.getIdstructure(), service.getIdservice(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 4);
            this.cibles.clear();
            cible = new Cible();
            cible.setIdindicateur(new Indicateur());
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
            listCibles = cibleFacadeLocal.findByIdSousPeriode(structure.getIdstructure(), cible.getIdservice().getIdservice(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 4);
            cible = new Cible();
            cible.setIdindicateur(new Indicateur());
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
            listCibles = cibleFacadeLocal.findByIdSousPeriode(structure.getIdstructure(), c.getIdservice().getIdservice(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 4);
            cible = new Cible();
            cible.setIdindicateur(new Indicateur());
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
}
