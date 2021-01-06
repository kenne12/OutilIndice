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
    @NamedQuery(name = "Sousrubriquerecette.findAll", query = "SELECT s FROM Sousrubriquerecette s"),
    @NamedQuery(name = "Sousrubriquerecette.findByIdsousrubriquerecette", query = "SELECT s FROM Sousrubriquerecette s WHERE s.idsousrubriquerecette = :idsousrubriquerecette"),
    @NamedQuery(name = "Sousrubriquerecette.findByNom", query = "SELECT s FROM Sousrubriquerecette s WHERE s.nom = :nom"),
    @NamedQuery(name = "Sousrubriquerecette.findByCode", query = "SELECT s FROM Sousrubriquerecette s WHERE s.code = :code")})
public class Sousrubriquerecette implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idsousrubriquerecette;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    @JoinColumn(name = "idrubriquerecette", referencedColumnName = "idrubriquerecette")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquerecette idrubriquerecette;
    @OneToMany(mappedBy = "idsousrubriquerecette", fetch = FetchType.LAZY)
    private Collection<Recette> recetteCollection;

    public Sousrubriquerecette() {
    }

    public Sousrubriquerecette(Integer idsousrubriquerecette) {
        this.idsousrubriquerecette = idsousrubriquerecette;
    }

    public Integer getIdsousrubriquerecette() {
        return idsousrubriquerecette;
    }

    public void setIdsousrubriquerecette(Integer idsousrubriquerecette) {
        this.idsousrubriquerecette = idsousrubriquerecette;
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

    public Rubriquerecette getIdrubriquerecette() {
        return idrubriquerecette;
    }

    public void setIdrubriquerecette(Rubriquerecette idrubriquerecette) {
        this.idrubriquerecette = idrubriquerecette;
    }

    @XmlTransient
    public Collection<Recette> getRecetteCollection() {
        return recetteCollection;
    }

    public void setRecetteCollection(Collection<Recette> recetteCollection) {
        this.recetteCollection = recetteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsousrubriquerecette != null ? idsousrubriquerecette.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sousrubriquerecette)) {
            return false;
        }
        Sousrubriquerecette other = (Sousrubriquerecette) object;
        if ((this.idsousrubriquerecette == null && other.idsousrubriquerecette != null) || (this.idsousrubriquerecette != null && !this.idsousrubriquerecette.equals(other.idsousrubriquerecette))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sousrubriquerecette[ idsousrubriquerecette=" + idsousrubriquerecette + " ]";
    }
    
}
