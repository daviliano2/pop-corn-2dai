/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.view;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import com.popcorn.persistence.Comentario;
import com.popcorn.persistence.Tema;
import com.popcorn.service.ComentarioService;

import javax.faces.context.FacesContext;
import com.popcorn.service.TemaService;
import com.popcorn.service.UsuarioService;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.component.UIData;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

@Controller("comentarioController")
@Scope("request")
public class ComentarioController implements Serializable {

    private Comentario comentario;
    private Key idComentario;
    private Collection<Key> comentarios;
    private Collection<Comentario> comentarios2 = new ArrayList<Comentario>();
    private ComentarioService comentarioService;
    private TemaService temaService;
    private UsuarioService usuarioService;
    private int numComentarios;
    
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
    
    public Collection<Key> getComentarios() {
        System.out.println("AQUI comentarioController getComentarios comentarios: " + comentarios);
        return comentarios;
    }
    
    public void setComentarios(Collection<Key> comentarios) {
        this.comentarios = comentarios;
    }   
    
    public Collection<Comentario> getComentarios2() {        
        comentarios2 = comentarioService.getAll();        
        return comentarios2;
    }

    public void setComentarios2(Collection<Comentario> comentarios2) {
        this.comentarios2 = comentarios2;
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
    
    public Key getIdComentario() {
        return comentario.getId();
    }

    public void setIdComentario(Key idComentario) {
        this.idComentario = idComentario;
    }   
    
    public Collection<Key> fijarComentarios(Tema tema) {
        comentarios = comentarioService.getAllComentarios(tema.getId());        
        System.out.println("AQUI comentarioController fijarComentarios 2 comentarios: " + comentarios);        
        return comentarios;
    }

    public void crearComenta(Tema tema) {
        if (comentario != null) {
            comentario.setFecha(new Date());
            comentario.setAutor(usuarioService.getCurrentUser().getUsername());
            comentario.setTemaTitulo(tema.getTitulo());
            comentario.setIdTema(tema.getId());
            if (comentario.getTitulo() == null) {
                comentario.setTitulo(tema.getTitulo());
            }
            comentarioService.create(comentario);
            comentarioService.addComentario(comentario, tema);
        }
    }
    
    public Comentario buscaComentario() {        
        return comentarioService.getComentario(idComentario);
    }

    public String borrarComentario(Comentario comentario, RequestContext context) {
        System.out.println("AQUI comentarioController borrarComentario comentario: " + comentario.getContent());
        comentarioService.removeComentario(comentario);
        return "si";
    }
    
    public void editarComenta(Comentario a) {
        System.out.println("Aki editarComenta");
        System.out.println("a id: " + a.getId());
        System.out.println("comentario id: " + comentario.getId());
    }
}
