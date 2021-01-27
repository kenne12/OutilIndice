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
@Table(name = "lignepenalitedept")
public class LignePenaliteDept implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idlignepenalitedept;
    private int valeur;
    private double pourcentage;
    private boolean etat;
    @JoinColumn(name = "idpenalite", referencedColumnName = "idpenalite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Penalite idpenalite;
    @JoinColumn(name = "idevaluationpenalitedept", referencedColumnName = "idevaluationpenalitedept")
    @ManyToOne(fetch = FetchType.LAZY)
    private EvaluationPenaliteDept idevaluationpenalitedept;

    public LignePenaliteDept() {
    }

    public LignePenaliteDept(Long idlignepenalitedept) {
        this.idlignepenalitedept = idlignepenalitedept;
    }

    public Long getIdlignepenalitedept() {
        return idlignepenalitedept;
    }

    public void setIdlignepenalitedept(Long idlignepenalitedept) {
        this.idlignepenalitedept = idlignepenalitedept;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Penalite getIdpenalite() {
        return idpenalite;
    }

    public void setIdpenalite(Penalite idpenalite) {
        this.idpenalite = idpenalite;
    }

    public EvaluationPenaliteDept getIdevaluationpenalitedept() {
        return idevaluationpenalitedept;
    }

    public void setIdevaluationpenalitedept(EvaluationPenaliteDept idevaluationpenalitedept) {
        this.idevaluationpenalitedept = idevaluationpenalitedept;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idlignepenalitedept);
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
        final LignePenaliteDept other = (LignePenaliteDept) obj;
        if (!Objects.equals(this.idlignepenalitedept, other.idlignepenalitedept)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LignePenaliteDept{" + "idlignepenalitedept=" + idlignepenalitedept + '}';
    }

}
