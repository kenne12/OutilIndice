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
@Table(name = "lignepenalitepersonnel")
public class LignePenalitePersonnel implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idlignepenalitepersonnel;

    private int valeur;
    private int cible;
    private double ratio;
    private boolean etat;

    @JoinColumn(name = "idpenalite", referencedColumnName = "idpenalite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Penalite idPenalite;

    @JoinColumn(name = "idEvaluationPenalitePersonnel", referencedColumnName = "idevaluationpenalitepersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private EvaluationPenalitePersonnel idEvaluationPenalitePersonnel;

    public LignePenalitePersonnel() {
    }

    public LignePenalitePersonnel(Long idlignepenalitepersonnel) {
        this.idlignepenalitepersonnel = idlignepenalitepersonnel;
    }

    public Long getIdlignepenalitepersonnel() {
        return idlignepenalitepersonnel;
    }

    public void setIdlignepenalitepersonnel(Long idlignepenalitepersonnel) {
        this.idlignepenalitepersonnel = idlignepenalitepersonnel;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
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

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Penalite getIdPenalite() {
        return idPenalite;
    }

    public void setIdPenalite(Penalite idPenalite) {
        this.idPenalite = idPenalite;
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
        hash = 41 * hash + Objects.hashCode(this.idlignepenalitepersonnel);
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
        if (!Objects.equals(this.idlignepenalitepersonnel, other.idlignepenalitepersonnel)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LignePenalitePersonnel{" + "idLignePenalitePersonnel=" + idlignepenalitepersonnel + '}';
    }

}
