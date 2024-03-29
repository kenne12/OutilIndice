/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EffectifResponsabilite;
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
public class EffectifResponsabiliteFacade extends AbstractFacade<EffectifResponsabilite> implements EffectifResponsabiliteFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EffectifResponsabiliteFacade() {
        super(EffectifResponsabilite.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idEffectifResponsabilite) FROM EffectifResponsabilite e");
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
    public List<EffectifResponsabilite> findByIdStructure(long idStructure) {
        Query query = em.createQuery("SELECT e FROM EffectifResponsabilite e WHERE e.structure.idstructure=:idStructure");
        query.setParameter("idStructure", idStructure);
        return query.getResultList();
    }

    @Override
    public void deleteByIdStructure(long idStructure) {
        Query query = em.createQuery("DELETE FROM EffectifResponsabilite e WHERE e.structure.idstructure=:idStructure");
        query.setParameter("idStructure", idStructure);
        query.executeUpdate();
    }

    @Override
    public EffectifResponsabilite findByIdStructureAndIdResponsabilite(long idStructure, int idResponsabilite) {
        Query query = em.createQuery("SELECT e FROM EffectifResponsabilite e WHERE e.structure.idstructure=:idStructure AND e.responsabilite.idresponsabilite=:idResp");
        query.setParameter("idStructure", idStructure).setParameter("idResp", idResponsabilite);
        try {
            return (EffectifResponsabilite) query.getResultList().get(0);
        } catch (Exception e) {
            return null;
        }
    }

}
