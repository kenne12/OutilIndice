/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.LignePenalitePersonnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface LignePenalitePersonnelFacadeLocal {

    void create(LignePenalitePersonnel lignePenalitePersonnel);

    void edit(LignePenalitePersonnel lignePenalitePersonnel);

    void remove(LignePenalitePersonnel lignePenalitePersonnel);

    LignePenalitePersonnel find(Object id);

    List<LignePenalitePersonnel> findAll();

    List<LignePenalitePersonnel> findRange(int[] range);

    int count();

    Long nextId();

    List<LignePenalitePersonnel> findByIdEvaluation(long idEvaluation);

    void deleteByIdEvaluation(long idEvaluation);

}
