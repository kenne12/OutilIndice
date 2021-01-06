/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Periode;
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
public class PeriodeFacade extends AbstractFacade<Periode> implements PeriodeFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodeFacade() {
        super(Periode.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(p.idperiode) FROM Periode p");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Periode> findAllRange() {
        Query query = em.createQuery("SELECT p FROM Periode p ORDER BY p.code");
        return query.getResultList();
    }

    @Override
    public List<Periode> findPeriodeParent() {
        Query query = em.createQuery("SELECT p FROM Periode p WHERE p.idparent IS NULL ORDER BY p.code");
        return query.getResultList();
    }

    @Override
    public List<Periode> findByEtat(boolean etat) {
        Query query = em.createQuery("SELECT p FROM Periode p WHERE p.etat=:etat ORDER BY p.code");
        query.setParameter("etat", etat);
        return query.getResultList();
    }

}
