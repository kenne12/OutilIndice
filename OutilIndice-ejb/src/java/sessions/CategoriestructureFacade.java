/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Categoriestructure;
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
public class CategoriestructureFacade extends AbstractFacade<Categoriestructure> implements CategoriestructureFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriestructureFacade() {
        super(Categoriestructure.class);
    }

    @Override
    public List<Categoriestructure> findByIdStructure(long idStructure) {
        Query query = em.createQuery("SELECT c FROM Categoriestructure c WHERE c.structure.idstructure=:idStructure ORDER BY c.categorie.code");
        query.setParameter("idStructure", idStructure);
        return query.getResultList();
    }

    @Override
    public Categoriestructure findByIdStructureIdCategorie(long idStructure, int idCategorie) {
        Query query = em.createQuery("SELECT c FROM Categoriestructure c WHERE c.structure.idstructure=:idStructure AND c.categorie.idcategorie=:idCategorie");
        query.setParameter("idStructure", idStructure).setParameter("idCategorie", idCategorie);
        List categoriestructures = query.getResultList();
        if (!categoriestructures.isEmpty()) {
            return (Categoriestructure) categoriestructures.get(0);
        }

        return null;
    }

}
