/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Souscritereservice;
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
public class SouscritereserviceFacade extends AbstractFacade<Souscritereservice> implements SouscritereserviceFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SouscritereserviceFacade() {
        super(Souscritereservice.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(s.idsouscritereservice) FROM Souscritereservice s");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Long) listObj.get(0)) + 1;
            } else {
                return 1 + 1L;
            }
        } catch (Exception e) {
            return 1l;
        }
    }

    @Override
    public List<Souscritereservice> findByIdService(long idStructure, long idService) {
        Query query = em.createQuery("SELECT s FROM Souscritereservice s WHERE s.structure.idstructure=:idStructure AND s.idservice.idservice=:idService ORDER BY s.idsouscritere.code");
        query.setParameter("idService", idService).setParameter("idStructure", idStructure);
        return query.getResultList();
    }

    @Override
    public List<Souscritereservice> findByIdServiceIdCritere(long idStructure, long idService, int idCritere) {
        Query query = em.createQuery("SELECT s FROM Souscritereservice s WHERE s.structure.idstructure=:idStructure AND s.idservice.idservice=:idService AND s.idsouscritere.idcritere.idcritere=:idCritere ORDER BY s.idsouscritere.code");
        query.setParameter("idService", idService).setParameter("idCritere", idCritere).setParameter("idStructure", idStructure);
        return query.getResultList();
    }

}
