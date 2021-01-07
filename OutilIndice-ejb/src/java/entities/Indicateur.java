/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author USER
 */
@Entity
public class Indicateur implements Serializable {

    @Id
    @Basic(optional = false)
    private Integer idindicateur;
    @Column(length = 150)
    private String nom;
    @Column(length = 20)
    private String code;

    public Indicateur() {
    }

    public Indicateur(Integer idindicateur) {
        this.idindicateur = idindicateur;
    }

    public Integer getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Integer idindicateur) {
        this.idindicateur = idindicateur;
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
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idindicateur);
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
        final Indicateur other = (Indicateur) obj;
        if (!Objects.equals(this.idindicateur, other.idindicateur)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Indicateur{" + "idindicateur=" + idindicateur + ", nom=" + nom + ", code=" + code + '}';
    }

}
