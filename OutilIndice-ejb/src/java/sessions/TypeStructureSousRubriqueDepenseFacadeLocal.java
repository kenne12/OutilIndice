/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.TypeStructureSousRubriqueDepense;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface TypeStructureSousRubriqueDepenseFacadeLocal {

    void create(TypeStructureSousRubriqueDepense typeStructureSousRubriqueDepense);

    void edit(TypeStructureSousRubriqueDepense typeStructureSousRubriqueDepense);

    void remove(TypeStructureSousRubriqueDepense typeStructureSousRubriqueDepense);

    TypeStructureSousRubriqueDepense find(Object id);

    List<TypeStructureSousRubriqueDepense> findAll();

    List<TypeStructureSousRubriqueDepense> findRange(int[] range);

    int count();
    
}
