/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.DestinoFacadeLocal;
import com.unileon.EJB.GruposFacadeLocal;
import com.unileon.EJB.TipoViajeFacadeLocal;
import com.unileon.EJB.VentaFacadeLocal;
import com.unileon.EJB.ViajeFacadeLocal;
import com.unileon.EJB.ZonaFacadeLocal;
import com.unileon.modelo.Destino;
import com.unileon.modelo.Grupos;
import com.unileon.modelo.TipoViaje;
import com.unileon.modelo.Usuario;
import com.unileon.modelo.Venta;
import com.unileon.modelo.Viaje;
import com.unileon.modelo.Zona;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
public class ShowDestinoController implements Serializable {
    
    @EJB
    private DestinoFacadeLocal destinosEJB;
    
     @EJB
    private ZonaFacadeLocal zonaEJB;
    private Zona zona;
    private List<Zona> listaZonas;
    
    @EJB
    private TipoViajeFacadeLocal tipoViajeEJB;
    private TipoViaje tipoViaje;
    private List<TipoViaje> listaTipos;
    
    @EJB
    private GruposFacadeLocal gruposEJB;
    private Grupos grupos;
    private List<Grupos> listaGrupos;
    
    private List<String> listaNomZon;
    private List<String> listaNomTip;
    private List<String> listaNomGr;
    private String zonaNom;
    private String tipoNom;
    private String grupoNom;
    
    private Destino destino;
    private String url;
    private String nombreViaje;
    private Usuario usuario;
    private String numTarjSt;
    private int num1;
    private int num2;
    
    @EJB
    private ViajeFacadeLocal viajeEJB;
    private Viaje viaje;
    
    @EJB
    private VentaFacadeLocal ventaEJB;
    private Venta venta;
    
    private boolean fechaInicialFinalMayoraHoy;
    private boolean fechaInicialyFinMayor30Dias;
    public static final int NEGATIVO_TREINTA = -30;

    private Date fechaInicio = null;
    private Date fechaFinal = null;
    
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
       
        zona = new Zona();
        tipoViaje = new TipoViaje();
        grupos = new Grupos();
        //Creo la lista de zonas
        listaZonas = zonaEJB.findAll();
        listaNomZon = new ArrayList<String>();    
        for(int i = 0; i < listaZonas.size(); i++){
            System.out.println(listaZonas.get(i).getNombre());
            listaNomZon.add(listaZonas.get(i).getNombre());
        }
        //Creo la lista de tipos de viaje
        listaTipos = tipoViajeEJB.findAll();
        listaNomTip = new ArrayList<String>();
        for(int i = 0; i < listaTipos.size(); i++){
            listaNomTip.add(listaTipos.get(i).getNombre());
        }
        //Creo la lista de grupos
        listaGrupos = gruposEJB.findAll();
        listaNomGr = new ArrayList<String>();
        for(int i = 0; i < listaGrupos.size(); i++){
            listaNomGr.add(listaGrupos.get(i).getNombre());
        }
        
        System.out.println("ENTRO EN SHOW DESTINOS CONTROLLER");
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        if(params.size() > 0){
            Integer destinoId = new Integer(params.get("destinoId"));
            destino = destinosEJB.find(destinoId);
        }
        System.out.println("URL " + destino.getUrl());
    }
    
    public boolean validarFechas() throws Exception {
        fechaInicialFinalMayoraHoy = false;
        fechaInicialyFinMayor30Dias = false;
        if (this.fechaInicio != null && this.fechaFinal != null) {
            if (isMayorFechaHoy(this.fechaInicio) && isMayorFechaHoy(this.fechaFinal)) {
                fechaInicialFinalMayoraHoy = true;
                if(this.fechaFinal.after(fechaInicio)){
                    viaje = new Viaje();
                    viaje.setDestino(destino);
                    viaje.setVendido(0);
                    viaje.setFechaInicio(fechaInicio);
                    viaje.setFechaFin(fechaFinal);
                    viaje.setNombre(nombreViaje);
                    viaje.setPersona(usuario.getPersona());
                    viajeEJB.create(viaje);  
                   
                    PrimeFaces current = PrimeFaces.current();
                    current.executeScript("PF('wdialogoNombre').hide();");
                    current.executeScript("PF('wdialogoPago').show();");
                    return true;
                }
                else
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha de Fin es mayor a la de inicio"));
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Inicio o Fecha Fin es menor a la fecha de hoy."));
        }
        return false;
    }
    
    public void validarVenta(){
        //                                 action="#{usuarioController.modificarNombre()}"
        venta = new Venta();
        Date hoy = new Date();
        venta.setFechaVenta(hoy);
        venta.setUsuario(usuario);
        viaje.setVendido(1);
        viajeEJB.edit(viaje);
        venta.setViaje(viaje);
        ventaEJB.create(venta);
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra", "Compra realizada con exito");
        PrimeFaces.current().dialog().showMessageDynamic(message);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('wdialogoPago').hide();");
            System.out.println("NOS VAMOSSSSSSSSS");
            ec.redirect("VerViaje.softwareII.xhtml?viajeId=" + venta.getViaje().getIdViajes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public Boolean isMayorFechaHoy(Date fecha) throws Exception {
        Date hoy = new Date();
        Boolean resultado = false;

        if (fecha != null && fecha.after(hoy)) {
            resultado = true;
        }

        return resultado;
    }
    
    public Boolean isMayor30Dias(Date fechaInicial, Date fechaFinal) throws Exception {
        Date fhFinal = fechaFinal;
        Boolean resultado = false;
        if (fechaInicial != null && fhFinal != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(fhFinal);
            c.add(Calendar.DATE, NEGATIVO_TREINTA);
            fhFinal = c.getTime();
            if (fechaInicial.getTime() < fhFinal.getTime()) {
                resultado = true;
            }
        }
        return resultado;
    }
    

    public void edita(){
        destinosEJB.edit(destino);
    }
    
    public void elimina(){
        destinosEJB.remove(destino);
    }
    
    public void editaZona(){
        zona = zonaEJB.buscarZona(zonaNom);
        destino.setZona(zona);
        destinosEJB.edit(destino);
    }
    
    public void editaTipo(){
        tipoViaje = tipoViajeEJB.buscarTipo(tipoNom);
        destino.setTipoViaje(tipoViaje);
        destinosEJB.edit(destino);
    }
    
    public void editaGrupo(){
        grupos = gruposEJB.buscarGrupo(grupoNom);
        destino.setGrupos(grupos);
        destinosEJB.edit(destino);
    }
    
    public Destino showDestinos(){
        destino = destinosEJB.find(destino.getIdDestino());
        return destino;
    }

    public DestinoFacadeLocal getDestinosEJB() {
        return destinosEJB;
    }

    public void setDestinosEJB(DestinoFacadeLocal destinosEJB) {
        this.destinosEJB = destinosEJB;
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

    public String getNombreViaje() {
        return nombreViaje;
    }

    public void setNombreViaje(String nombreViaje) {
        this.nombreViaje = nombreViaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ViajeFacadeLocal getViajeEJB() {
        return viajeEJB;
    }

    public void setViajeEJB(ViajeFacadeLocal viajeEJB) {
        this.viajeEJB = viajeEJB;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public boolean isFechaInicialFinalMayoraHoy() {
        return fechaInicialFinalMayoraHoy;
    }

    public void setFechaInicialFinalMayoraHoy(boolean fechaInicialFinalMayoraHoy) {
        this.fechaInicialFinalMayoraHoy = fechaInicialFinalMayoraHoy;
    }

    public boolean isFechaInicialyFinMayor30Dias() {
        return fechaInicialyFinMayor30Dias;
    }

    public void setFechaInicialyFinMayor30Dias(boolean fechaInicialyFinMayor30Dias) {
        this.fechaInicialyFinMayor30Dias = fechaInicialyFinMayor30Dias;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNumTarjSt() {
        return numTarjSt;
    }

    public void setNumTarjSt(String numTarjSt) {
        this.numTarjSt = numTarjSt;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public VentaFacadeLocal getVentaEJB() {
        return ventaEJB;
    }

    public void setVentaEJB(VentaFacadeLocal ventaEJB) {
        this.ventaEJB = ventaEJB;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public ZonaFacadeLocal getZonaEJB() {
        return zonaEJB;
    }

    public void setZonaEJB(ZonaFacadeLocal zonaEJB) {
        this.zonaEJB = zonaEJB;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public List<Zona> getListaZonas() {
        return listaZonas;
    }

    public void setListaZonas(List<Zona> listaZonas) {
        this.listaZonas = listaZonas;
    }

    public TipoViajeFacadeLocal getTipoViajeEJB() {
        return tipoViajeEJB;
    }

    public void setTipoViajeEJB(TipoViajeFacadeLocal tipoViajeEJB) {
        this.tipoViajeEJB = tipoViajeEJB;
    }

    public TipoViaje getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(TipoViaje tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public List<TipoViaje> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(List<TipoViaje> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public GruposFacadeLocal getGruposEJB() {
        return gruposEJB;
    }

    public void setGruposEJB(GruposFacadeLocal gruposEJB) {
        this.gruposEJB = gruposEJB;
    }

    public Grupos getGrupos() {
        return grupos;
    }

    public void setGrupos(Grupos grupos) {
        this.grupos = grupos;
    }

    public List<Grupos> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupos> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<String> getListaNomZon() {
        return listaNomZon;
    }

    public void setListaNomZon(List<String> listaNomZon) {
        this.listaNomZon = listaNomZon;
    }

    public List<String> getListaNomTip() {
        return listaNomTip;
    }

    public void setListaNomTip(List<String> listaNomTip) {
        this.listaNomTip = listaNomTip;
    }

    public List<String> getListaNomGr() {
        return listaNomGr;
    }

    public void setListaNomGr(List<String> listaNomGr) {
        this.listaNomGr = listaNomGr;
    }

    public String getZonaNom() {
        return zonaNom;
    }

    public void setZonaNom(String zonaNom) {
        this.zonaNom = zonaNom;
    }

    public String getTipoNom() {
        return tipoNom;
    }

    public void setTipoNom(String tipoNom) {
        this.tipoNom = tipoNom;
    }

    public String getGrupoNom() {
        return grupoNom;
    }

    public void setGrupoNom(String grupoNom) {
        this.grupoNom = grupoNom;
    }

    
    
}
