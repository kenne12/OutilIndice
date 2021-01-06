/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findByIdutilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idutilisateur = :idutilisateur"),
    @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom"),
    @NamedQuery(name = "Utilisateur.findByPrenom", query = "SELECT u FROM Utilisateur u WHERE u.prenom = :prenom"),
    @NamedQuery(name = "Utilisateur.findByLogin", query = "SELECT u FROM Utilisateur u WHERE u.login = :login"),
    @NamedQuery(name = "Utilisateur.findByPassword", query = "SELECT u FROM Utilisateur u WHERE u.password = :password"),
    @NamedQuery(name = "Utilisateur.findByPhoto", query = "SELECT u FROM Utilisateur u WHERE u.photo = :photo"),
    @NamedQuery(name = "Utilisateur.findByActif", query = "SELECT u FROM Utilisateur u WHERE u.actif = :actif"),
    @NamedQuery(name = "Utilisateur.findByTemplate", query = "SELECT u FROM Utilisateur u WHERE u.template = :template"),
    @NamedQuery(name = "Utilisateur.findByTheme", query = "SELECT u FROM Utilisateur u WHERE u.theme = :theme"),
    @NamedQuery(name = "Utilisateur.findByDatecreation", query = "SELECT u FROM Utilisateur u WHERE u.datecreation = :datecreation"),
    @NamedQuery(name = "Utilisateur.findByDatederniereconnexion", query = "SELECT u FROM Utilisateur u WHERE u.datederniereconnexion = :datederniereconnexion"),
    @NamedQuery(name = "Utilisateur.findByHeurederniereconnexion", query = "SELECT u FROM Utilisateur u WHERE u.heurederniereconnexion = :heurederniereconnexion")})
public class Utilisateurstructure implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected UtilisateurstructurePK utilisateurstructurePK;
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utilisateur utilisateur;
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Structure structure;

    public Utilisateurstructure() {
    }

    public Utilisateurstructure(UtilisateurstructurePK utilisateurstructurePK) {
        this.utilisateurstructurePK = utilisateurstructurePK;
    }

    public Utilisateurstructure(Utilisateur utilisateur, Structure structure) {
        this.utilisateur = utilisateur;
        this.structure = structure;
    }

    public UtilisateurstructurePK getUtilisateurstructurePK() {
        return utilisateurstructurePK;
    }

    public void setUtilisateurstructurePK(UtilisateurstructurePK utilisateurstructurePK) {
        this.utilisateurstructurePK = utilisateurstructurePK;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateurstructure)) {
            return false;
        }
        Utilisateurstructure other = (Utilisateurstructure) object;
        if ((this.utilisateurstructurePK == null && other.utilisateurstructurePK != null) || (this.utilisateurstructurePK != null && !this.utilisateurstructurePK.equals(other.utilisateurstructurePK))) {
            return false;
        }
        return true;
    }

}
