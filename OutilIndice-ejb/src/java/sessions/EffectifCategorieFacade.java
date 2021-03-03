/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EffectifCategorie;
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
public class EffectifCategorieFacade extends AbstractFacade<EffectifCategorie> implements EffectifCategorieFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EffectifCategorieFacade() {
        super(EffectifCategorie.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idEffectifCategorie) FROM EffectifCategorie E");
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
    public List<EffectifCategorie> findByIdStructure(long idStructure) {
        Query query = em.createQuery("SELECT e FROM EffectifCategorie e WHERE e.structure.idstructure=:idStructure");
        query.setParameter("idStructure", idStructure);
        return query.getResultList();
    }

    
    @Override
    public void deleteByIdStructure(long idStructure){
        Query query = em.createQuery("DELETE FROM EffectifCategorie e WHERE e.structure.idstructure=:idStructure");
        query.setParameter("idStructure", idStructure);
        query.executeUpdate();
    }
}
