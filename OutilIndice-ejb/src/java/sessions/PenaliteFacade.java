/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Penalite;
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
public class PenaliteFacade extends AbstractFacade<Penalite> implements PenaliteFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PenaliteFacade() {
        super(Penalite.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(p.idpenalite) FROM Penalite p");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Penalite> findAllService() {
        Query query = em.createQuery("SELECT p FROM Penalite p WHERE p.service=true ORDER BY p.pourcentage");
        return query.getResultList();
    }

    @Override
    public List<Penalite> findAllPersonnel() {
        Query query = em.createQuery("SELECT p FROM Penalite p WHERE p.personnel=true ORDER BY p.pourcentage");
        return query.getResultList();
    }

}
