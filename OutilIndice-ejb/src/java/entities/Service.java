/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
    @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
    @NamedQuery(name = "Service.findByIdservice", query = "SELECT s FROM Service s WHERE s.idservice = :idservice"),
    @NamedQuery(name = "Service.findByNom", query = "SELECT s FROM Service s WHERE s.nom = :nom"),
    @NamedQuery(name = "Service.findByCode", query = "SELECT s FROM Service s WHERE s.code = :code")})
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idservice;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "service", fetch = FetchType.LAZY)
    private Collection<Critereservice> critereserviceCollection;
    @OneToMany(mappedBy = "idservice", fetch = FetchType.LAZY)
    private Collection<Souscritereservice> souscritereserviceCollection;
    @OneToMany(mappedBy = "idservice", fetch = FetchType.LAZY)
    private Collection<Personnel> personnelCollection;

    public Service() {
    }

    public Service(Long idservice) {
        this.idservice = idservice;
    }

    public Long getIdservice() {
        return idservice;
    }

    public void setIdservice(Long idservice) {
        this.idservice = idservice;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public Collection<Critereservice> getCritereserviceCollection() {
        return critereserviceCollection;
    }

    public void setCritereserviceCollection(Collection<Critereservice> critereserviceCollection) {
        this.critereserviceCollection = critereserviceCollection;
    }

    @XmlTransient
    public Collection<Souscritereservice> getSouscritereserviceCollection() {
        return souscritereserviceCollection;
    }

    public void setSouscritereserviceCollection(Collection<Souscritereservice> souscritereserviceCollection) {
        this.souscritereserviceCollection = souscritereserviceCollection;
    }

    @XmlTransient
    public Collection<Personnel> getPersonnelCollection() {
        return personnelCollection;
    }

    public void setPersonnelCollection(Collection<Personnel> personnelCollection) {
        this.personnelCollection = personnelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idservice != null ? idservice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.idservice == null && other.idservice != null) || (this.idservice != null && !this.idservice.equals(other.idservice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Service[ idservice=" + idservice + " ]";
    }

}
