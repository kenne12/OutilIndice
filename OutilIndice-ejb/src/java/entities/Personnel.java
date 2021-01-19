/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Personnel.findAll", query = "SELECT p FROM Personnel p"),
    @NamedQuery(name = "Personnel.findByIdpersonnel", query = "SELECT p FROM Personnel p WHERE p.idpersonnel = :idpersonnel"),
    @NamedQuery(name = "Personnel.findByNom", query = "SELECT p FROM Personnel p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personnel.findByPrenom", query = "SELECT p FROM Personnel p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Personnel.findByDateembauche", query = "SELECT p FROM Personnel p WHERE p.dateembauche = :dateembauche"),
    @NamedQuery(name = "Personnel.findByMatricule", query = "SELECT p FROM Personnel p WHERE p.matricule = :matricule"),
    @NamedQuery(name = "Personnel.findByEtat", query = "SELECT p FROM Personnel p WHERE p.etat = :etat")})
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idpersonnel;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date dateembauche;
    @Size(max = 254)
    private String matricule;
    private Boolean etat;
    @OneToMany(mappedBy = "idpersonnel", fetch = FetchType.LAZY)
    private Collection<Evaluationpersonnel> evaluationpersonnelCollection;
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categorie idcategorie;
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    @ManyToOne(fetch = FetchType.LAZY)
    private Service idservice;
    @JoinColumn(name = "idresponsabilite", referencedColumnName = "idresponsabilite")
    @ManyToOne(fetch = FetchType.LAZY)
    private Responsabilite idresponsabilite;

    public Personnel() {
    }

    public Personnel(Long idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public Long getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(Long idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateembauche() {
        return dateembauche;
    }

    public void setDateembauche(Date dateembauche) {
        this.dateembauche = dateembauche;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Responsabilite getIdresponsabilite() {
        return idresponsabilite;
    }

    public void setIdresponsabilite(Responsabilite idresponsabilite) {
        this.idresponsabilite = idresponsabilite;
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

    public Service getIdservice() {
        return idservice;
    }

    public void setIdservice(Service idservice) {
        this.idservice = idservice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonnel != null ? idpersonnel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnel)) {
            return false;
        }
        Personnel other = (Personnel) object;
        if ((this.idpersonnel == null && other.idpersonnel != null) || (this.idpersonnel != null && !this.idpersonnel.equals(other.idpersonnel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personnel[ idpersonnel=" + idpersonnel + " ]";
    }

}
