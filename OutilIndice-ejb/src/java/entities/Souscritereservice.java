/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Souscritereservice.findAll", query = "SELECT s FROM Souscritereservice s"),
    @NamedQuery(name = "Souscritereservice.findByIdsouscritereservice", query = "SELECT s FROM Souscritereservice s WHERE s.idsouscritereservice = :idsouscritereservice"),
    @NamedQuery(name = "Souscritereservice.findByDetail", query = "SELECT s FROM Souscritereservice s WHERE s.detail = :detail"),
    @NamedQuery(name = "Souscritereservice.findByPointmax", query = "SELECT s FROM Souscritereservice s WHERE s.pointmax = :pointmax")})
public class Souscritereservice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idsouscritereservice;
    @Size(max = 254)
    private String detail;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private int pointmax;
    @OneToMany(mappedBy = "idsouscritereservice", fetch = FetchType.LAZY)
    private Collection<Evaluationservice> evaluationserviceCollection;
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    @ManyToOne(fetch = FetchType.LAZY)
    private Service idservice;
    @JoinColumn(name = "idsouscritere", referencedColumnName = "idsouscritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Souscritere idsouscritere;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure structure;

    public Souscritereservice() {
    }

    public Souscritereservice(Long idsouscritereservice) {
        this.idsouscritereservice = idsouscritereservice;
    }

    public Long getIdsouscritereservice() {
        return idsouscritereservice;
    }

    public void setIdsouscritereservice(Long idsouscritereservice) {
        this.idsouscritereservice = idsouscritereservice;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPointmax() {
        return pointmax;
    }

    public void setPointmax(int pointmax) {
        this.pointmax = pointmax;
    }

    @XmlTransient
    public Collection<Evaluationservice> getEvaluationserviceCollection() {
        return evaluationserviceCollection;
    }

    public void setEvaluationserviceCollection(Collection<Evaluationservice> evaluationserviceCollection) {
        this.evaluationserviceCollection = evaluationserviceCollection;
    }

    public Service getIdservice() {
        return idservice;
    }

    public void setIdservice(Service idservice) {
        this.idservice = idservice;
    }

    public Souscritere getIdsouscritere() {
        return idsouscritere;
    }

    public void setIdsouscritere(Souscritere idsouscritere) {
        this.idsouscritere = idsouscritere;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsouscritereservice != null ? idsouscritereservice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Souscritereservice)) {
            return false;
        }
        Souscritereservice other = (Souscritereservice) object;
        if ((this.idsouscritereservice == null && other.idsouscritereservice != null) || (this.idsouscritereservice != null && !this.idsouscritereservice.equals(other.idsouscritereservice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Souscritereservice[ idsouscritereservice=" + idsouscritereservice + " ]";
    }

}
