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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "prime")
@XmlRootElement
public class Prime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Long idprime;
    private double montant;
    private double indice;
    private double point;
    private double montantglobal;
    private boolean etat;
    private String observation;
    private double notepersonnelle;
    private double relicat;
    private double plafond;
    @JoinColumn(name = "idnote", referencedColumnName = "idnote")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Note idnote;
    @JoinColumn(name = "idpersonnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Personnel idpersonnel;
    @JoinColumn(name = "idperiode", referencedColumnName = "idperiode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Periode idperiode;
    @JoinColumn(name = "idsousperiode", referencedColumnName = "idsousperiode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Sousperiode idsousperiode;

    public Prime() {
    }

    public Prime(Long idprime) {
        this.idprime = idprime;
    }

    public Long getIdprime() {
        return idprime;
    }

    public void setIdprime(Long idprime) {
        this.idprime = idprime;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getIndice() {
        return indice;
    }

    public void setIndice(double indice) {
        this.indice = indice;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getMontantglobal() {
        return montantglobal;
    }

    public void setMontantglobal(double montantglobal) {
        this.montantglobal = montantglobal;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public double getPlafond() {
        return plafond;
    }

    public void setPlafond(double plafond) {
        this.plafond = plafond;
    }

    public Note getIdnote() {
        return idnote;
    }

    public void setIdnote(Note idnote) {
        this.idnote = idnote;
    }

    public Personnel getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(Personnel idpersonnel) {
        this.idpersonnel = idpersonnel;
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public double getNotepersonnelle() {
        return notepersonnelle;
    }

    public void setNotepersonnelle(double notepersonnelle) {
        this.notepersonnelle = notepersonnelle;
    }

    public double getRelicat() {
        return relicat;
    }

    public void setRelicat(double relicat) {
        this.relicat = relicat;
    }

}
