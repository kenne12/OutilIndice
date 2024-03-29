/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_responsabilite;

import controllers.util.JsfUtil;
import entities.Critereresponsabilite;
import entities.EffectifResponsabilite;
import entities.Responsabilite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class PrimeResponsabiliteCtrl extends AbstractPrimeResponsabiliteCtrl implements Serializable {

    /**
     * Creates a new instance of PrimeResponsabiliteCtrl
     */
    public PrimeResponsabiliteCtrl() {
    }

    @PostConstruct
    private void init() {
        try {
            listCriteres = critereresponsabiliteFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
            critereresponsabilite.setIdresponsabilite(new Responsabilite());
            criterestructures = criterestructureFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
            criterestructure = criterestructureFacadeLocal.findByIdStructureIdCritere(SessionMBean.getStructure().getIdstructure(), 1);
            totalPointMaxCritere = criterestructure.getResultat();
            indexCritere = criterestructures.indexOf(criterestructure);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareCreate(String option) {
        mode = "Create";
        if (option.equals("2")) {
            this.updateFiltre();
        }
    }

    public void prepareEdit(Critereresponsabilite cr) {
        this.critereresponsabilite = cr;
        mode = "Edit";
        RequestContext.getCurrentInstance().execute("PF('ResponsabiliteEditDialog').show()");
    }

    public void updateFiltre() {
        effectifResponsabilites = effectifResponsabiliteFacadeLocal.findByIdStructure(structure.getIdstructure());
        if (effectifResponsabilites == null || effectifResponsabilites.isEmpty()) {
            JsfUtil.addWarningMessage("Veuillez définir les effectifs par responsabilité pour cette structure");
            return;
        }

        critereresponsabilites.clear();
        List<Critereresponsabilite> list = critereresponsabiliteFacadeLocal.findByIdStructure(structure.getIdstructure());        
        if ((list == null || list.isEmpty())) {
            for (EffectifResponsabilite efr : effectifResponsabilites) {
                Critereresponsabilite cr = new Critereresponsabilite();
                cr.setIdcritereresponsabilite(0l);
                cr.setIdstructure(structure);
                cr.setIdcritere(criterestructure.getCritere());
                cr.setPoint(0d);
                cr.setNombre(efr.getNombre());
                cr.setIdresponsabilite(efr.getResponsabilite());
                cr.setTotal(0);
                cr.setResponsabilite(true);
                critereresponsabilites.add(cr);
            }
        } else {
            critereresponsabilites.addAll(list);
            responsabilites.clear();
            responsabilites.addAll(this.getListResponsabiliteNotCosted(effectifResponsabilites, list));
        }
        sommeDetail(critereresponsabilites);
        RequestContext.getCurrentInstance().execute("PF('ResponsabiliteCreateDialog').show()");
    }

    private List<Responsabilite> getListResponsabiliteNotCosted(List<EffectifResponsabilite> ers, List<Critereresponsabilite> cs) {
        List<Responsabilite> listResult = new ArrayList<>();
        List<Responsabilite> listCoasted = new ArrayList<>();
        cs.forEach(c -> {
            listCoasted.add(c.getIdresponsabilite());
        });

        ers.forEach(c -> {
            if (!listCoasted.contains(c.getResponsabilite())) {
                listResult.add(c.getResponsabilite());
            }
        });
        return listResult;
    }

    public void addResponsabilityToTable() {
        if (!selectedResponsabilites.isEmpty()) {
            for (Responsabilite r : selectedResponsabilites) {
                if (!checkResponsabilityInTable(r)) {
                    Critereresponsabilite cr = new Critereresponsabilite(0l);
                    cr.setIdstructure(structure);
                    cr.setIdcritere(criterestructure.getCritere());
                    cr.setPoint(0d);
                    cr.setIdresponsabilite(r);
                    cr.setResponsabilite(true);
                    cr.setNombre(0);
                    EffectifResponsabilite ef = effectifResponsabiliteFacadeLocal.findByIdStructureAndIdResponsabilite(structure.getIdstructure(), r.getIdresponsabilite());
                    if (ef != null) {
                        cr.setNombre(ef.getNombre());
                    }

                    critereresponsabilites.add(cr);
                }
            }
            responsabilites.removeAll(selectedResponsabilites);
            selectedResponsabilites.clear();
        }
    }

    private boolean checkResponsabilityInTable(Responsabilite r) {
        boolean result = false;
        for (Critereresponsabilite cr : critereresponsabilites) {
            if (cr.getIdresponsabilite().equals(r)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeResponsability(int index) {
        Critereresponsabilite item = critereresponsabilites.get(index);
        if (item.getIdcritereresponsabilite() != 0l) {
            critereresponsabiliteFacadeLocal.remove(item);
            listCriteres = critereresponsabiliteFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
        }
        critereresponsabilites.remove(index);
        responsabilites.add(item.getIdresponsabilite());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void refreshCosting() {
        criterestructure.setResultatfinal(totalPointSaisi);
        criterestructures.set(indexCritere, criterestructure);
        double somme = 0;
        for (int i = 0; i < criterestructures.size(); i++) {
            if (criterestructures.get(i).getPoids() != null && criterestructures.get(i).getPoids() > 0) {
                somme += criterestructures.get(i).getResultatfinal();
            }
        }
        for (int i = 0; i < criterestructures.size(); i++) {
            if (criterestructures.get(i).getPoids() != null && criterestructures.get(i).getPoids() > 0) {
                criterestructures.get(i).setPoidsfinal((criterestructures.get(i).getResultatfinal() / somme) * 100);
                criterestructures.get(i).setEcart(criterestructures.get(i).getPoidsfinal() - criterestructures.get(i).getPoids());
            }
        }
        criterestructure = criterestructures.get(indexCritere);
    }

    public void updateSaisieLine(int index) {
        try {
            critereresponsabilites.get(index).setTotal(critereresponsabilites.get(index).getPoint() * critereresponsabilites.get(index).getNombre());
            this.sommeDetail(critereresponsabilites);
        } catch (Exception e) {
            critereresponsabilites.get(index).setTotal(0);
        }
    }

    public void sommeDetail(List<Critereresponsabilite> list) {
        this.totalPointSaisi = 0;
        this.totalEffectifs = 0;
        for (Critereresponsabilite cr : list) {
            totalEffectifs += cr.getNombre();
            try {
                totalPointSaisi += cr.getTotal();
            } catch (Exception e) {
            }
        }
    }

    public void save() {
        try {
            if (critereresponsabilites.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            /*if (totalPointSaisi > totalPointMaxCritere) {
             JsfUtil.addErrorMessage("Le total saisi depasse le total point max possible");
             return;
             }*/
            for (Critereresponsabilite cr : critereresponsabilites) {
                if (cr.getIdcritereresponsabilite() == 0l) {
                    cr.setIdcritereresponsabilite(critereresponsabiliteFacadeLocal.nextId());
                    critereresponsabiliteFacadeLocal.create(cr);
                } else {
                    critereresponsabiliteFacadeLocal.edit(cr);
                }
            }
            listCriteres = critereresponsabiliteFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
            criterestructureFacadeLocal.edit(criterestructure);
            criterestructures.forEach(cs -> {
                criterestructureFacadeLocal.edit(cs);
            });
            this.critereresponsabilites.clear();

            RequestContext.getCurrentInstance().execute("PF('ResponsabiliteCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void edit() {
        try {
            critereresponsabiliteFacadeLocal.edit(critereresponsabilite);
            critereresponsabilite = new Critereresponsabilite();
            critereresponsabilite.setIdresponsabilite(new Responsabilite());
            listCriteres = critereresponsabiliteFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
            RequestContext.getCurrentInstance().execute("PF('ResponsabiliteEditDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Critereresponsabilite cr) {
        try {
            critereresponsabiliteFacadeLocal.remove(cr);
            listCriteres = critereresponsabiliteFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
