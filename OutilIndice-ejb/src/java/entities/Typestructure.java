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
    @NamedQuery(name = "Typestructure.findAll", query = "SELECT t FROM Typestructure t"),
    @NamedQuery(name = "Typestructure.findByIdtypestructure", query = "SELECT t FROM Typestructure t WHERE t.idtypestructure = :idtypestructure"),
    @NamedQuery(name = "Typestructure.findByNom", query = "SELECT t FROM Typestructure t WHERE t.nom = :nom"),
    @NamedQuery(name = "Typestructure.findByEtat", query = "SELECT t FROM Typestructure t WHERE t.etat = :etat")})
public class Typestructure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idtypestructure;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String etat;
    @OneToMany(mappedBy = "idtypestructure", fetch = FetchType.LAZY)
    private Collection<Structure> structureCollection;

    @OneToMany(mappedBy = "typestructure", fetch = FetchType.LAZY)
    private Collection<TypestructureService> typestructureServiceCollection;

    @OneToMany(mappedBy = "typestructure", fetch = FetchType.LAZY)
    private Collection<TypestructureTypeSousperiode> typestructureTypeSousperiodeCollection;

    @OneToMany(mappedBy = "typestructure", fetch = FetchType.LAZY)
    private Collection<TypestructureCategorie> typestructureCategorieCollection;

    @OneToMany(mappedBy = "typestructure", fetch = FetchType.LAZY)
    private Collection<TypestructureResponsabilite> typestructureResponsabiliteCollection;

    public Typestructure() {
    }

    public Typestructure(Long idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    public Long getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Long idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @XmlTransient
    public Collection<Structure> getStructureCollection() {
        return structureCollection;
    }

    public void setStructureCollection(Collection<Structure> structureCollection) {
        this.structureCollection = structureCollection;
    }

    @XmlTransient
    public Collection<TypestructureService> getTypestructureServiceCollection() {
        return typestructureServiceCollection;
    }

    public void setTypestructureServiceCollection(Collection<TypestructureService> typestructureServiceCollection) {
        this.typestructureServiceCollection = typestructureServiceCollection;
    }

    @XmlTransient
    public Collection<TypestructureTypeSousperiode> getTypestructureTypeSousperiodeCollection() {
        return typestructureTypeSousperiodeCollection;
    }

    public void setTypestructureTypeSousperiodeCollection(Collection<TypestructureTypeSousperiode> typestructureTypeSousperiodeCollection) {
        this.typestructureTypeSousperiodeCollection = typestructureTypeSousperiodeCollection;
    }

    @XmlTransient
    public Collection<TypestructureCategorie> getTypestructureCategorieCollection() {
        return typestructureCategorieCollection;
    }

    public void setTypestructureCategorieCollection(Collection<TypestructureCategorie> typestructureCategorieCollection) {
        this.typestructureCategorieCollection = typestructureCategorieCollection;
    }

    @XmlTransient
    public Collection<TypestructureResponsabilite> getTypestructureResponsabiliteCollection() {
        return typestructureResponsabiliteCollection;
    }

    public void setTypestructureResponsabiliteCollection(Collection<TypestructureResponsabilite> typestructureResponsabiliteCollection) {
        this.typestructureResponsabiliteCollection = typestructureResponsabiliteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypestructure != null ? idtypestructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typestructure)) {
            return false;
        }
        Typestructure other = (Typestructure) object;
        if ((this.idtypestructure == null && other.idtypestructure != null) || (this.idtypestructure != null && !this.idtypestructure.equals(other.idtypestructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typestructure[ idtypestructure=" + idtypestructure + " ]";
    }

}
