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

/**
 *
 * @author USER
 */
@Entity
public class Parametragecritere implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idparametragecritere;
    private int indice;
    private int denominateur;
    private double valeur;
    private double point;
    private double valeurjournee;
    private double valeurnuit;
    private double denominateurjournee;
    private double denominateurnuit;
    private boolean heuresupp;
    private boolean heuresupn;
    private boolean pratiqueprivee;
    private boolean resultatqualitatifdept;
    private boolean resultatquantitatifdept;
    private boolean performanceindividuelle;
    private boolean bonusrevenudept;
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categorie idcategorie;
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Critere idcritere;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    @ManyToOne(fetch = FetchType.LAZY)
    private Service idservice;

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

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getValeurjournee() {
        return valeurjournee;
    }

    public void setValeurjournee(double valeurjournee) {
        this.valeurjournee = valeurjournee;
    }

    public double getValeurnuit() {
        return valeurnuit;
    }

    public void setValeurnuit(double valeurnuit) {
        this.valeurnuit = valeurnuit;
    }

    public double getDenominateurjournee() {
        return denominateurjournee;
    }

    public void setDenominateurjournee(double denominateurjournee) {
        this.denominateurjournee = denominateurjournee;
    }

    public double getDenominateurnuit() {
        return denominateurnuit;
    }

    public void setDenominateurnuit(double denominateurnuit) {
        this.denominateurnuit = denominateurnuit;
    }

    public boolean isHeuresupp() {
        return heuresupp;
    }

    public void setHeuresupp(boolean heuresupp) {
        this.heuresupp = heuresupp;
    }

    public boolean isHeuresupn() {
        return heuresupn;
    }

    public void setHeuresupn(boolean heuresupn) {
        this.heuresupn = heuresupn;
    }

    public boolean isPratiqueprivee() {
        return pratiqueprivee;
    }

    public void setPratiqueprivee(boolean pratiqueprivee) {
        this.pratiqueprivee = pratiqueprivee;
    }

    public boolean isResultatqualitatifdept() {
        return resultatqualitatifdept;
    }

    public void setResultatqualitatifdept(boolean resultatqualitatifdept) {
        this.resultatqualitatifdept = resultatqualitatifdept;
    }

    public boolean isPerformanceindividuelle() {
        return performanceindividuelle;
    }

    public void setPerformanceindividuelle(boolean performanceindividuelle) {
        this.performanceindividuelle = performanceindividuelle;
    }

    public boolean isBonusrevenudept() {
        return bonusrevenudept;
    }

    public void setBonusrevenudept(boolean bonusrevenudept) {
        this.bonusrevenudept = bonusrevenudept;
    }

    public boolean isResultatquantitatifdept() {
        return resultatquantitatifdept;
    }

    public void setResultatquantitatifdept(boolean resultatquantitatifdept) {
        this.resultatquantitatifdept = resultatquantitatifdept;
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

    public Service getIdservice() {
        return idservice;
    }

    public void setIdservice(Service idservice) {
        this.idservice = idservice;
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
