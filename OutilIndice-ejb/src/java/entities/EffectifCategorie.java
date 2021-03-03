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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "effectifcategorie")
public class EffectifCategorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "ideffectifcategorie")
    private Long idEffectifCategorie;

    private int nombre;

    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categorie categorie;

    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure structure;

    public EffectifCategorie() {
    }

    public EffectifCategorie(Long idEffectifCategorie) {
        this.idEffectifCategorie = idEffectifCategorie;
    }

    public Long getIdEffectifCategorie() {
        return idEffectifCategorie;
    }

    public void setIdEffectifCategorie(Long idEffectifCategorie) {
        this.idEffectifCategorie = idEffectifCategorie;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idEffectifCategorie);
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
        final EffectifCategorie other = (EffectifCategorie) obj;
        if (!Objects.equals(this.idEffectifCategorie, other.idEffectifCategorie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EffectifCategorie{" + "idEffectifCategorie=" + idEffectifCategorie + '}';
    }

}
