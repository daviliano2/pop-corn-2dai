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
import com.popcorn.persistence.Comentario;
import com.popcorn.persistence.Tema;
import com.popcorn.service.ComentarioService;

import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

@Controller("comentarioController")
@Scope("request")
public class ComentarioController implements Serializable {

    private Comentario comentario = new Comentario();
    private Collection<Comentario> comentarios;
    private ComentarioService comentarioService;
    private int numComentarios;

    public int getNumComentarios() {
        return numComentarios;
    }
    
    public Collection<Comentario> getComentarios() {
        return comentarios;
    }

    public ComentarioController() {
    }

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

    public void fijarComentarios(Tema tema) {
        //System.out.println("Aki getComentarios1 tema...."+tema.getTitulo());
        comentarios = comentarioService.getAllComentarios(tema.getId());
        numComentarios = comentarios.size();
        //System.out.println("Aki getComentarios 2...." + comentarios.toString());
    }

    public String crearComenta(Tema tema) {
        //System.out.println("Aki crearComenta");
        String comentaCorrecto = "no";
        if (comentario != null) {
            comentaCorrecto = "si";
            comentario.setFecha(new Date());
            comentarioService.create(comentario, tema.getId());
            //System.out.println("Aki crearComenta2: " + comentario);
        }
        return comentaCorrecto;
    }
}
