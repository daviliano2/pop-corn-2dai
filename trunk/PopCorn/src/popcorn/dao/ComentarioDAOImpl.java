package popcorn.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import popcorn.persistence.Comentario;

public class ComentarioDAOImpl extends GenericPopDAOJPAImpl<Comentario, Long> implements ComentarioDAO {
	
}