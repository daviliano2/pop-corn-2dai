package com.popcorn.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    
    @Basic
    private String titulo;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Basic
    private String autor;
    
    @Basic
    private String temaTitulo;
    
    @Basic
    private Key idTema;
    
    /*@ManyToOne(fetch = FetchType.LAZY)
    private Tema tema;    */
    
    @Basic
    private int autorComents;
    
    @Basic
    private String avatar;

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

    public String getTemaTitulo() {
        return temaTitulo;
    }

    public void setTemaTitulo(String temaTitulo) {
        this.temaTitulo = temaTitulo;
    }

    public Key getIdTema() {
        return idTema;
    }

    public void setIdTema(Key idTema) {
        this.idTema = idTema;
    }

    public int getAutorComents() {
        return autorComents;
    }

    public void setAutorComents(int autorComents) {
        this.autorComents = autorComents;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
