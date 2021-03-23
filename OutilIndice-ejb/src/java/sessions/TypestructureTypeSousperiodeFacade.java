/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypestructureTypeSousperiode;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USER
 */
@Stateless
public class TypestructureTypeSousperiodeFacade extends AbstractFacade<TypestructureTypeSousperiode> implements TypestructureTypeSousperiodeFacadeLocal {
    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypestructureTypeSousperiodeFacade() {
        super(TypestructureTypeSousperiode.class);
    }
    
}
