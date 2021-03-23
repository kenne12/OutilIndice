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
@Table(name = "typestructureService")
public class TypestructureService implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "idtypestructureservice")
    private Integer idTypestructureService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idtypestructure", referencedColumnName = "idtypestructure")
    private Typestructure typestructure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    private Service service;

    public TypestructureService() {
    }

    public TypestructureService(Integer idTypestructureService) {
        this.idTypestructureService = idTypestructureService;
    }

    public TypestructureService(Integer idTypestructureService, Typestructure typestructure, Service service) {
        this.idTypestructureService = idTypestructureService;
        this.typestructure = typestructure;
        this.service = service;
    }

    public Integer getIdTypestructureService() {
        return idTypestructureService;
    }

    public void setIdTypestructureService(Integer idTypestructureService) {
        this.idTypestructureService = idTypestructureService;
    }

    public Typestructure getTypestructure() {
        return typestructure;
    }

    public void setTypestructure(Typestructure typestructure) {
        this.typestructure = typestructure;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idTypestructureService);
        hash = 53 * hash + Objects.hashCode(this.typestructure);
        hash = 53 * hash + Objects.hashCode(this.service);
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
        final TypestructureService other = (TypestructureService) obj;
        if (!Objects.equals(this.idTypestructureService, other.idTypestructureService)) {
            return false;
        }
        if (!Objects.equals(this.typestructure, other.typestructure)) {
            return false;
        }
        if (!Objects.equals(this.service, other.service)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypestructureService{" + "idTypestructureService=" + idTypestructureService + '}';
    }

}
