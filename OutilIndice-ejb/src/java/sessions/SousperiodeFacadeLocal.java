/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sousperiode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface SousperiodeFacadeLocal {

    void create(Sousperiode sousperiode);

    void edit(Sousperiode sousperiode);

    void remove(Sousperiode sousperiode);

    Sousperiode find(Object id);

    List<Sousperiode> findAll();

    List<Sousperiode> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Sousperiode> findAllRangeCode();

    List<Sousperiode> findIdTypeSousPeriode(int idType);

}
