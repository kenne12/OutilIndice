/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sousperiode.findAll", query = "SELECT s FROM Sousperiode s"),
    @NamedQuery(name = "Sousperiode.findByIdsousperiode", query = "SELECT s FROM Sousperiode s WHERE s.idsousperiode = :idsousperiode"),
    @NamedQuery(name = "Sousperiode.findByNom", query = "SELECT s FROM Sousperiode s WHERE s.nom = :nom"),
    @NamedQuery(name = "Sousperiode.findByCode", query = "SELECT s FROM Sousperiode s WHERE s.code = :code"),
    @NamedQuery(name = "Sousperiode.findByNumero", query = "SELECT s FROM Sousperiode s WHERE s.numero = :numero")})
public class Sousperiode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idsousperiode;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    private Integer numero;
    @OneToMany(mappedBy = "idsousperiode", fetch = FetchType.LAZY)
    private Collection<Note> noteCollection;
    @OneToMany(mappedBy = "idsousperiode", fetch = FetchType.LAZY)
    private Collection<Evaluationpersonnel> evaluationpersonnelCollection;
    @OneToMany(mappedBy = "idsousperiode", fetch = FetchType.LAZY)
    private Collection<Depense> depenseCollection;
    @OneToMany(mappedBy = "idsousperiode", fetch = FetchType.LAZY)
    private Collection<Recette> recetteCollection;

    public Sousperiode() {
    }

    public Sousperiode(Integer idsousperiode) {
        this.idsousperiode = idsousperiode;
    }

    public Integer getIdsousperiode() {
        return idsousperiode;
    }

    public void setIdsousperiode(Integer idsousperiode) {
        this.idsousperiode = idsousperiode;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @XmlTransient
    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    @XmlTransient
    public Collection<Evaluationpersonnel> getEvaluationpersonnelCollection() {
        return evaluationpersonnelCollection;
    }

    public void setEvaluationpersonnelCollection(Collection<Evaluationpersonnel> evaluationpersonnelCollection) {
        this.evaluationpersonnelCollection = evaluationpersonnelCollection;
    }

    @XmlTransient
    public Collection<Depense> getDepenseCollection() {
        return depenseCollection;
    }

    public void setDepenseCollection(Collection<Depense> depenseCollection) {
        this.depenseCollection = depenseCollection;
    }

    @XmlTransient
    public Collection<Recette> getRecetteCollection() {
        return recetteCollection;
    }

    public void setRecetteCollection(Collection<Recette> recetteCollection) {
        this.recetteCollection = recetteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsousperiode != null ? idsousperiode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sousperiode)) {
            return false;
        }
        Sousperiode other = (Sousperiode) object;
        if ((this.idsousperiode == null && other.idsousperiode != null) || (this.idsousperiode != null && !this.idsousperiode.equals(other.idsousperiode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sousperiode[ idsousperiode=" + idsousperiode + " ]";
    }
    
}
