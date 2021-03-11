/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Criterestructure.findAll", query = "SELECT c FROM Criterestructure c"),
    @NamedQuery(name = "Criterestructure.findByIdstructure", query = "SELECT c FROM Criterestructure c WHERE c.criterestructurePK.idstructure = :idstructure"),
    @NamedQuery(name = "Criterestructure.findByIdcritere", query = "SELECT c FROM Criterestructure c WHERE c.criterestructurePK.idcritere = :idcritere"),
    @NamedQuery(name = "Criterestructure.findByPoids", query = "SELECT c FROM Criterestructure c WHERE c.poids = :poids"),
    @NamedQuery(name = "Criterestructure.findByPointmax", query = "SELECT c FROM Criterestructure c WHERE c.pointMax = :pointmax")})
public class Criterestructure implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CriterestructurePK criterestructurePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double poids;
    @Column(name = "pointmax")
    private Integer pointMax;
    @Column(name = "scoremoyen")
    private Integer scoreMoyen;
    private Double resultat;
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Critere critere;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Structure structure;

    @Transient
    private boolean created;

    public Criterestructure() {
    }

    public Criterestructure(CriterestructurePK criterestructurePK) {
        this.criterestructurePK = criterestructurePK;
    }

    public Criterestructure(long idstructure, int idcritere) {
        this.criterestructurePK = new CriterestructurePK(idstructure, idcritere);
    }

    public CriterestructurePK getCriterestructurePK() {
        return criterestructurePK;
    }

    public void setCriterestructurePK(CriterestructurePK criterestructurePK) {
        this.criterestructurePK = criterestructurePK;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Integer getPointMax() {
        return pointMax;
    }

    public void setPointMax(Integer pointMax) {
        this.pointMax = pointMax;
    }

    public Integer getScoreMoyen() {
        return scoreMoyen;
    }

    public void setScoreMoyen(Integer scoreMoyen) {
        this.scoreMoyen = scoreMoyen;
    }

    public Double getResultat() {
        return resultat;
    }

    public void setResultat(Double resultat) {
        this.resultat = resultat;
    }

    public Critere getCritere() {
        return critere;
    }

    public void setCritere(Critere critere) {
        this.critere = critere;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (criterestructurePK != null ? criterestructurePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Criterestructure)) {
            return false;
        }
        Criterestructure other = (Criterestructure) object;
        if ((this.criterestructurePK == null && other.criterestructurePK != null) || (this.criterestructurePK != null && !this.criterestructurePK.equals(other.criterestructurePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Criterestructure[ criterestructurePK=" + criterestructurePK + " ]";
    }

}
