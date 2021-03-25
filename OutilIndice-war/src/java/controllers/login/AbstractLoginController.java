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

    protected String critere1 = "none";
    protected String critere2 = "none";
    protected String critere3 = "none";
    protected String critere4 = "none";
    protected String critere5 = "none";
    protected String critere6 = "none";
    protected String critere7 = "none";
    protected String critere8 = "none";
    protected String critere9 = "none";
    protected String critere10 = "none";

    protected String critere1_numero = "";
    protected String critere2_numero = "";
    protected String critere3_numero = "";
    protected String critere4_numero = "";
    protected String critere5_numero = "";
    protected String critere6_numero = "";
    protected String critere7_numero = "";
    protected String critere8_numero = "";
    protected String critere9_numero = "";
    protected String critere10_numero = "";

    public String getCritere1() {
        return critere1;
    }

    public String getCritere2() {
        return critere2;
    }

    public String getCritere3() {
        return critere3;
    }

    public String getCritere4() {
        return critere4;
    }

    public String getCritere5() {
        return critere5;
    }

    public String getCritere6() {
        return critere6;
    }

    public String getCritere7() {
        return critere7;
    }

    public String getCritere8() {
        return critere8;
    }

    public String getCritere9() {
        return critere9;
    }

    public String getCritere10() {
        return critere10;
    }

    public String getCritere1_numero() {
        return critere1_numero;
    }

    public String getCritere2_numero() {
        return critere2_numero;
    }

    public String getCritere3_numero() {
        return critere3_numero;
    }

    public String getCritere4_numero() {
        return critere4_numero;
    }

    public String getCritere5_numero() {
        return critere5_numero;
    }

    public String getCritere6_numero() {
        return critere6_numero;
    }

    public String getCritere7_numero() {
        return critere7_numero;
    }

    public String getCritere8_numero() {
        return critere8_numero;
    }

    public String getCritere9_numero() {
        return critere9_numero;
    }

    public String getCritere10_numero() {
        return critere10_numero;
    }

    public void setCritere10_numero(String critere10_numero) {
        this.critere10_numero = critere10_numero;
    }

}
