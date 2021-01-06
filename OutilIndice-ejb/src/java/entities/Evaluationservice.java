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
    @NamedQuery(name = "Evaluationservice.findAll", query = "SELECT e FROM Evaluationservice e"),
    @NamedQuery(name = "Evaluationservice.findByIdevaluationservice", query = "SELECT e FROM Evaluationservice e WHERE e.idevaluationservice = :idevaluationservice"),
    @NamedQuery(name = "Evaluationservice.findByNote", query = "SELECT e FROM Evaluationservice e WHERE e.note = :note"),
    @NamedQuery(name = "Evaluationservice.findByObservation", query = "SELECT e FROM Evaluationservice e WHERE e.observation = :observation")})
public class Evaluationservice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idevaluationservice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double note;
    @Size(max = 254)
    private String observation;
    private int multiplicateur;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idsouscritereservice", referencedColumnName = "idsouscritereservice")
    @ManyToOne(fetch = FetchType.LAZY)
    private Souscritereservice idsouscritereservice;
    @JoinColumn(name = "idsousperiode", referencedColumnName = "idsousperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousperiode idsousperiode;

    public Evaluationservice() {
    }

    public Evaluationservice(Long idevaluationservice) {
        this.idevaluationservice = idevaluationservice;
    }

    public Long getIdevaluationservice() {
        return idevaluationservice;
    }

    public void setIdevaluationservice(Long idevaluationservice) {
        this.idevaluationservice = idevaluationservice;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public int getMultiplicateur() {
        return multiplicateur;
    }

    public void setMultiplicateur(int multiplicateur) {
        this.multiplicateur = multiplicateur;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Souscritereservice getIdsouscritereservice() {
        return idsouscritereservice;
    }

    public void setIdsouscritereservice(Souscritereservice idsouscritereservice) {
        this.idsouscritereservice = idsouscritereservice;
    }

    public Sousperiode getIdsousperiode() {
        return idsousperiode;
    }

    public void setIdsousperiode(Sousperiode idsousperiode) {
        this.idsousperiode = idsousperiode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevaluationservice != null ? idevaluationservice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluationservice)) {
            return false;
        }
        Evaluationservice other = (Evaluationservice) object;
        if ((this.idevaluationservice == null && other.idevaluationservice != null) || (this.idevaluationservice != null && !this.idevaluationservice.equals(other.idevaluationservice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Evaluationservice[ idevaluationservice=" + idevaluationservice + " ]";
    }

}
