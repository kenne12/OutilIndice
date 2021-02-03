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
public class Evaluationheuresupp implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    private Long idevaluationheuresupp;
    private double coefnuit;
    private double coefjour;
    private int nbheurejour;
    private int nbheurenuit;
    private double pointjour;
    private double pointnuit;
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
    @JoinColumn(name = "idnote", referencedColumnName = "idnote")
    @ManyToOne(fetch = FetchType.LAZY)
    private Note idnote;

    public Evaluationheuresupp() {
    }

    public Evaluationheuresupp(Long idevaluationheuresupp) {
        this.idevaluationheuresupp = idevaluationheuresupp;
    }

    public Long getIdevaluationheuresupp() {
        return idevaluationheuresupp;
    }

    public void setIdevaluationheuresupp(Long idevaluationheuresupp) {
        this.idevaluationheuresupp = idevaluationheuresupp;
    }

    public double getCoefnuit() {
        return coefnuit;
    }

    public void setCoefnuit(double coefnuit) {
        this.coefnuit = coefnuit;
    }

    public double getCoefjour() {
        return coefjour;
    }

    public void setCoefjour(double coefjour) {
        this.coefjour = coefjour;
    }

    public int getNbheurejour() {
        return nbheurejour;
    }

    public void setNbheurejour(int nbheurejour) {
        this.nbheurejour = nbheurejour;
    }

    public int getNbheurenuit() {
        return nbheurenuit;
    }

    public void setNbheurenuit(int nbheurenuit) {
        this.nbheurenuit = nbheurenuit;
    }

    public double getPointjour() {
        return pointjour;
    }

    public void setPointjour(double pointjour) {
        this.pointjour = pointjour;
    }

    public double getPointnuit() {
        return pointnuit;
    }

    public void setPointnuit(double pointnuit) {
        this.pointnuit = pointnuit;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Critere getIdcritere() {
        return idcritere;
    }

    public void setIdcritere(Critere idcritere) {
        this.idcritere = idcritere;
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

    public Note getIdnote() {
        return idnote;
    }

    public void setIdnote(Note idnote) {
        this.idnote = idnote;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idevaluationheuresupp);
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
        final Evaluationheuresupp other = (Evaluationheuresupp) obj;
        if (!Objects.equals(this.idevaluationheuresupp, other.idevaluationheuresupp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evaluationheuresupp{" + "idevaluationheuresupp=" + idevaluationheuresupp + ", coefjour=" + coefjour + '}';
    }

}
