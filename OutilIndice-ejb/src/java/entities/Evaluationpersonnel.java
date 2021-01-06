/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluationpersonnel.findAll", query = "SELECT e FROM Evaluationpersonnel e"),
    @NamedQuery(name = "Evaluationpersonnel.findByIdevaluationpersonnel", query = "SELECT e FROM Evaluationpersonnel e WHERE e.idevaluationpersonnel = :idevaluationpersonnel"),
    @NamedQuery(name = "Evaluationpersonnel.findByNote", query = "SELECT e FROM Evaluationpersonnel e WHERE e.note = :note"),
    @NamedQuery(name = "Evaluationpersonnel.findByObservation", query = "SELECT e FROM Evaluationpersonnel e WHERE e.observation = :observation"),
    @NamedQuery(name = "Evaluationpersonnel.findByMultiplicateur", query = "SELECT e FROM Evaluationpersonnel e WHERE e.multiplicateur = :multiplicateur")})
public class Evaluationpersonnel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idevaluationpersonnel;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double note;
    @Size(max = 254)
    private String observation;
    private Integer multiplicateur;
    private Double pointmaxindice;
    @JoinColumn(name = "iddetailsc", referencedColumnName = "iddetailsc")
    @ManyToOne(fetch = FetchType.LAZY)
    private Detailsc iddetailsc;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idpersonnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel idpersonnel;
    @JoinColumn(name = "idsousperiode", referencedColumnName = "idsousperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousperiode idsousperiode;

    @JoinColumn(name = "idelementreponse", referencedColumnName = "idelementreponse")
    @ManyToOne(fetch = FetchType.LAZY)
    private Elementreponse idelementreponse;

    public Evaluationpersonnel() {
    }

    public Evaluationpersonnel(Long idevaluationpersonnel) {
        this.idevaluationpersonnel = idevaluationpersonnel;
    }

    public Long getIdevaluationpersonnel() {
        return idevaluationpersonnel;
    }

    public void setIdevaluationpersonnel(Long idevaluationpersonnel) {
        this.idevaluationpersonnel = idevaluationpersonnel;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Integer getMultiplicateur() {
        return multiplicateur;
    }

    public void setMultiplicateur(Integer multiplicateur) {
        this.multiplicateur = multiplicateur;
    }

    public Double getPointmaxindice() {
        return pointmaxindice;
    }

    public void setPointmaxindice(Double pointmaxindice) {
        this.pointmaxindice = pointmaxindice;
    }

    public Detailsc getIddetailsc() {
        return iddetailsc;
    }

    public void setIddetailsc(Detailsc iddetailsc) {
        this.iddetailsc = iddetailsc;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Personnel getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(Personnel idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public Sousperiode getIdsousperiode() {
        return idsousperiode;
    }

    public void setIdsousperiode(Sousperiode idsousperiode) {
        this.idsousperiode = idsousperiode;
    }

    public Elementreponse getIdelementreponse() {
        return idelementreponse;
    }

    public void setIdelementreponse(Elementreponse idelementreponse) {
        this.idelementreponse = idelementreponse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevaluationpersonnel != null ? idevaluationpersonnel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluationpersonnel)) {
            return false;
        }
        Evaluationpersonnel other = (Evaluationpersonnel) object;
        if ((this.idevaluationpersonnel == null && other.idevaluationpersonnel != null) || (this.idevaluationpersonnel != null && !this.idevaluationpersonnel.equals(other.idevaluationpersonnel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Evaluationpersonnel[ idevaluationpersonnel=" + idevaluationpersonnel + " ]";
    }

}
