/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Indicateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface IndicateurFacadeLocal {

    void create(Indicateur indicateur);

    void edit(Indicateur indicateur);

    void remove(Indicateur indicateur);

    Indicateur find(Object id);

    List<Indicateur> findAll();

    List<Indicateur> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Indicateur> findAllRangeCode();

}
