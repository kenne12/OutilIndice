/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypestructureResponsabilite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TypestructureResponsabiliteFacadeLocal {

    void create(TypestructureResponsabilite typestructureResponsabilite);

    void edit(TypestructureResponsabilite typestructureResponsabilite);

    void remove(TypestructureResponsabilite typestructureResponsabilite);

    TypestructureResponsabilite find(Object id);

    List<TypestructureResponsabilite> findAll();

    List<TypestructureResponsabilite> findRange(int[] range);

    int count();

    Integer nextId();

    List<TypestructureResponsabilite> findByIdTypestructure(long idTypeStructure);

}
