/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationservice;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationserviceFacadeLocal {

    void create(Evaluationservice evaluationservice);

    void edit(Evaluationservice evaluationservice);

    void remove(Evaluationservice evaluationservice);

    Evaluationservice find(Object id);

    List<Evaluationservice> findAll();

    List<Evaluationservice> findRange(int[] range);

    int count();

    Long nextId();

    List<Evaluationservice> findByService(long idService, int idPeriode, int idSousPeriode);

    void deleteData(long idService, int idPeriode, int idSousPeriode);

}
