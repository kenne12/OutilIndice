/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author USER
 */
@Embeddable
public class CategoriestructurePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    private long idstructure;
    @Basic(optional = false)
    @NotNull
    private int idcategorie;

    public CategoriestructurePK() {
    }

    public CategoriestructurePK(long idstructure, int idcategorie) {
        this.idstructure = idstructure;
        this.idcategorie = idcategorie;
    }

    public long getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(long idstructure) {
        this.idstructure = idstructure;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idstructure;
        hash += (int) idcategorie;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriestructurePK)) {
            return false;
        }
        CategoriestructurePK other = (CategoriestructurePK) object;
        if (this.idstructure != other.idstructure) {
            return false;
        }
        if (this.idcategorie != other.idcategorie) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CategoriestructurePK[ idstructure=" + idstructure + ", idcategorie=" + idcategorie + " ]";
    }
    
}
