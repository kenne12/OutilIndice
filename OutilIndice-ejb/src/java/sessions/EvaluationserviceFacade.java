/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationservice;
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
public class EvaluationserviceFacade extends AbstractFacade<Evaluationservice> implements EvaluationserviceFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationserviceFacade() {
        super(Evaluationservice.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idevaluationservice) FROM Evaluationservice e");
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
    public List<Evaluationservice> findByService(long idService, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT e FROM Evaluationservice e WHERE e.idsouscritereservice.idservice.idservice=:idService AND e.idperiode.idperiode=:idPeriode AND e.idsousperiode.idsousperiode=:idSousPeriode ORDER BY e.idsouscritereservice.idsouscritere.code");
        query.setParameter("idService", idService).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        return query.getResultList();
    }

    @Override
    public void deleteData(long idService, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("DELETE FROM Evaluationservice e WHERE e.idsouscritereservice.idservice.idservice=:idService AND e.idperiode.idperiode=:idPeriode AND e.idsousperiode.idsousperiode=:idSousPeriode");
        query.setParameter("idService", idService).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        query.executeUpdate();
    }
}
