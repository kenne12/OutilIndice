/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findByIdnote", query = "SELECT n FROM Note n WHERE n.idnote = :idnote")})

public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idnote;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "incitationpositif")
    private double incitationPositif;
    @Column(name = "incitationnegatif")
    private double incitationNegatif;
    @Column(name = "totalpoint")
    private double totalPoint;
    @Column(name = "pointpindiv")
    private double pointPIndiv;
    @Column(name = "scorepindiv")
    private double scorePIndiv;
    @Column(name = "pointmaxrqntif")
    private double pointMaxRQntif;
    @Column(name = "poucentagerqntif")
    private double poucentageRQntif;
    @Column(name = "pointrqntif")
    private double pointRqntif;
    @Column(name = "penalitedepartement")
    private double penaliteDepartement;
    @Column(name = "pointpenalitedepartement")
    private double pointPenaliteDepartement;
    @Column(name = "penalitepersonnel")
    protected double penalitePersonnel;
    @Column(name = "pointpenalitepersonnel")
    protected double pointPenalitePersonnel;
    @Column(name = "pointheuresupp")
    protected double pointHeureSupp;
    @Column(name = "incitationnhp")
    protected double incitationNHP;
    @Column(name = "pointresponsabilite")
    protected double pointResponsabilite;
    @Column(name = "pointpratiquep")
    protected double pointPratiqueP;
    @Column(name = "pointrqltifdept")
    private double pointRQltifDept;
    @Column(name = "pointbonusrdept")
    private double pointBonusRDept;
    @Column(name = "pointmaxprqltif")
    private double pointMaxPrQltif;
    @Column(name = "scoreprqltif")
    private double scorePrQltif;
    @Column(name = "pointmax_brd")
    private double pointMaxBrd;
    @Column(name = "scorebrd")
    private double scoreBrd;
    @Column(name = "pointmaxpi")
    private double pointMaxPi;
    @Column(name = "ratiopi")
    private double ratioPi;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idsousperiode", referencedColumnName = "idsousperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousperiode idsousperiode;
    @JoinColumn(name = "idpersonnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel idpersonnel;

    public Note() {
    }

    public Note(Long idnote) {
        this.idnote = idnote;
    }

    public Long getIdnote() {
        return idnote;
    }

    public void setIdnote(Long idnote) {
        this.idnote = idnote;
    }

    public double getPointPIndiv() {
        return pointPIndiv;
    }

    public void setPointPIndiv(double pointPIndiv) {
        this.pointPIndiv = pointPIndiv;
    }

    public double getPointMaxRQntif() {
        return pointMaxRQntif;
    }

    public void setPointMaxRQntif(double pointMaxRQntif) {
        this.pointMaxRQntif = pointMaxRQntif;
    }

    public double getPoucentageRQntif() {
        return poucentageRQntif;
    }

    public void setPoucentageRQntif(double poucentageRQntif) {
        this.poucentageRQntif = poucentageRQntif;
    }

    public double getPointRqntif() {
        return pointRqntif;
    }

    public void setPointRqntif(double pointRqntif) {
        this.pointRqntif = pointRqntif;
    }

    public double getPenaliteDepartement() {
        return penaliteDepartement;
    }

    public void setPenaliteDepartement(double penaliteDepartement) {
        this.penaliteDepartement = penaliteDepartement;
    }

    public double getPointPenaliteDepartement() {
        return pointPenaliteDepartement;
    }

    public void setPointPenaliteDepartement(double pointPenaliteDepartement) {
        this.pointPenaliteDepartement = pointPenaliteDepartement;
    }

    public double getPenalitePersonnel() {
        return penalitePersonnel;
    }

    public void setPenalitePersonnel(double penalitePersonnel) {
        this.penalitePersonnel = penalitePersonnel;
    }

    public double getPointPenalitePersonnel() {
        return pointPenalitePersonnel;
    }

    public void setPointPenalitePersonnel(double pointPenalitePersonnel) {
        this.pointPenalitePersonnel = pointPenalitePersonnel;
    }

    public double getIncitationPositif() {
        return incitationPositif;
    }

    public void setIncitationPositif(double incitationPositif) {
        this.incitationPositif = incitationPositif;
    }

    public double getIncitationNegatif() {
        return incitationNegatif;
    }

    public void setIncitationNegatif(double incitationNegatif) {
        this.incitationNegatif = incitationNegatif;
    }

    public double getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(double totalPoint) {
        this.totalPoint = totalPoint;
    }

    public double getScorePIndiv() {
        return scorePIndiv;
    }

    public void setScorePIndiv(double scorePIndiv) {
        this.scorePIndiv = scorePIndiv;
    }

    public double getPointHeureSupp() {
        return pointHeureSupp;
    }

    public void setPointHeureSupp(double pointHeureSupp) {
        this.pointHeureSupp = pointHeureSupp;
    }

    public double getIncitationNHP() {
        return incitationNHP;
    }

    public void setIncitationNHP(double incitationNHP) {
        this.incitationNHP = incitationNHP;
    }

    public double getPointResponsabilite() {
        return pointResponsabilite;
    }

    public void setPointResponsabilite(double pointResponsabilite) {
        this.pointResponsabilite = pointResponsabilite;
    }

    public double getPointPratiqueP() {
        return pointPratiqueP;
    }

    public void setPointPratiqueP(double pointPratiqueP) {
        this.pointPratiqueP = pointPratiqueP;
    }

    public double getPointRQltifDept() {
        return pointRQltifDept;
    }

    public void setPointRQltifDept(double pointRQltifDept) {
        this.pointRQltifDept = pointRQltifDept;
    }

    public double getPointBonusRDept() {
        return pointBonusRDept;
    }

    public double getPointMaxBrd() {
        return pointMaxBrd;
    }

    public void setPointMaxBrd(double pointMaxBrd) {
        this.pointMaxBrd = pointMaxBrd;
    }

    public double getScoreBrd() {
        return scoreBrd;
    }

    public void setScoreBrd(double scoreBrd) {
        this.scoreBrd = scoreBrd;
    }

    public void setPointBonusRDept(double pointBonusRDept) {
        this.pointBonusRDept = pointBonusRDept;
    }

    public double getPointMaxPi() {
        return pointMaxPi;
    }

    public void setPointMaxPi(double pointMaxPi) {
        this.pointMaxPi = pointMaxPi;
    }

    public double getRatioPi() {
        return ratioPi;
    }

    public void setRatioPi(double ratioPi) {
        this.ratioPi = ratioPi;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Sousperiode getIdsousperiode() {
        return idsousperiode;
    }

    public void setIdsousperiode(Sousperiode idsousperiode) {
        this.idsousperiode = idsousperiode;
    }

    public Personnel getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(Personnel idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public double getPointMaxPrQltif() {
        return pointMaxPrQltif;
    }

    public void setPointMaxPrQltif(double pointMaxPrQltif) {
        this.pointMaxPrQltif = pointMaxPrQltif;
    }

    public double getScorePrQltif() {
        return scorePrQltif;
    }

    public void setScorePrQltif(double scorePrQltif) {
        this.scorePrQltif = scorePrQltif;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnote != null ? idnote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.idnote == null && other.idnote != null) || (this.idnote != null && !this.idnote.equals(other.idnote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Note[ idnote=" + idnote + " ]";
    }

}
