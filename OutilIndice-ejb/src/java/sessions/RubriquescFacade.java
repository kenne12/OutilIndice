/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquesc;
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
public class RubriquescFacade extends AbstractFacade<Rubriquesc> implements RubriquescFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RubriquescFacade() {
        super(Rubriquesc.class);
    }

    @Override
    public List<Rubriquesc> findAllRange() {
        Query query = em.createQuery("SELECT r FROM rubriquesc r ORDER BY r.nom");
        return query.getResultList();
    }

}
