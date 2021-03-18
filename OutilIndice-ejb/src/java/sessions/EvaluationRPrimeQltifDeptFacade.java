/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvaluationRPrimeQltifDept;
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
public class EvaluationRPrimeQltifDeptFacade extends AbstractFacade<EvaluationRPrimeQltifDept> implements EvaluationRPrimeQltifDeptFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationRPrimeQltifDeptFacade() {
        super(EvaluationRPrimeQltifDept.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idevaluationrprimeqltifdept) FROM EvaluationRPrimeQltifDept e");
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
    public List<EvaluationRPrimeQltifDept> findByIdStructureSousPeriode(long idStructure, int idPeriode, int idSousPeriode, int idCritere) {
        Query query = em.createQuery("SELECT e FROM EvaluationRPrimeQltifDept e WHERE e.structure.idstructure   =:idStructure AND e.idperiode.idperiode=:idPeriode AND e.idsousperiode.idsousperiode=:idSousPeriode AND e.idcritere.idcritere=:idCritere ORDER BY e.idservice.nom");
        query.setParameter("idStructure", idStructure).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode).setParameter("idCritere", idCritere);
        return query.getResultList();
    }

    @Override
    public EvaluationRPrimeQltifDept findByIdService(long idStructure, long idService, int idPeriode, int idSousPeriode, int idCritere) {
        Query query = em.createQuery("SELECT e FROM EvaluationRPrimeQltifDept e WHERE e.structure.idstructure=:idStructure  AND e.idservice.idservice=:idService AND e.idperiode.idperiode=:idPeriode AND e.idsousperiode.idsousperiode=:idSousPeriode AND e.idcritere.idcritere=:idCritere ORDER BY e.idservice.nom");
        query.setParameter("idService", idService).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode).setParameter("idCritere", idCritere).setParameter("idStructure", idStructure);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (EvaluationRPrimeQltifDept) list.get(0);
        }
        return null;
    }

}
