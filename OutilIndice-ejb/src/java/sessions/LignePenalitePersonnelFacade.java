/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.LignePenalitePersonnel;
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
public class LignePenalitePersonnelFacade extends AbstractFacade<LignePenalitePersonnel> implements LignePenalitePersonnelFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LignePenalitePersonnelFacade() {
        super(LignePenalitePersonnel.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(l.idlignepenalitepersonnel) FROM LignePenalitePersonnel l");
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
    public List<LignePenalitePersonnel> findByIdEvaluation(long idEvaluation) {
        Query query = em.createQuery("SELECT l FROM LignePenalitePersonnel l WHERE l.idEvaluationPenalitePersonnel.idEvaluationPenalitePersonnel=:idEvaluation");
        query.setParameter("idEvaluation", idEvaluation);
        return query.getResultList();
    }

    @Override
    public void deleteByIdEvaluation(long idEvaluation) {
        Query query = em.createQuery("DELETE FROM LignePenalitePersonnel l WHERE l.idEvaluationPenalitePersonnel.idEvaluationPenalitePersonnel=:idEvaluation");
        query.setParameter("idEvaluation", idEvaluation);
        query.executeUpdate();
    }

}
