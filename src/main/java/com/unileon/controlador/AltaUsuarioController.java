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

public class AltaUsuarioController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    
    @EJB
    private RolesUsuarioFacadeLocal rolEJB;
    private RolesUsuario rol;
    
    @EJB
    private PersonaFacadeLocal personaEJB;
    private Persona persona;
    
    private List<RolesUsuario> listaRoles;
    private List<String> listaNomRoles = new ArrayList<String>();
    
    private String telefonoSt;
    private String numTarjSt;
   
    @PostConstruct
    public void init(){
        System.out.println("ESTOY EN INIT DE ALTAUSUARIOCONTROLLER");
        usuario = new Usuario();
        rol = new RolesUsuario();
        persona = new Persona();
        findListaRoles();
        // Obtengo la lista de roles
        for(int i = 0; i < listaRoles.size(); i++){
            listaNomRoles.add(listaRoles.get(i).getTipoUsuario().toString());
        }
        System.out.println(listaNomRoles);
    }
   
    public String registra(){
        String pagina = "";
        try{
            System.out.println("Registramos el usuario");
            System.out.println("DNI: " + persona.getDNI());
            System.out.println("NOMBRE PERSONA: " + persona.getNombre());
            System.out.println("numTarjSt: " + numTarjSt);
            numTarjSt = numTarjSt.replaceAll("\\s+","");
            System.out.println(numTarjSt);
            System.out.println((Long.parseLong(numTarjSt)));
            System.out.println(Long.valueOf(numTarjSt));
            System.out.println("numTarjInt: " + Long.valueOf(numTarjSt));
            System.out.println("TelfSt: " + telefonoSt);
            telefonoSt = telefonoSt.replaceAll("\\s+","");
            System.out.println("TelfInt: " + Integer.parseInt(telefonoSt));
            persona.setTelefono(Integer.parseInt(telefonoSt));
            
            persona.setNumTarjeta(Long.valueOf(numTarjSt));
            rol = rolEJB.buscarRolPorTipoUsuario(rol.getTipoUsuario());
            System.out.println("ERROR");
            personaEJB.create(persona);
            usuario.setPersona(persona);
            usuario.setRoles(rol); 

            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage("mensaje1", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro el usuario"));
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("mensaje1", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se ha podido registrar"));
            System.out.println("Error al insertar persona en Base de Datos. "+e.getMessage());
        }   
        // Es Administrador
        if(rol.getIdRolUs() == 1){
            pagina = "/privado/administrador/inicioAdmin?faces-redirect=true"; 
        }
        // Es Comprador
        else if(rol.getIdRolUs() == 2){
            pagina = "/privado/comprador/inicioCompr?faces-redirect=true";
        }
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Bienvenido a Viajes365 " + persona.getNombre() + "."));
        
        return pagina;
    }
    
    public void registraAdmin(){
        try{
            System.out.println("Registramos el usuario");
            System.out.println("DNI: " + persona.getDNI());
            System.out.println("NOMBRE PERSONA: " + persona.getNombre());
            System.out.println("numTarjSt: " + numTarjSt);
            numTarjSt = numTarjSt.replaceAll("\\s+","");
            System.out.println(numTarjSt);
            System.out.println((Long.parseLong(numTarjSt)));
            System.out.println(Long.valueOf(numTarjSt));
            System.out.println("numTarjInt: " + Long.valueOf(numTarjSt));
            System.out.println("TelfSt: " + telefonoSt);
            telefonoSt = telefonoSt.replaceAll("\\s+","");
            System.out.println("TelfInt: " + Integer.parseInt(telefonoSt));
            persona.setTelefono(Integer.parseInt(telefonoSt));
            
            persona.setNumTarjeta(Long.valueOf(numTarjSt));
            rol = rolEJB.buscarRolPorTipoUsuario(rol.getTipoUsuario());
            System.out.println("ERROR");
            personaEJB.create(persona);
            usuario.setPersona(persona);
            usuario.setRoles(rol); 

            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage("mensaje1", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro el usuario"));
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("mensaje1", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se ha podido registrar"));
            System.out.println("Error al insertar persona en Base de Datos. "+e.getMessage());
        }   
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Bienvenido a Viajes365 " + persona.getNombre() + "."));
    }
    
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
     public void findListaRoles(){
        listaRoles = rolEJB.findAll();
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

    public List<RolesUsuario> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<RolesUsuario> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<String> getListaNomRoles() {
        return listaNomRoles;
    }

    public void setListaNomRoles(List<String> listaNomRoles) {
        this.listaNomRoles = listaNomRoles;
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
