/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypestructureCategorie;
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
public class TypestructureCategorieFacade extends AbstractFacade<TypestructureCategorie> implements TypestructureCategorieFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypestructureCategorieFacade() {
        super(TypestructureCategorie.class);
    }
    
    @Override
    public Integer nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(t.idTypestructureCategorie) FROM TypestructureCategorie t");
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
    public List<TypestructureCategorie> findByIdTypestructure(long idTypeStructure) {
        Query query = em.createQuery("SELECT t FROM TypestructureCategorie t WHERE t.typestructure.idtypestructure=:idTypeStructure ORDER BY t.categorie.nom");
        query.setParameter("idTypeStructure", idTypeStructure);
        return query.getResultList();
    }

}
