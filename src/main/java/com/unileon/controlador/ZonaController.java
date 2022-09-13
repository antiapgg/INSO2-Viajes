/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.ZonaFacadeLocal;
import com.unileon.modelo.Zona;
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

public class ZonaController implements Serializable{
    
    @EJB
    private ZonaFacadeLocal zonaEJB;
    private Zona zona;
    
    @PostConstruct
    void init(){
        zona = new Zona();
    }
    
    public void eliminar(){
        zonaEJB.remove(zona);
    }
    
    public void registra(){
        try{
            zonaEJB.create(zona);
        }
        catch(Exception e){
            
        }   
    }
       
    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/ 

    public ZonaFacadeLocal getZonaEJB() {
        return zonaEJB;
    }

    public void setZonaEJB(ZonaFacadeLocal categoriaEJB) {
        this.zonaEJB = categoriaEJB;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona categoria) {
        this.zona = categoria;
    }
    
    
}
