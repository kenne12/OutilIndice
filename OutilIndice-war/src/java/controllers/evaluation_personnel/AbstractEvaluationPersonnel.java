/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.evaluation_personnel;

import entities.Cible;
import entities.Critere;
import entities.Critereresponsabilite;
import entities.Detailsc;
import entities.EvaluationBonusRDeptPersonnel;
import entities.EvaluationPenaliteDept;
import entities.EvaluationPenalitePersonnel;
import entities.EvaluationRPrimeQltifDept;
import entities.EvaluationRPrimeQltifPersonnel;
import entities.Evaluationbonuspp;
import entities.Evaluationheuresupp;
import entities.Evaluationpersonnel;
import entities.Evaluationresponsabilite;
import entities.Evaluationrqntifdept;
import entities.LignePenalitePersonnel;
import entities.Note;
import entities.Parametragecritere;
import entities.Penalite;
import entities.Personnel;
import entities.Sousperiode;
import entities.Structure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.CibleFacadeLocal;
import sessions.CritereresponsabiliteFacadeLocal;
import sessions.DetailscFacadeLocal;
import sessions.ElementReponseFacadeLocal;
import sessions.EvaluationBonusRDeptPersonnelFacadeLocal;
import sessions.EvaluationPenaliteDeptFacadeLocal;
import sessions.EvaluationPenalitePersonnelFacadeLocal;
import sessions.EvaluationRPrimeQltifDeptFacadeLocal;
import sessions.EvaluationRPrimeQltifPersonnelFacadeLocal;
import sessions.EvaluationbonusppFacadeLocal;
import sessions.EvaluationheuresuppFacadeLocal;
import sessions.EvaluationpersonnelFacadeLocal;
import sessions.EvaluationresponsabiliteFacadeLocal;
import sessions.EvaluationrqntifdeptFacadeLocal;
import sessions.LignePenalitePersonnelFacadeLocal;
import sessions.NoteFacadeLocal;
import sessions.ParametragePenaliteFacadeLocal;
import sessions.ParametragecritereFacadeLocal;
import sessions.PenaliteFacadeLocal;
import sessions.PersonnelFacadeLocal;
import sessions.SousperiodeFacadeLocal;
import sessions.StructureFacadeLocal;
import utils.MappingResultat;
import utils.Routine;
import utils.SessionMBean;

/**
 *
 * @author USER
 */
public class AbstractEvaluationPersonnel {

    @Resource
    protected UserTransaction ut;

    @EJB
    protected EvaluationpersonnelFacadeLocal evaluationpersonnelFacadeLocal;
    protected Evaluationpersonnel evaluationpersonnel = new Evaluationpersonnel();
    protected List<Evaluationpersonnel> evaluationpersonnels = new ArrayList<>();

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    protected Personnel personnel = new Personnel();
    protected List<Personnel> personnels = new ArrayList<>();

    @EJB
    protected ParametragecritereFacadeLocal parametragecritereFacadeLocal;
    protected Parametragecritere parametragecritere = new Parametragecritere();
    protected Parametragecritere parametragecritereHsp = new Parametragecritere();
    protected Parametragecritere parametragecritereHsn = new Parametragecritere();
    protected Parametragecritere parametragecritereBpp = new Parametragecritere();
    protected Parametragecritere parametragecriterePrq = new Parametragecritere();
    protected Parametragecritere parametragecritereBrd = new Parametragecritere();
    protected Parametragecritere parametragecriterePrqn = new Parametragecritere();

    @EJB
    protected EvaluationheuresuppFacadeLocal evaluationheuresuppFacadeLocal;
    protected Evaluationheuresupp evaluationheuresupp = new Evaluationheuresupp();
    protected Evaluationheuresupp evaluationheuresuppN = new Evaluationheuresupp();

    @EJB
    protected CritereresponsabiliteFacadeLocal critereresponsabiliteFacadeLocal;
    protected Critereresponsabilite critereresponsabilite = new Critereresponsabilite();

    @EJB
    protected EvaluationresponsabiliteFacadeLocal evaluationresponsabiliteFacadeLocal;
    protected Evaluationresponsabilite evaluationresponsabilite = new Evaluationresponsabilite();

    @EJB
    protected EvaluationbonusppFacadeLocal evaluationbonusppFacadeLocal;
    protected Evaluationbonuspp evaluationbonuspp = new Evaluationbonuspp();

    @EJB
    protected CibleFacadeLocal cibleFacadeLocal;
    protected List<Cible> cibleRqntifs = new ArrayList<>();
    protected Cible cibleBrd = new Cible();

    @EJB
    protected EvaluationrqntifdeptFacadeLocal evaluationrqntifdeptFacadeLocal;
    protected List<Evaluationrqntifdept> evaluationrqntifdepts = new ArrayList<>();

    @EJB
    protected EvaluationRPrimeQltifDeptFacadeLocal evaluationRPrimeQltifDeptFacadeLocal;
    protected EvaluationRPrimeQltifDept evaluationRPrimeQltifDept = new EvaluationRPrimeQltifDept();

    @EJB
    protected EvaluationRPrimeQltifPersonnelFacadeLocal evaluationRPrimeQltifPersonnelFacadeLocal;
    protected EvaluationRPrimeQltifPersonnel evaluationRPrimeQltifPersonnel = new EvaluationRPrimeQltifPersonnel();

    @EJB
    protected EvaluationBonusRDeptPersonnelFacadeLocal evaluationBonusRDeptPersonnelFacadeLocal;
    protected EvaluationBonusRDeptPersonnel evaluationBonusRDeptPersonnel = new EvaluationBonusRDeptPersonnel();

    @EJB
    protected EvaluationPenaliteDeptFacadeLocal evaluationPenaliteDeptFacadeLocal;
    protected EvaluationPenaliteDept evaluationPenaliteDept = new EvaluationPenaliteDept();

    @EJB
    protected EvaluationPenalitePersonnelFacadeLocal evaluationPenalitePersonnelFacade;
    protected EvaluationPenalitePersonnel evaluationPenalitePersonnel = new EvaluationPenalitePersonnel();

    @EJB
    protected LignePenalitePersonnelFacadeLocal lignePenalitePersonnelFacadeLocal;
    protected List<LignePenalitePersonnel> lignePenalitePersonnels = new ArrayList<>();
    
    @EJB
    protected ParametragePenaliteFacadeLocal parametragePenaliteFacadeLocal;

    @EJB
    protected PenaliteFacadeLocal penaliteFacadeLocal;
    protected List<Penalite> penalites = new ArrayList<>();
    protected List<Penalite> selectedPenalites = new ArrayList<>();

    @EJB
    protected SousperiodeFacadeLocal sousperiodeFacadeLocal;
    protected Sousperiode sousperiode = new Sousperiode();
    protected List<Sousperiode> sousperiodes = new ArrayList<>();

    @EJB
    protected DetailscFacadeLocal detailscFacadeLocal;
    protected List<Detailsc> selectedDetailsc = new ArrayList<>();
    protected List<Detailsc> listDetailsc = new ArrayList<>();

    @EJB
    protected StructureFacadeLocal structureFacadeLocal;
    protected Structure structure = SessionMBean.getStructure();
    protected List<Structure> structures = new ArrayList<>();

    @EJB
    protected NoteFacadeLocal noteFacadeLocal;
    protected Note note = new Note();
    protected List<Note> notes = new ArrayList<>();

    protected List<Critere> criteres = SessionMBean.getCriteres();

    @EJB
    protected ElementReponseFacadeLocal elementReponseFacadeLocal;

    protected Routine routine = new Routine();

    protected double totalPointPi;
    protected double percentagePi;

    protected double notePi = 0;
    protected double scorePi = 0;
    protected double pointPi = 0;

    protected double ratioPrqnt = 0;
    protected double ciblePrqnt = 0;
    protected double realisationPrqnt = 0;

    protected double totalPIncitationPositif = 0;
    protected double totalPIncitationNegatif = 0;

    protected List<MappingResultat> mappingResultats = new ArrayList<>();

    protected String mode = "";
    protected String message = "";

    protected boolean criter1 = false;
    protected boolean criter2 = false;
    protected boolean criter3 = false;
    protected boolean criter4 = false;
    protected boolean criter5 = false;
    protected boolean criter6 = false;
    protected boolean criter7 = false;
    protected boolean criter8 = false;
    protected boolean criter9 = false;
    protected boolean criter10 = false;

    public Routine getRoutine() {
        return routine;
    }

    public String getMode() {
        return mode;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public List<Detailsc> getListDetailsc() {
        return listDetailsc;
    }

    public String getMessage() {
        return message;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Personnel> getPersonnels() {
        personnels = personnelFacadeLocal.findByIdStructure(SessionMBean.getStructure().getIdstructure());
        return personnels;
    }

    public Sousperiode getSousperiode() {
        return sousperiode;
    }

    public void setSousperiode(Sousperiode sousperiode) {
        this.sousperiode = sousperiode;
    }

    public List<Sousperiode> getSousperiodes() {
        sousperiodes = sousperiodeFacadeLocal.findAllRangeCode();
        return sousperiodes;
    }

    public List<Detailsc> getSelectedDetailsc() {
        return selectedDetailsc;
    }

    public void setSelectedDetailsc(List<Detailsc> selectedDetailsc) {
        this.selectedDetailsc = selectedDetailsc;
    }

    public List<Evaluationpersonnel> getEvaluationpersonnels() {
        return evaluationpersonnels;
    }

    public Evaluationpersonnel getEvaluationpersonnel() {
        return evaluationpersonnel;
    }

    public void setEvaluationpersonnel(Evaluationpersonnel evaluationpersonnel) {
        this.evaluationpersonnel = evaluationpersonnel;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public Parametragecritere getParametragecritere() {
        return parametragecritere;
    }

    public double getPercentagePi() {
        return percentagePi;
    }

    public double getTotalPointPi() {
        return totalPointPi;
    }

    public Evaluationheuresupp getEvaluationheuresupp() {
        return evaluationheuresupp;
    }

    public void setEvaluationheuresupp(Evaluationheuresupp evaluationheuresupp) {
        this.evaluationheuresupp = evaluationheuresupp;
    }

    public Evaluationresponsabilite getEvaluationresponsabilite() {
        return evaluationresponsabilite;
    }

    public void setEvaluationresponsabilite(Evaluationresponsabilite evaluationresponsabilite) {
        this.evaluationresponsabilite = evaluationresponsabilite;
    }

    public Critereresponsabilite getCritereresponsabilite() {
        return critereresponsabilite;
    }

    public void setCritereresponsabilite(Critereresponsabilite critereresponsabilite) {
        this.critereresponsabilite = critereresponsabilite;
    }

    public Parametragecritere getParametragecritereBpp() {
        return parametragecritereBpp;
    }

    public void setParametragecritereBpp(Parametragecritere parametragecritereBpp) {
        this.parametragecritereBpp = parametragecritereBpp;
    }

    public Parametragecritere getParametragecriterePrq() {
        return parametragecriterePrq;
    }

    public void setParametragecriterePrq(Parametragecritere parametragecriterePrq) {
        this.parametragecriterePrq = parametragecriterePrq;
    }

    public Evaluationbonuspp getEvaluationbonuspp() {
        return evaluationbonuspp;
    }

    public void setEvaluationbonuspp(Evaluationbonuspp evaluationbonuspp) {
        this.evaluationbonuspp = evaluationbonuspp;
    }

    public List<Evaluationrqntifdept> getEvaluationrqntifdepts() {
        return evaluationrqntifdepts;
    }

    public double getRatioPrqnt() {
        return ratioPrqnt;
    }

    public double getCiblePrqnt() {
        return ciblePrqnt;
    }

    public double getRealisationPrqnt() {
        return realisationPrqnt;
    }

    public EvaluationRPrimeQltifDept getEvaluationRPrimeQltifDept() {
        return evaluationRPrimeQltifDept;
    }

    public void setEvaluationRPrimeQltifDept(EvaluationRPrimeQltifDept evaluationRPrimeQltifDept) {
        this.evaluationRPrimeQltifDept = evaluationRPrimeQltifDept;
    }

    public EvaluationRPrimeQltifPersonnel getEvaluationRPrimeQltifPersonnel() {
        return evaluationRPrimeQltifPersonnel;
    }

    public void setEvaluationRPrimeQltifPersonnel(EvaluationRPrimeQltifPersonnel evaluationRPrimeQltifPersonnel) {
        this.evaluationRPrimeQltifPersonnel = evaluationRPrimeQltifPersonnel;
    }

    public Parametragecritere getParametragecritereBrd() {
        return parametragecritereBrd;
    }

    public Cible getCibleBrd() {
        return cibleBrd;
    }

    public void setCibleBrd(Cible cibleBrd) {
        this.cibleBrd = cibleBrd;
    }

    public EvaluationBonusRDeptPersonnel getEvaluationBonusRDeptPersonnel() {
        return evaluationBonusRDeptPersonnel;
    }

    public Evaluationheuresupp getEvaluationheuresuppN() {
        return evaluationheuresuppN;
    }

    public Parametragecritere getParametragecritereHsn() {
        return parametragecritereHsn;
    }

    public void setParametragecritereHsn(Parametragecritere parametragecritereHsn) {
        this.parametragecritereHsn = parametragecritereHsn;
    }

    public EvaluationPenaliteDept getEvaluationPenaliteDept() {
        return evaluationPenaliteDept;
    }

    public void setEvaluationPenaliteDept(EvaluationPenaliteDept evaluationPenaliteDept) {
        this.evaluationPenaliteDept = evaluationPenaliteDept;
    }

    public Parametragecritere getParametragecriterePrqn() {
        return parametragecriterePrqn;
    }

    public void setParametragecriterePrqn(Parametragecritere parametragecriterePrqn) {
        this.parametragecriterePrqn = parametragecriterePrqn;
    }

    public List<LignePenalitePersonnel> getLignePenalitePersonnels() {
        return lignePenalitePersonnels;
    }

    public EvaluationPenalitePersonnel getEvaluationPenalitePersonnel() {
        return evaluationPenalitePersonnel;
    }

    public void setEvaluationPenalitePersonnel(EvaluationPenalitePersonnel evaluationPenalitePersonnel) {
        this.evaluationPenalitePersonnel = evaluationPenalitePersonnel;
    }

    public List<Penalite> getPenalites() {
        return penalites;
    }

    public List<Penalite> getSelectedPenalites() {
        return selectedPenalites;
    }

    public void setSelectedPenalites(List<Penalite> selectedPenalites) {
        this.selectedPenalites = selectedPenalites;
    }

    public double getScorePi() {
        return scorePi;
    }

    public double getPointPi() {
        return pointPi;
    }

    public double getNotePi() {
        return notePi;
    }

    public double getTotalPIncitationPositif() {
        return totalPIncitationPositif;
    }

    public double getTotalPIncitationNegatif() {
        return totalPIncitationNegatif;
    }

    public boolean isCriter1() {
        return criter1;
    }

    public boolean isCriter2() {
        return criter2;
    }

    public boolean isCriter3() {
        return criter3;
    }

    public boolean isCriter4() {
        return criter4;
    }

    public boolean isCriter5() {
        return criter5;
    }

    public boolean isCriter6() {
        return criter6;
    }

    public boolean isCriter7() {
        return criter7;
    }

    public boolean isCriter8() {
        return criter8;
    }

    public boolean isCriter9() {
        return criter9;
    }

    public boolean isCriter10() {
        return criter10;
    }

}
