/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author antiapgg
 */

@Named
@ViewScoped

public class LoginController implements Serializable {
    
    private Usuario usuario;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    void init(){
        usuario = new Usuario();
    }
    
    public String iniciaSesion(){

        String url = null;
        try{
            usuario = usuarioEJB.consultarUsuario(usuario);
            if(usuario != null){
                if(usuario.getRoles().getIdRolUs() == 1){
                    System.out.println("Es ADMINISTRADOR. ");
                    url = "/privado/administrador/inicioAdmin?faces-redirect=true"; 
                }
                else{
                    System.out.println("Sesión iniciada. ES COMPRADOR. ");
                    url = "/privado/comprador/inicioCompr?faces-redirect=true";
                }
            }
            else{
                FacesContext.getCurrentInstance().addMessage("mensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Usuario No Encontrado."));
                url="/index?faces-redirect=true";
            }
        }
        catch(Exception e){
            System.out.println("Error al iniciar sesion " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("error", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al iniciar sesión."));
        }
        return url;
    }
    
    public UsuarioFacadeLocal getUsuarioEJB() {
        return usuarioEJB;
    }

    public void setUsuarioEJB(UsuarioFacadeLocal usuarioEJB) {
        this.usuarioEJB = usuarioEJB;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
