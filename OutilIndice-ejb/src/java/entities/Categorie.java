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
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c"),
    @NamedQuery(name = "Categorie.findByIdcategorie", query = "SELECT c FROM Categorie c WHERE c.idcategorie = :idcategorie"),
    @NamedQuery(name = "Categorie.findByNom", query = "SELECT c FROM Categorie c WHERE c.nom = :nom"),
    @NamedQuery(name = "Categorie.findByCode", query = "SELECT c FROM Categorie c WHERE c.code = :code"),
    @NamedQuery(name = "Categorie.findByPointmax", query = "SELECT c FROM Categorie c WHERE c.pointmax = :pointmax")})
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idcategorie;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    private Integer pointmax;
    private Integer indice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie", fetch = FetchType.LAZY)
    private Collection<Categoriestructure> categoriestructureCollection;
    @OneToMany(mappedBy = "idcategorie", fetch = FetchType.LAZY)
    private Collection<Detailsc> detailscCollection;
    @OneToMany(mappedBy = "idcategorie", fetch = FetchType.LAZY)
    private Collection<Personnel> personnelCollection;

    public Categorie() {
    }

    public Categorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
    }

    public Integer getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
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

    public Integer getPointmax() {
        return pointmax;
    }

    public void setPointmax(Integer pointmax) {
        this.pointmax = pointmax;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    @XmlTransient
    public Collection<Categoriestructure> getCategoriestructureCollection() {
        return categoriestructureCollection;
    }

    public void setCategoriestructureCollection(Collection<Categoriestructure> categoriestructureCollection) {
        this.categoriestructureCollection = categoriestructureCollection;
    }

    @XmlTransient
    public Collection<Detailsc> getDetailscCollection() {
        return detailscCollection;
    }

    public void setDetailscCollection(Collection<Detailsc> detailscCollection) {
        this.detailscCollection = detailscCollection;
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
        hash += (idcategorie != null ? idcategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.idcategorie == null && other.idcategorie != null) || (this.idcategorie != null && !this.idcategorie.equals(other.idcategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categorie[ idcategorie=" + idcategorie + " ]";
    }

}
