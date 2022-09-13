/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.PersonaFacadeLocal;
import com.unileon.modelo.Persona;
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
public class PersonaController implements Serializable {
    
    @EJB
    private PersonaFacadeLocal personaEJB;
    private Persona persona;
    
    @PostConstruct
    void init(){
        persona = new Persona();
    }
    
    public void registra(){
        try{
            personaEJB.create(persona);
            System.out.println("PERSONA REGISTRADA "+ persona.getNombre());
        }
        catch(Exception e){
            
        }   
    }
    
    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
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
    
}
