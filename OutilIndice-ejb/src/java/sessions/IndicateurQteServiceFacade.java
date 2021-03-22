/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.IndicateurQteService;
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
public class IndicateurQteServiceFacade extends AbstractFacade<IndicateurQteService> implements IndicateurQteServiceFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicateurQteServiceFacade() {
        super(IndicateurQteService.class);
    }
    
    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(i.idIndicateurQteService) FROM IndicateurQteService i");
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
    public List<IndicateurQteService> findByIdService(long idStructure, long idService) {
        Query query = em.createQuery("SELECT i FROM IndicateurQteService i WHERE i.structure.idstructure=:idStructure AND i.service.idservice=:idService ORDER BY i.indicateur.code");
        query.setParameter("idStructure", idStructure).setParameter("idService", idService);
        return query.getResultList();
    }

}
