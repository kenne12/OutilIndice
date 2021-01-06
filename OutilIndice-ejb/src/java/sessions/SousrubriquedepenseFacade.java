/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquedepense;
import entities.Sousrubriquedepense;
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
public class SousrubriquedepenseFacade extends AbstractFacade<Sousrubriquedepense> implements SousrubriquedepenseFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SousrubriquedepenseFacade() {
        super(Sousrubriquedepense.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(s.idsousrubriquedepense) FROM Sousrubriquedepense s");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Sousrubriquedepense> findAllRangeByCode() {
        Query query = em.createQuery("SELECT s FROM Sousrubriquedepense s ORDER BY s.code");
        return query.getResultList();
    }

    @Override
    public List<Sousrubriquedepense> findAllEtatPrime(boolean prime) {
        Query query = em.createQuery("SELECT s FROM Sousrubriquedepense s WHERE s.special=:prime ORDER BY s.code");
        query.setParameter("prime", prime);
        return query.getResultList();
    }

}
