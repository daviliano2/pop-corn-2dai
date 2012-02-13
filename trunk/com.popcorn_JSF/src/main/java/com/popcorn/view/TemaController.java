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
    
    public TemaController() {
        
    }
        
    public Tema getTema() {
        return tema;
    }
    
    public void setTema(Tema tema) {
        this.tema = tema;
    }
    
    public Collection<Tema> getTemas() {
        temas = temaService.getAll();
        return temas;
    }
    
    @Required
    @Autowired
    public void setTemaService(TemaService temaService) {
        this.temaService = temaService;
    }
    
    public String crearTema() {
        String temaCorrecto = "no";      
        if(tema != null) {
            temaCorrecto = "si";
            tema.setFecha(new Date());
            temaService.create(tema);
        }
        return temaCorrecto;
    }
    
    
    
}
