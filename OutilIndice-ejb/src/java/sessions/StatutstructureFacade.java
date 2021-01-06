/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Statutstructure;
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
public class StatutstructureFacade extends AbstractFacade<Statutstructure> implements StatutstructureFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatutstructureFacade() {
        super(Statutstructure.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(s.idstatutstructure) FROM Statutstructure s");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1l;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Statutstructure> findAllRangeByNom() {
        Query query = em.createQuery("SELECT s FROM Statutstructure s ORDER BY s.nom");
        return query.getResultList();
    }

}
