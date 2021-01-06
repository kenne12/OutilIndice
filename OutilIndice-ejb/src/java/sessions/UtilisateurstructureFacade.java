/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Utilisateurstructure;
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
public class UtilisateurstructureFacade extends AbstractFacade<Utilisateurstructure> implements UtilisateurstructureFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurstructureFacade() {
        super(Utilisateurstructure.class);
    }

    @Override
    public List<Utilisateurstructure> findByIdutilisateur(int idUtilisateur) {
        Query query = em.createQuery("SELECT u FROM Utilisateurstructure u WHERE u.utilisateur.idutilisateur=:idUtilisateur");
        query.setParameter("idUtilisateur", idUtilisateur);
        return query.getResultList();
    }

    @Override
    public void deleteByIdutilisateur(int idUtilisateur) {
        Query query = em.createQuery("DELETE FROM Utilisateurstructure u WHERE u.utilisateur.idutilisateur=:idUtilisateur");
        query.setParameter("idUtilisateur", idUtilisateur);
        query.executeUpdate();
    }

    @Override
    public Utilisateurstructure findByIdutilisateurIdstructure(int idUtilisateur, long idStructure) {
        Query query = em.createQuery("SELECT u FROM Utilisateurstructure u WHERE u.utilisateur.idutilisateur=:idUtilisateur AND u.structure.idstructure=:idStructure");
        query.setParameter("idUtilisateur", idUtilisateur).setParameter("idStructure", idStructure);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            return (Utilisateurstructure) list.get(0);
        }
        return null;
    }

}
