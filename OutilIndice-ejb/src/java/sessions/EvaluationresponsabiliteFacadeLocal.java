/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evaluationresponsabilite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EvaluationresponsabiliteFacadeLocal {

    void create(Evaluationresponsabilite evaluationresponsabilite);

    void edit(Evaluationresponsabilite evaluationresponsabilite);

    void remove(Evaluationresponsabilite evaluationresponsabilite);

    Evaluationresponsabilite find(Object id);

    List<Evaluationresponsabilite> findAll();

    List<Evaluationresponsabilite> findRange(int[] range);

    int count();

    Long nextId();

    Evaluationresponsabilite findByIdPersonnel(int idPeriode, int idSousPeriode, long idPersonnel, int idCritere);

    void deleteByIdNote(Long idNote);

}
