/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author antiapgg
 */
@Entity
@Table(name="Venta")
public class Venta implements Serializable {
       
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int idVenta;
    
    @JoinColumn (name="usuario_idUsuario")
    @ManyToOne
    private Usuario Usuario;
    
    @JoinColumn (name="Viajes_idViajes")
    @ManyToOne
    private Viaje Viaje;
    
    @Column (name="FechaVenta")
    @Temporal (TemporalType.TIMESTAMP)
    private Date FechaVenta;

        
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public Viaje getViaje() {
        return Viaje;
    }

    public void setViaje(Viaje Viaje) {
        this.Viaje = Viaje;
    }

    public Date getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(Date FechaVenta) {
        this.FechaVenta = FechaVenta;
    }

            
    /*************************
     *   HASHCODE & EQUALS   *
     *************************/
    
  
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.idVenta;
        hash = 59 * hash + Objects.hashCode(this.Usuario);
        hash = 59 * hash + Objects.hashCode(this.Viaje);
        hash = 59 * hash + Objects.hashCode(this.FechaVenta);
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
        final Venta other = (Venta) obj;
        if (this.idVenta != other.idVenta) {
            return false;
        }
        if (!Objects.equals(this.Usuario, other.Usuario)) {
            return false;
        }
        if (!Objects.equals(this.Viaje, other.Viaje)) {
            return false;
        }
        if (!Objects.equals(this.FechaVenta, other.FechaVenta)) {
            return false;
        }
        return true;
    }


    
    
    
}
