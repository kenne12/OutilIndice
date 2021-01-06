/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Structure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface StructureFacadeLocal {

    void create(Structure structure);

    void edit(Structure structure);

    void remove(Structure structure);

    Structure find(Object id);

    List<Structure> findAll();

    List<Structure> findRange(int[] range);

    int count();

    Long nextVal();

}
