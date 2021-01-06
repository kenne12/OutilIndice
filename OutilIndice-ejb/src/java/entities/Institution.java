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
    @NamedQuery(name = "Institution.findAll", query = "SELECT i FROM Institution i"),
    @NamedQuery(name = "Institution.findByIdinstitution", query = "SELECT i FROM Institution i WHERE i.idinstitution = :idinstitution"),
    @NamedQuery(name = "Institution.findByNom", query = "SELECT i FROM Institution i WHERE i.nom = :nom"),
    @NamedQuery(name = "Institution.findByCode", query = "SELECT i FROM Institution i WHERE i.code = :code"),
    @NamedQuery(name = "Institution.findByChoixstrategique", query = "SELECT i FROM Institution i WHERE i.choixstrategique = :choixstrategique"),
    @NamedQuery(name = "Institution.findByEtat", query = "SELECT i FROM Institution i WHERE i.etat = :etat"),
    @NamedQuery(name = "Institution.findBySigle", query = "SELECT i FROM Institution i WHERE i.sigle = :sigle"),
    @NamedQuery(name = "Institution.findByChapitre", query = "SELECT i FROM Institution i WHERE i.chapitre = :chapitre")})
public class Institution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idinstitution;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    private String choixstrategique;
    @Size(max = 254)
    private String etat;
    @Size(max = 254)
    private String sigle;
    @Size(max = 254)
    private String chapitre;
    @OneToMany(mappedBy = "idinstitution", fetch = FetchType.LAZY)
    private Collection<Structure> structureCollection;

    public Institution() {
    }

    public Institution(Integer idinstitution) {
        this.idinstitution = idinstitution;
    }

    public Integer getIdinstitution() {
        return idinstitution;
    }

    public void setIdinstitution(Integer idinstitution) {
        this.idinstitution = idinstitution;
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

    public String getChoixstrategique() {
        return choixstrategique;
    }

    public void setChoixstrategique(String choixstrategique) {
        this.choixstrategique = choixstrategique;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getChapitre() {
        return chapitre;
    }

    public void setChapitre(String chapitre) {
        this.chapitre = chapitre;
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
        hash += (idinstitution != null ? idinstitution.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institution)) {
            return false;
        }
        Institution other = (Institution) object;
        if ((this.idinstitution == null && other.idinstitution != null) || (this.idinstitution != null && !this.idinstitution.equals(other.idinstitution))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Institution[ idinstitution=" + idinstitution + " ]";
    }
    
}
