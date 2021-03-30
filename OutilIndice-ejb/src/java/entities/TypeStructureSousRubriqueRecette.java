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
@Table(name = "typestructuresousrubriquerecette")
public class TypeStructureSousRubriqueRecette implements Serializable {

    @Id
    @Basic(optional = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    private Typestructure typestructure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsousrubriquerecette", referencedColumnName = "idsousrubriquerecette")
    private Sousrubriquerecette sousrubriquerecette;

    public TypeStructureSousRubriqueRecette() {
    }

    public TypeStructureSousRubriqueRecette(Integer id) {
        this.id = id;
    }

    public TypeStructureSousRubriqueRecette(Integer id, Typestructure typestructure, Sousrubriquerecette sousrubriquerecette) {
        this.id = id;
        this.typestructure = typestructure;
        this.sousrubriquerecette = sousrubriquerecette;
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

    public Sousrubriquerecette getSousrubriquerecette() {
        return sousrubriquerecette;
    }

    public void setSousrubriquerecette(Sousrubriquerecette sousrubriquerecette) {
        this.sousrubriquerecette = sousrubriquerecette;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final TypeStructureSousRubriqueRecette other = (TypeStructureSousRubriqueRecette) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SousRubriqueRecetteTypeStructure{" + "id=" + id + '}';
    }

}
