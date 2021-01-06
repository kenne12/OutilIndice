/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Responsabilite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface ResponsabiliteFacadeLocal {

    void create(Responsabilite responsabilite);

    void edit(Responsabilite responsabilite);

    void remove(Responsabilite responsabilite);

    Responsabilite find(Object id);

    List<Responsabilite> findAll();

    List<Responsabilite> findRange(int[] range);

    int count();
    
}
