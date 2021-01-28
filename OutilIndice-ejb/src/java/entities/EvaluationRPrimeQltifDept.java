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
import javax.persistence.Table;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "evaluationrprimeqltifdept")
public class EvaluationRPrimeQltifDept implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idevaluationrprimeqltifdept;
    private int cible;
    private int score;
    private double pourcentage;
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    @ManyToOne(fetch = FetchType.LAZY)
    private Service idservice;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idsousperiode", referencedColumnName = "idsousperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousperiode idsousperiode;
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Critere idcritere;

    public EvaluationRPrimeQltifDept() {
    }

    public EvaluationRPrimeQltifDept(Long idevaluationrprimeqltifdept) {
        this.idevaluationrprimeqltifdept = idevaluationrprimeqltifdept;
    }

    public Long getIdevaluationrprimeqltifdept() {
        return idevaluationrprimeqltifdept;
    }

    public void setIdevaluationrprimeqltifdept(Long idevaluationrprimeqltifdept) {
        this.idevaluationrprimeqltifdept = idevaluationrprimeqltifdept;
    }

    public int getCible() {
        return cible;
    }

    public void setCible(int cible) {
        this.cible = cible;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Sousperiode getIdsousperiode() {
        return idsousperiode;
    }

    public void setIdsousperiode(Sousperiode idsousperiode) {
        this.idsousperiode = idsousperiode;
    }

    public Service getIdservice() {
        return idservice;
    }

    public void setIdservice(Service idservice) {
        this.idservice = idservice;
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
        hash = 29 * hash + Objects.hashCode(this.idevaluationrprimeqltifdept);
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
        final EvaluationRPrimeQltifDept other = (EvaluationRPrimeQltifDept) obj;
        if (!Objects.equals(this.idevaluationrprimeqltifdept, other.idevaluationrprimeqltifdept)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EvaluationRPrimeQltifDept{" + "idevaluationrprimeqltifdept=" + idevaluationrprimeqltifdept + '}';
    }

}
