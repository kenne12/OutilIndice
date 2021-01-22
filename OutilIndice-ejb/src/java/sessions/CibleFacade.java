/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Cible;
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
public class CibleFacade extends AbstractFacade<Cible> implements CibleFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CibleFacade() {
        super(Cible.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(c.idcible) FROM Cible c");
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
    public List<Cible> findByIdSousPeriode(long idService, int idPeriode, int idSousPeriode, int idCritere) {
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.idservice.idservice=:idService AND c.idperiode.idperiode=:idPeriode AND c.idsousperiode.idsousperiode=:idSousPeriode AND c.idcritere.idcritere=:idCritere ORDER BY c.idindicateur.nom");
        query.setParameter("idService", idService).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode).setParameter("idCritere", idCritere);
        return query.getResultList();
    }

    @Override
    public List<Cible> findByIdStructureSousPeriode(long idStructure, int idPeriode, int idSousPeriode, int idCritere) {
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.idstructure.idstructure=:idStructure AND c.idperiode.idperiode=:idPeriode AND c.idsousperiode.idsousperiode=:idSousPeriode AND c.idcritere.idcritere=:idCritere ORDER BY c.idservice.nom");
        query.setParameter("idStructure", idStructure).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode).setParameter("idCritere", idCritere);
        return query.getResultList();
    }

    @Override
    public Cible findByIdSousPeriodeOneLine(long idService, int idPeriode, int idSousPeriode, int idCritere) {
        Query query = em.createQuery("SELECT c FROM Cible c WHERE c.idservice.idservice=:idService AND c.idperiode.idperiode=:idPeriode AND c.idsousperiode.idsousperiode=:idSousPeriode AND c.idcritere.idcritere=:idCritere ORDER BY c.idindicateur.nom");
        query.setParameter("idService", idService).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode).setParameter("idCritere", idCritere);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (Cible) list.get(0);
        }
        return null;
    }
}
