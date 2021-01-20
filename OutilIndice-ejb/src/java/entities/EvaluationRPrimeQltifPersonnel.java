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
@Table(name = "evaluationrprimeqltifpersonnel")
public class EvaluationRPrimeQltifPersonnel implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idevaluationrprimeqltifpersonnel;
    private double point;
    @JoinColumn(name = "idpersonnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel idpersonnel;
    @JoinColumn(name = "idevaluationrprimeqltifdept", referencedColumnName = "idevaluationrprimeqltifdept")
    @ManyToOne(fetch = FetchType.LAZY)
    private EvaluationRPrimeQltifDept idevaluationrprimeqltifdept;

    public EvaluationRPrimeQltifPersonnel() {
    }

    public EvaluationRPrimeQltifPersonnel(Long idevaluationrprimeqltifpersonnel) {
        this.idevaluationrprimeqltifpersonnel = idevaluationrprimeqltifpersonnel;
    }

    public Long getIdevaluationrprimeqltifpersonnel() {
        return idevaluationrprimeqltifpersonnel;
    }

    public void setIdevaluationrprimeqltifpersonnel(Long idevaluationrprimeqltifpersonnel) {
        this.idevaluationrprimeqltifpersonnel = idevaluationrprimeqltifpersonnel;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public EvaluationRPrimeQltifDept getIdevaluationrprimeqltifdept() {
        return idevaluationrprimeqltifdept;
    }

    public void setIdevaluationrprimeqltifdept(EvaluationRPrimeQltifDept idevaluationrprimeqltifdept) {
        this.idevaluationrprimeqltifdept = idevaluationrprimeqltifdept;
    }

    public Personnel getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(Personnel idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idevaluationrprimeqltifpersonnel);
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
        final EvaluationRPrimeQltifPersonnel other = (EvaluationRPrimeQltifPersonnel) obj;
        if (!Objects.equals(this.idevaluationrprimeqltifpersonnel, other.idevaluationrprimeqltifpersonnel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EvaluationRPrimeQltifPersonnel{" + "idevaluationrprimeqltifpersonnel=" + idevaluationrprimeqltifpersonnel + '}';
    }

}
