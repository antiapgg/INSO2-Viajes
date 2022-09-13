/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.TipoViajeFacadeLocal;
import com.unileon.modelo.TipoViaje;
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
public class TipoViajeController implements Serializable{
    
    @EJB
    private TipoViajeFacadeLocal tiposEJB;
    
    private TipoViaje tipo;
    
    private String nombre;
    private String actividades;
    
       
    
    public void eliminar(){
        tiposEJB.remove(tipo);
    }
    public void registra(){
        try{
            tiposEJB.create(tipo);
        }
        catch(Exception e){
            
        }   
    }
    
    
    public void setNombre(String Nombre){
        this.nombre=Nombre;
    }
    
    public void setActividades(String Actividades){
        this.actividades=Actividades;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    
    
    public String getActividades(){
        return this.actividades;
    }
    
    
           
    
    
}
