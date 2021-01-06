/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sousrubriquedepense;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface SousrubriquedepenseFacadeLocal {

    void create(Sousrubriquedepense sousrubriquedepense);

    void edit(Sousrubriquedepense sousrubriquedepense);

    void remove(Sousrubriquedepense sousrubriquedepense);

    Sousrubriquedepense find(Object id);

    List<Sousrubriquedepense> findAll();

    List<Sousrubriquedepense> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Sousrubriquedepense> findAllRangeByCode();

    List<Sousrubriquedepense> findAllEtatPrime(boolean prime);

}
