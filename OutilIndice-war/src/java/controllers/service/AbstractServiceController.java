package controllers.service;

import entities.Service;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.MouchardFacadeLocal;
import sessions.ServiceFacadeLocal;
import utils.Routine;

public class AbstractServiceController {

    @EJB
    protected ServiceFacadeLocal serviceFacadeLocal;
    protected Service service = new Service();
    protected List<Service> services = new ArrayList<>();

    protected Routine routine = new Routine();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    protected Boolean detail = true;
    protected Boolean modifier = true;
    protected Boolean consulter = true;
    protected Boolean imprimer = true;
    protected Boolean supprimer = true;

    protected String mode = "";

    public Boolean getDetail() {
        return this.detail;

    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    public Boolean getModifier() {
        return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        this.modifier = modifier;
    }

    public Boolean getConsulter() {
        return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        this.consulter = consulter;
    }

    public Boolean getImprimer() {
        this.imprimer = this.serviceFacadeLocal.findAll().isEmpty();
        return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        this.supprimer = supprimer;
    }

    public Routine getRoutine() {
        return this.routine;
    }

    public String getMode() {
        return this.mode;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
        this.modifier = this.supprimer = this.detail = service == null;
    }

    public List<Service> getServices() {
        services = serviceFacadeLocal.findAllOrderByCode();
        return services;
    }

}
