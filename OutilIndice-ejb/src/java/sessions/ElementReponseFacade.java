/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Elementreponse;
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
public class ElementReponseFacade extends AbstractFacade<Elementreponse> implements ElementReponseFacadeLocal {

    @PersistenceContext(unitName = "OutilIndice-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElementReponseFacade() {
        super(Elementreponse.class);
    }

    @Override
    public Integer nextVal() {
        Query query = this.em.createQuery("SELECT MAX(e.idelementreponse) FROM Elementreponse e");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result = result + 1;
        }
        return result;
    }

    @Override
    public List<Elementreponse> findAllRange() {
        Query query = em.createQuery("SELECT e FROM Elementreponse e ORDER BY e.idsouscritere.code, e.valeur");
        return query.getResultList();
    }

    @Override
    public List<Elementreponse> findByIdSousCritere(int idSousCritere) {
        Query query = em.createQuery("SELECT e FROM Elementreponse e WHERE e.idsouscritere.idsouscritere=:idSousCritere ORDER BY e.valeur");
        query.setParameter("idSousCritere", idSousCritere);
        return query.getResultList();
    }

}
