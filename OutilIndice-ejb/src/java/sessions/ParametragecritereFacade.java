/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Parametragecritere;
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
public class ParametragecritereFacade extends AbstractFacade<Parametragecritere> implements ParametragecritereFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametragecritereFacade() {
        super(Parametragecritere.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(p.idparametragecritere) FROM Parametragecritere p");
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
    public List<Parametragecritere> findByIdStructureHs(long idStructure, int idCritere, boolean hs) {
        Query query = em.createQuery("SELECT p FROM Parametragecritere p WHERE p.idstructure.idstructure=:idStructure AND p.heuresupp=:hs ORDER BY p.idcategorie.nom");
        query.setParameter("idStructure", idStructure).setParameter("hs", hs);
        return query.getResultList();
    }

    @Override
    public List<Parametragecritere> findByIdStructurePp(long idStructure, int idCritere, boolean pratiquePrivee) {
        Query query = em.createQuery("SELECT p FROM Parametragecritere p WHERE p.idstructure.idstructure=:idStructure AND p.idcritere.idcritere=:idCritere AND p.pratiqueprivee=:pratiquePrivee ORDER BY p.idcategorie.nom");
        query.setParameter("idStructure", idStructure).setParameter("pratiquePrivee", pratiquePrivee).setParameter("idCritere", idCritere);
        return query.getResultList();
    }
    
    @Override
    public List<Parametragecritere> findByIdStructurePrqd(long idStructure, int idCritere, boolean prqd) {
        Query query = em.createQuery("SELECT p FROM Parametragecritere p WHERE p.idstructure.idstructure=:idStructure AND p.idcritere.idcritere=:idCritere AND p.resultatqualitatifdept=:prqp ORDER BY p.idcategorie.nom");
        query.setParameter("idStructure", idStructure).setParameter("prqp", prqd).setParameter("idCritere", idCritere);
        return query.getResultList();
    }

}
