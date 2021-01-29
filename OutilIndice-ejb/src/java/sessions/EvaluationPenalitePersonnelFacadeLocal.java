/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvaluationPenalitePersonnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationPenalitePersonnelFacadeLocal {

    void create(EvaluationPenalitePersonnel evaluationPenalitePersonnel);

    void edit(EvaluationPenalitePersonnel evaluationPenalitePersonnel);

    void remove(EvaluationPenalitePersonnel evaluationPenalitePersonnel);

    EvaluationPenalitePersonnel find(Object id);

    List<EvaluationPenalitePersonnel> findAll();

    List<EvaluationPenalitePersonnel> findRange(int[] range);

    int count();

    Long nextId();

    EvaluationPenalitePersonnel findIdPersonnelIdPeriode(long idPersonnel, int idPeriode, int idSousPeriode);

    List<EvaluationPenalitePersonnel> findIdServiceIdPeriode(long idService, int idPeriode, int idSousPeriode);

}
