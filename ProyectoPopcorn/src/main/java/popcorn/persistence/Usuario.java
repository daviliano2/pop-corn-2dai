package popcorn.persistence;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.jdo.annotations.Unique;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id; 
    
    @Unique
    private String username;

    @Basic
    private String password;
    
    @Basic
    private String nombre;
    
    @Basic
    private String apellido;
    
    @Basic
    private List<Key> categoria = new ArrayList<Key>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Rol rol; 
    
    @Basic
    private String tipoRol;

    
    
    public Usuario() {        
    }
    
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Key getId() {
        return id;
    }
    
    public void setId(Key id) {
        this.id = id;
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
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public List<Key> getCategoria() {
        return categoria;
    }
    
    public void setCategoria(List<Key> categoria) {
        this.categoria = categoria;
    }
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
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