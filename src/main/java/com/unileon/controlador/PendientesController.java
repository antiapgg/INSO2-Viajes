/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.DestinoFacadeLocal;
import com.unileon.EJB.GruposFacadeLocal;
import com.unileon.EJB.VentaFacadeLocal;
import com.unileon.EJB.ViajeFacadeLocal;
import com.unileon.modelo.Destino;
import com.unileon.modelo.Grupos;
import com.unileon.modelo.Usuario;
import com.unileon.modelo.Venta;
import com.unileon.modelo.Viaje;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author antiapgg
 */
@Named
@ViewScoped
public class PendientesController implements Serializable {
    
    @EJB
    private ViajeFacadeLocal viajesEJB;
    private List<Viaje> listaViajes;
    
    @EJB
    private VentaFacadeLocal ventasEJB;
    private Venta venta;
    
    private Viaje viaje;
    private Usuario usuario;
    private String url;
    private String numTarjSt;
    
    @PostConstruct
    void init(){
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        numTarjSt = String.valueOf(usuario.getPersona().getNumTarjeta());
        if(numTarjSt.length()<16){
            int r = 16 - numTarjSt.length();
            for(int i = 0; i < r; i++){
                numTarjSt = numTarjSt + "0";
            }
        }
        numTarjSt = numTarjSt.substring(0, 4) + "-" + numTarjSt.substring(4, 8) + "-" + numTarjSt.substring(8, 12) + "-" + numTarjSt.substring(12, 16);

        viaje =  new Viaje();
        listaViajes = showViajes();
    }
    
    public void buscar(Viaje viaje){
        //TODO Buscar viajes relacionados con el grupo indicado y mostrarlos
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            if(viaje == null){
                ec.redirect("Pendientes.softwareII.xhtml");
            } else {
                ec.redirect("VerViaje.softwareII.xhtml?viajeId=" + viaje.getIdViajes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
    }
    
    public void confirmar(Viaje viaje){
        venta = new Venta();
        Date hoy = new Date();
        System.out.println((int)hoy.getTime()/1000);
        venta.setFechaVenta(hoy);
        venta.setUsuario(usuario);
        viaje.setVendido(1);
        viajesEJB.edit(viaje);
        venta.setViaje(viaje);
        ventasEJB.create(venta);
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra", "Compra realizada con exito");
        PrimeFaces.current().dialog().showMessageDynamic(message);

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('wdialogoPago').hide();");
            ec.redirect("VerViaje.softwareII.xhtml?viajeId=" + venta.getViaje().getIdViajes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
    }
    
    public void eliminar(Viaje viaje){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try{
            viajesEJB.remove(viaje);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar", "Viaje eliminado con exito");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            ec.redirect("Pendientes.softwareII.xhtml");
        }
        catch(Exception e){
            System.out.println("ELIMINARARRRRR " + e.getMessage());
        }
        
    }
    
    public List<Viaje> showViajes(){
        listaViajes = viajesEJB.consultarViajesUs();
        return listaViajes;
    }

    public ViajeFacadeLocal getViajesEJB() {
        return viajesEJB;
    }

    public void setViajesEJB(ViajeFacadeLocal viajesEJB) {
        this.viajesEJB = viajesEJB;
    }

    public List<Viaje> getListaViajes() {
        return listaViajes;
    }

    public void setListaViajes(List<Viaje> listaViajes) {
        this.listaViajes = listaViajes;
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

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNumTarjSt() {
        return numTarjSt;
    }

    public void setNumTarjSt(String numTarjSt) {
        this.numTarjSt = numTarjSt;
    }

   
    
}
