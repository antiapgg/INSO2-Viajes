/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Menus;
import com.unileon.modelo.RolesUsuario;
import com.unileon.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author antiapgg
 */
@Local
public interface MenusFacadeLocal {

    void create(Menus menus);

    void edit(Menus menus);

    void remove(Menus menus);

    Menus find(Object id);

    List<Menus> findAll();

    List<Menus> findRange(int[] range);

    int count();
    
    public List<Menus> obtenerMenusUsuario(RolesUsuario us);
    
}
