/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.LignePrimeQualiteDept;
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
public class LignePrimeQualiteDeptFacade extends AbstractFacade<LignePrimeQualiteDept> implements LignePrimeQualiteDeptFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LignePrimeQualiteDeptFacade() {
        super(LignePrimeQualiteDept.class);
    }
    
    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(l.idligneprimequalitedept) FROM LignePrimeQualiteDept l");
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
    public List<LignePrimeQualiteDept> findByIdEvaluation(long idEvaluation) {
        Query query = em.createQuery("SELECT l FROM LignePrimeQualiteDept l WHERE l.idEvaluationRPrimeQltifDept.idevaluationrprimeqltifdept=:idEvaluation ORDER BY l.idsouscritereservice.idsouscritere.code");
        query.setParameter("idEvaluation", idEvaluation);
        return query.getResultList();
    }

}
