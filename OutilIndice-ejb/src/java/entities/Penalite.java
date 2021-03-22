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
import javax.persistence.Id;

/**
 *
 * @author USER
 */
@Entity
public class Penalite implements Serializable {

    @Id
    @Basic(optional = false)
    private Integer idpenalite;
    private String nom;
    @Column(length = 300)
    private String detail;
    @Column(length = 20)
    private String code;
    private int pourcentage;
    private boolean personnel;
    private boolean service;

    public Penalite() {
    }

    public Penalite(Integer idpenalite) {
        this.idpenalite = idpenalite;
    }

    public Integer getIdpenalite() {
        return idpenalite;
    }

    public void setIdpenalite(Integer idpenalite) {
        this.idpenalite = idpenalite;
    }

    public String getNom() {
        return nom;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isPersonnel() {
        return personnel;
    }

    public void setPersonnel(boolean personnel) {
        this.personnel = personnel;
    }

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.idpenalite);
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
        final Penalite other = (Penalite) obj;
        if (!Objects.equals(this.idpenalite, other.idpenalite)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Penalite{" + "idpenalite=" + idpenalite + '}';
    }

}
