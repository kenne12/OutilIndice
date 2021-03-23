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
import javax.persistence.Table;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "typesousperiode")
public class TypeSousPeriode implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idtypesousperiode")
    private Integer idTypeSousperiode;
    @Column(length = 20)
    private String code;

    @Column(length = 50)
    private String nom;

    public TypeSousPeriode() {
    }

    public TypeSousPeriode(Integer idTypeSousperiode) {
        this.idTypeSousperiode = idTypeSousperiode;
    }

    public Integer getIdTypeSousperiode() {
        return idTypeSousperiode;
    }

    public void setIdTypeSousperiode(Integer idTypeSousperiode) {
        this.idTypeSousperiode = idTypeSousperiode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.idTypeSousperiode);
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
        final TypeSousPeriode other = (TypeSousPeriode) obj;
        if (!Objects.equals(this.idTypeSousperiode, other.idTypeSousperiode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypeSousPeriode{" + "idTypeSousperiode=" + idTypeSousperiode + ", code=" + code + ", nom=" + nom + '}';
    }

}
