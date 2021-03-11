/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Personnel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
@Stateless
public class PersonnelFacade extends AbstractFacade<Personnel> implements PersonnelFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonnelFacade() {
        super(Personnel.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(p.idpersonnel) FROM Personnel p");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1l;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Personnel> findByIdStructure(long idStructure) {
        Query query = em.createQuery("SELECT p FROM Personnel p WHERE p.structure.idstructure=:idStructure ORDER BY p.nom , p.prenom");
        query.setParameter("idStructure", idStructure);
        return query.getResultList();
    }

    @Override
    public List<Personnel> findByIdStructure(long idStructure, boolean etat) {
        Query query = em.createQuery("SELECT p FROM Personnel p WHERE p.structure.idstructure=:idStructure AND p.etat=:etat ORDER BY p.nom , p.prenom");
        query.setParameter("idStructure", idStructure).setParameter("etat", etat);
        return query.getResultList();
    }

    @Override
    public Long nextVal(long idStructure) {
        Query query = this.em.createQuery("SELECT COUNT(p.idpersonnel) FROM Personnel p WHERE p.structure.idstructure=:idStructure");
        query.setParameter("idStructure", idStructure);
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1l;
        } else {
            result = result + 1;
        }
        return result;
    }

}
