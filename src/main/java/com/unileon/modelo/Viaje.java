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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author antiapgg
 */

@Entity
@Table(name="viaje")
public class Viaje implements Serializable {
       
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int idViajes;
    
    @Column (name="Nombre")
    private String Nombre;
    
    @Column (name="FechaInicio")
    @Temporal (TemporalType.TIMESTAMP)
    private Date FechaInicio;
    
    @Column (name="FechaFin")
    @Temporal (TemporalType.TIMESTAMP)
    private Date FechaFin;
    
    @JoinColumn (name="persona_idPersona")
    @ManyToOne
    private Persona Persona;
    
    @JoinColumn (name="destino_idDestino")
    @OneToOne
    private Destino Destino;
    
    @Column (name="vendido")
    private int vendido;

        
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public int getIdViajes() {
        return idViajes;
    }

    public void setIdViajes(int idViajes) {
        this.idViajes = idViajes;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date FechaFin) {
        this.FechaFin = FechaFin;
    }

    public Persona getPersona() {
        return Persona;
    }

    public void setPersona(Persona Persona) {
        this.Persona = Persona;
    }

    public Destino getDestino() {
        return Destino;
    }

    public void setDestino(Destino Destino) {
        this.Destino = Destino;
    }

    public int getVendido() {
        return vendido;
    }

    public void setVendido(int vendido) {
        this.vendido = vendido;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idViajes;
        hash = 29 * hash + Objects.hashCode(this.Nombre);
        hash = 29 * hash + Objects.hashCode(this.FechaInicio);
        hash = 29 * hash + Objects.hashCode(this.FechaFin);
        hash = 29 * hash + Objects.hashCode(this.Persona);
        hash = 29 * hash + Objects.hashCode(this.Destino);
        hash = 29 * hash + this.vendido;
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
        final Viaje other = (Viaje) obj;
        if (this.idViajes != other.idViajes) {
            return false;
        }
        if (this.vendido != other.vendido) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.FechaInicio, other.FechaInicio)) {
            return false;
        }
        if (!Objects.equals(this.FechaFin, other.FechaFin)) {
            return false;
        }
        if (!Objects.equals(this.Persona, other.Persona)) {
            return false;
        }
        if (!Objects.equals(this.Destino, other.Destino)) {
            return false;
        }
        return true;
    }
    
    

    /*************************
     *   HASHCODE & EQUALS   *
     *************************/
    
    
    
    
    
}
