/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.DestinoFacadeLocal;
import com.unileon.EJB.GruposFacadeLocal;
import com.unileon.EJB.VentaFacadeLocal;
import com.unileon.modelo.Destino;
import com.unileon.modelo.Grupos;
import com.unileon.modelo.Usuario;
import com.unileon.modelo.Venta;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author antiapgg
 */
@Named
@ViewScoped
public class ListarViajesController implements Serializable {
    
    @EJB
    private DestinoFacadeLocal destinosEJB;
    private List<Venta> listaVentas;
    
    @EJB
    private VentaFacadeLocal ventasEJB;
    private Venta venta;
    
    private Destino destino;
    private Usuario usuario;
    private String url;
    
    @PostConstruct
    void init(){
       
        System.out.println("ENTRO EN LYFDESTINOSCONTROLLER");
        destino =  new Destino();
        usuario = new Usuario();
        listaVentas = showVentas();
    }
    
    public void buscar(Destino destino){
        //TODO Buscar viajes relacionados con el grupo indicado y mostrarlos
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(destino == null){
                ec.redirect("Listar.softwareII.xhtml");
            } else {
                ec.redirect("DestinoSelected.softwareII.xhtml?destinoId=" + destino.getIdDestino());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
    }
    
    public List<Venta> showVentas(){
        listaVentas = ventasEJB.findAll();
        return listaVentas;
    }

    public DestinoFacadeLocal getDestinosEJB() {
        return destinosEJB;
    }

    public void setDestinosEJB(DestinoFacadeLocal destinosEJB) {
        this.destinosEJB = destinosEJB;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    public VentaFacadeLocal getVentasEJB() {
        return ventasEJB;
    }

    public void setVentasEJB(VentaFacadeLocal ventasEJB) {
        this.ventasEJB = ventasEJB;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
