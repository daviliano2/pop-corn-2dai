package popcorn.persistence;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import javax.persistence.Basic;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Usuario implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id; 
    
    @Basic
    private String username;

    //@Column(nullable = false)
    @Basic
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rol rol;   
    
    
    public Usuario() {        
    }
    
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Rol getRoles() {
        return rol;
    }

    public void setRoles(Rol rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        Usuario usuario = (Usuario)o;
        return super.equals(username.equals(usuario.getUsername()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }    

    @Override
    public String toString() {
        return "Usuario{" + "username=" + username + '}';
    }

}