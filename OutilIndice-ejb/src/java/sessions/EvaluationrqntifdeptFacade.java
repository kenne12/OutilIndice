/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationrqntifdept;
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
public class EvaluationrqntifdeptFacade extends AbstractFacade<Evaluationrqntifdept> implements EvaluationrqntifdeptFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationrqntifdeptFacade() {
        super(Evaluationrqntifdept.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idevaluationrqntifdept) FROM Evaluationrqntifdept e");
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
    public List<Evaluationrqntifdept> findByIdPersonnel(long idPersonnel, int idPeriode, int idSousPeriode, int idCritere) {
        Query query = em.createQuery("SELECT e FROM Evaluationrqntifdept e WHERE e.idpersonnel.idpersonnel=:idPersonnel AND e.idcible.idperiode.idperiode=:idPeriode AND e.idcible.idsousperiode.idsousperiode=:idSousPeriode AND e.idcible.idcritere.idcritere=:idCritere");
        query.setParameter("idPersonnel", idPersonnel).setParameter("idPeriode", idPeriode);
        query.setParameter("idSousPeriode", idSousPeriode).setParameter("idCritere", idCritere);
        return query.getResultList();
    }
}
