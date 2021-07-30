/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EffectifResponsabilite;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface EffectifResponsabiliteFacadeLocal {

    void create(EffectifResponsabilite effectifResponsabilite);

    void edit(EffectifResponsabilite effectifResponsabilite);

    void remove(EffectifResponsabilite effectifResponsabilite);

    EffectifResponsabilite find(Object id);

    List<EffectifResponsabilite> findAll();

    List<EffectifResponsabilite> findRange(int[] range);

    int count();

    Long nextId();

    List<EffectifResponsabilite> findByIdStructure(long idStructure);

    void deleteByIdStructure(long idStructure);

    EffectifResponsabilite findByIdStructureAndIdResponsabilite(long idStructure, int idResponsabilite);

}
