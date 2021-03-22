/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Indicateur;
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
public class IndicateurFacade extends AbstractFacade<Indicateur> implements IndicateurFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicateurFacade() {
        super(Indicateur.class);
    }

    @Override
    public List<Indicateur> findAllRangeCode() {
        Query query = em.createQuery("SELECT i FROM Indicateur i ORDER BY i.code");
        return query.getResultList();
    }

}
