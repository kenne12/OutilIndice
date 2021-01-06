/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquedepense;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface RubriquedepenseFacadeLocal {

    void create(Rubriquedepense rubriquedepense);

    void edit(Rubriquedepense rubriquedepense);

    void remove(Rubriquedepense rubriquedepense);

    Rubriquedepense find(Object id);

    List<Rubriquedepense> findAll();

    List<Rubriquedepense> findRange(int[] range);

    int count();

    Integer nextVal();
    
    List<Rubriquedepense> findAllRangeByCode();

}
