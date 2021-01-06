/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Depense;
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
public class DepenseFacade extends AbstractFacade<Depense> implements DepenseFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepenseFacade() {
        super(Depense.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(d.iddepense) FROM Depense d");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1l;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Depense> findByIdstructureIdperiodeIdSp(long idStructure, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT d FROM Depense d WHERE d.idstructure.idstructure=:idStructure AND d.idperiode.idperiode=:idPeriode AND d.idsousperiode.idsousperiode=:iDsp ORDER BY d.idsousrubriquedepense.code");
        query.setParameter("idStructure", idStructure).setParameter("idPeriode", idPeriode).setParameter("iDsp", idSousPeriode);
        return query.getResultList();
    }

    @Override
    public Depense findByIdstructureIdperiodeIdSpPrime(long idStructure, int idPeriode, int idSousPeriode, boolean prime) {
        Query query = em.createQuery("SELECT d FROM Depense d WHERE d.idstructure.idstructure=:idStructure AND d.idperiode.idperiode=:idPeriode AND d.idsousperiode.idsousperiode=:iDsp AND d.idsousrubriquedepense.special=:special ORDER BY d.idsousrubriquedepense.code");
        query.setParameter("idStructure", idStructure).setParameter("idPeriode", idPeriode).setParameter("iDsp", idSousPeriode).setParameter("special", prime);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (Depense) list.get(0);
        }
        return null;
    }

}
