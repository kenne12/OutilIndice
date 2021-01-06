/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Noteservice;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface NoteserviceFacadeLocal {

    void create(Noteservice noteservice);

    void edit(Noteservice noteservice);

    void remove(Noteservice noteservice);

    Noteservice find(Object id);

    List<Noteservice> findAll();

    List<Noteservice> findRange(int[] range);

    int count();

    Long nextId();

    List<Noteservice> findByIdSousPeriode(long idStructure, int idPeriode, int idSousPeriode);

    Noteservice findByIdService(long idService, int idPeriode, int idSousPeriode);

}
