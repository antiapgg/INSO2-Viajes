/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Destino;
import com.unileon.modelo.Grupos;
import com.unileon.modelo.RolesUsuario;
import com.unileon.modelo.TipoViaje;
import com.unileon.modelo.Usuario;
import com.unileon.modelo.Zona;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author antiapgg
 */
@Stateless
public class DestinoFacade extends AbstractFacade<Destino> implements DestinoFacadeLocal {

    @PersistenceContext(unitName = "viajesVI")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DestinoFacade() {
        super(Destino.class);
    }
    
    @Override
    public List<Destino> consultarZonas(Zona zona){

        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        String consulta = "FROM Destino d WHERE d.Zona=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", zona);
        System.out.println("QUERY " + query);
        List<Destino> resultado = query.getResultList();
        System.out.println(resultado);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
        return resultado;
    }

    @Override
    public List<Destino> consultarGrupos(Grupos grupo) {
     
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        String consulta = "FROM Destino d WHERE d.Grupos=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", grupo);
        System.out.println("QUERY " + query);
        List<Destino> resultado = query.getResultList();
        System.out.println(resultado);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
        return resultado;
    
    }

    @Override
    public List<Destino> consultarTiposs(TipoViaje tipo) {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        String consulta = "FROM Destino d WHERE d.TipoViaje=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", tipo);
        System.out.println("QUERY " + query);
        List<Destino> resultado = query.getResultList();
        System.out.println(resultado);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
        return resultado;
    }
    
    @Override
    public Destino buscarDestino(String nom){
        Destino rol = new Destino();
        String consulta;
        try{
            consulta = "FROM Destino r WHERE r.Nombre=:param1";
            Query query = em.createQuery(consulta);
            query.setParameter("param1", nom);
            System.out.println(query);
            List<Destino> resultado = query.getResultList();
            System.out.println(resultado);
            
            if(resultado.isEmpty()){
                rol = null;
            }
            else{
                rol = resultado.get(0);
            }
        
        }catch(Exception e){
            System.out.println("Destino no encontrado en la base de datos. " + e.getMessage());
        }
        return rol;
    }
    
    
}
