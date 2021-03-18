/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Note;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface NoteFacadeLocal {

    void create(Note note);

    void edit(Note note);

    void remove(Note note);

    Note find(Object id);

    List<Note> findAll();

    List<Note> findRange(int[] range);

    int count();

    Long nextId();

    Note findByIdPersonnel(long idPersonnel, int idPeriode, int idSousPeriode);

    List<Note> findByIdService(long idStructure, long idService, int idPeriode, int idSousPeriode);

    List<Note> findByIdSousPeriode(long idStructure, int idPeriode, int idSousPeriode);

}
