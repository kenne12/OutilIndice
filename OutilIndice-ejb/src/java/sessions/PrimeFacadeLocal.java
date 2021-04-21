/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Prime;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface PrimeFacadeLocal {

    void create(Prime prime);

    void edit(Prime prime);

    void remove(Prime prime);

    Prime find(Object id);

    List<Prime> findAll();

    List<Prime> findRange(int[] range);

    int count();

    Long nextId();

    List<Prime> findByIdSousPeriode(long idStructure, int idPeriode, int idSousPeriode);

    void deleteByIdNote(long idNote);

}
