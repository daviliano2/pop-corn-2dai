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
import com.popcorn.persistence.Tema;
import com.popcorn.service.TemaService;

import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

@Controller("temaController")
@Scope("request")
public class TemaController implements Serializable {

    private Tema tema = new Tema();
    private Collection<Tema> temas;
    private TemaService temaService;
    private int numComentarios;

    public TemaController() {
    }

    public int getNumComentarios() {
        //System.out.println("AKI temaController getNumComentarios 1 numComentarios= " + numComentarios);
        return numComentarios;
    }

    public void setNumComentarios(int numComentarios) {
        this.numComentarios = numComentarios;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Collection<Tema> getTemas() {
        //System.out.println("AKI temaController getTemas 1");
        temas = temaService.getAll();
        return temas;
    }

    public void setTemas(Collection<Tema> temas) {
        this.temas = temas;
        
    }

    public void fijarNumComentarios(Tema tema) {
        //System.out.println("AKI temaController fijarNumComentarios 1");
        //System.out.println("AKI temaController fijarNumComentarios 2 tema: " + tema.getTitulo());
        if (tema == null) {
            System.out.println("AKI temaController fijarNumComentarios 3 tema: " + tema.getTitulo());
        } else {
            numComentarios = tema.getComentarios().size();
        }
    }

    @Required
    @Autowired
    public void setTemaService(TemaService temaService) {
        this.temaService = temaService;
    }

    public String crearTema() {
        String temaCorrecto = "no";
        if (tema != null) {
            temaCorrecto = "si";
            tema.setFecha(new Date());
            temaService.create(tema);
        }
        return temaCorrecto;
    }
    
    
}
