/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Destino;
import com.unileon.modelo.Grupos;
import com.unileon.modelo.TipoViaje;
import com.unileon.modelo.Zona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author antiapgg
 */
@Local
public interface DestinoFacadeLocal {

    void create(Destino destino);

    void edit(Destino destino);

    void remove(Destino destino);

    Destino find(Object id);

    List<Destino> findAll();

    List<Destino> findRange(int[] range);

    int count();
    
    List<Destino> consultarZonas(Zona zona);
    
    List<Destino> consultarGrupos(Grupos grupo);
    
    List<Destino> consultarTiposs(TipoViaje tipo);
    
    Destino buscarDestino(String nom);
    
}
