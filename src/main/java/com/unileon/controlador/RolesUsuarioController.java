/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.RolesUsuarioFacadeLocal;
import com.unileon.modelo.RolesUsuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author antiapgg
 */

@Named
@ViewScoped

public class RolesUsuarioController implements Serializable {
    
    @EJB
    private RolesUsuarioFacadeLocal rolEJB;
    private RolesUsuario rol;
    
    @PostConstruct
    void init(){
        rol = new RolesUsuario();
    }
    
    public void registra(){
        try{
            rolEJB.create(rol);
        }
        catch(Exception e){
            
        }   
    }
    
    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public RolesUsuarioFacadeLocal getRolEJB() {
        return rolEJB;
    }

    public void setRolEJB(RolesUsuarioFacadeLocal rolEJB) {
        this.rolEJB = rolEJB;
    }

    public RolesUsuario getRol() {
        return rol;
    }

    public void setRol(RolesUsuario rol) {
        this.rol = rol;
    }
    
}
