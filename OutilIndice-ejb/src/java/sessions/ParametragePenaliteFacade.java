/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ParametragePenalite;
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
public class ParametragePenaliteFacade extends AbstractFacade<ParametragePenalite> implements ParametragePenaliteFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametragePenaliteFacade() {
        super(ParametragePenalite.class);
    }

    @Override
    public Integer nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(p.idParametragePenalite) FROM ParametragePenalite p");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Integer) listObj.get(0)) + 1;
            } else {
                return 1 + 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public List<ParametragePenalite> findByIdServiceIdCritere(long idStructure, long idService, int idCritere) {
        Query query = em.createQuery("SELECT p FROM ParametragePenalite p WHERE p.structure.idstructure=:idStructure AND p.service.idservice=:idService AND p.critere.idcritere=:idCritere");
        query.setParameter("idStructure", idStructure).setParameter("idService", idService).setParameter("idCritere", idCritere);
        return query.getResultList();
    }

}
