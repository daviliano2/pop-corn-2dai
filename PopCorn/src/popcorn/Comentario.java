package popcorn;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.google.appengine.api.users.User;

@Entity
public class Comentario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private User author;

    private String content;

    private Date date;

    public Comentario(User author, String content, Date date) {
        this.author = author;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
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

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
