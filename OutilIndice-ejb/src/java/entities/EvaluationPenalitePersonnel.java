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
    @JoinColumn(name = "idnote", referencedColumnName = "idnote")
    @ManyToOne(fetch = FetchType.LAZY)
    private Note idnote;

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

    public Note getIdnote() {
        return idnote;
    }

    public void setIdnote(Note idnote) {
        this.idnote = idnote;
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
