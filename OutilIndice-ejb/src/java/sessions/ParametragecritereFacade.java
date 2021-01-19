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
        Query query = em.createQuery("SELECT p FROM Parametragecritere p WHERE p.idstructure.idstructure=:idStructure AND p.idcritere.idcritere=:idCritere AND p.heuresupp=:hs ORDER BY p.idcategorie.nom");
        query.setParameter("idStructure", idStructure).setParameter("hs", hs).setParameter("idCritere", idCritere);
        return query.getResultList();
    }

    @Override
    public List<Parametragecritere> findByIdStructureHp(long idStructure, int idCritere, boolean hp) {
        Query query = em.createQuery("SELECT p FROM Parametragecritere p WHERE p.idstructure.idstructure=:idStructure AND p.idcritere.idcritere=:idCritere AND p.heuresupn=:hp ORDER BY p.idcategorie.nom");
        query.setParameter("idStructure", idStructure).setParameter("hp", hp).setParameter("idCritere", idCritere);
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

    @Override
    public List<Parametragecritere> findByIdStructurePpi(long idStructure, int idCritere, boolean ppi) {
        Query query = em.createQuery("SELECT p FROM Parametragecritere p WHERE p.idstructure.idstructure=:idStructure AND p.idcritere.idcritere=:idCritere AND p.performanceindividuelle=:ppi ORDER BY p.idcategorie.nom");
        query.setParameter("idStructure", idStructure).setParameter("ppi", ppi).setParameter("idCritere", idCritere);
        return query.getResultList();
    }

    @Override
    public List<Parametragecritere> findByIdStructureBrd(long idStructure, int idCritere, boolean brd) {
        Query query = em.createQuery("SELECT p FROM Parametragecritere p WHERE p.idstructure.idstructure=:idStructure AND p.idcritere.idcritere=:idCritere AND p.bonusrevenudept=:brd ORDER BY p.idservice.nom");
        query.setParameter("idStructure", idStructure).setParameter("brd", brd).setParameter("idCritere", idCritere);
        return query.getResultList();
    }

    @Override
    public Parametragecritere findByIdStructureIdCategorie(long idStructure, int idCritere, int idCategorie) {
        Query query = em.createQuery("SELECT p FROM Parametragecritere p WHERE p.idstructure.idstructure=:idStructure AND p.idcritere.idcritere=:idCritere AND p.idcategorie.idcategorie=:idCategorie");
        query.setParameter("idStructure", idStructure).setParameter("idCategorie", idCategorie).setParameter("idCritere", idCritere);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (Parametragecritere) list.get(0);
        }
        return null;
    }

}
