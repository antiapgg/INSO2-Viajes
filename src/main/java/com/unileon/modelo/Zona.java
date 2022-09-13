/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author antiapgg
 */
@Entity
@Table(name="zonas")
public class Zona   implements Serializable {
       
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int idZona;
    
    @Column (name="Nombre")
    private String Nombre;
    
    @Column (name="Descripcion")
    private String Descripcion;
        
        
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
    /*************************
     *   HASHCODE & EQUALS   *
     *************************/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idZona;
        hash = 29 * hash + Objects.hashCode(this.Nombre);
        hash = 29 * hash + Objects.hashCode(this.Descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zona other = (Zona) obj;
        if (this.idZona != other.idZona) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.Descripcion, other.Descripcion)) {
            return false;
        }
        return true;
    }
   
}
