/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.PersonaFacadeLocal;
import com.unileon.EJB.RolesUsuarioFacadeLocal;
import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Persona;
import com.unileon.modelo.RolesUsuario;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


/**
 *
 * @author leoma
 */

public class UsuariosController  implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    private Usuario [] usuarios;
    
    @EJB
    private RolesUsuarioFacadeLocal rolEJB;
    private RolesUsuario rol;
    
    @EJB
    private PersonaFacadeLocal personaEJB;
    private Persona persona;
    
    private String telefonoSt;
    private String numTarjSt;
    
    public UsuariosController(){
        usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        persona=usuario.getPersona();
        usuarios [0]= usuario;
        
        
    }
    
    public Usuario [] getUsuarios(){
        
        return usuarios;
        
    }
    
    
    
    
    
}
