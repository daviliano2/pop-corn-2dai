package popcorn;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.google.appengine.api.users.User;



@Entity
public class Valoracion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*@Basic
	private String rate;*/
	@Basic 
	private User author;
	@Basic
	private String voto;
	
	/*public Valoracion(String rate,User author,String voto) {
		this.rate = rate;
		this.author = author;
		this.voto = voto;
	}*/
	public Valoracion(User author,String voto) {
		this.author = author;
		this.voto = voto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}*/
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getVoto() {
		return voto;
	}
	public void setVoto(String voto) {
		this.voto = voto;
	}
	
}
