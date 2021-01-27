/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.LignePenaliteDept;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface LignePenaliteDeptFacadeLocal {

    void create(LignePenaliteDept lignePenaliteDept);

    void edit(LignePenaliteDept lignePenaliteDept);

    void remove(LignePenaliteDept lignePenaliteDept);

    LignePenaliteDept find(Object id);

    List<LignePenaliteDept> findAll();

    List<LignePenaliteDept> findRange(int[] range);

    int count();

    Long nextId();

    List<LignePenaliteDept> findByIdEvaluationPenaliteDept(long idEvaluationPenaliteDept);

    void deleteByIdEvaluation(long idEvaluationPenaliteDept);

}
