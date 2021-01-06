/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Privilege;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface PrivilegeFacadeLocal {

    void create(Privilege privilege);

    void edit(Privilege privilege);

    void remove(Privilege privilege);

    Privilege find(Object id);

    List<Privilege> findAll();

    List<Privilege> findRange(int[] range);

    int count();

    Long nextVal();


    List<Privilege> findByUser(int utilisateur);

    Privilege findByUser(int utilisateur, int idmenu);

    void delete(int idmenu, int utilisateur);

    void deleteByIdUtilisateur(int idutilisateur);

}
