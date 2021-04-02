/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.critere_uo;

import controllers.util.JsfUtil;
import entities.Critere;
import entities.Criterestructure;
import entities.CriterestructurePK;
import entities.Souscritere;
import entities.Structure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.Transactional;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class CritereStrController extends AbstractCritereStr implements Serializable {

    public CritereStrController() {

    }

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
    }

    public void prepareCreate() {
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    public void prepareEdit(Structure s) {
        this.structure = s;
        if (structure != null) {
            mode = "Edit";
            this.updateFiltre();
            RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
        }
    }

    public void updateFiltre() {
        criteres.clear();
        selectedCriteres.clear();
        criterestructures.clear();
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            List<Criterestructure> list = criterestructureFacadeLocal.findByIdStructure(structure.getIdstructure());
            if (list.isEmpty() || list == null) {
                criteres.addAll(critereFacadeLocal.findAllRangeByCode());
            } else {
                criteres.addAll(critereFacadeLocal.findAllRangeByCode());
                for (Criterestructure cs : list) {
                    selectedCriteres.add(cs.getCritere());
                }
                criteres.removeAll(selectedCriteres);
                criterestructures.addAll(list);
                for (int i = 0; i < criterestructures.size(); i++) {
                    criterestructures.get(i).setCreated(true);
                }
            }
        }
        this.sommeCritere();
    }

    public void addCritereToTable() {
        if (!selectedCriteres.isEmpty()) {
            List<Critere> list = new ArrayList();
            for (Critere c : selectedCriteres) {
                if (!checkCritereInTable(c)) {
                    Criterestructure cs = new Criterestructure();
                    cs.setStructure(structure);
                    cs.setCritere(c);
                    if (c.isWorkflow()) {
                        cs.setValeurInferieur(c.getValeurInferieur());
                        cs.setValeurSuperieur(c.getValeurSuperieur());
                    }
                    cs.setPoids(c.getPoids());
                    cs.setPointMax(c.getPointMax());
                    cs.setScoreMoyen(c.getScoreMoyen());
                    cs.setResultat(c.getResultat());
                    cs.setCreated(false);
                    criterestructures.add(cs);
                    list.add(c);
                }
            }
            criteres.removeAll(list);
            selectedCriteres.clear();
        }
        this.sommeCritere();
    }

    private boolean checkCritereInTable(Critere c) {
        boolean result = false;
        for (Criterestructure cs : criterestructures) {
            if (c.getIdcritere().equals(cs.getCritere().getIdcritere())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeCritere(int index) {
        Criterestructure item = criterestructures.get(index);
        if (item.isCreated()) {
            criterestructureFacadeLocal.remove(item);
        }
        criterestructures.remove(index);
        criteres.add(item.getCritere());
        this.sommeCritere();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    public String returnCritere(Structure s) {
        String resultat = "";
        if (s.getCriterestructureCollection() != null) {
            int i = 0;
            for (Criterestructure c : s.getCriterestructureCollection()) {
                if (i == 0) {
                    resultat = "" + c.getCritere().getNom() + " (" + c.getPointMax() + " Pts)";
                } else {
                    resultat += "\n" + c.getCritere().getNom() + " (" + c.getPointMax() + " Pts)";;
                }
                i++;
            }
        }
        return resultat;
    }

    @Transactional
    public void save() {
        try {
            if (criterestructures.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            this.sommeCritere();

            if (this.poids != 100) {
                JsfUtil.addErrorMessage(routine.localizeMessage("notification.erreur_poids"));
                return;
            }

            criterestructures.forEach(cs -> {
                Criterestructure obj = criterestructureFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), cs.getCritere().getIdcritere());
                if (obj == null) {
                    cs.setCriterestructurePK(new CriterestructurePK(structure.getIdstructure(), cs.getCritere().getIdcritere()));
                    cs.setStructure(structure);
                    criterestructureFacadeLocal.create(cs);
                } else {
                    criterestructureFacadeLocal.edit(cs);
                }
            });

            this.criterestructures.clear();
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
                souscritere = new Souscritere();
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private void sommeCritere() {
        this.pointMax = 0;
        this.poids = 0.0;
        this.pointObtenu = 0.0;
        if (criterestructures.isEmpty()) {
            return;
        }
        for (Criterestructure cs : criterestructures) {
            poids += cs.getPoids();
            pointMax += cs.getPointMax();
            pointObtenu += cs.getPoidsfinal();
        }
    }

}
