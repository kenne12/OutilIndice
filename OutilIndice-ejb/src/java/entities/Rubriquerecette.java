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
    @NamedQuery(name = "Rubriquerecette.findAll", query = "SELECT r FROM Rubriquerecette r"),
    @NamedQuery(name = "Rubriquerecette.findByIdrubriquerecette", query = "SELECT r FROM Rubriquerecette r WHERE r.idrubriquerecette = :idrubriquerecette"),
    @NamedQuery(name = "Rubriquerecette.findByNom", query = "SELECT r FROM Rubriquerecette r WHERE r.nom = :nom"),
    @NamedQuery(name = "Rubriquerecette.findByCode", query = "SELECT r FROM Rubriquerecette r WHERE r.code = :code")})
public class Rubriquerecette implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idrubriquerecette;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    @OneToMany(mappedBy = "idrubriquerecette", fetch = FetchType.LAZY)
    private Collection<Sousrubriquerecette> sousrubriquerecetteCollection;

    public Rubriquerecette() {
    }

    public Rubriquerecette(Integer idrubriquerecette) {
        this.idrubriquerecette = idrubriquerecette;
    }

    public Integer getIdrubriquerecette() {
        return idrubriquerecette;
    }

    public void setIdrubriquerecette(Integer idrubriquerecette) {
        this.idrubriquerecette = idrubriquerecette;
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
    public Collection<Sousrubriquerecette> getSousrubriquerecetteCollection() {
        return sousrubriquerecetteCollection;
    }

    public void setSousrubriquerecetteCollection(Collection<Sousrubriquerecette> sousrubriquerecetteCollection) {
        this.sousrubriquerecetteCollection = sousrubriquerecetteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubriquerecette != null ? idrubriquerecette.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubriquerecette)) {
            return false;
        }
        Rubriquerecette other = (Rubriquerecette) object;
        if ((this.idrubriquerecette == null && other.idrubriquerecette != null) || (this.idrubriquerecette != null && !this.idrubriquerecette.equals(other.idrubriquerecette))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rubriquerecette[ idrubriquerecette=" + idrubriquerecette + " ]";
    }
    
}
