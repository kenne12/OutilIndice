/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Critere;
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
public class CritereFacade extends AbstractFacade<Critere> implements CritereFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CritereFacade() {
        super(Critere.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(c.idcritere) FROM Critere c");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Critere> findAllRangeByCode() {
        Query query = em.createQuery("SELECT c FROM Critere c ORDER BY c.idcritere");
        return query.getResultList();
    }
}
