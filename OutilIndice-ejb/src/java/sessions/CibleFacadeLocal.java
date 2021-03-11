/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Cible;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CibleFacadeLocal {

    void create(Cible cible);

    void edit(Cible cible);

    void remove(Cible cible);

    Cible find(Object id);

    List<Cible> findAll();

    List<Cible> findRange(int[] range);

    int count();

    Long nextId();

    List<Cible> findByIdSousPeriode(long idStructure, long idService, int idPeriode, int idSousPeriode, int idCritere);

    List<Cible> findByIdStructureSousPeriode(long idStructure, int idPeriode, int idSousPeriode, int idCritere);

    Cible findByIdSousPeriodeOneLine(long idService, int idPeriode, int idSousPeriode, int idCritere);

}
