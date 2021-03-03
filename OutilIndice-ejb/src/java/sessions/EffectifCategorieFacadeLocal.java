/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EffectifCategorie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EffectifCategorieFacadeLocal {

    void create(EffectifCategorie effectifCategorie);

    void edit(EffectifCategorie effectifCategorie);

    void remove(EffectifCategorie effectifCategorie);

    EffectifCategorie find(Object id);

    List<EffectifCategorie> findAll();

    List<EffectifCategorie> findRange(int[] range);

    int count();

    Long nextId();

    List<EffectifCategorie> findByIdStructure(long idStructure);

    void deleteByIdStructure(long idStructure);

}
