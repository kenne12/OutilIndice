/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriestructure.findAll", query = "SELECT c FROM Categoriestructure c"),
    @NamedQuery(name = "Categoriestructure.findByIdstructure", query = "SELECT c FROM Categoriestructure c WHERE c.categoriestructurePK.idstructure = :idstructure"),
    @NamedQuery(name = "Categoriestructure.findByIdcategorie", query = "SELECT c FROM Categoriestructure c WHERE c.categoriestructurePK.idcategorie = :idcategorie"),
    @NamedQuery(name = "Categoriestructure.findByPointmax", query = "SELECT c FROM Categoriestructure c WHERE c.pointmax = :pointmax")})
public class Categoriestructure implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CategoriestructurePK categoriestructurePK;
    private double pointmax;
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categorie categorie;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Structure structure;
    private int indice;
    private int denominateur;

    public Categoriestructure() {
    }

    public Categoriestructure(CategoriestructurePK categoriestructurePK) {
        this.categoriestructurePK = categoriestructurePK;
    }

    public Categoriestructure(long idstructure, int idcategorie) {
        this.categoriestructurePK = new CategoriestructurePK(idstructure, idcategorie);
    }

    public CategoriestructurePK getCategoriestructurePK() {
        return categoriestructurePK;
    }

    public void setCategoriestructurePK(CategoriestructurePK categoriestructurePK) {
        this.categoriestructurePK = categoriestructurePK;
    }

    public double getPointmax() {
        return pointmax;
    }

    public void setPointmax(double pointmax) {
        this.pointmax = pointmax;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getDenominateur() {
        return denominateur;
    }

    public void setDenominateur(int denominateur) {
        this.denominateur = denominateur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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
        hash += (categoriestructurePK != null ? categoriestructurePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoriestructure)) {
            return false;
        }
        Categoriestructure other = (Categoriestructure) object;
        if ((this.categoriestructurePK == null && other.categoriestructurePK != null) || (this.categoriestructurePK != null && !this.categoriestructurePK.equals(other.categoriestructurePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categoriestructure[ categoriestructurePK=" + categoriestructurePK + " ]";
    }

}
