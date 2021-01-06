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
    @NamedQuery(name = "Rubriquedepense.findAll", query = "SELECT r FROM Rubriquedepense r"),
    @NamedQuery(name = "Rubriquedepense.findByIdrubriquedepense", query = "SELECT r FROM Rubriquedepense r WHERE r.idrubriquedepense = :idrubriquedepense"),
    @NamedQuery(name = "Rubriquedepense.findByNom", query = "SELECT r FROM Rubriquedepense r WHERE r.nom = :nom"),
    @NamedQuery(name = "Rubriquedepense.findByCode", query = "SELECT r FROM Rubriquedepense r WHERE r.code = :code")})
public class Rubriquedepense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrubriquedepense;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    @OneToMany(mappedBy = "idrubriquedepense", fetch = FetchType.LAZY)
    private Collection<Sousrubriquedepense> sousrubriquedepenseCollection;

    public Rubriquedepense() {
    }

    public Rubriquedepense(Integer idrubriquedepense) {
        this.idrubriquedepense = idrubriquedepense;
    }

    public Integer getIdrubriquedepense() {
        return idrubriquedepense;
    }

    public void setIdrubriquedepense(Integer idrubriquedepense) {
        this.idrubriquedepense = idrubriquedepense;
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
    public Collection<Sousrubriquedepense> getSousrubriquedepenseCollection() {
        return sousrubriquedepenseCollection;
    }

    public void setSousrubriquedepenseCollection(Collection<Sousrubriquedepense> sousrubriquedepenseCollection) {
        this.sousrubriquedepenseCollection = sousrubriquedepenseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubriquedepense != null ? idrubriquedepense.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubriquedepense)) {
            return false;
        }
        Rubriquedepense other = (Rubriquedepense) object;
        if ((this.idrubriquedepense == null && other.idrubriquedepense != null) || (this.idrubriquedepense != null && !this.idrubriquedepense.equals(other.idrubriquedepense))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rubriquedepense[ idrubriquedepense=" + idrubriquedepense + " ]";
    }
    
}
