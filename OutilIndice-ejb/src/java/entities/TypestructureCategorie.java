/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
@Table(name = "typestructurecategorie")
public class TypestructureCategorie implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idtypestructurecategorie")
    private Integer idTypestructureCategorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    private Typestructure typestructure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    private Categorie categorie;

    public TypestructureCategorie() {
    }

    public TypestructureCategorie(Integer idTypestructureCategorie) {
        this.idTypestructureCategorie = idTypestructureCategorie;
    }

    public TypestructureCategorie(Integer idTypestructureCategorie, Typestructure typestructure, Categorie categorie) {
        this.idTypestructureCategorie = idTypestructureCategorie;
        this.typestructure = typestructure;
        this.categorie = categorie;
    }

    public Integer getIdTypestructureCategorie() {
        return idTypestructureCategorie;
    }

    public void setIdTypestructureCategorie(Integer idTypestructureCategorie) {
        this.idTypestructureCategorie = idTypestructureCategorie;
    }

    public Typestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

}
