package popcorn.persistence;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@Entity
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;
	
	@Basic
    private User author;
	
	@Basic
    private String content;
	
	@Basic
    private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Pelicula pelicula;
		
    public Comentario(User author, String content, Date date) {
        this.author = author;
        this.content = content;
        this.date = date;
    }

    public Key getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Key id) {
		this.id = id;
	}
    
    public void setAuthor(User author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
    
}
