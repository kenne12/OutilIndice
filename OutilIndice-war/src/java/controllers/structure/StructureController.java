package controllers.structure;

import entities.Institution;
import entities.Statutstructure;
import entities.Structure;
import entities.Typestructure;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class StructureController extends AbstractStructureController implements Serializable {

    @PostConstruct
    private void init() {
        this.structure = new Structure();
        this.initStructure();
    }

    private void initStructure() {
        structure = new Structure();
        structure.setIdstatutstructure(new Statutstructure());
        structure.setIdtypestructure(new Typestructure());
        structure.setIdinstitution(new Institution());
    }

    public void prepareCreate() {
        try {
            /*if (!Utilitaires.isAccess(2L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.mode = "Create";

            this.initStructure();
            structure.setArretecreation("-");
            structure.setVision("-");
            structure.setObjectifgeneral("-");
            structure.setObjectifspecifique("-");
            structure.setAxeintervention("-");
            structure.setNomrespo("-");
            this.structure.setEtat("Actif");

            //this.structure.setTemplate((String) this.templates.get(1));
            RequestContext.getCurrentInstance().execute("PF('StructureCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void prepareEdit() {
        try {
            if (!Utilitaires.isAccess(3L)) {
                signalError("acces_refuse");
                return;
            }

            if (this.structure == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            RequestContext.getCurrentInstance().execute("PF('StructureCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void prepareEdit(Structure structure) {
        try {
            /*if (!Utilitaires.isAccess(3L)) {
             signalError("acces_refuse");
             return;
             }*/

            this.structure = structure;

            if (this.structure == null) {
                signalError("not_row_selected");
                return;
            }

            this.mode = "Edit";

            RequestContext.getCurrentInstance().execute("PF('StructureCreerDialog').show()");
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void create() {
        try {
            if (this.mode.equals("Create")) {

                this.structure.setIdstructure(this.structureFacadeLocal.nextVal());
                this.structure.setEtat("Actif");
                this.structureFacadeLocal.create(this.structure);

                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
                this.initStructure();

                modifier = detail = supprimer = true;
                RequestContext.getCurrentInstance().execute("PF('StructureCreerDialog').hide()");
                signalSuccess();
            } else if (structure != null) {
                structure.setIdstatutstructure(statutstructureFacadeLocal.find(structure.getIdstatutstructure().getIdstatutstructure()));
                structure.setIdtypestructure(typestructureFacadeLocal.find(structure.getIdtypestructure().getIdtypestructure()));
                structureFacadeLocal.edit(structure);
                detail = supprimer = modifier = true;
                RequestContext.getCurrentInstance().execute("PF('StructureCreerDialog').hide()");
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete() {
        try {
            if (this.structure != null) {
                if (!Utilitaires.isAccess(4L)) {
                    signalError("acces_refuse");
                    return;
                }

                this.structureFacadeLocal.remove(this.structure);
                //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
                signalSuccess();
            } else {
                signalError("not_row_selected");
            }
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void delete(Structure item) {
        try {
            structure = item;

            /*if (!Utilitaires.isAccess(4L)) {
             signalError("acces_refuse");
             return;
             }*/
            this.structureFacadeLocal.remove(this.structure);
            //Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'structure : " + this.structure.getNom() + " " + this.structure.getPrenom(), SessionMBean.getUserAccount());
            signalSuccess();
        } catch (Exception e) {
            signalException(e);
        }
    }

    public void signalError(String chaine) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("information", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(chaine));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void signalSuccess() {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void signalException(Exception e) {
        RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        this.routine.catchException(e, this.routine.localizeMessage("erreur_execution"));
        RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}
