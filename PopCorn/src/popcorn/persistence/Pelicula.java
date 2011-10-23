package popcorn.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.google.appengine.api.datastore.Key;

@Entity
public class Pelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;
	
	@Basic
    private String sinopsis;
	
	@Basic
    private String titulo;
	
	@Basic
	private String categoria;
	
	@OneToMany
    private List<Valoracion> valoraciones = new ArrayList<Valoracion>();
	
	@OneToMany
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	public Pelicula(String sinopsis, String titulo,ArrayList<Valoracion> valoraciones) {
		this.sinopsis = sinopsis;
		this.titulo = titulo;
		this.valoraciones = valoraciones;
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
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
}
