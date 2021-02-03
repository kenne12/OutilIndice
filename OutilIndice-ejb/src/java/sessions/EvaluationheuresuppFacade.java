/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationheuresupp;
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
public class EvaluationheuresuppFacade extends AbstractFacade<Evaluationheuresupp> implements EvaluationheuresuppFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationheuresuppFacade() {
        super(Evaluationheuresupp.class);
    }
    
    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idevaluationheuresupp) FROM Evaluationheuresupp e");
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
    public Evaluationheuresupp findByIdPersonnel(int idPeriode, int idSousPeriode, long idPersonnel, int idCritere) {
        Query query = em.createQuery("SELECT e FROM Evaluationheuresupp e WHERE e.idperiode.idperiode=:idPeriode AND e.idsousperiode.idsousperiode=:idSousPeriode AND e.idpersonnel.idpersonnel=:idPersonnel AND e.idcritere.idcritere=:idCritere");
        query.setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        query.setParameter("idPersonnel", idPersonnel).setParameter("idCritere", idCritere);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (Evaluationheuresupp) list.get(0);
        }
        return null;
    }
    
    @Override
    public void deleteByIdNote(Long idNote) {
        Query query = em.createQuery("DELETE FROM Evaluationheuresupp e WHERE e.idnote.idnote=:idNote");
        query.setParameter("idNote", idNote);
        query.executeUpdate();
    }

}
