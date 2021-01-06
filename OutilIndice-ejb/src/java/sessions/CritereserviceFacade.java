/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Critereservice;
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
public class CritereserviceFacade extends AbstractFacade<Critereservice> implements CritereserviceFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CritereserviceFacade() {
        super(Critereservice.class);
    }

    @Override
    public List<Critereservice> findByIdService(long idService) {
        Query query = em.createQuery("SELECT c FROM Critereservice c WHERE c.service.idservice=:idService ORDER BY c.service.code");
        query.setParameter("idService", idService);
        return query.getResultList();
    }

    @Override
    public Critereservice findByIdServiceIdCritere(long idService, int idCritere) {
        Query query = em.createQuery("SELECT c FROM Critereservice c WHERE c.service.idservice=:idService AND c.critere.idcritere=:idCritere");
        query.setParameter("idService", idService).setParameter("idCritere", idCritere);

        List list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return (Critereservice) list.get(0);
    }

}
