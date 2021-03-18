/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvaluationRPrimeQltifDept;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationRPrimeQltifDeptFacadeLocal {

    void create(EvaluationRPrimeQltifDept evaluationRPrimeQltifDept);

    void edit(EvaluationRPrimeQltifDept evaluationRPrimeQltifDept);

    void remove(EvaluationRPrimeQltifDept evaluationRPrimeQltifDept);

    EvaluationRPrimeQltifDept find(Object id);

    List<EvaluationRPrimeQltifDept> findAll();

    List<EvaluationRPrimeQltifDept> findRange(int[] range);

    int count();

    Long nextId();

    List<EvaluationRPrimeQltifDept> findByIdStructureSousPeriode(long idStructure, int idPeriode, int idSousPeriode, int idCritere);

    EvaluationRPrimeQltifDept findByIdService(long idStructure, long idService, int idPeriode, int idSousPeriode, int idCritere);

}
