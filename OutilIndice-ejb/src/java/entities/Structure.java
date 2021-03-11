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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Structure.findAll", query = "SELECT s FROM Structure s"),
    @NamedQuery(name = "Structure.findByIdstructure", query = "SELECT s FROM Structure s WHERE s.idstructure = :idstructure"),
    @NamedQuery(name = "Structure.findByNom", query = "SELECT s FROM Structure s WHERE s.nom = :nom"),
    @NamedQuery(name = "Structure.findByCode", query = "SELECT s FROM Structure s WHERE s.code = :code"),
    @NamedQuery(name = "Structure.findByArticle", query = "SELECT s FROM Structure s WHERE s.article = :article"),
    @NamedQuery(name = "Structure.findByNomrespo", query = "SELECT s FROM Structure s WHERE s.nomrespo = :nomrespo"),
    @NamedQuery(name = "Structure.findByArretecreation", query = "SELECT s FROM Structure s WHERE s.arretecreation = :arretecreation"),
    @NamedQuery(name = "Structure.findByDesignation", query = "SELECT s FROM Structure s WHERE s.designation = :designation"),
    @NamedQuery(name = "Structure.findByPresentation", query = "SELECT s FROM Structure s WHERE s.presentation = :presentation"),
    @NamedQuery(name = "Structure.findByEtat", query = "SELECT s FROM Structure s WHERE s.etat = :etat"),
    @NamedQuery(name = "Structure.findByDateouverture", query = "SELECT s FROM Structure s WHERE s.dateouverture = :dateouverture"),
    @NamedQuery(name = "Structure.findByVision", query = "SELECT s FROM Structure s WHERE s.vision = :vision"),
    @NamedQuery(name = "Structure.findByAxeintervention", query = "SELECT s FROM Structure s WHERE s.axeintervention = :axeintervention"),
    @NamedQuery(name = "Structure.findByObjectifgeneral", query = "SELECT s FROM Structure s WHERE s.objectifgeneral = :objectifgeneral"),
    @NamedQuery(name = "Structure.findByObjectifspecifique", query = "SELECT s FROM Structure s WHERE s.objectifspecifique = :objectifspecifique")})
public class Structure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idstructure;
    @Size(max = 254)
    private String nom;
    @Size(max = 254)
    private String code;
    @Size(max = 254)
    private String article;
    @Size(max = 254)
    private String nomrespo;
    @Size(max = 254)
    private String arretecreation;
    @Size(max = 254)
    private String designation;
    @Size(max = 254)
    private String presentation;
    @Size(max = 254)
    private String etat;
    @Size(max = 254)
    private String dateouverture;
    @Size(max = 254)
    private String vision;
    @Size(max = 254)
    private String axeintervention;
    @Size(max = 254)
    private String objectifgeneral;
    @Size(max = 254)
    private String objectifspecifique;

    @JoinTable(name = "utilisateurstructure", joinColumns = {
        @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")}, inverseJoinColumns = {
        @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Utilisateur> utilisateurCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "structure", fetch = FetchType.LAZY)
    private Collection<Criterestructure> criterestructureCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "structure", fetch = FetchType.LAZY)
    private Collection<Categoriestructure> categoriestructureCollection;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private Collection<Detailsc> detailscCollection;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private Collection<Depense> depenseCollection;
    @JoinColumn(name = "idinstitution", referencedColumnName = "idinstitution")
    @ManyToOne(fetch = FetchType.LAZY)
    private Institution idinstitution;
    @JoinColumn(name = "idstatutstructure", referencedColumnName = "idstatutstructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Statutstructure idstatutstructure;
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typestructure idtypestructure;
    @OneToMany(mappedBy = "idstructure", fetch = FetchType.LAZY)
    private Collection<Recette> recetteCollection;

    @OneToMany(mappedBy = "structure", fetch = FetchType.LAZY)
    private Collection<EffectifCategorie> effectifCategorieCollection;

    @OneToMany(mappedBy = "structure", fetch = FetchType.LAZY)
    private Collection<EffectifResponsabilite> effectifResponsabiliteCollection;

    @OneToMany(mappedBy = "structure", fetch = FetchType.LAZY)
    private Collection<Personnel> personnelCollection;

    @OneToMany(mappedBy = "structure", fetch = FetchType.LAZY)
    private Collection<Souscritereservice> sousCritereServiceCollection;

    public Structure() {
    }

    public Structure(Long idstructure) {
        this.idstructure = idstructure;
    }

    public Long getIdstructure() {
        return idstructure;
    }

    public void setIdstructure(Long idstructure) {
        this.idstructure = idstructure;
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

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNomrespo() {
        return nomrespo;
    }

    public void setNomrespo(String nomrespo) {
        this.nomrespo = nomrespo;
    }

    public String getArretecreation() {
        return arretecreation;
    }

    public void setArretecreation(String arretecreation) {
        this.arretecreation = arretecreation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDateouverture() {
        return dateouverture;
    }

    public void setDateouverture(String dateouverture) {
        this.dateouverture = dateouverture;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getAxeintervention() {
        return axeintervention;
    }

    public void setAxeintervention(String axeintervention) {
        this.axeintervention = axeintervention;
    }

    public String getObjectifgeneral() {
        return objectifgeneral;
    }

    public void setObjectifgeneral(String objectifgeneral) {
        this.objectifgeneral = objectifgeneral;
    }

    public String getObjectifspecifique() {
        return objectifspecifique;
    }

    public void setObjectifspecifique(String objectifspecifique) {
        this.objectifspecifique = objectifspecifique;
    }

    @XmlTransient
    public Collection<Utilisateur> getUtilisateurCollection() {
        return utilisateurCollection;
    }

    public void setUtilisateurCollection(Collection<Utilisateur> utilisateurCollection) {
        this.utilisateurCollection = utilisateurCollection;
    }

    @XmlTransient
    public Collection<EffectifCategorie> getEffectifCategorieCollection() {
        return effectifCategorieCollection;
    }

    public void setEffectifCategorieCollection(Collection<EffectifCategorie> effectifCategorieCollection) {
        this.effectifCategorieCollection = effectifCategorieCollection;
    }

    @XmlTransient
    public Collection<Criterestructure> getCriterestructureCollection() {
        return criterestructureCollection;
    }

    public void setCriterestructureCollection(Collection<Criterestructure> criterestructureCollection) {
        this.criterestructureCollection = criterestructureCollection;
    }

    @XmlTransient
    public Collection<Categoriestructure> getCategoriestructureCollection() {
        return categoriestructureCollection;
    }

    public void setCategoriestructureCollection(Collection<Categoriestructure> categoriestructureCollection) {
        this.categoriestructureCollection = categoriestructureCollection;
    }

    @XmlTransient
    public Collection<Detailsc> getDetailscCollection() {
        return detailscCollection;
    }

    public void setDetailscCollection(Collection<Detailsc> detailscCollection) {
        this.detailscCollection = detailscCollection;
    }

    @XmlTransient
    public Collection<Depense> getDepenseCollection() {
        return depenseCollection;
    }

    public void setDepenseCollection(Collection<Depense> depenseCollection) {
        this.depenseCollection = depenseCollection;
    }

    @XmlTransient
    public Collection<EffectifResponsabilite> getEffectifResponsabiliteCollection() {
        return effectifResponsabiliteCollection;
    }

    public void setEffectifResponsabiliteCollection(Collection<EffectifResponsabilite> effectifResponsabiliteCollection) {
        this.effectifResponsabiliteCollection = effectifResponsabiliteCollection;
    }

    public Institution getIdinstitution() {
        return idinstitution;
    }

    public void setIdinstitution(Institution idinstitution) {
        this.idinstitution = idinstitution;
    }

    public Statutstructure getIdstatutstructure() {
        return idstatutstructure;
    }

    public void setIdstatutstructure(Statutstructure idstatutstructure) {
        this.idstatutstructure = idstatutstructure;
    }

    public Typestructure getIdtypestructure() {
        return idtypestructure;
    }

    public void setIdtypestructure(Typestructure idtypestructure) {
        this.idtypestructure = idtypestructure;
    }

    @XmlTransient
    public Collection<Recette> getRecetteCollection() {
        return recetteCollection;
    }

    public void setRecetteCollection(Collection<Recette> recetteCollection) {
        this.recetteCollection = recetteCollection;
    }

    @XmlTransient
    public Collection<Personnel> getPersonnelCollection() {
        return personnelCollection;
    }

    public void setPersonnelCollection(Collection<Personnel> personnelCollection) {
        this.personnelCollection = personnelCollection;
    }

    @XmlTransient
    public Collection<Souscritereservice> getSousCritereServiceCollection() {
        return sousCritereServiceCollection;
    }

    public void setSousCritereServiceCollection(Collection<Souscritereservice> sousCritereServiceCollection) {
        this.sousCritereServiceCollection = sousCritereServiceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstructure != null ? idstructure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Structure)) {
            return false;
        }
        Structure other = (Structure) object;
        if ((this.idstructure == null && other.idstructure != null) || (this.idstructure != null && !this.idstructure.equals(other.idstructure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Structure[ idstructure=" + idstructure + " ]";
    }

}
