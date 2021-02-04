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
public class Evaluationresponsabilite implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    private Long idevaluationresponsabilite;
    @Column(name = "pointmax")
    private double pointMax;
    private double point;
    private double ratio;
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Critere idcritere;
    @JoinColumn(name = "idnote", referencedColumnName = "idnote")
    @ManyToOne(fetch = FetchType.LAZY)
    private Note idnote;

    public Evaluationresponsabilite() {
    }

    public Evaluationresponsabilite(Long idevaluationresponsabilite) {
        this.idevaluationresponsabilite = idevaluationresponsabilite;
    }

    public Long getIdevaluationresponsabilite() {
        return idevaluationresponsabilite;
    }

    public void setIdevaluationresponsabilite(Long idevaluationresponsabilite) {
        this.idevaluationresponsabilite = idevaluationresponsabilite;
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
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idevaluationresponsabilite);
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
        final Evaluationresponsabilite other = (Evaluationresponsabilite) obj;
        if (!Objects.equals(this.idevaluationresponsabilite, other.idevaluationresponsabilite)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evaluationresponsabilite{" + "idevaluationresponsabilite=" + idevaluationresponsabilite + '}';
    }

}
