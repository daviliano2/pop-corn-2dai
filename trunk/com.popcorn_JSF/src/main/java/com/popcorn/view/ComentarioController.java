/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.view;

import java.io.Serializable;
import java.util.Collection;
import org.springframework.context.annotation.Scope;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import com.popcorn.persistence.Comentario;

import com.popcorn.service.ComentarioService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

@Controller("comentarioController")
@Scope("request")
public class ComentarioController implements Serializable {
    
    private Comentario comentario = new Comentario();
    private ComentarioService comentarioService;
        
    public Comentario getComentario() {
        return comentario;
    }
    
    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
    
    @Required
    @Autowired
    public void setComentarioService(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }
    
    public String crearComenta() {
        //System.out.println("Aki crearComenta");
        String comentaCorrecto = "no";      
        //System.out.println("Aki crearComenta2: " + comentario);
        if(comentario != null) {
            comentaCorrecto = "si";
            comentario.setFecha(new Date());
            comentarioService.create(comentario);
        }
        return comentaCorrecto;
    }
    
    public String verComenta() {
        String verCorrecto = "no";
        
        return verCorrecto;
    }
}
