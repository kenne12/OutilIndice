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
import javax.validation.constraints.NotNull;

/**
 *
 * @author USER
 */
@Entity
public class Evaluationbonuspp implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    private Long idevaluationbonuspp;
    @Column(name = "pointmax")
    private double pointMax;
    private double ratio;
    private double point;
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Critere idcritere;
    @JoinColumn(name = "idnote", referencedColumnName = "idnote")
    @ManyToOne(fetch = FetchType.LAZY)
    private Note idnote;

    public Evaluationbonuspp() {
    }

    public Evaluationbonuspp(Long idevaluationbonuspp) {
        this.idevaluationbonuspp = idevaluationbonuspp;
    }

    public Long getIdevaluationbonuspp() {
        return idevaluationbonuspp;
    }

    public void setIdevaluationbonuspp(Long idevaluationbonuspp) {
        this.idevaluationbonuspp = idevaluationbonuspp;
    }

    public double getPointMax() {
        return pointMax;
    }

    public void setPointMax(double pointMax) {
        this.pointMax = pointMax;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public Critere getIdcritere() {
        return idcritere;
    }

    public void setIdcritere(Critere idcritere) {
        this.idcritere = idcritere;
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
        hash = 79 * hash + Objects.hashCode(this.idevaluationbonuspp);
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
        final Evaluationbonuspp other = (Evaluationbonuspp) obj;
        if (!Objects.equals(this.idevaluationbonuspp, other.idevaluationbonuspp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evaluationbonuspp{" + "idevaluationbonuspp=" + idevaluationbonuspp + '}';
    }

}
