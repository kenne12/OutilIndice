/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.IndicateurQteService;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface IndicateurQteServiceFacadeLocal {

    void create(IndicateurQteService indicateurQteService);

    void edit(IndicateurQteService indicateurQteService);

    void remove(IndicateurQteService indicateurQteService);

    IndicateurQteService find(Object id);

    List<IndicateurQteService> findAll();

    List<IndicateurQteService> findRange(int[] range);

    int count();
    
}
