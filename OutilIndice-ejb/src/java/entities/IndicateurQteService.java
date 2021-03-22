/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "indicateurqteservice")
public class IndicateurQteService implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idindicateurqteservice")
    private Long idIndicateurQteService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idstructure", referencedColumnName = "idstructure")
    private Structure structure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idindicateur", referencedColumnName = "idindicateur")
    private Indicateur indicateur;

    public IndicateurQteService() {
    }

    public IndicateurQteService(Long idIndicateurQteService) {
        this.idIndicateurQteService = idIndicateurQteService;
    }

    public Long getIdIndicateurQteService() {
        return idIndicateurQteService;
    }

    public void setIdIndicateurQteService(Long idIndicateurQteService) {
        this.idIndicateurQteService = idIndicateurQteService;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Indicateur getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.idIndicateurQteService);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IndicateurQteService other = (IndicateurQteService) obj;
        if (!Objects.equals(this.idIndicateurQteService, other.idIndicateurQteService)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IndicateurQteService{" + "idIndicateurQteService=" + idIndicateurQteService + '}';
    }

}
