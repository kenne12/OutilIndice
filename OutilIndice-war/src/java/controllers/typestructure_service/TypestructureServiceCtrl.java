/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.typestructure_service;

import controllers.util.JsfUtil;
import entities.Service;
import entities.TypestructureService;
import entities.Typestructure;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.Transactional;
import org.primefaces.context.RequestContext;

/**
 *
 * @author USER
 */
@ManagedBean
@SessionScoped
public class TypestructureServiceCtrl extends AbstractTypestructServiceCtrl implements Serializable {
    
    public TypestructureServiceCtrl() {
        
    }
    
    @PostConstruct
    private void init() {
        
    }
    
    public void prepareCreate() {
        this.updateFiltre();
        mode = "Create";
        RequestContext.getCurrentInstance().execute("PF('TypestructureServiceCreateDialog').show()");
    }
    
    public void prepareEdit(Typestructure s) {
        this.typestructure = s;
        mode = "Edit";
        this.updateFiltre();
        RequestContext.getCurrentInstance().execute("PF('TypestructureServiceCreateDialog').show()");
    }
    
    public void updateFiltre() {
        services.clear();
        selectedServices.clear();
        typestructureServices.clear();
        if (typestructure.getIdtypestructure() != null && typestructure.getIdtypestructure() > 0) {
            List<TypestructureService> list = typestructureServiceFacadeLocal.findByIdTypestructure(typestructure.getIdtypestructure());
            
            services.addAll(serviceFacadeLocal.findAllOrderByCode());
            if (!list.isEmpty()) {
                for (TypestructureService cs : list) {
                    selectedServices.add(cs.getService());
                }
                services.removeAll(selectedServices);
                typestructureServices.addAll(list);
                selectedServices.clear();
            }
        }
    }
    
    public void addServiceToTable() {
        if (!selectedServices.isEmpty()) {
            List<Service> list = new ArrayList();
            for (Service s : selectedServices) {
                if (!checkServiceInTable(s)) {
                    TypestructureService ts = new TypestructureService();
                    ts.setIdTypestructureService(0);
                    ts.setService(s);
                    ts.setTypestructure(typestructure);
                    typestructureServices.add(ts);
                    list.add(s);
                }
            }
            services.removeAll(list);
            selectedServices.clear();
        }
    }
    
    private boolean checkServiceInTable(Service s) {
        boolean result = false;
        for (TypestructureService ts : typestructureServices) {
            if (s.getIdservice().equals(ts.getService().getIdservice())) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public void removeService(int index, TypestructureService item) {
        if (item.getIdTypestructureService() != 0) {
            typestructureServiceFacadeLocal.remove(item);
            typestructureServices.remove(item);
        } else {
            typestructureServices.remove(index);
        }
        services.add(item.getService());
        JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
    }
    
    public String returnService(Typestructure t) {
        String resultat = "";
        if (t.getTypestructureCollection().isEmpty()) {
            t.setTypestructureCollection(typestructureServiceFacadeLocal.findByIdTypestructure(t.getIdtypestructure()));
        }
        if (t.getTypestructureCollection() != null) {
            int i = 0;
            for (TypestructureService ts : t.getTypestructureCollection()) {
                if (i == 0) {
                    resultat = "" + ts.getService().getNom() + " ";
                } else {
                    resultat += "\n" + ts.getService().getNom() + " ";
                }
                i++;
            }
        }
        return resultat;
    }
    
    @Transactional
    public void save() {
        try {
            if (typestructureServices.isEmpty()) {
                JsfUtil.addErrorMessage(routine.localizeMessage("common.tableau_vide"));
                return;
            }
            
            typestructureServices.forEach(ts -> {
                if (ts.getIdTypestructureService() == 0) {
                    ts.setIdTypestructureService(typestructureServiceFacadeLocal.nextId());
                    typestructureServiceFacadeLocal.create(ts);
                } else {
                    typestructureServiceFacadeLocal.edit(ts);
                }
            });
            
            this.typestructureServices.clear();
            RequestContext.getCurrentInstance().execute("PF('TypestructureServiceCreateDialog').hide()");
            JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
    public void delete(Typestructure ts) {
        try {
            /*if (sc != null) {
             souscritereFacadeLocal.remove(sc);
             souscritere = new Souscritere();
             JsfUtil.addSuccessMessage(routine.localizeMessage("notification.operation_reussie"));
             } else {
             JsfUtil.addErrorMessage("Aucune ligne seletionnée");
             }*/
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addFatalErrorMessage("Exception");
        }
    }
    
}
