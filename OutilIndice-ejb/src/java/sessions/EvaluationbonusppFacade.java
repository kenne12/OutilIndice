/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationbonuspp;
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
public class EvaluationbonusppFacade extends AbstractFacade<Evaluationbonuspp> implements EvaluationbonusppFacadeLocal {
    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationbonusppFacade() {
        super(Evaluationbonuspp.class);
    }
    
    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idevaluationbonuspp) FROM Evaluationbonuspp e");
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
    public Evaluationbonuspp findByIdPersonnel(int idPeriode, int idSousPeriode, long idPersonnel, int idCritere) {
        Query query = em.createQuery("SELECT e FROM Evaluationbonuspp e WHERE e.idnote.idperiode.idperiode=:idPeriode AND e.idnote.idsousperiode.idsousperiode=:idSousPeriode AND e.idnote.idpersonnel.idpersonnel=:idPersonnel AND e.idcritere.idcritere=:idCritere");
        query.setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        query.setParameter("idPersonnel", idPersonnel).setParameter("idCritere", idCritere);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (Evaluationbonuspp) list.get(0);
        }
        return null;
    }
    
    @Override
    public void deleteByIdNote(Long idNote) {
        Query query = em.createQuery("DELETE FROM Evaluationbonuspp e WHERE e.idnote.idnote=:idNote");
        query.setParameter("idNote", idNote);
        query.executeUpdate();
    }
    
}
