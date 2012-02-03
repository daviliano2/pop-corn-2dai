package popcorn.dao;

import com.google.appengine.api.datastore.Key;

import java.util.List;
import popcorn.persistence.Noticia;

public interface NoticiaDAO extends GenericPopDAO<Noticia, Key> {
	
    List<Noticia> getNoticias();
}
