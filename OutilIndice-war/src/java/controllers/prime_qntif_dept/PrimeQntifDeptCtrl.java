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
import entities.IndicateurQteService;
import entities.Service;
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
        cible = new Cible();
        cible.setIdindicateur(new Indicateur());
        services = SessionMBean.getServices();
        typeSousPeriodes = SessionMBean.getTypeSousPeriodes();
        if (criterestructureFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure(), true).isEmpty()) {
            JsfUtil.addErrorMessage("Veuillez valider les critères d'évaluation");
            return;
        }
        stateBtn = false;
    }

    public void prepareCreate() {
        mode = "Create";
        sousperiode = new Sousperiode();
        typeSousPeriode = new TypeSousPeriode(0);
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
                List<Cible> list = cibleFacadeLocal.findByIdSousPeriode(structure.getIdstructure(), service.getIdservice(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 4);

                List<IndicateurQteService> listIndicateur = indicateurQteServiceFacadeLocal.findByIdService(structure.getIdstructure(), service.getIdservice());

                for (IndicateurQteService i : listIndicateur) {
                    indicateurs.add(i.getIndicateur());
                }

                if (!list.isEmpty()) {
                    cibles.addAll(list);
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
                Cible c = new Cible(0l);
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
            selectedIndicateurs.clear();
        }
    }

    public void removeIndicator(int index, Cible c) {
        Cible cibleTemp = cibles.get(index);
        if (cibleTemp.getIdcible() != 0l) {
            cibleFacadeLocal.remove(c);
            cibles.remove(cibleTemp);
            listCibles = cibleFacadeLocal.findByIdSousPeriode(structure.getIdstructure(), service.getIdservice(), periode.getIdperiode(), sousperiode.getIdsousperiode(), 4);
            indicateurs.add(cibleTemp.getIdindicateur());
        } else {

            cibles.remove(index);
            indicateurs.add(cibleTemp.getIdindicateur());
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void updateSousPeriode(String option) {
        sousperiodes.clear();
        sousperiode = new Sousperiode(0);

        if (option.equals("2")) {
            indicateurs.clear();
            cibles.clear();
        }

        if (option.equals("1")) {
            listCibles.clear();
        }

        if (typeSousPeriode.getIdTypeSousperiode() != 0) {
            sousperiodes = sousperiodeFacadeLocal.findIdTypeSousPeriode(typeSousPeriode.getIdTypeSousperiode());
        }
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
