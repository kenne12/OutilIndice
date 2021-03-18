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
@Table(name = "parametragepenalite")
public class ParametragePenalite implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idparametragepenalite")
    private Integer idParametragePenalite;

    private int pourcentage;
    @Column(length = 300)
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpenalite", referencedColumnName = "idpenalite")
    private Penalite penalite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    private Structure structure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere")
    private Critere critere;

    public ParametragePenalite() {
    }

    public ParametragePenalite(Integer idParametragePenalite) {
        this.idParametragePenalite = idParametragePenalite;
    }

    public Integer getIdParametragePenalite() {
        return idParametragePenalite;
    }

    public void setIdParametragePenalite(Integer idParametragePenalite) {
        this.idParametragePenalite = idParametragePenalite;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Penalite getPenalite() {
        return penalite;
    }

    public void setPenalite(Penalite penalite) {
        this.penalite = penalite;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Critere getCritere() {
        return critere;
    }

    public void setCritere(Critere critere) {
        this.critere = critere;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idParametragePenalite);
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
        final ParametragePenalite other = (ParametragePenalite) obj;
        if (!Objects.equals(this.idParametragePenalite, other.idParametragePenalite)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParametragePenalite{" + "idParametragePenalite=" + idParametragePenalite + ", pourcentage=" + pourcentage + '}';
    }

}
