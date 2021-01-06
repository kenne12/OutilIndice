/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author USER
 */
@Entity
public class Parametragecritere implements Serializable {

    @Id
    private Long idparametragecritere;

    public Parametragecritere() {
    }

    public Parametragecritere(Long idparametragecritere) {
        this.idparametragecritere = idparametragecritere;
    }

    public Long getIdparametragecritere() {
        return idparametragecritere;
    }

    public void setIdparametragecritere(Long idparametragecritere) {
        this.idparametragecritere = idparametragecritere;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.idparametragecritere);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Parametragecritere other = (Parametragecritere) obj;
        if (!Objects.equals(this.idparametragecritere, other.idparametragecritere)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Parametragecritere{" + "idparametragecritere=" + idparametragecritere + '}';
    }

}
