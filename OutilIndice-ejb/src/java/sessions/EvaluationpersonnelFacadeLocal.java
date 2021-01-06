/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationpersonnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationpersonnelFacadeLocal {

    void create(Evaluationpersonnel evaluationpersonnel);

    void edit(Evaluationpersonnel evaluationpersonnel);

    void remove(Evaluationpersonnel evaluationpersonnel);

    Evaluationpersonnel find(Object id);

    List<Evaluationpersonnel> findAll();

    List<Evaluationpersonnel> findRange(int[] range);

    int count();

    Long nextId();

    List<Evaluationpersonnel> findByPersonnel(long idPersonnel, int periode, int idSousPeriode);

    void deleteData(long idPersonnel, int idPeriode, int idSousPeriode);

}
