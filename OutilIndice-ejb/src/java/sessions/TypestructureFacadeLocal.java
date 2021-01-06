/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Typestructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TypestructureFacadeLocal {

    void create(Typestructure typestructure);

    void edit(Typestructure typestructure);

    void remove(Typestructure typestructure);

    Typestructure find(Object id);

    List<Typestructure> findAll();

    List<Typestructure> findRange(int[] range);

    int count();

    Long nextVal();

    List<Typestructure> findAllRangeByNom();

}
