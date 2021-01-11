/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Critereresponsabilite;
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
public class CritereresponsabiliteFacade extends AbstractFacade<Critereresponsabilite> implements CritereresponsabiliteFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CritereresponsabiliteFacade() {
        super(Critereresponsabilite.class);
    }
    
    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(c.idcritereresponsabilite) FROM  Critereresponsabilite c");
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
    public List<Critereresponsabilite> findByIdStructure(long idStructure) {
        Query query = em.createQuery("SELECT c FROM Critereresponsabilite c WHERE c.idstructure.idstructure=:idStructure ORDER BY c.idresponsabilite.nom");
        query.setParameter("idStructure", idStructure);
        return query.getResultList();
    }

}
