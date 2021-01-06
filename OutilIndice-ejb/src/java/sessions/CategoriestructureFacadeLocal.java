/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Categoriestructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CategoriestructureFacadeLocal {

    void create(Categoriestructure categoriestructure);

    void edit(Categoriestructure categoriestructure);

    void remove(Categoriestructure categoriestructure);

    Categoriestructure find(Object id);

    List<Categoriestructure> findAll();

    List<Categoriestructure> findRange(int[] range);

    int count();

    List<Categoriestructure> findByIdStructure(long idStructure);

    Categoriestructure findByIdStructureIdCategorie(long idStructure, int idCategorie);

}
