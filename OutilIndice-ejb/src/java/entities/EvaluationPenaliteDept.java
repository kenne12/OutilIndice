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
@Table(name = "evaluationpenalitedept")
public class EvaluationPenaliteDept implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idevaluationpenalitedept;
    private double valeur;
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    @ManyToOne(fetch = FetchType.LAZY)
    private Service idservice;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idsousperiode", referencedColumnName = "idsousperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousperiode idsousperiode;

    public EvaluationPenaliteDept() {
    }

    public EvaluationPenaliteDept(Long idevaluationpenalitedept) {
        this.idevaluationpenalitedept = idevaluationpenalitedept;
    }

    public Long getIdevaluationpenalitedept() {
        return idevaluationpenalitedept;
    }

    public void setIdevaluationpenalitedept(Long idevaluationpenalitedept) {
        this.idevaluationpenalitedept = idevaluationpenalitedept;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.idevaluationpenalitedept);
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
        final EvaluationPenaliteDept other = (EvaluationPenaliteDept) obj;
        if (!Objects.equals(this.idevaluationpenalitedept, other.idevaluationpenalitedept)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EvaluationPenaliteDept{" + "idevaluationpenalitedept=" + idevaluationpenalitedept + '}';
    }

}
