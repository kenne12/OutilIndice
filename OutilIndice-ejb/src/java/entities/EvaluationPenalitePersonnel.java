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
@Table(name = "evaluationpenalitepersonnel")
public class EvaluationPenalitePersonnel implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idevaluationpenalitepersonnel;

    private int score;
    private int cible;
    private double ratio;

    @JoinColumn(name = "idPersonnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel idPersonnel;

    @JoinColumn(name = "idPeriode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idPeriode;

    @JoinColumn(name = "idSousperiode", referencedColumnName = "idsousperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousperiode idSousperiode;

    public EvaluationPenalitePersonnel() {
    }

    public EvaluationPenalitePersonnel(Long idevaluationpenalitepersonnel) {
        this.idevaluationpenalitepersonnel = idevaluationpenalitepersonnel;
    }

    public Long getIdevaluationpenalitepersonnel() {
        return idevaluationpenalitepersonnel;
    }

    public void setIdevaluationpenalitepersonnel(Long idevaluationpenalitepersonnel) {
        this.idevaluationpenalitepersonnel = idevaluationpenalitepersonnel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCible() {
        return cible;
    }

    public void setCible(int cible) {
        this.cible = cible;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public Personnel getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Personnel idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public Periode getIdPeriode() {
        return idPeriode;
    }

    public void setIdPeriode(Periode idPeriode) {
        this.idPeriode = idPeriode;
    }

    public Sousperiode getIdSousperiode() {
        return idSousperiode;
    }

    public void setIdSousperiode(Sousperiode idSousperiode) {
        this.idSousperiode = idSousperiode;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.idevaluationpenalitepersonnel);
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
        final EvaluationPenalitePersonnel other = (EvaluationPenalitePersonnel) obj;
        if (!Objects.equals(this.idevaluationpenalitepersonnel, other.idevaluationpenalitepersonnel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EvaluationPenalitePersonnel{" + "idEvaluationPenalitePersonnel=" + idevaluationpenalitepersonnel + '}';
    }

}
