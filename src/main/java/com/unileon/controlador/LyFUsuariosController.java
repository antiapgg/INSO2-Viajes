/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author leoma
 */
@Named
@ViewScoped

public class LyFUsuariosController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private List<Usuario> listaUsuarios;
   
    
    @PostConstruct
    void init(){
        System.out.println("ENTRO EN LYF ZONAS CONTROLLER");
        listaUsuarios = showUsuarios();
    }
    
   
    public List<Usuario> showUsuarios(){
        listaUsuarios = usuarioEJB.findAll();
        return listaUsuarios;
    }

    public UsuarioFacadeLocal getZonasEJB() {
        return usuarioEJB;
    }

    public void setZonasEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuarios;
    }

    public void setListaZonas(List<Usuario> listaUsuario) {
        this.listaUsuarios = listaUsuario;
    }



   
   

    
}
