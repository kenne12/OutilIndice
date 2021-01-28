/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.LignePrimeQualiteDept;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface LignePrimeQualiteDeptFacadeLocal {

    void create(LignePrimeQualiteDept lignePrimeQualiteDept);

    void edit(LignePrimeQualiteDept lignePrimeQualiteDept);

    void remove(LignePrimeQualiteDept lignePrimeQualiteDept);

    LignePrimeQualiteDept find(Object id);

    List<LignePrimeQualiteDept> findAll();

    List<LignePrimeQualiteDept> findRange(int[] range);

    int count();

    Long nextId();

    List<LignePrimeQualiteDept> findByIdEvaluation(long idEvaluation);

}
