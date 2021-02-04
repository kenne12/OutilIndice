/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationpersonnel;
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
public class EvaluationpersonnelFacade extends AbstractFacade<Evaluationpersonnel> implements EvaluationpersonnelFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationpersonnelFacade() {
        super(Evaluationpersonnel.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idevaluationpersonnel) FROM Evaluationpersonnel e");
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
    public List<Evaluationpersonnel> findByPersonnel(long idPersonnel, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT e FROM Evaluationpersonnel e WHERE e.idnote.idpersonnel.idpersonnel=:idPersonnel AND e.idnote.idperiode.idperiode=:idPeriode AND e.idnote.idsousperiode.idsousperiode=:idSousPeriode");
        query.setParameter("idPersonnel", idPersonnel).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        return query.getResultList();
    }

    @Override
    public void deleteData(long idPersonnel, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("DELETE FROM Evaluationpersonnel e WHERE e.idnote.idpersonnel.idpersonnel=:idPersonnel AND e.idnote.idperiode.idperiode=:idPeriode AND e.idnote.idsousperiode.idsousperiode=:idSousPeriode");
        query.setParameter("idPersonnel", idPersonnel).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        query.executeUpdate();
    }
    
    @Override
    public void deleteData(long idNote) {
        Query query = em.createQuery("DELETE FROM Evaluationpersonnel e WHERE e.idnote.idnote=:idNote");
        query.setParameter("idNote", idNote);
        query.executeUpdate();
    }

}
