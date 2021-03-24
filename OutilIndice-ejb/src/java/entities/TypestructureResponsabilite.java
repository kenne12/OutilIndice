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
@Table(name = "typestructureresponsabilite")
public class TypestructureResponsabilite implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idtypestructureresponsabilite")
    private Integer idTypestructureResponsabilite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    private Typestructure typestructure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idresponsabilite", referencedColumnName = "ididresponsabilite")
    private Responsabilite responsabilite;

    public TypestructureResponsabilite() {
    }

    public TypestructureResponsabilite(Integer idTypestructureResponsabilite) {
        this.idTypestructureResponsabilite = idTypestructureResponsabilite;
    }

    public TypestructureResponsabilite(Integer idTypestructureResponsabilite, Typestructure typestructure, Responsabilite responsabilite) {
        this.idTypestructureResponsabilite = idTypestructureResponsabilite;
        this.typestructure = typestructure;
        this.responsabilite = responsabilite;
    }

    public Integer getIdTypestructureResponsabilite() {
        return idTypestructureResponsabilite;
    }

    public void setIdTypestructureResponsabilite(Integer idTypestructureResponsabilite) {
        this.idTypestructureResponsabilite = idTypestructureResponsabilite;
    }

    public Typestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }

    public Responsabilite getResponsabilite() {
        return responsabilite;
    }

    public void setResponsabilite(Responsabilite responsabilite) {
        this.responsabilite = responsabilite;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.idTypestructureResponsabilite);
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
        final TypestructureResponsabilite other = (TypestructureResponsabilite) obj;
        if (!Objects.equals(this.idTypestructureResponsabilite, other.idTypestructureResponsabilite)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypestructureResponsabilite{" + "idTypestructureResponsabilite=" + idTypestructureResponsabilite + '}';
    }

}
