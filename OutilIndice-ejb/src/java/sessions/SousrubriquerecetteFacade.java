/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sousrubriquerecette;
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
public class SousrubriquerecetteFacade extends AbstractFacade<Sousrubriquerecette> implements SousrubriquerecetteFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SousrubriquerecetteFacade() {
        super(Sousrubriquerecette.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(s.idsousrubriquerecette) FROM Sousrubriquerecette s");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Sousrubriquerecette> findAllRangeByCode() {
        Query query = em.createQuery("SELECT s FROM Sousrubriquerecette s ORDER BY s.code");
        return query.getResultList();
    }

}
