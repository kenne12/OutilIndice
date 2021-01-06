/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquerecette;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface RubriquerecetteFacadeLocal {

    void create(Rubriquerecette rubriquerecette);

    void edit(Rubriquerecette rubriquerecette);

    void remove(Rubriquerecette rubriquerecette);

    Rubriquerecette find(Object id);

    List<Rubriquerecette> findAll();

    List<Rubriquerecette> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Rubriquerecette> findAllRangeByCode();

}
