/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquedepense;
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
public class RubriquedepenseFacade extends AbstractFacade<Rubriquedepense> implements RubriquedepenseFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriquedepenseFacade() {
        super(Rubriquedepense.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(r.idrubriquedepense) FROM Rubriquedepense r");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Rubriquedepense> findAllRangeByCode() {
        Query query = em.createQuery("SELECT r FROM Rubriquedepense r ORDER BY r.code");
        return query.getResultList();
    }

}
