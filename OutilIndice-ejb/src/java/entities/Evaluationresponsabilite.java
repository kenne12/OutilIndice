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
    private double point;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idsousperiode", referencedColumnName = "idsousperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousperiode idsousperiode;
    @JoinColumn(name = "idpersonnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel idpersonnel;
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Critere idcritere;

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

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
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

    public Personnel getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(Personnel idpersonnel) {
        this.idpersonnel = idpersonnel;
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
