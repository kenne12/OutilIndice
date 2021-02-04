/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvaluationRPrimeQltifPersonnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationRPrimeQltifPersonnelFacadeLocal {

    void create(EvaluationRPrimeQltifPersonnel evaluationRPrimeQltifPersonnel);

    void edit(EvaluationRPrimeQltifPersonnel evaluationRPrimeQltifPersonnel);

    void remove(EvaluationRPrimeQltifPersonnel evaluationRPrimeQltifPersonnel);

    EvaluationRPrimeQltifPersonnel find(Object id);

    List<EvaluationRPrimeQltifPersonnel> findAll();

    List<EvaluationRPrimeQltifPersonnel> findRange(int[] range);

    int count();

    Long nextId();

    EvaluationRPrimeQltifPersonnel findByIdPersonnel(long idPersonnel, int idPeriode, int idSousPeriode);

    void deleteByIdNote(Long idNote);

}
