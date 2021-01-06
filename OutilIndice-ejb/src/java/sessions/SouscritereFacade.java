/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Souscritere;
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
public class SouscritereFacade extends AbstractFacade<Souscritere> implements SouscritereFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SouscritereFacade() {
        super(Souscritere.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(s.idsouscritere) FROM Souscritere s");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Souscritere> findAllRangeByCode() {
        Query query = em.createQuery("SELECT s FROM Souscritere s ORDER BY s.code");
        return query.getResultList();
    }

    @Override
    public List<Souscritere> findByIdCritere(int idCritere) {
        Query query = em.createQuery("SELECT  s FROM Souscritere s WHERE s.idcritere.idcritere=:idCritere ORDER BY s.code");
        query.setParameter("idCritere", idCritere);
        return query.getResultList();
    }

    @Override
    public List<Souscritere> findByIdCriterePersonnel(int idCritere) {
        Query query = em.createQuery("SELECT  s FROM Souscritere s WHERE s.idcritere.idcritere=:idCritere AND s.personnel=true ORDER BY s.code");
        query.setParameter("idCritere", idCritere);
        return query.getResultList();
    }
    
    @Override
    public List<Souscritere> findByIdCritereService(int idCritere) {
        Query query = em.createQuery("SELECT  s FROM Souscritere s WHERE s.idcritere.idcritere=:idCritere AND s.service=true ORDER BY s.code");
        query.setParameter("idCritere", idCritere);
        return query.getResultList();
    }

}
