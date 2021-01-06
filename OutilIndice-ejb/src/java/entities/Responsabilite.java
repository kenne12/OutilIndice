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
public class Responsabilite implements Serializable {

    @Id
    private Integer idresponsabilite;
    private String nom;
    private String code;

    public Responsabilite() {
    }

    public Responsabilite(Integer idresponsabilite) {
        this.idresponsabilite = idresponsabilite;
    }

    public Integer getIdresponsabilite() {
        return idresponsabilite;
    }

    public void setIdresponsabilite(Integer idresponsabilite) {
        this.idresponsabilite = idresponsabilite;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.idresponsabilite);
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
        final Responsabilite other = (Responsabilite) obj;
        if (!Objects.equals(this.idresponsabilite, other.idresponsabilite)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Responsabilite{" + "idresponsabilite=" + idresponsabilite + ", nom=" + nom + ", code=" + code + '}';
    }

}
