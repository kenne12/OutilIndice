/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Utilisateurstructure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface UtilisateurstructureFacadeLocal {

    void create(Utilisateurstructure utilisateurstructure);

    void edit(Utilisateurstructure utilisateurstructure);

    void remove(Utilisateurstructure utilisateurstructure);

    Utilisateurstructure find(Object id);

    List<Utilisateurstructure> findAll();

    List<Utilisateurstructure> findRange(int[] range);

    int count();

    List<Utilisateurstructure> findByIdutilisateur(int idUtilisateur);

    void deleteByIdutilisateur(int idUtilisateur);

    Utilisateurstructure findByIdutilisateurIdstructure(int idUtilisateur, long idStructure);

}
