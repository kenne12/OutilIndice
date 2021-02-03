/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationbonuspp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationbonusppFacadeLocal {

    void create(Evaluationbonuspp evaluationbonuspp);

    void edit(Evaluationbonuspp evaluationbonuspp);

    void remove(Evaluationbonuspp evaluationbonuspp);

    Evaluationbonuspp find(Object id);

    List<Evaluationbonuspp> findAll();

    List<Evaluationbonuspp> findRange(int[] range);

    int count();

    Long nextId();

    Evaluationbonuspp findByIdPersonnel(int idPeriode, int idSousPeriode, long idPersonnel, int idCritere);

    void deleteByIdNote(Long idNote);

}
