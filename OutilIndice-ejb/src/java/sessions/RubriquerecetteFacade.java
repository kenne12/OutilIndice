/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquerecette;
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
public class RubriquerecetteFacade extends AbstractFacade<Rubriquerecette> implements RubriquerecetteFacadeLocal {
    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriquerecetteFacade() {
        super(Rubriquerecette.class);
    }
    
    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(r.idrubriquerecette) FROM Rubriquerecette r");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Rubriquerecette> findAllRangeByCode() {
        Query query = em.createQuery("SELECT r FROM Rubriquerecette r ORDER BY r.code");
        return query.getResultList();
    }
    
}
