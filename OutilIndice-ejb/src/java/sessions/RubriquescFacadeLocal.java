/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Rubriquesc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface RubriquescFacadeLocal {

    void create(Rubriquesc rubriquesc);

    void edit(Rubriquesc rubriquesc);

    void remove(Rubriquesc rubriquesc);

    Rubriquesc find(Object id);

    List<Rubriquesc> findAll();

    List<Rubriquesc> findRange(int[] range);

    int count();

    List<Rubriquesc> findAllRange();

}
