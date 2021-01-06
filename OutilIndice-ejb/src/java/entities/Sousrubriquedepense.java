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
    @NamedQuery(name = "Sousrubriquedepense.findAll", query = "SELECT s FROM Sousrubriquedepense s"),
    @NamedQuery(name = "Sousrubriquedepense.findByIdsousrubriquedepense", query = "SELECT s FROM Sousrubriquedepense s WHERE s.idsousrubriquedepense = :idsousrubriquedepense"),
    @NamedQuery(name = "Sousrubriquedepense.findByNom", query = "SELECT s FROM Sousrubriquedepense s WHERE s.nom = :nom"),
    @NamedQuery(name = "Sousrubriquedepense.findByCode", query = "SELECT s FROM Sousrubriquedepense s WHERE s.code = :code"),
    @NamedQuery(name = "Sousrubriquedepense.findBySpecial", query = "SELECT s FROM Sousrubriquedepense s WHERE s.special = :special")})
public class Sousrubriquedepense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idsousrubriquedepense;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    private Boolean special;
    @JoinColumn(name = "idrubriquedepense", referencedColumnName = "idrubriquedepense")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquedepense idrubriquedepense;
    @OneToMany(mappedBy = "idsousrubriquedepense", fetch = FetchType.LAZY)
    private Collection<Depense> depenseCollection;

    public Sousrubriquedepense() {
    }

    public Sousrubriquedepense(Integer idsousrubriquedepense) {
        this.idsousrubriquedepense = idsousrubriquedepense;
    }

    public Integer getIdsousrubriquedepense() {
        return idsousrubriquedepense;
    }

    public void setIdsousrubriquedepense(Integer idsousrubriquedepense) {
        this.idsousrubriquedepense = idsousrubriquedepense;
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

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public Rubriquedepense getIdrubriquedepense() {
        return idrubriquedepense;
    }

    public void setIdrubriquedepense(Rubriquedepense idrubriquedepense) {
        this.idrubriquedepense = idrubriquedepense;
    }

    @XmlTransient
    public Collection<Depense> getDepenseCollection() {
        return depenseCollection;
    }

    public void setDepenseCollection(Collection<Depense> depenseCollection) {
        this.depenseCollection = depenseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsousrubriquedepense != null ? idsousrubriquedepense.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sousrubriquedepense)) {
            return false;
        }
        Sousrubriquedepense other = (Sousrubriquedepense) object;
        if ((this.idsousrubriquedepense == null && other.idsousrubriquedepense != null) || (this.idsousrubriquedepense != null && !this.idsousrubriquedepense.equals(other.idsousrubriquedepense))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sousrubriquedepense[ idsousrubriquedepense=" + idsousrubriquedepense + " ]";
    }
    
}
