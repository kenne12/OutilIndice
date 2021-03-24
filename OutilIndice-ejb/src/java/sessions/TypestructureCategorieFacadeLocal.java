/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypestructureCategorie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TypestructureCategorieFacadeLocal {

    void create(TypestructureCategorie typestructureCategorie);

    void edit(TypestructureCategorie typestructureCategorie);

    void remove(TypestructureCategorie typestructureCategorie);

    TypestructureCategorie find(Object id);

    List<TypestructureCategorie> findAll();

    List<TypestructureCategorie> findRange(int[] range);

    int count();
    
}
