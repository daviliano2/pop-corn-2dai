package popcorn.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;
    
    @Basic
    private String sinopsis;
    
    @Basic
    private String titulo;
    
    @Basic
    private int duracion;
    
    @Basic
    private String categoria;
    
    @Basic
    private List<String> actores = new ArrayList<String>();
    
    @Basic
    private String director;
    
    @Basic
    private String imagen;
    
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Valoracion> valoraciones ;
    
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comentario> comentarios;

    public Pelicula() {
    }

    public Pelicula(String titulo, String sinopsis, int duracion, String categoria, List<String> actores, String director, String imagen) {
        this.sinopsis = sinopsis;
        this.titulo = titulo;
        this.duracion = duracion;
        this.categoria = categoria;
        this.director = director;
        this.actores = actores;
        this.valoraciones = new ArrayList<Valoracion>();
        this.comentarios = new ArrayList<Comentario>();
        this.imagen = imagen;

    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Valoracion> getValoraciones() {
        if(valoraciones == null) {
            valoraciones = new ArrayList<Valoracion>();
        } 
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Comentario> getComentarios() {
        if(comentarios == null) {
            comentarios = new ArrayList<Comentario>();
        }
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<String> getActores() {
        return actores;
    }

    public void setActores(List<String> actores) {
        this.actores = actores;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public String getIdString() {
        return KeyFactory.keyToString(id);
    }
}
