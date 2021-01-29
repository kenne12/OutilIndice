/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Detailsc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface DetailscFacadeLocal {

    void create(Detailsc detailsc);

    void edit(Detailsc detailsc);

    void remove(Detailsc detailsc);

    Detailsc find(Object id);

    List<Detailsc> findAll();

    List<Detailsc> findRange(int[] range);

    int count();

    Long nextId();

    List<Detailsc> findByIdStructure(long idStructure);

    List<Detailsc> findByIdStructureIdCritere(long idStructure, int idCritere);

    List<Detailsc> findByIdStructure(long idStructure, int idCategorie);

    List<Detailsc> findByIdStructurePersonnel(long idStructure, int idCategorie);

}
