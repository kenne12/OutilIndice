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
import javax.validation.constraints.NotNull;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "effectifresponsabilite")
public class EffectifResponsabilite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "ideffectifresponsabilite")
    private Long idEffectifResponsabilite;

    private int nombre;

    @JoinColumn(name = "idresponsabilite", referencedColumnName = "idresponsabilite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Responsabilite responsabilite;

    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure structure;

    public EffectifResponsabilite() {
    }

    public EffectifResponsabilite(Long idEffectifResponsabilite) {
        this.idEffectifResponsabilite = idEffectifResponsabilite;
    }

    public Long getIdEffectifResponsabilite() {
        return idEffectifResponsabilite;
    }

    public void setIdEffectifResponsabilite(Long idEffectifResponsabilite) {
        this.idEffectifResponsabilite = idEffectifResponsabilite;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Responsabilite getResponsabilite() {
        return responsabilite;
    }

    public void setResponsabilite(Responsabilite responsabilite) {
        this.responsabilite = responsabilite;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idEffectifResponsabilite);
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
        final EffectifResponsabilite other = (EffectifResponsabilite) obj;
        if (!Objects.equals(this.idEffectifResponsabilite, other.idEffectifResponsabilite)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EffectifResponsabilite{" + "idEffectifResponsabilite=" + idEffectifResponsabilite + ", nombre=" + nombre + ", responsabilite=" + responsabilite + ", structure=" + structure + '}';
    }

}
