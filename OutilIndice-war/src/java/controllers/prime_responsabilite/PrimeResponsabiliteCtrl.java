/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.prime_responsabilite;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.Critereresponsabilite;
import entities.Responsabilite;
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
public class PrimeResponsabiliteCtrl extends AbstractPrimeResponsabiliteCtrl {

    /**
     * Creates a new instance of PrimeResponsabiliteCtrl
     */
    public PrimeResponsabiliteCtrl() {
    }

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        listCriteres = critereresponsabiliteFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
        critereresponsabilite.setIdresponsabilite(new Responsabilite());
    }

    public void prepareCreate() {
        structure = SessionMBean.getStructure();
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('ResponsabiliteCreateDialog').show()");
    }

    public void prepareEdit(Critereresponsabilite cr) {
        this.critereresponsabilite = cr;
        if (structure != null) {
            mode = "Edit";
            RequestContext.getCurrentInstance().execute("PF('ResponsabiliteEditDialog').show()");
        }
    }

    public void updateFiltre() {
        responsabilites.clear();
        selectedResponsabilites.clear();
        critereresponsabilites.clear();
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            List<Critereresponsabilite> list = critereresponsabiliteFacadeLocal.findByIdStructure(structure.getIdstructure());
            if (list.isEmpty() || list == null) {
                responsabilites.addAll(responsabiliteFacadeLocal.findAll());
            } else {
                critereresponsabilites.addAll(list);
                responsabilites.addAll(responsabiliteFacadeLocal.findAll());
                for (Critereresponsabilite cr : list) {
                    selectedResponsabilites.add(cr.getIdresponsabilite());
                }
                responsabilites.removeAll(selectedResponsabilites);
                selectedResponsabilites.clear();
            }
        }
    }

    public void addResponsabilityToTable() {
        if (!selectedResponsabilites.isEmpty()) {
            for (Responsabilite r : selectedResponsabilites) {
                if (!checkResponsabilityInTable(r)) {
                    Critereresponsabilite cr = new Critereresponsabilite();
                    cr.setIdcritereresponsabilite(0l);
                    cr.setIdstructure(structure);
                    cr.setIdcritere(new Critere(1));
                    cr.setPoint(0d);
                    cr.setIdresponsabilite(r);
                    cr.setResponsabilite(true);
                    critereresponsabilites.add(cr);
                }
            }
            responsabilites.removeAll(selectedResponsabilites);
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

    public void removeResponsability(Critereresponsabilite item) {
        if (item.getIdcritereresponsabilite() != 0l) {
            critereresponsabiliteFacadeLocal.remove(item);
            critereresponsabilites.remove(item);
            listCriteres = critereresponsabiliteFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
        } else {
            int conteur = 0;
            for (Critereresponsabilite cr : critereresponsabilites) {
                if (cr.getIdresponsabilite().getIdresponsabilite().equals(item.getIdresponsabilite().getIdresponsabilite())) {
                    break;
                }
                conteur++;
            }
            critereresponsabilites.remove(conteur);
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public void save() {
        try {
            if (critereresponsabilites.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            for (Critereresponsabilite cr : critereresponsabilites) {
                if (cr.getIdcritereresponsabilite() == 0l) {
                    cr.setIdcritereresponsabilite(critereresponsabiliteFacadeLocal.nextId());
                    critereresponsabiliteFacadeLocal.create(cr);
                } else {
                    critereresponsabiliteFacadeLocal.edit(cr);
                }
            }
            listCriteres = critereresponsabiliteFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
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
            if (sc != null) {
                critereresponsabiliteFacadeLocal.remove(cr);
                listCriteres = critereresponsabiliteFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

}
