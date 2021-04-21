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
@Table(name = "evaluationbonusrdeptpersonnel")
public class EvaluationBonusRDeptPersonnel implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idevaluationbonusrdeptpersonnel;
    private double point;
    @Column(name = "point_max")
    private double pointMax;
    @JoinColumn(name = "idpersonnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel idpersonnel;
    @JoinColumn(name = "idcible", referencedColumnName = "idcible")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cible idcible;
    @JoinColumn(name = "idnote", referencedColumnName = "idnote")
    @ManyToOne(fetch = FetchType.LAZY)
    private Note idnote;

    public EvaluationBonusRDeptPersonnel() {
    }

    public EvaluationBonusRDeptPersonnel(Long idevaluationbonusrdeptpersonnel) {
        this.idevaluationbonusrdeptpersonnel = idevaluationbonusrdeptpersonnel;
    }

    public Long getIdevaluationbonusrdeptpersonnel() {
        return idevaluationbonusrdeptpersonnel;
    }

    public void setIdevaluationbonusrdeptpersonnel(Long idevaluationbonusrdeptpersonnel) {
        this.idevaluationbonusrdeptpersonnel = idevaluationbonusrdeptpersonnel;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getPointMax() {
        return pointMax;
    }

    public void setPointMax(double pointMax) {
        this.pointMax = pointMax;
    }

    public Personnel getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(Personnel idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public Cible getIdcible() {
        return idcible;
    }

    public void setIdcible(Cible idcible) {
        this.idcible = idcible;
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
        hash = 73 * hash + Objects.hashCode(this.idevaluationbonusrdeptpersonnel);
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
        final EvaluationBonusRDeptPersonnel other = (EvaluationBonusRDeptPersonnel) obj;
        if (!Objects.equals(this.idevaluationbonusrdeptpersonnel, other.idevaluationbonusrdeptpersonnel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EvaluationBonusRDeptPersonnel{" + "idevaluationbonusrdeptpersonnel=" + idevaluationbonusrdeptpersonnel + '}';
    }

}
