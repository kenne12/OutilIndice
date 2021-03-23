/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypestructureService;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TypestructureServiceFacadeLocal {

    void create(TypestructureService typestructureService);

    void edit(TypestructureService typestructureService);

    void remove(TypestructureService typestructureService);

    TypestructureService find(Object id);

    List<TypestructureService> findAll();

    List<TypestructureService> findRange(int[] range);

    int count();

    Integer nextId();

    List<TypestructureService> findByIdTypestructure(long idTypeStructure);

}
