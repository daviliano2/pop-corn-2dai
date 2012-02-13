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

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.execution.RequestContext;

@Controller("comentarioController")
@Scope("request")
public class ComentarioController implements Serializable {

    Comentario comentario ;
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

    @PostConstruct
    private void iniciar(){
        comentario = new Comentario();
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
        System.out.println("Aki crearComenta");
        String comentaCorrecto = "no";
        
        
        System.out.println("temaTitulo"+tema.getTitulo());
        
        if (comentario != null) {
            comentaCorrecto = "si";
            comentario.setFecha(new Date());
              System.out.println("comentarioTitulo"+comentario.getTitulo());
            if(comentario.getTitulo() == null){
                  comentario.setTitulo(tema.getTitulo());
            }
                
                comentarioService.create(comentario, tema.getId());
            //System.out.println("Aki crearComenta2: " + comentario);
        }
        return comentaCorrecto;
    }
 
      
           
        
        public void borrarComenta(){
            System.out.println("Aki borrarComenta");
           
            
           }
    
    
        public void editarComenta(Comentario a) {
        System.out.println("Aki editarComenta");
        System.out.println("a id: "+a.getId());
        System.out.println("comentario id: "+comentario.getId());
        
        }

}
