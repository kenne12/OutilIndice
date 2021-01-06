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
public class CritereservicePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    private long idservice;
    @Basic(optional = false)
    @NotNull
    private int idcritere;

    public CritereservicePK() {
    }

    public CritereservicePK(long idservice, int idcritere) {
        this.idservice = idservice;
        this.idcritere = idcritere;
    }

    public long getIdservice() {
        return idservice;
    }

    public void setIdservice(long idservice) {
        this.idservice = idservice;
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
        hash += (int) idservice;
        hash += (int) idcritere;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CritereservicePK)) {
            return false;
        }
        CritereservicePK other = (CritereservicePK) object;
        if (this.idservice != other.idservice) {
            return false;
        }
        if (this.idcritere != other.idcritere) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CritereservicePK[ idservice=" + idservice + ", idcritere=" + idcritere + " ]";
    }
    
}
