/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypestructureTypeSousperiode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TypestructureTypeSousperiodeFacadeLocal {

    void create(TypestructureTypeSousperiode typestructureTypeSousperiode);

    void edit(TypestructureTypeSousperiode typestructureTypeSousperiode);

    void remove(TypestructureTypeSousperiode typestructureTypeSousperiode);

    TypestructureTypeSousperiode find(Object id);

    List<TypestructureTypeSousperiode> findAll();

    List<TypestructureTypeSousperiode> findRange(int[] range);

    int count();

    Integer nextId();

    List<TypestructureTypeSousperiode> findByIdTypestructure(long idTypeStructure);

}
