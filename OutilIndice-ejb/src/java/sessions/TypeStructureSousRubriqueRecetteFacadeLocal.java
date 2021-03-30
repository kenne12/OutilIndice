/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypeStructureSousRubriqueRecette;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TypeStructureSousRubriqueRecetteFacadeLocal {

    void create(TypeStructureSousRubriqueRecette typeStructureSousRubriqueRecette);

    void edit(TypeStructureSousRubriqueRecette typeStructureSousRubriqueRecette);

    void remove(TypeStructureSousRubriqueRecette typeStructureSousRubriqueRecette);

    TypeStructureSousRubriqueRecette find(Object id);

    List<TypeStructureSousRubriqueRecette> findAll();

    List<TypeStructureSousRubriqueRecette> findRange(int[] range);

    int count();
    
}
