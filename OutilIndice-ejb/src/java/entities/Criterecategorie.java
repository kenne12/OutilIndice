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
public class Criterecategorie implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idcriterecategorie;
    private double valeurmax;
    private int indice;
    private int denominateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    private Categorie idcategorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere")
    private Critere idcritere;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    private Structure idstructure;

    public Criterecategorie() {
    }

    public Criterecategorie(Integer idcriterecategorie) {
        this.idcriterecategorie = idcriterecategorie;
    }

    public Integer getIdcriterecategorie() {
        return idcriterecategorie;
    }

    public void setIdcriterecategorie(Integer idcriterecategorie) {
        this.idcriterecategorie = idcriterecategorie;
    }

    public double getValeurmax() {
        return valeurmax;
    }

    public void setValeurmax(double valeurmax) {
        this.valeurmax = valeurmax;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getDenominateur() {
        return denominateur;
    }

    public void setDenominateur(int denominateur) {
        this.denominateur = denominateur;
    }

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
    }

    public Critere getIdcritere() {
        return idcritere;
    }

    public void setIdcritere(Critere idcritere) {
        this.idcritere = idcritere;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idcriterecategorie);
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
        final Criterecategorie other = (Criterecategorie) obj;
        if (!Objects.equals(this.idcriterecategorie, other.idcriterecategorie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Criterecategorie{" + "idcriterecategorie=" + idcriterecategorie + '}';
    }

}
