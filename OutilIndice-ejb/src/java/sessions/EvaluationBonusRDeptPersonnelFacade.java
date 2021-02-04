/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvaluationBonusRDeptPersonnel;
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
public class EvaluationBonusRDeptPersonnelFacade extends AbstractFacade<EvaluationBonusRDeptPersonnel> implements EvaluationBonusRDeptPersonnelFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationBonusRDeptPersonnelFacade() {
        super(EvaluationBonusRDeptPersonnel.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(e.idevaluationbonusrdeptpersonnel) FROM EvaluationBonusRDeptPersonnel e");
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
    public EvaluationBonusRDeptPersonnel findByIdPersonnel(long idPersonnel, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT e FROM EvaluationBonusRDeptPersonnel e WHERE e.idcible.idperiode.idperiode=:idPeriode AND e.idcible.idsousperiode.idsousperiode=:idSousPeriode AND e.idpersonnel.idpersonnel=:idPersonnel");
        query.setParameter("idPersonnel", idPersonnel).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (EvaluationBonusRDeptPersonnel) list.get(0);
        }
        return null;
    }
    
    @Override
    public void deleteByIdNote(Long idNote) {
        Query query = em.createQuery("DELETE FROM EvaluationBonusRDeptPersonnel e WHERE e.idnote.idnote=:idNote");
        query.setParameter("idNote", idNote);
        query.executeUpdate();
    }

}
