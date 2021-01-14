/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Parametragecritere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ParametragecritereFacadeLocal {

    void create(Parametragecritere parametragecritere);

    void edit(Parametragecritere parametragecritere);

    void remove(Parametragecritere parametragecritere);

    Parametragecritere find(Object id);

    List<Parametragecritere> findAll();

    List<Parametragecritere> findRange(int[] range);

    int count();

    Long nextId();

    List<Parametragecritere> findByIdStructureHs(long idStructure, int idCritere, boolean hs);

    List<Parametragecritere> findByIdStructureHp(long idStructure, int idCritere, boolean hp);

    List<Parametragecritere> findByIdStructurePp(long idStructure, int idCritere, boolean pratiquePrivee);

    List<Parametragecritere> findByIdStructurePrqd(long idStructure, int idCritere, boolean prqd);

    List<Parametragecritere> findByIdStructurePpi(long idStructure, int idCritere, boolean ppi);

    List<Parametragecritere> findByIdStructureBrd(long idStructure, int idCritere, boolean brd);

}
