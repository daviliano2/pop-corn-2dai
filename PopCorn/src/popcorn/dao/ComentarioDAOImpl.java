package popcorn.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import popcorn.persistence.Comentario;

public class ComentarioDAOImpl extends GenericPopDAOJPAImpl<Comentario, Long> implements ComentarioDAO {
	
	@Override	
	public void insert(Comentario c) {
		EntityManager em;
		em = EMF.get().createEntityManager();
		em.persist(c);
		em.close();
    }
	
	@Override
    public Comentario findByPK(Class<Comentario> typeClass, Long id) {
		EntityManager em;
		em = EMF.get().createEntityManager();
		return em.find(typeClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comentario> getAll(Class<Comentario> typeClass) {
    	EntityManager em;
    	em = EMF.get().createEntityManager();
        final StringBuilder sql = new StringBuilder("select c from ").append(
                typeClass.getSimpleName()).append(" c");
        return (List<Comentario>) em.createQuery(sql.toString()).getResultList();
    }
}