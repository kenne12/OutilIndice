/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypeStructureSousRubriqueRecette;
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
public class TypeStructureSousRubriqueRecetteFacade extends AbstractFacade<TypeStructureSousRubriqueRecette> implements TypeStructureSousRubriqueRecetteFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeStructureSousRubriqueRecetteFacade() {
        super(TypeStructureSousRubriqueRecette.class);
    }

    @Override
    public Integer nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(t.id) FROM TypeStructureSousRubriqueRecette t");
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
    public List<TypeStructureSousRubriqueRecette> findByIdTypestructure(long idTypeStructure) {
        Query query = em.createQuery("SELECT t FROM TypeStructureSousRubriqueRecette t WHERE t.typestructure.idtypestructure=:idTypeStructure ORDER BY t.sousrubriquerecette.code");
        query.setParameter("idTypeStructure", idTypeStructure);
        return query.getResultList();
    }

}
