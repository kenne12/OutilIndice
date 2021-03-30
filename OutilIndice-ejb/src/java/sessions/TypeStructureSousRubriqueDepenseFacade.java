/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypeStructureSousRubriqueDepense;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USER
 */
@Stateless
public class TypeStructureSousRubriqueDepenseFacade extends AbstractFacade<TypeStructureSousRubriqueDepense> implements TypeStructureSousRubriqueDepenseFacadeLocal {
    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeStructureSousRubriqueDepenseFacade() {
        super(TypeStructureSousRubriqueDepense.class);
    }
    
}
