/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Critereservice;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface CritereserviceFacadeLocal {

    void create(Critereservice critereservice);

    void edit(Critereservice critereservice);

    void remove(Critereservice critereservice);

    Critereservice find(Object id);

    List<Critereservice> findAll();

    List<Critereservice> findRange(int[] range);

    int count();

    List<Critereservice> findByIdService(long idService);

    Critereservice findByIdServiceIdCritere(long idService, int idCritere);

}
