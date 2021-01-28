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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "ligneprimequalitedept")
public class LignePrimeQualiteDept implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idligneprimequalitedept;
    @Column(name = "valeurcible")
    private int valeurCible;
    @Column(name = "valeurrealisee")
    private int valeurRealisee;
    private double ratio;

    @JoinColumn(name = "idsouscritereservice", referencedColumnName = "idsouscritereservice")
    @ManyToOne(fetch = FetchType.LAZY)
    private Souscritereservice idsouscritereservice;

    @JoinColumn(name = "idevaluationrprimeqltifdept", referencedColumnName = "idevaluationrprimeqltifdept")
    @ManyToOne(fetch = FetchType.LAZY)
    private EvaluationRPrimeQltifDept idEvaluationRPrimeQltifDept;

    public LignePrimeQualiteDept() {
    }

    public LignePrimeQualiteDept(Long idligneprimequalitedept) {
        this.idligneprimequalitedept = idligneprimequalitedept;
    }

    public Long getIdligneprimequalitedept() {
        return idligneprimequalitedept;
    }

    public void setIdligneprimequalitedept(Long idligneprimequalitedept) {
        this.idligneprimequalitedept = idligneprimequalitedept;
    }

    public int getValeurCible() {
        return valeurCible;
    }

    public void setValeurCible(int valeurCible) {
        this.valeurCible = valeurCible;
    }

    public int getValeurRealisee() {
        return valeurRealisee;
    }

    public void setValeurRealisee(int valeurRealisee) {
        this.valeurRealisee = valeurRealisee;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public Souscritereservice getIdsouscritereservice() {
        return idsouscritereservice;
    }

    public void setIdsouscritereservice(Souscritereservice idsouscritereservice) {
        this.idsouscritereservice = idsouscritereservice;
    }

    public EvaluationRPrimeQltifDept getIdEvaluationRPrimeQltifDept() {
        return idEvaluationRPrimeQltifDept;
    }

    public void setIdEvaluationRPrimeQltifDept(EvaluationRPrimeQltifDept idEvaluationRPrimeQltifDept) {
        this.idEvaluationRPrimeQltifDept = idEvaluationRPrimeQltifDept;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.idligneprimequalitedept);
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
        final LignePrimeQualiteDept other = (LignePrimeQualiteDept) obj;
        if (!Objects.equals(this.idligneprimequalitedept, other.idligneprimequalitedept)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LignePrimeQualiteDept{" + "idligneprimequalitedept=" + idligneprimequalitedept + '}';
    }

}
