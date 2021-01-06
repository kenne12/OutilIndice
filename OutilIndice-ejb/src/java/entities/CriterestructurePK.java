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
public class CriterestructurePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    private long idstructure;
    @Basic(optional = false)
    @NotNull
    private int idcritere;

    public CriterestructurePK() {
    }

    public CriterestructurePK(long idstructure, int idcritere) {
        this.idstructure = idstructure;
        this.idcritere = idcritere;
    }

    public long getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(long idstructure) {
        this.idstructure = idstructure;
    }

    public int getIdcritere() {
        return idcritere;
    }

    public void setIdcritere(int idcritere) {
        this.idcritere = idcritere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idstructure;
        hash += (int) idcritere;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CriterestructurePK)) {
            return false;
        }
        CriterestructurePK other = (CriterestructurePK) object;
        if (this.idstructure != other.idstructure) {
            return false;
        }
        if (this.idcritere != other.idcritere) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CriterestructurePK[ idstructure=" + idstructure + ", idcritere=" + idcritere + " ]";
    }
    
}
