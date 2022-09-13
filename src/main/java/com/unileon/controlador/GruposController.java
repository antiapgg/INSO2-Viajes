/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.GruposFacadeLocal;
import com.unileon.modelo.Grupos;
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
public class GruposController implements Serializable {
    
    @EJB
    private GruposFacadeLocal gruposEJB;
    private Grupos grupos;
    
    @PostConstruct
    void init(){
        grupos = new Grupos();
    }
    
    public void registra(){
        try{
            gruposEJB.create(grupos);
        }
        catch(Exception e){
            
        }   
    }
    
    public void eliminar(Grupos grupo){
        gruposEJB.remove(grupo);
    }
           
    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/ 

    public GruposFacadeLocal getGruposEJB() {
        return gruposEJB;
    }

    public void setGruposEJB(GruposFacadeLocal gruposEJB) {
        this.gruposEJB = gruposEJB;
    }

    public Grupos getGrupos() {
        return grupos;
    }

    public void setGrupos(Grupos grupos) {
        this.grupos = grupos;
    }
    
    
}
