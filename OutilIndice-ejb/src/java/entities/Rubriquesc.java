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
import javax.validation.constraints.NotNull;

/**
 *
 * @author USER
 */
@Entity(name = "rubriquesc")
public class Rubriquesc implements Serializable {

    @Id
    @NotNull
    @Basic(optional = false)
    private Integer idrubriquesc;
    @Column(length = 200)
    private String nom;

    public Rubriquesc() {
    }

    public Rubriquesc(Integer idrubriquesc) {
        this.idrubriquesc = idrubriquesc;
    }

    public Integer getIdrubriquesc() {
        return idrubriquesc;
    }

    public void setIdrubriquesc(Integer idrubriquesc) {
        this.idrubriquesc = idrubriquesc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Rubriquesc{" + "idrubriquesc=" + idrubriquesc + ", nom=" + nom + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idrubriquesc);
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
        final Rubriquesc other = (Rubriquesc) obj;
        if (!Objects.equals(this.idrubriquesc, other.idrubriquesc)) {
            return false;
        }
        return true;
    }

}
