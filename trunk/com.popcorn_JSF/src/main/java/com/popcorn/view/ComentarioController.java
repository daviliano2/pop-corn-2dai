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

import com.popcorn.service.TemaService;
import com.popcorn.service.UsuarioService;
import javax.annotation.PostConstruct;
import javax.faces.component.UIData;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.execution.RequestContext;

@Controller("comentarioController")
@Scope("request")
public class ComentarioController implements Serializable {

    Comentario comentario;
    private Collection<Comentario> comentarios;
    private ComentarioService comentarioService;
    private TemaService temaService;
    private UsuarioService usuarioService;
    private int numComentarios;
    private UIData tabla;
    
    @Required
    @Autowired
    public void setComentarioService(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }
    
    @Required
    @Autowired
    public void setTemaService(TemaService temaService) {
        this.temaService = temaService;
    }
    
    @Required
    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    public Collection<Comentario> getComentarios() {
        return comentarios;
    }

    public ComentarioController() {
    }

    @PostConstruct
    private void iniciar() {
        comentario = new Comentario();
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
    
    public int getNumComentarios() {
        return numComentarios;
    }
    
    public void setNumComentarios(int numComentarios) {
        this.numComentarios = numComentarios;
    }

    public Collection<Comentario> getComentarios(Tema tema) {
        return comentarioService.getAllComentarios(tema.getId());
    }
    
    public UIData getTabla() {
        return tabla;
    }

    public void setTabla(UIData tabla) {
        this.tabla = tabla;
    }

    public void fijarComentarios(Tema tema) {
        System.out.println("Aki comentarioController fijarComentarios tema...." + tema.getTitulo());
        comentarios = comentarioService.getAllComentarios(tema.getId());
        numComentarios = comentarios.size();
        System.out.println("Aki comentarioController fijarComentarios 1...." + numComentarios);
        System.out.println("Aki comentarioController fijarComentarios 2...." + comentarios.toString());
    }

    public void crearComenta(Tema tema) {
        //String comentaCorrecto = "no";
        System.out.println("Aki comentarioController crearComenta 1 tema: " + tema);
        if (comentario != null) {
            //comentaCorrecto = "si";
            System.out.println("Aki comentarioController crearComenta 2");
            comentario.setFecha(new Date());
            comentario.setAutor(usuarioService.getCurrentUser().getUsername());
            if (comentario.getTitulo() == null) {
                comentario.setTitulo(tema.getTitulo());
            }
            comentarioService.create(comentario, tema.getId());
        }
        //return comentaCorrecto;
    }

    public void borrarComentario(Comentario comentario, RequestContext context) {
        System.out.println("AQUI  comentarioController borrarComentario");
    }

    public void editarComenta(Comentario a) {
        System.out.println("Aki editarComenta");
        System.out.println("a id: " + a.getId());
        System.out.println("comentario id: " + comentario.getId());
    }
}
