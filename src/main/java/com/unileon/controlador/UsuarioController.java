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
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author antiapgg
 */

@Named
@ViewScoped

public class UsuarioController implements Serializable {
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    
    @EJB
    private RolesUsuarioFacadeLocal rolEJB;
    private RolesUsuario rol;
    
    @EJB
    private PersonaFacadeLocal personaEJB;
    private Persona persona;
    
    private String telefonoSt;
    private String numTarjSt;
    
    public UsuarioController(){
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        persona = usuario.getPersona();
        System.out.println("ESTOY EN USUARIO CONTROLLER");
        telefonoSt = String.valueOf(persona.getTelefono());
        telefonoSt = telefonoSt.substring(0, 3) + "-" + telefonoSt.substring(3, 6) + "-" + telefonoSt.substring(6, 9);
        numTarjSt = String.valueOf(persona.getNumTarjeta());
        if(numTarjSt.length()<16){
            int r = 16 - numTarjSt.length();
            for(int i = 0; i < r; i++){
                numTarjSt = numTarjSt + "0";
            }
        }
        numTarjSt = numTarjSt.substring(0, 4) + "-" + numTarjSt.substring(4, 8) + "-" + numTarjSt.substring(8, 12) + "-" + numTarjSt.substring(12, 16);
    }
    
    public void modificarTelf(){
        persona.setTelefono(Integer.parseInt(telefonoSt));
        personaEJB.edit(persona);
    }
    
    public void modificarNum(){
        persona.setNumTarjeta(Long.valueOf(numTarjSt));
        personaEJB.edit(persona);
    }
    
    public void modificarNombre(){
        personaEJB.edit(persona);
    }
    
    public void modificarApell(){
        personaEJB.edit(persona);
    }
    
    public void modificarUser(){
        usuarioEJB.edit(usuario);
    }
    
    public void modificarCorreo(){
        usuarioEJB.edit(usuario);
    }
    
    public void modificarPasswr(){
        usuarioEJB.edit(usuario);
    }
    
    public void registra(){
        try{
            System.out.println("Registramos el usuario");
            rol = rolEJB.buscarRolPorTipoUsuario(rol.getTipoUsuario());
            personaEJB.create(persona);
            usuario.setPersona(persona);
            usuario.setRoles(rol); 

            usuarioEJB.create(usuario);
        }
        catch(Exception e){
            System.out.println("Error al insertar persona en Base de Datos. "+e.getMessage());
        }   
    }

    public String getTelefonoSt() {
        return telefonoSt;
    }

    public void setTelefonoSt(String telefonoSt) {
        this.telefonoSt = telefonoSt;
    }
    
    public void updateUser(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        
    }
    
    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
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

    public PersonaFacadeLocal getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonaFacadeLocal personaEJB) {
        this.personaEJB = personaEJB;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
           
    public void setTelfSt(String st){
        this.telefonoSt = st;
    }
    public void setNumTarjSt(String st){
        this.numTarjSt = st;
    }
    public String getTelfSt(){
        return this.telefonoSt;
    }
    public String getNumTarjSt(){
        return this.numTarjSt;
    }
    
}
