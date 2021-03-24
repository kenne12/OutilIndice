/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypestructureResponsabilite;
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
public class TypestructureResponsabiliteFacade extends AbstractFacade<TypestructureResponsabilite> implements TypestructureResponsabiliteFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypestructureResponsabiliteFacade() {
        super(TypestructureResponsabilite.class);
    }

    @Override
    public Integer nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(t.idTypestructureResponsabilite) FROM TypestructureResponsabilite t");
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
    public List<TypestructureResponsabilite> findByIdTypestructure(long idTypeStructure) {
        Query query = em.createQuery("SELECT t FROM TypestructureResponsabilite t WHERE t.typestructure.idtypestructure=:idTypeStructure");
        query.setParameter("idTypeStructure", idTypeStructure);
        return query.getResultList();
    }

}
