/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Criterestructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CriterestructureFacadeLocal {

    void create(Criterestructure criterestructure);

    void edit(Criterestructure criterestructure);

    void remove(Criterestructure criterestructure);

    Criterestructure find(Object id);

    List<Criterestructure> findAll();

    List<Criterestructure> findRange(int[] range);

    int count();

    List<Criterestructure> findByIdStructure(Long idstructure);

    List<Criterestructure> findByIdStructure(Long idstructure, boolean etat);

    Criterestructure findByIdStructureIdCritere(Long idstructure, int idCritere);

}
