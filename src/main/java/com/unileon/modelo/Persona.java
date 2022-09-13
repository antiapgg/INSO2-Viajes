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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author antiapgg
 */

@Entity
@Table(name="persona")
public class Persona implements Serializable {
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int idPersona;
    
    @Column (name="Nombre")
    private String Nombre;
    
    @Column (name="Apellidos")
    private String Apellidos;
    
    @Column (name="FechaNacimiento")
    @Temporal (TemporalType.TIMESTAMP)
    private Date FechaNacimiento;
    
    @Column (name="NumTarjeta")
    private long NumTarjeta;
    
    @Column (name="DNI")
    private String DNI;
    
    @Column (name="Telefono")
    private int Telefono;
    
    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public long getNumTarjeta() {
        return NumTarjeta;
    }

    public void setNumTarjeta(long NumTarjeta) {
        this.NumTarjeta = NumTarjeta;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        System.out.println("\t" + DNI);
        this.DNI = DNI.toString();
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }
    

    
    /*************************
     *   HASHCODE & EQUALS   *
     *************************/
    
  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idPersona;
        hash = 97 * hash + Objects.hashCode(this.Nombre);
        hash = 97 * hash + Objects.hashCode(this.Apellidos);
        hash = 97 * hash + Objects.hashCode(this.FechaNacimiento);
        hash = 97 * hash + (int) (this.NumTarjeta ^ (this.NumTarjeta >>> 32));
        hash = 97 * hash + Objects.hashCode(this.DNI);
        hash = 97 * hash + this.Telefono;
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
        final Persona other = (Persona) obj;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.NumTarjeta != other.NumTarjeta) {
            return false;
        }
        if (this.Telefono != other.Telefono) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.Apellidos, other.Apellidos)) {
            return false;
        }
        if (!Objects.equals(this.DNI, other.DNI)) {
            return false;
        }
        if (!Objects.equals(this.FechaNacimiento, other.FechaNacimiento)) {
            return false;
        }
        return true;
    }
    
    
    
}
