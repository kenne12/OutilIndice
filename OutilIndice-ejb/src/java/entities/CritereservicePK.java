package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

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
        return this.idservice;
    }

    public void setIdservice(long idservice) {
        this.idservice = idservice;
    }

    public int getIdcritere() {
        return this.idcritere;
    }

    public void setIdcritere(int idcritere) {
        this.idcritere = idcritere;
    }

    public int hashCode() {
        int hash = 0;
        hash += (int) this.idservice;
        hash += this.idcritere;
        return hash;
    }

    public boolean equals(Object object) {
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

    public String toString() {
        return "entities.CritereservicePK[ idservice=" + this.idservice + ", idcritere=" + this.idcritere + " ]";
    }
}
