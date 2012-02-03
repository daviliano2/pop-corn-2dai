package popcorn.dao;

import com.google.appengine.api.datastore.Key;

import java.util.List;
import popcorn.persistence.Comentario;

public interface ComentarioDAO extends GenericPopDAO<Comentario, Key> {

    List<Comentario> getComentarios(String username);

    List<Comentario> getComentariosPeli(String titulo);
}
