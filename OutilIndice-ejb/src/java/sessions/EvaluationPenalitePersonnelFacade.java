/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvaluationPenalitePersonnel;
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
public class EvaluationPenalitePersonnelFacade extends AbstractFacade<EvaluationPenalitePersonnel> implements EvaluationPenalitePersonnelFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationPenalitePersonnelFacade() {
        super(EvaluationPenalitePersonnel.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idevaluationpenalitepersonnel) FROM EvaluationPenalitePersonnel e");
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
    public EvaluationPenalitePersonnel findIdPersonnelIdPeriode(long idPersonnel, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT e FROM EvaluationPenalitePersonnel e WHERE e.idnote.idpersonnel.idpersonnel=:idPersonnel AND e.idnote.idperiode.idperiode=:idPeriode AND e.idnote.idsousperiode.idsousperiode=:idSousPeriode");
        query.setParameter("idPersonnel", idPersonnel).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (EvaluationPenalitePersonnel) list.get(0);
        }
        return null;
    }

    @Override
    public List<EvaluationPenalitePersonnel> findIdServiceIdPeriode(long idService, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT e FROM EvaluationPenalitePersonnel e WHERE e.idnote.idpersonnel.idservice.idservice=:idService AND e.idnote.idperiode.idperiode=:idPeriode AND e.idnote.idsousperiode.idsousperiode=:idSousPeriode");
        query.setParameter("idService", idService).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        return query.getResultList();
    }

    @Override
    public void deleteByIdNote(Long idNote) {
        Query query = em.createQuery("DELETE FROM EvaluationPenalitePersonnel e WHERE e.idnote.idnote=:idNote");
        query.setParameter("idNote", idNote);
        query.executeUpdate();
    }

}
