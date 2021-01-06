/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Utilisateur;
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
public class UtilisateurFacade extends AbstractFacade<Utilisateur> implements UtilisateurFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(u.idutilisateur) FROM Utilisateur u");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public Utilisateur login(String login, String password) throws Exception {
        Utilisateur utilisateur = null;
        Query query = this.em.createQuery("SELECT u FROM Utilisateur U WHERE u.login=:login AND u.password=:password");
        query.setParameter("login", login).setParameter("password", password);
        if (!query.getResultList().isEmpty()) {
            utilisateur = (Utilisateur) query.getResultList().get(0);
        }
        return utilisateur;
    }

    @Override
    public List<Utilisateur> findByActif(Boolean actif) {
        Query query = this.em.createQuery("SELECT u FROM Utilisateur u WHERE u.actif=:actif ORDER BY u.nom,u.prenom");
        query.setParameter("actif", actif);
        return query.getResultList();
    }
}
