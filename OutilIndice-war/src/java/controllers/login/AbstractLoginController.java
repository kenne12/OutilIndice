/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.login;

import entities.Periode;
import entities.Structure;
import entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.CriterestructureFacadeLocal;
import sessions.MenuFacadeLocal;
import sessions.PeriodeFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import sessions.StructureFacadeLocal;
import sessions.TypestructureCategorieFacadeLocal;
import sessions.TypestructureResponsabiliteFacadeLocal;
import sessions.TypestructureServiceFacadeLocal;
import sessions.TypestructureTypeSousperiodeFacadeLocal;
import sessions.UtilisateurFacadeLocal;
import sessions.UtilisateurstructureFacadeLocal;
import utils.Routine;

/**
 *
 * @author USER
 */
public class AbstractLoginController {

    @EJB
    protected CriterestructureFacadeLocal criterestructureFacadeLocal;

    @EJB
    protected MenuFacadeLocal menuBFacadeLocal;

    @EJB
    protected UtilisateurFacadeLocal utilisateurFacade;
    protected Utilisateur utilisateur = new Utilisateur();

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = new Structure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected PeriodeFacadeLocal periodeFacadeLocal;
    protected Periode periode = new Periode();
    protected List<Periode> periodes = new ArrayList<>();

    @EJB
    protected UtilisateurstructureFacadeLocal utilisateurstructureFacadeLocal;

    @EJB
    protected TypestructureCategorieFacadeLocal typestructureCategorieFacadeLocal;

    @EJB
    protected TypestructureServiceFacadeLocal typestructureServiceFacadeLocal;

    @EJB
    protected TypestructureTypeSousperiodeFacadeLocal typestructureTypeSousperiodeFacadeLocal;
    
    @EJB
    protected TypestructureResponsabiliteFacadeLocal typestructureResponsabiliteFacadeLocal;

    protected String language = "fr";

    protected String confirmPassword = "";
    protected boolean showSessionPanel = true;
    protected Routine routine = new Routine();

}
