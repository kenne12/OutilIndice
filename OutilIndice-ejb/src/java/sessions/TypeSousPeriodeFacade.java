/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypeSousPeriode;
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
public class TypeSousPeriodeFacade extends AbstractFacade<TypeSousPeriode> implements TypeSousPeriodeFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeSousPeriodeFacade() {
        super(TypeSousPeriode.class);
    }
    
    @Override
    public Integer nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(t.idTypeSousperiode) FROM TypeSousPeriode t");
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
    public List<TypeSousPeriode> findAllOrderByCode() {
        Query query = em.createQuery("SELECT t FROM TypeSousPeriode t ORDER BY t.code");
        return query.getResultList();
    }

}
