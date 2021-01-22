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
    @NamedQuery(name = "Note.findByIdnote", query = "SELECT n FROM Note n WHERE n.idnote = :idnote"),
    @NamedQuery(name = "Note.findByNotepersonnelle", query = "SELECT n FROM Note n WHERE n.notepersonnelle = :notepersonnelle"),
    @NamedQuery(name = "Note.findByNoteservice", query = "SELECT n FROM Note n WHERE n.noteservice = :noteservice"),
    @NamedQuery(name = "Note.findByPoidsservice", query = "SELECT n FROM Note n WHERE n.poidsservice = :poidsservice"),
    @NamedQuery(name = "Note.findByPoidpersonnel", query = "SELECT n FROM Note n WHERE n.poidpersonnel = :poidpersonnel")})
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idnote;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double notepersonnelle;
    private Double noteservice;
    private Double poidsservice;
    private Double poidpersonnel;
    private Double notein;
    private Double noteip;
    private Double notefinale;
    private Double sommeindice;
    @Column(name = "penalitedepartement")
    private double penaliteDepartement;
    @Column(name = "pointpenalitedepartement")
    private double pointPenaliteDepartement;
    @Column(name = "penalitepersonnel")
    protected double penalitePersonnel;
    @Column(name = "pointpenalitepersonnel")
    protected double pointPenalitePersonnel;
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

    public Double getNotepersonnelle() {
        return notepersonnelle;
    }

    public void setNotepersonnelle(Double notepersonnelle) {
        this.notepersonnelle = notepersonnelle;
    }

    public Double getNoteservice() {
        return noteservice;
    }

    public void setNoteservice(Double noteservice) {
        this.noteservice = noteservice;
    }

    public Double getPoidsservice() {
        return poidsservice;
    }

    public void setPoidsservice(Double poidsservice) {
        this.poidsservice = poidsservice;
    }

    public Double getPoidpersonnel() {
        return poidpersonnel;
    }

    public void setPoidpersonnel(Double poidpersonnel) {
        this.poidpersonnel = poidpersonnel;
    }

    public Double getNotein() {
        return notein;
    }

    public void setNotein(Double notein) {
        this.notein = notein;
    }

    public Double getNoteip() {
        return noteip;
    }

    public void setNoteip(Double noteip) {
        this.noteip = noteip;
    }

    public Double getNotefinale() {
        return notefinale;
    }

    public void setNotefinale(Double notefinale) {
        this.notefinale = notefinale;
    }

    public Double getSommeindice() {
        return sommeindice;
    }

    public void setSommeindice(Double sommeindice) {
        this.sommeindice = sommeindice;
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
