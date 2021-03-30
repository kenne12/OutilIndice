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
@Table(name = "typestructuresousrubriquedepense")
public class TypeStructureSousRubriqueDepense implements Serializable {

    @Id
    @Basic(optional = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    private Typestructure typestructure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsousrubriquedepense", referencedColumnName = "idsousrubriquedepense")
    private Sousrubriquedepense sousrubriquedepense;

    public TypeStructureSousRubriqueDepense() {
    }

    public TypeStructureSousRubriqueDepense(Integer id) {
        this.id = id;
    }

    public TypeStructureSousRubriqueDepense(Integer id, Typestructure typestructure, Sousrubriquedepense sousrubriquedepense) {
        this.id = id;
        this.typestructure = typestructure;
        this.sousrubriquedepense = sousrubriquedepense;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Typestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }

    public Sousrubriquedepense getSousrubriquedepense() {
        return sousrubriquedepense;
    }

    public void setSousrubriquedepense(Sousrubriquedepense sousrubriquedepense) {
        this.sousrubriquedepense = sousrubriquedepense;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final TypeStructureSousRubriqueDepense other = (TypeStructureSousRubriqueDepense) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SousRubriqueDepenseTypeStructure{" + "id=" + id + '}';
    }

}
