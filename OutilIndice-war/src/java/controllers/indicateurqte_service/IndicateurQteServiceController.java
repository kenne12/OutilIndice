/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.indicateurqte_service;

import controllers.util.JsfUtil;
import entities.Criterestructure;
import entities.Indicateur;
import entities.IndicateurQteService;
import entities.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class IndicateurQteServiceController extends AbstractIndicateurQteService implements Serializable {

    public IndicateurQteServiceController() {

    }

    @PostConstruct
    private void init() {

    }

    public void prepareCreate() {
        Criterestructure criterestructure = Utilitaires.findCritereSInSession(5);
        if (criterestructure == null) {
            JsfUtil.addWarningMessage("La prime de resultat qualitatif de departement de fait pas partie des critères de cette structure");
            return;
        }

        indicateurs.clear();
        selectedindicateurs.clear();
        mode = "Create";
        message = "";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    public void prepareEdit(Service s) {
        this.service = s;
        mode = "Edit";
        message = "";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");

    }

    public void updateFiltre() {
        indicateurs.clear();
        indicateurQteServices.clear();
        selectedindicateurs.clear();

        if (service.getIdservice() != null && service.getIdservice() > 0) {
            indicateurQteServices = indicateurQteServiceFacadeLocal.findByIdService(structure.getIdstructure(), service.getIdservice());
            indicateurs = indicateurFacadeLocal.findAll();
            if (!indicateurQteServices.isEmpty()) {
                List<Indicateur> list = new ArrayList<>();
                for (IndicateurQteService i : indicateurQteServices) {
                    list.add(i.getIndicateur());
                }

                if (!indicateurs.isEmpty()) {
                    indicateurs.removeAll(list);
                }
            }
        }
    }

    public void addIndicateurToTable() {
        if (!selectedindicateurs.isEmpty()) {
            for (Indicateur i : selectedindicateurs) {
                if (!checkCritereInTable(i)) {
                    IndicateurQteService obj = new IndicateurQteService();
                    obj.setIdIndicateurQteService(0l);
                    obj.setService(service);
                    obj.setIndicateur(i);
                    obj.setStructure(structure);
                    indicateurQteServices.add(obj);
                }
            }
            indicateurs.removeAll(selectedindicateurs);
            selectedindicateurs.clear();
        }

    }

    private boolean checkCritereInTable(Indicateur item) {
        boolean result = false;
        for (IndicateurQteService i : indicateurQteServices) {
            if (i.getIndicateur().getIdindicateur().equals(item.getIdindicateur())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeCritere(IndicateurQteService item) {
        if (item.getIdIndicateurQteService() != 0 && item.getIdIndicateurQteService() != null) {
            indicateurQteServiceFacadeLocal.remove(item);
            indicateurQteServices.remove(item);
        } else {
            int conteur = 0;
            for (IndicateurQteService i : indicateurQteServices) {
                if (Objects.equals(item.getIndicateur().getIdindicateur(), i.getIndicateur().getIdindicateur())) {
                    break;
                }
                conteur++;
            }
            indicateurQteServices.remove(conteur);
        }
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnCritere(Service s) {
        String resultat = "";

        if (s.getSouscritereserviceCollection() != null) {
            s.setIndicateurQteServiceCollection(indicateurQteServiceFacadeLocal.findByIdService(structure.getIdstructure(), s.getIdservice()));
        }
        int count = 0;
        for (IndicateurQteService i : s.getIndicateurQteServiceCollection()) {
            if (count == 0) {
                resultat = "" + i.getIndicateur().getNom() + " ";
            } else {
                resultat += "\n" + i.getIndicateur().getNom() + " ";;
            }
            count++;
        }
        return resultat;
    }

    public void save() {
        try {
            if (indicateurQteServices.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            indicateurQteServices.forEach(i -> {
                if (i.getIdIndicateurQteService() == 0l) {
                    i.setIdIndicateurQteService(indicateurQteServiceFacadeLocal.nextId());
                    i.setStructure(structure);
                    indicateurQteServiceFacadeLocal.create(i);
                } else {
                    indicateurQteServiceFacadeLocal.edit(i);
                }
            });

            this.indicateurQteServices.clear();
            RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void delete(Indicateur i) {
        try {
            if (i != null) {
                indicateurFacadeLocal.remove(i);
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
