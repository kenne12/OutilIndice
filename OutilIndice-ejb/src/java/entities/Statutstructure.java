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
    @NamedQuery(name = "Statutstructure.findAll", query = "SELECT s FROM Statutstructure s"),
    @NamedQuery(name = "Statutstructure.findByIdstatutstructure", query = "SELECT s FROM Statutstructure s WHERE s.idstatutstructure = :idstatutstructure"),
    @NamedQuery(name = "Statutstructure.findByNom", query = "SELECT s FROM Statutstructure s WHERE s.nom = :nom"),
    @NamedQuery(name = "Statutstructure.findByEtat", query = "SELECT s FROM Statutstructure s WHERE s.etat = :etat")})
public class Statutstructure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idstatutstructure;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String etat;
    @OneToMany(mappedBy = "idstatutstructure", fetch = FetchType.LAZY)
    private Collection<Structure> structureCollection;

    public Statutstructure() {
    }

    public Statutstructure(Long idstatutstructure) {
        this.idstatutstructure = idstatutstructure;
    }

    public Long getIdstatutstructure() {
        return idstatutstructure;
    }

    public void setIdstatutstructure(Long idstatutstructure) {
        this.idstatutstructure = idstatutstructure;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatutstructure != null ? idstatutstructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statutstructure)) {
            return false;
        }
        Statutstructure other = (Statutstructure) object;
        if ((this.idstatutstructure == null && other.idstatutstructure != null) || (this.idstatutstructure != null && !this.idstatutstructure.equals(other.idstatutstructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Statutstructure[ idstatutstructure=" + idstatutstructure + " ]";
    }
    
}
