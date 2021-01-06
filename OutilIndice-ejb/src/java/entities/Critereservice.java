/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Critereservice.findAll", query = "SELECT c FROM Critereservice c"),
    @NamedQuery(name = "Critereservice.findByIdservice", query = "SELECT c FROM Critereservice c WHERE c.critereservicePK.idservice = :idservice"),
    @NamedQuery(name = "Critereservice.findByIdcritere", query = "SELECT c FROM Critereservice c WHERE c.critereservicePK.idcritere = :idcritere"),
    @NamedQuery(name = "Critereservice.findByPoids", query = "SELECT c FROM Critereservice c WHERE c.poids = :poids"),
    @NamedQuery(name = "Critereservice.findByPointmax", query = "SELECT c FROM Critereservice c WHERE c.pointmax = :pointmax")})
public class Critereservice implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CritereservicePK critereservicePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double poids;
    private Double pointmax;
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Critere critere;
    @JoinColumn(name = "idservice", referencedColumnName = "idservice", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Service service;

    public Critereservice() {
    }

    public Critereservice(CritereservicePK critereservicePK) {
        this.critereservicePK = critereservicePK;
    }

    public Critereservice(long idservice, int idcritere) {
        this.critereservicePK = new CritereservicePK(idservice, idcritere);
    }

    public CritereservicePK getCritereservicePK() {
        return critereservicePK;
    }

    public void setCritereservicePK(CritereservicePK critereservicePK) {
        this.critereservicePK = critereservicePK;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getPointmax() {
        return pointmax;
    }

    public void setPointmax(Double pointmax) {
        this.pointmax = pointmax;
    }

    public Critere getCritere() {
        return critere;
    }

    public void setCritere(Critere critere) {
        this.critere = critere;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (critereservicePK != null ? critereservicePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Critereservice)) {
            return false;
        }
        Critereservice other = (Critereservice) object;
        if ((this.critereservicePK == null && other.critereservicePK != null) || (this.critereservicePK != null && !this.critereservicePK.equals(other.critereservicePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Critereservice[ critereservicePK=" + critereservicePK + " ]";
    }
    
}
