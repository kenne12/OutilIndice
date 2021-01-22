/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvaluationBonusRDeptPersonnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationBonusRDeptPersonnelFacadeLocal {

    void create(EvaluationBonusRDeptPersonnel evaluationBonusRDeptPersonnel);

    void edit(EvaluationBonusRDeptPersonnel evaluationBonusRDeptPersonnel);

    void remove(EvaluationBonusRDeptPersonnel evaluationBonusRDeptPersonnel);

    EvaluationBonusRDeptPersonnel find(Object id);

    List<EvaluationBonusRDeptPersonnel> findAll();

    List<EvaluationBonusRDeptPersonnel> findRange(int[] range);

    int count();

    Long nextId();

    EvaluationBonusRDeptPersonnel findByIdPersonnel(long idPersonnel, int idPeriode, int idSousPeriode);

}
