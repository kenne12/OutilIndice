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

/**
 *
 * @author USER
 */
@Entity
public class Critereresponsabilite implements Serializable {

    @Id
    @Basic(optional = false)
    private Long idcritereresponsabilite;
    private double point;
    private boolean responsabilite;

    @JoinColumn(name = "idresponsabilite", referencedColumnName = "idresponsabilite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Responsabilite idresponsabilite;

    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Critere idcritere;

    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public Critereresponsabilite() {
    }

    public Critereresponsabilite(Long idcritereresponsabilite) {
        this.idcritereresponsabilite = idcritereresponsabilite;
    }

    public Long getIdcritereresponsabilite() {
        return idcritereresponsabilite;
    }

    public void setIdcritereresponsabilite(Long idcritereresponsabilite) {
        this.idcritereresponsabilite = idcritereresponsabilite;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public boolean isResponsabilite() {
        return responsabilite;
    }

    public void setResponsabilite(boolean responsabilite) {
        this.responsabilite = responsabilite;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    public Critere getIdcritere() {
        return idcritere;
    }

    public void setIdcritere(Critere idcritere) {
        this.idcritere = idcritere;
    }

    public Responsabilite getIdresponsabilite() {
        return idresponsabilite;
    }

    public void setIdresponsabilite(Responsabilite idresponsabilite) {
        this.idresponsabilite = idresponsabilite;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idcritereresponsabilite);
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
        final Critereresponsabilite other = (Critereresponsabilite) obj;
        if (!Objects.equals(this.idcritereresponsabilite, other.idcritereresponsabilite)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Critereresponsabilite{" + "idcritereresponsabilite=" + idcritereresponsabilite + '}';
    }

}
