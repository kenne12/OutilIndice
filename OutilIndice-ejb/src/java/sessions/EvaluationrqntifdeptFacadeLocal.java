/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationrqntifdept;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationrqntifdeptFacadeLocal {

    void create(Evaluationrqntifdept evaluationrqntifdept);

    void edit(Evaluationrqntifdept evaluationrqntifdept);

    void remove(Evaluationrqntifdept evaluationrqntifdept);

    Evaluationrqntifdept find(Object id);

    List<Evaluationrqntifdept> findAll();

    List<Evaluationrqntifdept> findRange(int[] range);

    int count();

    Long nextId();

    List<Evaluationrqntifdept> findByIdPersonnel(long idPersonnel, int idPeriode, int idSousPeriode, int idCritere);

    void deleteByIdNote(Long idNote);

}
