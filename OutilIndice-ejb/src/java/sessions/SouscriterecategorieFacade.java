/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Criterecategorie;
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
public class SouscriterecategorieFacade extends AbstractFacade<Criterecategorie> implements SouscriterecategorieFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SouscriterecategorieFacade() {
        super(Criterecategorie.class);
    }

    @Override
    public List<Criterecategorie> findByIdcategorieIdStructure(int idCategorie, long idStructure) {
        Query query = em.createQuery("SELECT s FROM Souscriterecategorie s WHERE s.idcategorie.idcategorie=:idCategorie AND s.iddetailsc.idstructure.idstructure=:idStructure");
        query.setParameter("idCategorie", idCategorie).setParameter("idStructure", idStructure);
        return query.getResultList();
    }

}
