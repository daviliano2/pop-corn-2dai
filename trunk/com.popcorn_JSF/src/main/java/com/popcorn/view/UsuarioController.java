/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.view;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.popcorn.persistence.Usuario;
import com.popcorn.service.UsuarioService;

import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

@Controller("usuarioController")
@Scope("request")
public class UsuarioController implements Serializable {
    
    private Usuario usuario = new Usuario();
    private Collection<Usuario> usuarios;
    private UsuarioService usuarioService;
    
    public UsuarioController() {
        
    }
        
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /*public Collection<Usuario> getUsuario() {
       // System.out.println("Aki getComentarios....");
        usuarios = usuarioService.getAll();
        //System.out.println("Aki getComentarios 2...." + comentarios);
        return usuarios;
    }*/
    
    @Required
    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    public String crearUsuario() {
        //System.out.println("Aki crearComenta");
        String registroCorrecto = "no";      
        //System.out.println("Aki crearComenta2: " + comentario);
        if(usuario != null) {
            registroCorrecto = "si";
            usuarioService.create(usuario);
        }
        return registroCorrecto;
    }
    
}
