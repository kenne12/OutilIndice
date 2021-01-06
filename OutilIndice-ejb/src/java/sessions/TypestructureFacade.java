/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typestructure;
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
public class TypestructureFacade extends AbstractFacade<Typestructure> implements TypestructureFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypestructureFacade() {
        super(Typestructure.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(t.idtypestructure) FROM Typestructure t");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1l;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Typestructure> findAllRangeByNom() {
        Query query = em.createQuery("SELECT t FROM Typestructure t ORDER BY t.nom");
        return query.getResultList();
    }

}
