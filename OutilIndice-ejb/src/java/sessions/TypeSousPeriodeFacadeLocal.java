/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypeSousPeriode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TypeSousPeriodeFacadeLocal {

    void create(TypeSousPeriode typeSousPeriode);

    void edit(TypeSousPeriode typeSousPeriode);

    void remove(TypeSousPeriode typeSousPeriode);

    TypeSousPeriode find(Object id);

    List<TypeSousPeriode> findAll();

    List<TypeSousPeriode> findRange(int[] range);

    int count();

    Integer nextId();

    List<TypeSousPeriode> findAllOrderByCode();

}
