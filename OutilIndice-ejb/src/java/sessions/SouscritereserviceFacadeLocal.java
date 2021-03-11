/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Souscritereservice;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface SouscritereserviceFacadeLocal {

    void create(Souscritereservice souscritereservice);

    void edit(Souscritereservice souscritereservice);

    void remove(Souscritereservice souscritereservice);

    Souscritereservice find(Object id);

    List<Souscritereservice> findAll();

    List<Souscritereservice> findRange(int[] range);

    int count();

    Long nextId();

    List<Souscritereservice> findByIdService(long idStructure, long idService);

    List<Souscritereservice> findByIdServiceIdCritere(long idStructure, long idService, int idCritere);

}
