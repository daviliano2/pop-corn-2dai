package com.popcorn.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;    
       
    @Basic
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Pelicula pelicula;
    
    @Basic
    private String autor;

    public Comentario() {
    }

    public Comentario(Usuario autor, String content, Date fecha) {        
        if (autor != null) {
            this.autor = autor.getUsername();
        }
        this.content = content;
        this.fecha = fecha;
    }
    
    public Comentario(String content, Date fecha) {
        this.content = content;
        this.fecha = fecha;
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

    public void setId(Key id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
