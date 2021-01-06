/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Criterecategorie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author USER
 */
@Local
public interface SouscriterecategorieFacadeLocal {

    void create(Criterecategorie souscriterecategorie);

    void edit(Criterecategorie souscriterecategorie);

    void remove(Criterecategorie souscriterecategorie);

    Criterecategorie find(Object id);

    List<Criterecategorie> findAll();

    List<Criterecategorie> findRange(int[] range);

    int count();

    List<Criterecategorie> findByIdcategorieIdStructure(int idCategorie, long idStructure);

}
