package controllers.privilege;

import entities.Menu;
import entities.Privilege;
import entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import sessions.MenuFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import utils.Routine;

public class AbstractPrivilegeController {

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;
    protected List<Privilege> privileges = new ArrayList();

    @EJB
    protected MenuFacadeLocal menuFacadeLocal;
    protected List<Menu> menus = new ArrayList();
    protected DualListModel<Menu> dualMenu = new DualListModel();

    @EJB
    protected UtilisateurFacadeLocal utilisateurFacadeLocal;
    protected Utilisateur utilisateur;
    protected List<Utilisateur> utilisateurs = new ArrayList();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;
    protected String fileName;
    protected boolean detail = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    protected Routine routine = new Routine();

    public boolean isDetail() {
        return this.detail;
    }

    public boolean isSupprimer() {
        return this.supprimer;
    }

    public boolean isImprimer() {
        this.imprimer = this.privilegeFacadeLocal.findAll().isEmpty();
        return this.imprimer;
    }

    public List<Menu> getMenus() {
        return this.menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public DualListModel<Menu> getDualMenu() {
        return this.dualMenu;
    }

    public void setDualMenu(DualListModel<Menu> dualMenu) {
        this.dualMenu = dualMenu;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        this.utilisateurs = this.utilisateurFacadeLocal.findByActif(true);
        return this.utilisateurs;
    }

    public List<Privilege> getPrivileges() {
        return this.privileges;
    }

    public Routine getRoutine() {
        return routine;
    }

}
