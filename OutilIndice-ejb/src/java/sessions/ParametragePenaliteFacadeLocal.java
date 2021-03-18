/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.ParametragePenalite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ParametragePenaliteFacadeLocal {

    void create(ParametragePenalite parametragePenalite);

    void edit(ParametragePenalite parametragePenalite);

    void remove(ParametragePenalite parametragePenalite);

    ParametragePenalite find(Object id);

    List<ParametragePenalite> findAll();

    List<ParametragePenalite> findRange(int[] range);

    int count();

    Integer nextId();

    List<ParametragePenalite> findByIdServiceIdCritere(long idStructure, long idService, int idCritere);

    List<ParametragePenalite> findByIdStructureIdCritere(long idStructure, int idCritere);

}
