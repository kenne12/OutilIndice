/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Sousrubriquerecette;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface SousrubriquerecetteFacadeLocal {

    void create(Sousrubriquerecette sousrubriquerecette);

    void edit(Sousrubriquerecette sousrubriquerecette);

    void remove(Sousrubriquerecette sousrubriquerecette);

    Sousrubriquerecette find(Object id);

    List<Sousrubriquerecette> findAll();

    List<Sousrubriquerecette> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Sousrubriquerecette> findAllRangeByCode();

}
