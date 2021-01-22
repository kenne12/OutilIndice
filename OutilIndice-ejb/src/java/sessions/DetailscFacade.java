/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Detailsc;
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
public class DetailscFacade extends AbstractFacade<Detailsc> implements DetailscFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetailscFacade() {
        super(Detailsc.class);
    }

    @Override
    public Long nextId() {
        try {
            Query query = em.createQuery("SELECT MAX(d.iddetailsc) FROM Detailsc d");
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
    public List<Detailsc> findByIdStructure(long idStructure) {
        Query query = em.createQuery("SELECT d FROM Detailsc d WHERE d.idstructure.idstructure=:idStructure ORDER BY d.idsouscritere.code");
        query.setParameter("idStructure", idStructure);
        return query.getResultList();
    }

    @Override
    public List<Detailsc> findByIdStructure(long idStructure, boolean personnel) {
        Query query = em.createQuery("SELECT d FROM Detailsc d WHERE d.idstructure.idstructure=:idStructure AND d.idsouscritere.personnel=:personnel ORDER BY d.idsouscritere.code");
        query.setParameter("idStructure", idStructure).setParameter("personnel", personnel);
        return query.getResultList();
    }
    
    @Override
    public List<Detailsc> findByIdStructureIdCritere(long idStructure, int idCritere) {
        Query query = em.createQuery("SELECT d FROM Detailsc d WHERE d.idstructure.idstructure=:idStructure AND d.idsouscritere.idcritere.idcritere=:idCritere ORDER BY d.idsouscritere.code");
        query.setParameter("idStructure", idStructure).setParameter("idCritere", idCritere);
        return query.getResultList();
    }

    @Override
    public List<Detailsc> findByIdStructure(long idStructure, int idCategorie) {
        Query query = em.createQuery("SELECT d FROM Detailsc d WHERE d.idstructure.idstructure=:idStructure AND d.idcategorie.idcategorie=:idCategorie ORDER BY d.idsouscritere.code");
        query.setParameter("idStructure", idStructure).setParameter("idCategorie", idCategorie);
        return query.getResultList();
    }

    @Override
    public List<Detailsc> findByIdStructurePersonnel(long idStructure, int idCategorie) {
        Query query = em.createQuery("SELECT d FROM Detailsc d WHERE d.idstructure.idstructure=:idStructure AND d.idcategorie.idcategorie=:idCategorie ORDER BY d.idsouscritere.code");
        query.setParameter("idStructure", idStructure).setParameter("idCategorie", idCategorie);
        return query.getResultList();
    }

    @Override
    public List<Detailsc> findByIdStructurePersonnel(long idStructure, int idCategorie, boolean personnel) {
        Query query = em.createQuery("SELECT d FROM Detailsc d WHERE d.idstructure.idstructure=:idStructure AND d.idcategorie.idcategorie=:idCategorie AND d.idsouscritere.personnel=:personnel ORDER BY d.idsouscritere.code");
        query.setParameter("idStructure", idStructure).setParameter("idCategorie", idCategorie).setParameter("personnel", personnel);
        return query.getResultList();
    }

}
