/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noteservice.findAll", query = "SELECT n FROM Noteservice n"),
    @NamedQuery(name = "Noteservice.findByIdnoteservice", query = "SELECT n FROM Noteservice n WHERE n.idnoteservice = :idnoteservice"),
    @NamedQuery(name = "Noteservice.findByNote", query = "SELECT n FROM Noteservice n WHERE n.note = :note"),
    @NamedQuery(name = "Noteservice.findByPoids", query = "SELECT n FROM Noteservice n WHERE n.poids = :poids")})
public class Noteservice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idnoteservice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double note;
    private Double poids;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Periode idperiode;
    @JoinColumn(name = "idsousperiode", referencedColumnName = "idsousperiode")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sousperiode idsousperiode;
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    @ManyToOne(fetch = FetchType.LAZY)
    private Service idservice;

    public Noteservice() {
    }

    public Noteservice(Long idnoteservice) {
        this.idnoteservice = idnoteservice;
    }

    public Long getIdnoteservice() {
        return idnoteservice;
    }

    public void setIdnoteservice(Long idnoteservice) {
        this.idnoteservice = idnoteservice;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Periode getIdperiode() {
        return idperiode;
    }

    public void setIdperiode(Periode idperiode) {
        this.idperiode = idperiode;
    }

    public Sousperiode getIdsousperiode() {
        return idsousperiode;
    }

    public void setIdsousperiode(Sousperiode idsousperiode) {
        this.idsousperiode = idsousperiode;
    }

    public Service getIdservice() {
        return idservice;
    }

    public void setIdservice(Service idservice) {
        this.idservice = idservice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnoteservice != null ? idnoteservice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noteservice)) {
            return false;
        }
        Noteservice other = (Noteservice) object;
        if ((this.idnoteservice == null && other.idnoteservice != null) || (this.idnoteservice != null && !this.idnoteservice.equals(other.idnoteservice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Noteservice[ idnote=" + idnoteservice + " ]";
    }

}
