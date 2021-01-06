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
public class UtilisateurstructurePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    private long idutilisateur;
    @Basic(optional = false)
    @NotNull
    private long idstructure;

    public UtilisateurstructurePK() {
    }

    public UtilisateurstructurePK(long idutilisateur, long idstructure) {
        this.idutilisateur = idutilisateur;
        this.idstructure = idstructure;
    }

    public long getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(long idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public long getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(int idstructure) {
        this.idstructure = idstructure;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UtilisateurstructurePK)) {
            return false;
        }
        UtilisateurstructurePK other = (UtilisateurstructurePK) object;
        if (this.idstructure != other.idstructure) {
            return false;
        }
        if (this.idutilisateur != other.idutilisateur) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UtilisateurstructurePK{" + "idutilisateur=" + idutilisateur + ", idstructure=" + idstructure + '}';
    }

}
