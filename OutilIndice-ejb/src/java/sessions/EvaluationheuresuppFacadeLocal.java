/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationheuresupp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationheuresuppFacadeLocal {

    void create(Evaluationheuresupp evaluationheuresupp);

    void edit(Evaluationheuresupp evaluationheuresupp);

    void remove(Evaluationheuresupp evaluationheuresupp);

    Evaluationheuresupp find(Object id);

    List<Evaluationheuresupp> findAll();

    List<Evaluationheuresupp> findRange(int[] range);

    int count();

    Long nextId();

    Evaluationheuresupp findByIdPersonnel(int idPeriode, int idSousPeriode, long idPersonnel, int idCritere);

    void deleteByIdNote(Long idNote);

}
