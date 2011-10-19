package popcorn.dao;

import java.util.Date;

import javax.persistence.EntityManager;

import com.google.appengine.api.datastore.Key;
import popcorn.persistence.Comentario;
import popcorn.persistence.EMF;

public class ComentarioDAOImpl extends GenericPopDAOJPAImpl<Comentario, Key> implements ComentarioDAO {
	
	
	EntityManager em = EMF.get().createEntityManager();
    try {
    	em.persist(comentario);
    } finally {	
    	em.close();
    }
}