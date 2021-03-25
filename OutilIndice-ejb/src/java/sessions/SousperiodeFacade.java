/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sousperiode;
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
public class SousperiodeFacade extends AbstractFacade<Sousperiode> implements SousperiodeFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SousperiodeFacade() {
        super(Sousperiode.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(s.idsousperiode) FROM Sousperiode s");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Sousperiode> findAllRangeCode() {
        Query query = em.createQuery("SELECT s FROM Sousperiode s ORDER BY s.code");
        return query.getResultList();
    }

    @Override
    public List<Sousperiode> findIdTypeSousPeriode(int idType) {
        Query query = em.createQuery("SELECT s FROM Sousperiode s WHERE s.typeSousPeriode.idTypeSousperiode=:idType ORDER BY s.code");
        query.setParameter("idType", idType);
        return query.getResultList();
    }

}
