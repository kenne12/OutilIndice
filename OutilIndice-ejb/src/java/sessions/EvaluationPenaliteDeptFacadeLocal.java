/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvaluationPenaliteDept;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationPenaliteDeptFacadeLocal {

    void create(EvaluationPenaliteDept evaluationPenaliteDept);

    void edit(EvaluationPenaliteDept evaluationPenaliteDept);

    void remove(EvaluationPenaliteDept evaluationPenaliteDept);

    EvaluationPenaliteDept find(Object id);

    List<EvaluationPenaliteDept> findAll();

    List<EvaluationPenaliteDept> findRange(int[] range);

    int count();

    Long nextId();

    List<EvaluationPenaliteDept> findByIdStructure(long idStructure, int idPeriode, int idSousPeriode);

    EvaluationPenaliteDept findByIdService(long idService, int idPeriode, int idSousPeriode);

}
