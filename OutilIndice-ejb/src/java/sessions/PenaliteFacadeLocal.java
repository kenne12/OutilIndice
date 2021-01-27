/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Penalite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface PenaliteFacadeLocal {

    void create(Penalite penalite);

    void edit(Penalite penalite);

    void remove(Penalite penalite);

    Penalite find(Object id);

    List<Penalite> findAll();

    List<Penalite> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Penalite> findAllService();

    List<Penalite> findAllPersonnel();

}
