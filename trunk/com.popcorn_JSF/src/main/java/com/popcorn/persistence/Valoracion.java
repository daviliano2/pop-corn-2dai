package com.popcorn.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class Valoracion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;
    
    @Basic
    private String autor;
    
    @Basic
    private int valoracion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Pelicula pelicula;
    
    public Valoracion() {
    }

    public Valoracion(Usuario autor, int valoracion) {        
        if (autor != null) {
            this.autor = autor.getUsername();
        }
        this.valoracion = valoracion;
    }
    
    public Valoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    
    public String getIdString() {
        return KeyFactory.keyToString(id);
    }
}
