/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.s_critere_uo;

import controllers.util.JsfUtil;
import entities.Criterestructure;
import entities.Detailsc;
import entities.Souscritere;
import entities.Structure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;
import utils.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class SousCritereStrController extends AbstractSousCritereStr implements Serializable {

    public SousCritereStrController() {

    }

    @PostConstruct
    private void init() {
        structures.clear();
        structures.add(SessionMBean.getStructure());
        structure = SessionMBean.getStructure();
        this.initDetailSCData();
    }

    private void initDetailSCData() {
        listDetail.clear();
        for (int i = 0; i < 4; i++) {
            listDetail.add(0);
        }
    }

    public void prepareCreate() {
        Criterestructure criterestructure = Utilitaires.findCritereSInSession(7);
        if (criterestructure == null) {
            JsfUtil.addWarningMessage("La prime de performance individuelle ne fait pas partie des critères de cette structure");
            return;
        }
        souscriteres.clear();
        selectedSouscriteres.clear();
        listDetailsc.clear();
        score = 0;
        mode = "Create";
        message = "";
        RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
    }

    public void prepareDuplicate() {
        this.initDetailSCData();
        idStructureDestination = 0;
        idStructureSource = 0;
        listDetailsc.clear();
        message = "";
        listDetailscTemp.clear();
        listDetailscTemp.clear();
        RequestContext.getCurrentInstance().execute("PF('DuplicationCritereCreateDialog').show()");
    }

    public void prepareEdit(Structure s) {
        this.structure = s;
        if (structure != null) {
            mode = "Edit";
            message = "";
            this.updateFiltre();
            RequestContext.getCurrentInstance().execute("PF('CritereCreateDialog').show()");
        }
    }

    public void updateFiltreDuplication() {
        if (idStructureSource != 0) {

            //criterestructuresTemp.clear();
            listDetailscTemp.clear();
            /*List<Criterestructure> list = criterestructureFacadeLocal.findByIdStructure(idStructureSource);
             if (list.isEmpty()) {
             JsfUtil.addErrorMessage("Veuillez définir les criteres pour cette structure");
             return;
             }

             listDetail.set(0, list.size());
             criterestructuresTemp.addAll(list);
             listDetailscTemp = detailscFacadeLocal.findByIdStructure(idStructureSource, categorie.getIdcategorie());
             listDetail.set(1, listDetailscTemp.size());*/

        }

        if (idStructureDestination != 0) {

            if (idStructureDestination == idStructureSource) {
                JsfUtil.addErrorMessage("La structure source et celle de destination sont identiques");
                return;
            }

            /*List<Criterestructure> list = criterestructureFacadeLocal.findByIdStructure(idStructureDestination);
             listDetail.set(2, list.size());

             List<Detailsc> listDetailDest = detailscFacadeLocal.findByIdStructure(idStructureDestination, categorie.getIdcategorie());
             listDetail.set(3, listDetailDest.size());

             if (listDetailDest.isEmpty()) {
             if (!listDetailscTemp.isEmpty()) {
             listDetailsc.clear();
             for (Detailsc dsc : listDetailscTemp) {
             Detailsc obj = new Detailsc();
             obj.setIddetailsc(0l);
             obj.setIdsouscritere(dsc.getIdsouscritere());
             obj.setIdcategorie(new Categorie(categorie.getIdcategorie()));
             obj.setIdstructure(new Structure(idStructureDestination));
             obj.setPointMax(dsc.getPointMax());
             obj.setDetail(dsc.getDetail());
             listDetailsc.add(obj);
             }
             }

             if (!list.isEmpty()) {
             criterestructuresTemp.removeAll(list);
             }

             if (!criterestructuresTemp.isEmpty()) {
             criterestructures.clear();
             for (Criterestructure cs : criterestructuresTemp) {
             CriterestructurePK csPk = new CriterestructurePK(idStructureDestination, cs.getCritere().getIdcritere());
             Criterestructure obj = new Criterestructure(csPk);
             obj.setStructure(structureFacadeLocal.find(idStructureDestination));
             obj.setCritere(cs.getCritere());
             obj.setPoids(cs.getPoids());
             obj.setPointmax(cs.getPointmax());
             criterestructures.add(obj);
             }
             }
             }*/
        }
        score = this.sommeCritere();
    }

    public void updateFiltre() {
        souscriteres.clear();
        selectedSouscriteres.clear();
        if (structure.getIdstructure() != null && structure.getIdstructure() > 0) {
            souscriteres = souscritereFacadeLocal.findByIdCritere(7);
            listDetailsc = detailscFacadeLocal.findByIdStructureIdCritere(structure.getIdstructure(), 7);
            if (!listDetailsc.isEmpty()) {
                for (Detailsc dsc : listDetailsc) {
                    selectedSouscriteres.add(dsc.getIdsouscritere());
                }
                souscriteres.removeAll(selectedSouscriteres);
                selectedSouscriteres.clear();
                score = this.sommeCritere();
            }
        }
    }

    public void updateFiltreSc() {
        souscriteres.clear();
        selectedSouscriteres.clear();
        List<Souscritere> list = souscritereFacadeLocal.findByIdCritere(7);
        if (list.isEmpty()) {
            JsfUtil.addErrorMessage("Veuillez définir les criteres pour cette structure");
            return;
        }
        souscriteres.addAll(list);
        score = this.sommeCritere();
    }

    public void addCritereToTable() {
        if (!selectedSouscriteres.isEmpty()) {
            List<Souscritere> adds = new ArrayList<>();
            for (Souscritere sc : selectedSouscriteres) {
                if (!checkCritereInTable(sc)) {
                    Detailsc dcs = new Detailsc();
                    dcs.setIddetailsc(0l);
                    dcs.setIdstructure(structure);
                    dcs.setIdsouscritere(sc);

                    dcs.setPointMax((double) sc.getPointmax());
                    dcs.setDetail(sc.getDetail());
                    listDetailsc.add(dcs);
                    adds.add(sc);
                }
            }

            selectedSouscriteres.removeAll(adds);
            souscriteres.removeAll(adds);
        }
        score = this.sommeCritere();
    }

    private boolean checkCritereInTable(Souscritere sc) {
        boolean result = false;
        for (Detailsc dsc : listDetailsc) {
            if (dsc.getIdsouscritere().getIdsouscritere().equals(sc.getIdsouscritere())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void prepareEditDetail(Detailsc item) {
        this.detailsc = item;
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').show()");
    }

    public void removeCritere(Detailsc item) {
        if (item.getIddetailsc() != 0 && item.getIddetailsc() != null) {
            detailscFacadeLocal.remove(item);
            listDetailsc.remove(item);
        } else {
            int conteur = 0;
            for (Detailsc dsc : listDetailsc) {
                if (Objects.equals(item.getIdsouscritere().getIdsouscritere(), dsc.getIdsouscritere().getIdsouscritere())) {
                    break;
                }
                conteur++;
            }
            listDetailsc.remove(conteur);
        }
        souscriteres.add(item.getIdsouscritere());
        score = this.sommeCritere();
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }

    private boolean verifyQuantite() {
        return false;
    }

    public void editDetail() {
        if (detailsc.getIddetailsc() != null && detailsc.getIddetailsc() > 0) {
            int i = 0;
            for (Detailsc dsc : listDetailsc) {
                if (dsc.getIddetailsc().equals(detailsc.getIddetailsc())) {
                    break;
                }
                i++;
            }
            listDetailsc.set(i, detailsc);
        } else {
            int i = 0;
            for (Detailsc dsc : listDetailsc) {
                if (dsc.getIdsouscritere().equals(detailsc.getIdsouscritere())) {
                    break;
                }
                i++;
            }
            listDetailsc.set(i, detailsc);
        }
        RequestContext.getCurrentInstance().execute("PF('DetailEditDialog').hide()");
    }

    public String returnCritere(Structure s) {
        String resultat = "";
        if (s.getDetailscCollection() != null) {
            s.setDetailscCollection(detailscFacadeLocal.findByIdStructure(s.getIdstructure()));
        }
        int i = 0;
        for (Detailsc c : s.getDetailscCollection()) {
            if (i == 0) {
                resultat = "" + c.getIdsouscritere().getNom() + " (" + c.getPointMax() + " Pts)";
            } else {
                resultat += "\n" + c.getIdsouscritere().getNom() + " (" + c.getPointMax() + " Pts)";;
            }
            i++;
        }

        return resultat;
    }

    public void duplicate() {
        try {
            if (listDetailsc.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            if (this.verifyQuantite()) {
                JsfUtil.addErrorMessage(message);
                return;
            }

            /*for (Criterestructure cs : criterestructures) {
             Criterestructure csTemp = criterestructureFacadeLocal.findByIdStructureIdCritere(idStructureDestination, cs.getCritere().getIdcritere());
             if (csTemp == null) {
             csTemp = new Criterestructure(new CriterestructurePK(idStructureDestination, cs.getCritere().getIdcritere()));
             csTemp.setPoids(cs.getPoids());
             csTemp.setPointmax(cs.getPointmax());
             csTemp.setCritere(cs.getCritere());
             criterestructureFacadeLocal.create(csTemp);
             }
             }*/
            listDetailsc.forEach(dsc -> {
                dsc.setIddetailsc(detailscFacadeLocal.nextId());
                detailscFacadeLocal.create(dsc);
            });

            /*this.listDetailsc.clear();
             criterestructures.clear();
             criterestructuresTemp.clear();
             this.listDetailscTemp.clear();
             this.initDetailSCData();*/
            RequestContext.getCurrentInstance().execute("PF('DuplicationCritereCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    public void save() {
        try {
            if (listDetailsc.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }

            listDetailsc.forEach(dsc -> {
                if (dsc.getIddetailsc() == 0l) {
                    dsc.setIddetailsc(detailscFacadeLocal.nextId());
                    dsc.setIdstructure(structure);
                    detailscFacadeLocal.create(dsc);
                } else {
                    detailscFacadeLocal.edit(dsc);
                }
            });

            this.listDetailsc.clear();
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
                JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
            } else {
                JsfUtil.addErrorMessage("Aucune ligne seletionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }

    private double sommeCritere() {
        if (listDetailsc.isEmpty()) {
            return 0;
        }
        double pointMax = 0;
        for (Detailsc dsc : listDetailsc) {
            pointMax += dsc.getPointMax();
        }
        return pointMax;
    }

    public void updateDataTable(String mode) {
        score = 0;
        for (int i = 0; i < listDetailsc.size(); i++) {
            try {
                score += listDetailsc.get(i).getPointMax();
            } catch (Exception e) {
            }
        }
    }

}
