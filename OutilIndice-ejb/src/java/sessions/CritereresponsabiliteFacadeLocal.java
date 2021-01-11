/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Critereresponsabilite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CritereresponsabiliteFacadeLocal {

    void create(Critereresponsabilite critereresponsabilite);

    void edit(Critereresponsabilite critereresponsabilite);

    void remove(Critereresponsabilite critereresponsabilite);

    Critereresponsabilite find(Object id);

    List<Critereresponsabilite> findAll();

    List<Critereresponsabilite> findRange(int[] range);

    int count();

    Long nextId();

    List<Critereresponsabilite> findByIdStructure(long idStructure);

}
