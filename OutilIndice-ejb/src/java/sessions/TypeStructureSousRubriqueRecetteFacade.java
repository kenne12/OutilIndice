/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypeStructureSousRubriqueRecette;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
