/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.view;

import java.io.Serializable;
import com.popcorn.persistence.Usuario;
import com.popcorn.service.UsuarioService;

import com.popcorn.view.utils.MessageProvider;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

@Controller("usuarioController")
@Scope("request")
public class UsuarioController implements Serializable {

    private Usuario usuario = new Usuario();
    private UsuarioService usuarioService;
    private AuthenticationManager authenticationManager;
    private UploadedFile file;
    private String error;

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

    public UsuarioController() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Usuario getCurrent() {
        return usuarioService.getCurrentUser();
    }

    public Collection<Usuario> getUsuarios() {
        return usuarioService.getAll();
    }

    public String crearUsuario(RequestContext context) {
        String registroCorrecto = "si";
        Collection<Usuario> usuarios = usuarioService.getAll();
        System.out.println("AQUI usuarioController crearUsuario usuarios: " + usuarios);
        if (usuario != null) {
            for (Usuario usr : usuarios) {
                if (usr.getUsername().equals(usuario.getUsername())) {
                    registroCorrecto = "no";
                }
            }
        }
        if (registroCorrecto.compareTo("no") == 0) {
            context.getMessageContext().addMessage(new MessageBuilder().info().defaultText(MessageProvider.getValue("registro_invalido")).build());
        } else {
            usuarioService.create(usuario);
        }
        return registroCorrecto;
    }

    public void validarUsuario(RequestContext context) {
        String rdo = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());
            authToken.setDetails(authentication.getDetails());
            Authentication newAuth = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(newAuth);
            if (newAuth.isAuthenticated()) {
                context.getFlowScope().put("usuario", usuarioService.getCurrentUser());
                context.getMessageContext().addMessage(new MessageBuilder().info().defaultText(MessageProvider.getValue("conectado")).build());
            } else {
                rdo = "Error al conectar. Comprueba usuario y contraseña";
            }
        } catch (BadCredentialsException ex) {
            context.getMessageContext().addMessage(new MessageBuilder().info().defaultText(MessageProvider.getValue("usuario_invalido")).build());
        } catch (Exception unfe) {
            rdo = " ERROR excepcion unfe: " + unfe.getMessage();
            unfe.printStackTrace();
        }
        this.error = rdo;
    }

    public void logout() throws java.io.IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        httpSession.invalidate();
    }

    public void upload() {
        FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}