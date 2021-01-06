/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Souscritere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface SouscritereFacadeLocal {

    void create(Souscritere souscritere);

    void edit(Souscritere souscritere);

    void remove(Souscritere souscritere);

    Souscritere find(Object id);

    List<Souscritere> findAll();

    List<Souscritere> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Souscritere> findAllRangeByCode();

    List<Souscritere> findByIdCritere(int idCritere);

    List<Souscritere> findByIdCriterePersonnel(int idCritere);

    List<Souscritere> findByIdCritereService(int idCritere);

}
