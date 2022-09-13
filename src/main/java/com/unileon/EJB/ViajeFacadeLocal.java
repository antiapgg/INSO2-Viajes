/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Viaje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author antiapgg
 */
@Local
public interface ViajeFacadeLocal {

    void create(Viaje viaje);

    void edit(Viaje viaje);

    void remove(Viaje viaje);

    Viaje find(Object id);

    List<Viaje> findAll();

    List<Viaje> findRange(int[] range);

    int count();
    
    List<Viaje> consultarViajesUs();
    
}
