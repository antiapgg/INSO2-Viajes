/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Zona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author antiapgg
 */
@Local
public interface ZonaFacadeLocal {

    void create(Zona zona);

    void edit(Zona zona);

    void remove(Zona zona);

    Zona find(Object id);

    List<Zona> findAll();

    List<Zona> findRange(int[] range);

    int count();
    
    public Zona buscarZona(String nom);
    
}
