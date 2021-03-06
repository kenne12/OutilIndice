/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Mouchard;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
@Stateless
public class MouchardFacade extends AbstractFacade<Mouchard> implements MouchardFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MouchardFacade() {
        super(Mouchard.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(m.idmouchard) FROM Mouchard m");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1L;
        }
        return result;
    }

    @Override
    public void deleteByIdutilisateur(int idutilisateur) {
        Query query = em.createQuery("DELETE  FROM Mouchard m WHERE m.idutilisateur.idutilisateur=:idutilisateur");
        query.setParameter("idutilisateur", idutilisateur);
        query.executeUpdate();
    }
}
