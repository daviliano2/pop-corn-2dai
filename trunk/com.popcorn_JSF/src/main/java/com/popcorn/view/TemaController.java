/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.popcorn.view;

import com.google.appengine.api.datastore.Key;
import com.popcorn.persistence.Comentario;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import com.popcorn.persistence.Tema;
import com.popcorn.persistence.Usuario;
import com.popcorn.service.ComentarioService;
import com.popcorn.service.TemaService;

import com.popcorn.service.UsuarioService;
import com.popcorn.view.utils.MessageProvider;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

@Controller("temaController")
@Scope("request")
public class TemaController implements Serializable {

    private Tema tema = new Tema();
    private Tema tema2 = new Tema();
    private Collection<Tema> temas;
    private TemaService temaService;
    private UsuarioService usuarioService;
    private int numComentarios;

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

    public TemaController() {
    }

    public int getNumComentarios() {
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
    
    
    public Tema getTema2() {
        return tema2;
    }

    public void setTema2(Tema tema2) {
        this.tema2 = tema2;
    }

    public Collection<Tema> getTemas() {
        temas = temaService.getAll();
        return temas;
    }

    public void setTemas(Collection<Tema> temas) {
        this.temas = temas;

    }

    public void fijarNumComentarios(Tema tema) {
        if (tema == null) {
            System.out.println("AKI temaController fijarNumComentarios 3 tema: " + tema.getTitulo());
        } else {
            numComentarios = tema.getComentarios().size();
        }
    }

    public String crearTema() {
        String temaCorrecto = "no";
        Usuario user = usuarioService.getCurrentUser();
        if (tema != null) {
            temaCorrecto = "si";
            tema.setFecha(new Date());
            tema.setAutor(user.getUsername());
            tema.setAvatar(user.getAvatar());
            temaService.create(tema);
        }        
        return temaCorrecto;
    }

    public String borrarTema(Tema tema, RequestContext context) {
        temaService.removeTema(tema);
        context.getMessageContext().addMessage(new MessageBuilder().info().defaultText(MessageProvider.getValue("tema_borrado")).build());
        return "si";
    }

    public String editaTema() {
        /*System.out.println("AQUI temaController editaTema 1 tema2id: " + tema2.getId());
        System.out.println("AQUI temaController editaTema 2 tema2contenido: " + tema2.getContent());    
        System.out.println("AQUI temaController editaTema 3 temaid: " + tema.getId());
        System.out.println("AQUI temaController editaTema 4 temacontenido: " + tema.getContent());*/
        tema2.setAutor(usuarioService.getCurrentUser().getUsername());
        temaService.editar(tema.getId(), tema2);  
        return "si";
    }
}
