/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Recette;
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
public class RecetteFacade extends AbstractFacade<Recette> implements RecetteFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecetteFacade() {
        super(Recette.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(r.idrecette) FROM Recette r");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1l;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Recette> findByIdstructureIdperiodeIdSp(long idStructure, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT r FROM Recette r WHERE r.idstructure.idstructure=:idStructure AND r.idperiode.idperiode=:idPeriode AND r.idsousperiode.idsousperiode=:iDsp ORDER BY r.idsousrubriquerecette.code");
        query.setParameter("idStructure", idStructure).setParameter("idPeriode", idPeriode).setParameter("iDsp", idSousPeriode);
        return query.getResultList();
    }

}
