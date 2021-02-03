/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
public class Evaluationrqntifdept implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idevaluationrqntifdept;
    private double cible;
    private double realisation;
    private double ratio;
    @JoinColumn(name = "idpersonnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel idpersonnel;
    @JoinColumn(name = "idcible", referencedColumnName = "idcible")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cible idcible;
    @JoinColumn(name = "idnote", referencedColumnName = "idnote")
    @ManyToOne(fetch = FetchType.LAZY)
    private Note idnote;

    public Evaluationrqntifdept() {
    }

    public Evaluationrqntifdept(Long idevaluationrqntifdept) {
        this.idevaluationrqntifdept = idevaluationrqntifdept;
    }

    public Long getIdevaluationrqntifdept() {
        return idevaluationrqntifdept;
    }

    public void setIdevaluationrqntifdept(Long idevaluationrqntifdept) {
        this.idevaluationrqntifdept = idevaluationrqntifdept;
    }

    public double getCible() {
        return cible;
    }

    public void setCible(double cible) {
        this.cible = cible;
    }

    public double getRealisation() {
        return realisation;
    }

    public void setRealisation(double realisation) {
        this.realisation = realisation;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
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
        int hash = 7;
        hash = 41 * hash + (int) (this.idevaluationrqntifdept ^ (this.idevaluationrqntifdept >>> 32));
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
        final Evaluationrqntifdept other = (Evaluationrqntifdept) obj;
        if (this.idevaluationrqntifdept != other.idevaluationrqntifdept) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evaluationrqntifdept{" + "idevaluationrqntifdept=" + idevaluationrqntifdept + '}';
    }

}
