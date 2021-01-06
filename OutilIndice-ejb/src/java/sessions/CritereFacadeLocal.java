/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Critere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CritereFacadeLocal {

    void create(Critere critere);

    void edit(Critere critere);

    void remove(Critere critere);

    Critere find(Object id);

    List<Critere> findAll();

    List<Critere> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Critere> findAllRangeByCode();

}
