package popcorn.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.Date;


@Entity
public class Noticia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;
    
    @Basic
    private String titulo;
    
    @Basic
    private String contenido;
    
    @Basic
    private String imagen;
     
    @Basic
    private String fuenteNoticia;    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Basic
    private String trailer;
    
    public Noticia() {
    }

    public Noticia(String titulo, String contenido, Date fecha, String trailer, String fuenteNoticia) {
        this.trailer = trailer;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha; 
        this.fuenteNoticia = fuenteNoticia;
    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
    
    public String getFuenteNoticia() {
        return fuenteNoticia;
    }

    public void setFuenteNoticia(String fuenteNoticia) {
        this.fuenteNoticia = fuenteNoticia;
    }

    public String getIdString() {
        return KeyFactory.keyToString(id);
    }
}
