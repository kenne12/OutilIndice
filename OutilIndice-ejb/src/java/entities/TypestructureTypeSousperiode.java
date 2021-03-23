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
@Table(name = "typestructuretypesousperiode")
public class TypestructureTypeSousperiode implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idtypestructuretypesousperiode")
    private Integer idTypestructureTypesousperiode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    private Typestructure typestructure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtypesousperiode", referencedColumnName = "idtypesousperiode")
    private TypeSousPeriode typeSousPeriode;

    public TypestructureTypeSousperiode() {
    }

    public TypestructureTypeSousperiode(Integer idTypestructureTypesousperiode) {
        this.idTypestructureTypesousperiode = idTypestructureTypesousperiode;
    }

    public TypestructureTypeSousperiode(Integer idTypestructureTypesousperiode, Typestructure typestructure, TypeSousPeriode typeSousPeriode) {
        this.idTypestructureTypesousperiode = idTypestructureTypesousperiode;
        this.typestructure = typestructure;
        this.typeSousPeriode = typeSousPeriode;
    }

    public Integer getIdTypestructureTypesousperiode() {
        return idTypestructureTypesousperiode;
    }

    public void setIdTypestructureTypesousperiode(Integer idTypestructureTypesousperiode) {
        this.idTypestructureTypesousperiode = idTypestructureTypesousperiode;
    }

    public Typestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }

    public TypeSousPeriode getTypeSousPeriode() {
        return typeSousPeriode;
    }

    public void setTypeSousPeriode(TypeSousPeriode typeSousPeriode) {
        this.typeSousPeriode = typeSousPeriode;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.idTypestructureTypesousperiode);
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
        final TypestructureTypeSousperiode other = (TypestructureTypeSousperiode) obj;
        if (!Objects.equals(this.idTypestructureTypesousperiode, other.idTypestructureTypesousperiode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypestructureTypeSousperiode{" + "idTypestructureTypesousperiode=" + idTypestructureTypesousperiode + '}';
    }

}
