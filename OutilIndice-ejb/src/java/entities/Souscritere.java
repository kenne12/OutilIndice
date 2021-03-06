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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Souscritere.findAll", query = "SELECT s FROM Souscritere s"),
    @NamedQuery(name = "Souscritere.findByIdsouscritere", query = "SELECT s FROM Souscritere s WHERE s.idsouscritere = :idsouscritere"),
    @NamedQuery(name = "Souscritere.findByNom", query = "SELECT s FROM Souscritere s WHERE s.nom = :nom"),
    @NamedQuery(name = "Souscritere.findByCode", query = "SELECT s FROM Souscritere s WHERE s.code = :code"),
    @NamedQuery(name = "Souscritere.findByDetail", query = "SELECT s FROM Souscritere s WHERE s.detail = :detail")})
public class Souscritere implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer idsouscritere;
    private String nom;
    @Size(max = 15)
    private String code;
    private String detail;

    private int pointmax;
    @JoinColumn(name = "idcritere", referencedColumnName = "idcritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Critere idcritere;

    @JoinColumn(name = "idrubriquesc", referencedColumnName = "idrubriquesc")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rubriquesc idrubriquesc;

    @OneToMany(mappedBy = "idsouscritere", fetch = FetchType.LAZY)
    private Collection<Detailsc> detailscCollection;
    @OneToMany(mappedBy = "idsouscritere", fetch = FetchType.LAZY)
    private Collection<Souscritereservice> souscritereserviceCollection;

    @OneToMany(mappedBy = "idsouscritere", fetch = FetchType.LAZY)
    private Collection<Elementreponse> elementreponseCollection;

    public Souscritere() {
    }

    public Souscritere(Integer idsouscritere) {
        this.idsouscritere = idsouscritere;
    }

    public Integer getIdsouscritere() {
        return idsouscritere;
    }

    public void setIdsouscritere(Integer idsouscritere) {
        this.idsouscritere = idsouscritere;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPointmax() {
        return pointmax;
    }

    public void setPointmax(int pointmax) {
        this.pointmax = pointmax;
    }

    public Critere getIdcritere() {
        return idcritere;
    }

    public void setIdcritere(Critere idcritere) {
        this.idcritere = idcritere;
    }

    @XmlTransient
    public Collection<Detailsc> getDetailscCollection() {
        return detailscCollection;
    }

    public void setDetailscCollection(Collection<Detailsc> detailscCollection) {
        this.detailscCollection = detailscCollection;
    }

    @XmlTransient
    public Collection<Souscritereservice> getSouscritereserviceCollection() {
        return souscritereserviceCollection;
    }

    public void setSouscritereserviceCollection(Collection<Souscritereservice> souscritereserviceCollection) {
        this.souscritereserviceCollection = souscritereserviceCollection;
    }

    public Collection<Elementreponse> getElementreponseCollection() {
        return elementreponseCollection;
    }

    public void setElementreponseCollection(Collection<Elementreponse> elementreponseCollection) {
        this.elementreponseCollection = elementreponseCollection;
    }

    public Rubriquesc getIdrubriquesc() {
        return idrubriquesc;
    }

    public void setIdrubriquesc(Rubriquesc idrubriquesc) {
        this.idrubriquesc = idrubriquesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsouscritere != null ? idsouscritere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Souscritere)) {
            return false;
        }
        Souscritere other = (Souscritere) object;
        if ((this.idsouscritere == null && other.idsouscritere != null) || (this.idsouscritere != null && !this.idsouscritere.equals(other.idsouscritere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Souscritere[ idsouscritere=" + idsouscritere + " ]";
    }

}
