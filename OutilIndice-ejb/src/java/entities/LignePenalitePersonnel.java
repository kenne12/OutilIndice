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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "lignepenalitepersonnel")
public class LignePenalitePersonnel implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idlignepenalitepersonnel")
    private Long idLignePenalitePersonnel;

    private int score;
    private int cible;
    private double ratio;

    @JoinColumn(name = "idevaluationpenalitepersonnel", referencedColumnName = "idevaluationpenalitepersonnel")
    @ManyToMany(fetch = FetchType.LAZY)
    private EvaluationPenalitePersonnel idEvaluationPenalitePersonnel;

    public LignePenalitePersonnel() {
    }

    public LignePenalitePersonnel(Long idLignePenalitePersonnel) {
        this.idLignePenalitePersonnel = idLignePenalitePersonnel;
    }

    public Long getIdLignePenalitePersonnel() {
        return idLignePenalitePersonnel;
    }

    public void setIdLignePenalitePersonnel(Long idLignePenalitePersonnel) {
        this.idLignePenalitePersonnel = idLignePenalitePersonnel;
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

    public EvaluationPenalitePersonnel getIdEvaluationPenalitePersonnel() {
        return idEvaluationPenalitePersonnel;
    }

    public void setIdEvaluationPenalitePersonnel(EvaluationPenalitePersonnel idEvaluationPenalitePersonnel) {
        this.idEvaluationPenalitePersonnel = idEvaluationPenalitePersonnel;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.idLignePenalitePersonnel);
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
        final LignePenalitePersonnel other = (LignePenalitePersonnel) obj;
        if (!Objects.equals(this.idLignePenalitePersonnel, other.idLignePenalitePersonnel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LignePenalitePersonnel{" + "idLignePenalitePersonnel=" + idLignePenalitePersonnel + '}';
    }

}
