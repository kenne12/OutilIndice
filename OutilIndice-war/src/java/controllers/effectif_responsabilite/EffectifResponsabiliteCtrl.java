/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.effectif_responsabilite;

import controllers.util.JsfUtil;
import entities.Responsabilite;
import entities.EffectifResponsabilite;
import entities.Personnel;
import entities.Structure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class EffectifResponsabiliteCtrl extends AbstractEffectifResponsabiliteCtrl implements Serializable {

    /**
     * Creates a new instance of EffectifResponsabiliteCtrl
     */
    public EffectifResponsabiliteCtrl() {
    }

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
    }

    public void prepareCreate() {
        mode = "Create";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('EffectifResponsabiliteCreateDialog').show()");
    }

    public void prepareEdit(Structure s) {
        this.structure = s;
        if (structure != null) {
            mode = "Edit";
            this.updateFiltre();
            RequestContext.getCurrentInstance().execute("PF('EffectifResponsabiliteCreateDialog').show()");
        }
    }

    public void updateFiltre() {
        responsabilites.clear();
        selectedResponsabilites.clear();
        effectifResponsabilites.clear();
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            List<EffectifResponsabilite> list = effectifResponsabiliteFacadeLocal.findByIdStructure(structure.getIdstructure());
            if (list.isEmpty() || list == null) {
                responsabilites.addAll(responsabiliteFacadeLocal.findAll());
            } else {
                responsabilites.addAll(responsabiliteFacadeLocal.findAll());
                for (EffectifResponsabilite cs : list) {
                    selectedResponsabilites.add(cs.getResponsabilite());
                }
                responsabilites.removeAll(selectedResponsabilites);
                effectifResponsabilites.addAll(list);
            }
        }
        this.sommeData();
    }

    public void addResponsabiliteToTable() {
        if (!selectedResponsabilites.isEmpty()) {
            List<Responsabilite> list = new ArrayList<>();
            List<Personnel> personnels = personnelFacadeLocal.findByIdStructure(structure.getIdstructure(), true);
            for (Responsabilite r : selectedResponsabilites) {
                if (!checkResponsabiliteInTable(r)) {

                    List<Personnel> listPr = new ArrayList<>();
                    for (Personnel p : personnels) {
                        if (p.getIdresponsabilite().getIdresponsabilite().equals(r.getIdresponsabilite())) {
                            listPr.add(p);
                        }
                    }

                    EffectifResponsabilite cs = new EffectifResponsabilite();
                    cs.setIdEffectifResponsabilite(0l);
                    cs.setStructure(structure);
                    cs.setResponsabilite(r);
                    cs.setNombre(listPr.size());
                    effectifResponsabilites.add(cs);
                    list.add(r);

                    if (!listPr.isEmpty()) {
                        personnels.removeAll(listPr);
                    }
                }
            }

            this.responsabilites.removeAll(list);
            this.selectedResponsabilites.clear();
            this.sommeData();
        }
    }

    private boolean checkResponsabiliteInTable(Responsabilite c) {
        boolean result = false;
        for (EffectifResponsabilite cs : effectifResponsabilites) {
            if (c.getIdresponsabilite().equals(cs.getResponsabilite().getIdresponsabilite())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeResponsabilite(EffectifResponsabilite item) {
        if (item.getResponsabilite() != null) {
            effectifResponsabiliteFacadeLocal.remove(item);
            effectifResponsabilites.remove(item);
        } else {
            int conteur = 0;
            for (EffectifResponsabilite cs : effectifResponsabilites) {
                if (item.getResponsabilite().getIdresponsabilite().equals(cs.getResponsabilite().getIdresponsabilite())) {
                    break;
                }
                conteur++;
            }
            effectifResponsabilites.remove(conteur);
        }
        this.sommeData();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnResponsabilite(Structure s) {
        try {
            String resultat = "";
            if (s.getEffectifResponsabiliteCollection() != null) {
                int i = 0;
                for (EffectifResponsabilite c : s.getEffectifResponsabiliteCollection()) {
                    if (i == 0) {
                        resultat = "" + c.getResponsabilite().getNom() + " (" + c.getNombre() + ")";
                    } else {
                        resultat += "\n" + c.getResponsabilite().getNom() + " (" + c.getNombre() + ")";;
                    }
                    i++;
                }
            }
            return resultat;
        } catch (Exception e) {
            return "";
        }

    }

    @Transactional
    public void save() {
        try {
            if (effectifResponsabilites.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            effectifResponsabilites.forEach(efc -> {
                if (efc.getIdEffectifResponsabilite().equals(0l)) {
                    efc.setIdEffectifResponsabilite(effectifResponsabiliteFacadeLocal.nextId());
                    effectifResponsabiliteFacadeLocal.create(efc);
                } else {
                    effectifResponsabiliteFacadeLocal.edit(efc);
                }
            });

            this.effectifResponsabilites.clear();
            RequestContext.getCurrentInstance().execute("PF('EffectifResponsabiliteCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Structure s) {
        try {
            effectifResponsabiliteFacadeLocal.deleteByIdStructure(s.getIdstructure());
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private double sommeData() {
        this.effectif = 0;
        if (effectifResponsabilites.isEmpty()) {
            return 0;
        }

        for (EffectifResponsabilite eff : effectifResponsabilites) {
            try {
                effectif += eff.getNombre();
            } catch (Exception e) {
            }
        }
        return this.effectif;
    }

    public void updateSaisie() {
        this.sommeData();
    }

}
