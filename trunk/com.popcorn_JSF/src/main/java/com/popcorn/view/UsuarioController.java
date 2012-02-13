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

import javax.annotation.PostConstruct;
import javax.faces.bean.CustomScoped;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

@Controller("usuarioController")
@Scope("request")
public class UsuarioController implements Serializable {
    
    private Usuario usuario = new Usuario();
    private Collection<Usuario> usuarios;
    private UsuarioService usuarioService;
    private AuthenticationManager authenticationManager;
    private String error;
    
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
   @PostConstruct
    public void iniciar(){
        usuario= new Usuario();
    }
    
    @Required
    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @Autowired
    @Required
    @Qualifier("authenticationManager")
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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
    
    
    public void validarUsuario (RequestContext context){
          String rdo = null;        
 
          try {
            System.out.println(usuario.getUsername() + usuario.getPassword());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Linea 2");            
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(),usuario.getPassword());
            System.out.println("Linea 3");
            authToken.setDetails(authentication.getDetails());
            System.out.println("Linea 4"+ authentication);
            System.out.println("--------------------");
            System.out.println(authToken);
            System.out.println("Linea 5"+authenticationManager.authenticate(authToken));
            Authentication newAuth = authenticationManager.authenticate(authToken);
           
            SecurityContextHolder.getContext().setAuthentication(newAuth);
            System.out.println("Linea 6");
            if (newAuth.isAuthenticated()) {
            System.out.println("Linea 7");
            context.getFlowScope().put("usuario", usuarioService.getCurrentUser());
                System.out.println(usuario.getUsername() + "LOGON");
              
               // model.addAttribute("usuario", usuarioService.getCurrentUser());
            } else {
                rdo = "Error al conectar. Comprueba usuario y contraseña";
            }
        } catch (Exception unfe) {
            rdo = " ERROR " + unfe.getMessage();
            unfe.printStackTrace();
        }
        this.error=rdo;
        System.out.println(this.error);
        
    }
}
