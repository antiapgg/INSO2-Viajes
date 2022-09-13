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
@Table(name="tipoViaje")
public class TipoViaje   implements Serializable {
       
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int idTipoViaje;
    
    @Column (name="Nombre")
    private String Nombre;
    
    @Column (name="Actividades")
    private String Actividades;

            
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public int getIdTipoViaje() {
        return idTipoViaje;
    }

    public void setIdTipoViaje(int idTipoViaje) {
        this.idTipoViaje = idTipoViaje;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getActividades() {
        return Actividades;
    }

    public void setActividades(String Actividades) {
        this.Actividades = Actividades;
    }
    
    
    /*************************
     *   HASHCODE & EQUALS   *
     *************************/
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.idTipoViaje;
        hash = 17 * hash + Objects.hashCode(this.Nombre);
        hash = 17 * hash + Objects.hashCode(this.Actividades);
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
        final TipoViaje other = (TipoViaje) obj;
        if (this.idTipoViaje != other.idTipoViaje) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.Actividades, other.Actividades)) {
            return false;
        }
        return true;
    }
    
}
