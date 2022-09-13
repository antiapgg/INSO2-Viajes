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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author antiapgg
 */
@Entity
@Table(name="destino")
public class Destino implements Serializable {
       
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private int idDestino;
    
    @Column (name="Nombre")
    private String Nombre;
    
    @Column (name="Pais")
    private String Pais;
    
    @Column (name="Precio")
    private int Precio;
    
     @Column (name="url")
    private String url;
    
    @JoinColumn (name="grupos_idGrupos")
    @ManyToOne
    private Grupos Grupos;
    
    @JoinColumn (name="zonas_idZona")
    @ManyToOne
    private Zona Zona;
        
    @JoinColumn (name="tipoViaje_idTipoViaje")
    @ManyToOne
    private TipoViaje TipoViaje;

       
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/
    
    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public Grupos getGrupos() {
        return Grupos;
    }

    public void setGrupos(Grupos Grupos) {
        this.Grupos = Grupos;
    }

    public Zona getZona() {
        return Zona;
    }

    public void setZona(Zona Zona) {
        this.Zona = Zona;
    }

    public TipoViaje getTipoViaje() {
        return TipoViaje;
    }

    public void setTipoViaje(TipoViaje TipoViaje) {
        this.TipoViaje = TipoViaje;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

        
    /*************************
     *   HASHCODE & EQUALS   *
     *************************/
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.idDestino;
        hash = 11 * hash + Objects.hashCode(this.Nombre);
        hash = 11 * hash + Objects.hashCode(this.Pais);
        hash = 11 * hash + this.Precio;
        hash = 11 * hash + Objects.hashCode(this.url);
        hash = 11 * hash + Objects.hashCode(this.Grupos);
        hash = 11 * hash + Objects.hashCode(this.Zona);
        hash = 11 * hash + Objects.hashCode(this.TipoViaje);
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
        final Destino other = (Destino) obj;
        if (this.idDestino != other.idDestino) {
            return false;
        }
        if (this.Precio != other.Precio) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.Pais, other.Pais)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.Grupos, other.Grupos)) {
            return false;
        }
        if (!Objects.equals(this.Zona, other.Zona)) {
            return false;
        }
        if (!Objects.equals(this.TipoViaje, other.TipoViaje)) {
            return false;
        }
        return true;
    }
       

}
