/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvaluationPenaliteDept;
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
public class EvaluationPenaliteDeptFacade extends AbstractFacade<EvaluationPenaliteDept> implements EvaluationPenaliteDeptFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationPenaliteDeptFacade() {
        super(EvaluationPenaliteDept.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idevaluationpenalitedept) FROM EvaluationPenaliteDept e");
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
    public List<EvaluationPenaliteDept> findByIdStructure(long idStructure, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT e FROM EvaluationPenaliteDept e WHERE e.idservice.idstructure.idstructure=:idStructure AND e.idperiode.idperiode=:idPeriode AND e.idsousperiode.idsousperiode=:idSousPeriode ORDER BY e.idservice.nom");
        query.setParameter("idStructure", idStructure).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        return query.getResultList();
    }

    @Override
    public EvaluationPenaliteDept findByIdService(long idService, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT e FROM EvaluationPenaliteDept e WHERE e.idservice.idservice=:idService AND e.idperiode.idperiode=:idPeriode AND e.idsousperiode.idsousperiode=:idSousPeriode ORDER BY e.idservice.nom");
        query.setParameter("idService", idService).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (EvaluationPenaliteDept) list.get(0);
        }
        return null;
    }

}
