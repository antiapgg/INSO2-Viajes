/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.RolesUsuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author antiapgg
 */
@Local
public interface RolesUsuarioFacadeLocal {

    void create(RolesUsuario rolesUsuario);

    void edit(RolesUsuario rolesUsuario);

    void remove(RolesUsuario rolesUsuario);
    
    RolesUsuario buscarRolPorTipoUsuario(String tipo);

    RolesUsuario find(Object id);

    List<RolesUsuario> findAll();

    List<RolesUsuario> findRange(int[] range);

    int count();
    
}
