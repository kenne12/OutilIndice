/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailsc.findAll", query = "SELECT d FROM Detailsc d"),
    @NamedQuery(name = "Detailsc.findByIddetailsc", query = "SELECT d FROM Detailsc d WHERE d.iddetailsc = :iddetailsc"),
    @NamedQuery(name = "Detailsc.findByPointMax", query = "SELECT d FROM Detailsc d WHERE d.pointMax = :pointMax"),
    @NamedQuery(name = "Detailsc.findByDetail", query = "SELECT d FROM Detailsc d WHERE d.detail = :detail")})
public class Detailsc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long iddetailsc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "point_max")
    private Double pointMax;
    private String detail;
    @OneToMany(mappedBy = "iddetailsc", fetch = FetchType.LAZY)
    private Collection<Evaluationpersonnel> evaluationpersonnelCollection;
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categorie idcategorie;
    @JoinColumn(name = "idsouscritere", referencedColumnName = "idsouscritere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Souscritere idsouscritere;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Structure idstructure;

    public Detailsc() {
    }

    public Detailsc(Long iddetailsc) {
        this.iddetailsc = iddetailsc;
    }

    public Long getIddetailsc() {
        return iddetailsc;
    }

    public void setIddetailsc(Long iddetailsc) {
        this.iddetailsc = iddetailsc;
    }

    public Double getPointMax() {
        return pointMax;
    }

    public void setPointMax(Double pointMax) {
        this.pointMax = pointMax;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @XmlTransient
    public Collection<Evaluationpersonnel> getEvaluationpersonnelCollection() {
        return evaluationpersonnelCollection;
    }

    public void setEvaluationpersonnelCollection(Collection<Evaluationpersonnel> evaluationpersonnelCollection) {
        this.evaluationpersonnelCollection = evaluationpersonnelCollection;
    }

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
    }

    public Souscritere getIdsouscritere() {
        return idsouscritere;
    }

    public void setIdsouscritere(Souscritere idsouscritere) {
        this.idsouscritere = idsouscritere;
    }

    public Structure getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Structure idstructure) {
        this.idstructure = idstructure;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetailsc != null ? iddetailsc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailsc)) {
            return false;
        }
        Detailsc other = (Detailsc) object;
        if ((this.iddetailsc == null && other.iddetailsc != null) || (this.iddetailsc != null && !this.iddetailsc.equals(other.iddetailsc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Detailsc[ iddetailsc=" + iddetailsc + " ]";
    }

}
