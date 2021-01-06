/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Criterestructure;
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
public class CriterestructureFacade extends AbstractFacade<Criterestructure> implements CriterestructureFacadeLocal {
    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CriterestructureFacade() {
        super(Criterestructure.class);
    }
    
    @Override
    public List<Criterestructure> findByIdStructure(Long idstructure) {
        Query query = em.createQuery("SELECT c FROM Criterestructure c WHERE c.structure.idstructure=:idStructure ORDER BY c.critere.code");
        query.setParameter("idStructure", idstructure);
        return query.getResultList();
    }

    @Override
    public Criterestructure findByIdStructureIdCritere(Long idstructure, int idCritere) {
        Query query = em.createQuery("SELECT c FROM Criterestructure c WHERE c.structure.idstructure=:idStructure AND c.critere.idcritere=:idCritere");
        query.setParameter("idStructure", idstructure).setParameter("idCritere", idCritere);
        List<Criterestructure> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    
}
