/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvaluationRPrimeQltifPersonnel;
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
public class EvaluationRPrimeQltifPersonnelFacade extends AbstractFacade<EvaluationRPrimeQltifPersonnel> implements EvaluationRPrimeQltifPersonnelFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationRPrimeQltifPersonnelFacade() {
        super(EvaluationRPrimeQltifPersonnel.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idevaluationrprimeqltifpersonnel) FROM EvaluationRPrimeQltifPersonnel e");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Long) listObj.get(0)) + 1;
            } else {
                return 1 + 1L;
            }
        } catch (Exception e) {
            return 1l;
        }
    }

    @Override
    public EvaluationRPrimeQltifPersonnel findByIdPersonnel(long idPersonnel, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT e FROM EvaluationRPrimeQltifPersonnel e WHERE e.idevaluationrprimeqltifdept.idperiode.idperiode=:idPeriode AND e.idevaluationrprimeqltifdept.idsousperiode.idsousperiode=:idSousPeriode AND e.idpersonnel.idpersonnel=:idPersonnel");
        query.setParameter("idPersonnel", idPersonnel).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (EvaluationRPrimeQltifPersonnel) list.get(0);
        }
        return null;
    }

}
