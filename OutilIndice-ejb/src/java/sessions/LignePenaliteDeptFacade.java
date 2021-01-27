/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.LignePenaliteDept;
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
public class LignePenaliteDeptFacade extends AbstractFacade<LignePenaliteDept> implements LignePenaliteDeptFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LignePenaliteDeptFacade() {
        super(LignePenaliteDept.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(l.idlignepenalitedept) FROM LignePenaliteDept l");
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
    public List<LignePenaliteDept> findByIdEvaluationPenaliteDept(long idEvaluationPenaliteDept) {
        Query query = em.createQuery("SELECT l FROM LignePenaliteDept l WHERE l.idevaluationpenalitedept.idevaluationpenalitedept=:idEvaluationPenaliteDept ORDER BY l.valeur");
        query.setParameter("idEvaluationPenaliteDept", idEvaluationPenaliteDept);
        return query.getResultList();
    }

    @Override
    public void deleteByIdEvaluation(long idEvaluationPenaliteDept) {
        Query query = em.createQuery("DELETE FROM LignePenaliteDept l WHERE l.idevaluationpenalitedept.idevaluationpenalitedept=:idEvaluationPenaliteDept");
        query.setParameter("idEvaluationPenaliteDept", idEvaluationPenaliteDept);
        query.executeUpdate();
    }

}
