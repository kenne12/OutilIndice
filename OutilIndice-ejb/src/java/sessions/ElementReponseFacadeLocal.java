/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Elementreponse;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ElementReponseFacadeLocal {

    void create(Elementreponse elementReponse);

    void edit(Elementreponse elementReponse);

    void remove(Elementreponse elementReponse);

    Elementreponse find(Object id);

    List<Elementreponse> findAll();

    List<Elementreponse> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Elementreponse> findAllRange();

    List<Elementreponse> findByIdSousCritere(int idSousCritere);

}
