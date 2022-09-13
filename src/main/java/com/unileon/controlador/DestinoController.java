/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controlador;

import com.unileon.EJB.DestinoFacadeLocal;
import com.unileon.EJB.GruposFacadeLocal;
import com.unileon.EJB.TipoViajeFacadeLocal;
import com.unileon.EJB.ZonaFacadeLocal;
import com.unileon.modelo.Destino;
import com.unileon.modelo.Grupos;
import com.unileon.modelo.TipoViaje;
import com.unileon.modelo.Zona;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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

public class DestinoController implements Serializable {
    
    @EJB
    private DestinoFacadeLocal destinoEJB;
    private Destino destino;
    
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
    
    @PostConstruct
    void init(){
        System.out.println("ESTOY EN INIT DE ALTADESTINOCONTROLLER");
        destino = new Destino();
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
        System.out.println("LISTAS CREADAS, READYS PARA REGISTRAR");
    }
    
    public void eliminar(Destino destin){
        destinoEJB.remove(destin);
    }
    
    public void registra(){
        try{
            zona = zonaEJB.buscarZona(zonaNom);
            destino.setZona(zona);
            grupos = gruposEJB.buscarGrupo(grupoNom);
            destino.setGrupos(grupos);
            tipoViaje = tipoViajeEJB.buscarTipo(tipoNom);
            destino.setTipoViaje(tipoViaje);
            destinoEJB.create(destino);
        }
        catch(Exception e){
            
        }   
    }
    public void goVerDestino(){
        //TODO Buscar viajes relacionados con el grupo indicado y mostrarlos
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Destino dest = destinoEJB.buscarDestino(this.destino.getNombre());
        System.out.println("THIS DESTINO: " + dest.getNombre() + " ID: " + dest.getIdDestino());
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Destino", "Destino registrado con exito");
        try {
            if(this.destino == null){
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialogoUser').hide();");
                ec.redirect("inicioAdmin.xhtml");
            } else {
                PrimeFaces current = PrimeFaces.current();
                current.executeScript("PF('wdialogoUser').hide();");
                ec.redirect("DestinoSelectedAdm.softwareII.xhtml?destinoId=" + dest.getIdDestino());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

    public DestinoFacadeLocal getDestinoEJB() {
        return destinoEJB;
    }

    public void setDestinoEJB(DestinoFacadeLocal destinoEJB) {
        this.destinoEJB = destinoEJB;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
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
       
    
    /*************************
     *   GETTERS Y SETTERS   *
     *************************/ 

   
    
}
