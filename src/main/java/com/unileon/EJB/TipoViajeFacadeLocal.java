/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.TipoViaje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author antiapgg
 */
@Local
public interface TipoViajeFacadeLocal {

    void create(TipoViaje tipoViaje);

    void edit(TipoViaje tipoViaje);

    void remove(TipoViaje tipoViaje);

    TipoViaje find(Object id);

    List<TipoViaje> findAll();

    List<TipoViaje> findRange(int[] range);

    int count();
    
    TipoViaje buscarTipo(String nom);
    
}
