package com.popcorn.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;
       
    @Basic
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Basic
    private String autor;
    
    @Basic
    private String titulo;
    
    /*@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comentario> comentarios = new ArrayList<Comentario>();*/

    @Basic
    List<Key> comentarios = new ArrayList<Key>();
    
    public Tema() {
    }

    public Tema(Usuario autor, String content, Date fecha, String titulo) {        
        if (autor != null) {
            this.autor = autor.getUsername();
        }
        this.content = content;
        this.fecha = fecha;
        this.titulo = titulo;
        this.comentarios = new ArrayList<Key>();  
    }
    
    public Tema(String content, Date fecha) {
        this.content = content;
        this.fecha = fecha;
        this.comentarios = new ArrayList<Key>();  
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public Key getId() {
        return id;
    }  

    public String getContent() {
        return content;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
     public List<Key> getComentarios() {
        if(comentarios == null) {
            comentarios = new ArrayList<Key>();
        }
        return comentarios;
    }

    public void setComentarios(List<Key> comentarios) {
        this.comentarios = comentarios;
    }
    
    @Override
    public String toString() {
        return comentarios.size() + "";
    }
}
