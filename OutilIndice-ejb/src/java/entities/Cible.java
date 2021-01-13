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
public class Cible implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idcible;
    private double valeurcible;
    private double valeurrealisee;
    private double ratio;
    private boolean primeresultatquant;
    private boolean bonusrevenudept;

    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    @ManyToOne(fetch = FetchType.LAZY)
    private Indicateur idindicateur;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    @ManyToOne(fetch = FetchType.LAZY)
    private Service idservice;
    @JoinColumn(name = "idsousperiode", referencedColumnName = "idsousperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousperiode idsousperiode;
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Critere idcritere;

    public Cible() {
    }

    public Cible(Long idcible) {
        this.idcible = idcible;
    }

    public Long getIdcible() {
        return idcible;
    }

    public void setIdcible(Long idcible) {
        this.idcible = idcible;
    }

    public double getValeurcible() {
        return valeurcible;
    }

    public void setValeurcible(double valeurcible) {
        this.valeurcible = valeurcible;
    }

    public double getValeurrealisee() {
        return valeurrealisee;
    }

    public void setValeurrealisee(double valeurrealisee) {
        this.valeurrealisee = valeurrealisee;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public boolean isPrimeresultatquant() {
        return primeresultatquant;
    }

    public void setPrimeresultatquant(boolean primeresultatquant) {
        this.primeresultatquant = primeresultatquant;
    }

    public boolean isBonusrevenudept() {
        return bonusrevenudept;
    }

    public void setBonusrevenudept(boolean bonusrevenudept) {
        this.bonusrevenudept = bonusrevenudept;
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

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Indicateur getIdindicateur() {
        return idindicateur;
    }

    public void setIdindicateur(Indicateur idindicateur) {
        this.idindicateur = idindicateur;
    }

    public Sousperiode getIdsousperiode() {
        return idsousperiode;
    }

    public void setIdsousperiode(Sousperiode idsousperiode) {
        this.idsousperiode = idsousperiode;
    }

    public Critere getIdcritere() {
        return idcritere;
    }

    public void setIdcritere(Critere idcritere) {
        this.idcritere = idcritere;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idcible);
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
        final Cible other = (Cible) obj;
        if (!Objects.equals(this.idcible, other.idcible)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cible{" + "idcible=" + idcible + '}';
    }

}
