/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author USER
 */
@Entity
public class Elementreponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idelementreponse;
    private String nom;
    private double valeur;

    @JoinColumn(name = "idsouscritere", referencedColumnName = "idsouscritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Souscritere idsouscritere;

    public Elementreponse() {
    }

    public Elementreponse(Integer idelementReponse) {
        this.idelementreponse = idelementReponse;
    }

    public Integer getIdelementreponse() {
        return idelementreponse;
    }

    public void setIdelementreponse(Integer idelementreponse) {
        this.idelementreponse = idelementreponse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public Souscritere getIdsouscritere() {
        return idsouscritere;
    }

    public void setIdsouscritere(Souscritere idsouscritere) {
        this.idsouscritere = idsouscritere;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.idelementreponse);
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
        final Elementreponse other = (Elementreponse) obj;
        if (!Objects.equals(this.idelementreponse, other.idelementreponse)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ElementReponse{" + "idelementReponse=" + idelementreponse + ", nom=" + nom + ", valeur=" + valeur + ", idsouscritere=" + idsouscritere + '}';
    }

}
