/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Noteservice;
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
public class NoteserviceFacade extends AbstractFacade<Noteservice> implements NoteserviceFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoteserviceFacade() {
        super(Noteservice.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(n.idnoteservice) FROM Noteservice n");
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
    public List<Noteservice> findByIdSousPeriode(long idStructure, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT n FROM Noteservice n WHERE n.idservice.idstructure.idstructure=:idStructure AND n.idperiode.idperiode=:idPeriode AND n.idsousperiode.idsousperiode=:idSousPeriode");
        query.setParameter("idStructure", idStructure).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        return query.getResultList();
    }

    @Override
    public Noteservice findByIdService(long idService, int idPeriode, int idSousPeriode) {
        Query query = em.createQuery("SELECT n FROM Noteservice n WHERE n.idservice.idservice=:idService AND n.idperiode.idperiode=:idPeriode AND n.idsousperiode.idsousperiode=:idSousPeriode");
        query.setParameter("idService", idService).setParameter("idPeriode", idPeriode).setParameter("idSousPeriode", idSousPeriode);
        List<Noteservice> list = query.getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}
