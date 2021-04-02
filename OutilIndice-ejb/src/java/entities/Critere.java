/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    @NamedQuery(name = "Critere.findAll", query = "SELECT c FROM Critere c"),
    @NamedQuery(name = "Critere.findByIdcritere", query = "SELECT c FROM Critere c WHERE c.idcritere = :idcritere"),
    @NamedQuery(name = "Critere.findByNom", query = "SELECT c FROM Critere c WHERE c.nom = :nom"),
    @NamedQuery(name = "Critere.findByCode", query = "SELECT c FROM Critere c WHERE c.code = :code"),
    @NamedQuery(name = "Critere.findByPoids", query = "SELECT c FROM Critere c WHERE c.poids = :poids")})
public class Critere implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idcritere;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double poids;
    @Column(name = "pointmax")
    private Integer pointMax;
    @Column(name = "scoremoyen")
    private Integer scoreMoyen;
    private Double resultat;
    @Column(name = "valeurinferieur")
    private int valeurInferieur;
    @Column(name = "valeursuperieur")
    private int valeurSuperieur;
    private boolean workflow;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "critere", fetch = FetchType.LAZY)
    private Collection<Criterestructure> criterestructureCollection;
    @OneToMany(mappedBy = "idcritere", fetch = FetchType.LAZY)
    private Collection<Souscritere> souscritereCollection;

    public Critere() {
    }

    public Critere(Integer idcritere) {
        this.idcritere = idcritere;
    }

    public Integer getIdcritere() {
        return idcritere;
    }

    public void setIdcritere(Integer idcritere) {
        this.idcritere = idcritere;
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

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Integer getPointMax() {
        return pointMax;
    }

    public void setPointMax(Integer pointMax) {
        this.pointMax = pointMax;
    }

    public Integer getScoreMoyen() {
        return scoreMoyen;
    }

    public void setScoreMoyen(Integer scoreMoyen) {
        this.scoreMoyen = scoreMoyen;
    }

    public Double getResultat() {
        return resultat;
    }

    public void setResultat(Double resultat) {
        this.resultat = resultat;
    }

    public int getValeurInferieur() {
        return valeurInferieur;
    }

    public void setValeurInferieur(int valeurInferieur) {
        this.valeurInferieur = valeurInferieur;
    }

    public int getValeurSuperieur() {
        return valeurSuperieur;
    }

    public void setValeurSuperieur(int valeurSuperieur) {
        this.valeurSuperieur = valeurSuperieur;
    }

    public boolean isWorkflow() {
        return workflow;
    }

    public void setWorkflow(boolean workflow) {
        this.workflow = workflow;
    }

    @XmlTransient
    public Collection<Criterestructure> getCriterestructureCollection() {
        return criterestructureCollection;
    }

    public void setCriterestructureCollection(Collection<Criterestructure> criterestructureCollection) {
        this.criterestructureCollection = criterestructureCollection;
    }

    @XmlTransient
    public Collection<Souscritere> getSouscritereCollection() {
        return souscritereCollection;
    }

    public void setSouscritereCollection(Collection<Souscritere> souscritereCollection) {
        this.souscritereCollection = souscritereCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcritere != null ? idcritere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Critere)) {
            return false;
        }
        Critere other = (Critere) object;
        if ((this.idcritere == null && other.idcritere != null) || (this.idcritere != null && !this.idcritere.equals(other.idcritere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Critere[ idcritere=" + idcritere + " ]";
    }

}
