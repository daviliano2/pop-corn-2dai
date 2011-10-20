package popcorn.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.Query;


public class GenericPopDAOJPAImpl<T, PK extends Serializable> implements GenericPopDAO<T, PK> {

    /*@PersistenceContext
    EntityManager em;*/
	
    @Override
    public void insert(T t) {
    	EntityManager em;
    	em = EMF.get().createEntityManager();
        em.persist(t);
        em.close();
    }

    @Override
    public T findByPK(Class<T> typeClass, PK id) {
    	EntityManager em;
    	em = EMF.get().createEntityManager();
    	return em.find(typeClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll(Class<T> typeClass) {
    	EntityManager em;
    	em = EMF.get().createEntityManager();
        final StringBuilder sql = new StringBuilder("select c from ").append(
                typeClass.getSimpleName()).append(" c");
        return (List<T>) em.createQuery(sql.toString()).getResultList();

    }

    @Override
    public void remove(T object) {
    	EntityManager em;
    	em = EMF.get().createEntityManager();
    	em.remove(em.merge(object));
    	em.close();
    }

    @Override
    public void update(T object) {
    	EntityManager em;
    	em = EMF.get().createEntityManager();
    	em.merge(object);
    	em.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getPaginated(Class<T> typeClass, int startPosition,
            int maxResult) {
    	EntityManager em;
    	em = EMF.get().createEntityManager();
        final StringBuilder sql = new StringBuilder("select c from ").append(
                typeClass.getSimpleName()).append(" c");
        final Query query = em.createQuery(sql.toString());
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);

        return (List<T>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getOrderedPaginated(Class<T> typeClass, int startPosition,
            int maxResult, String order, int dir) {
    	EntityManager em;
    	em = EMF.get().createEntityManager();
        String direccion = "ASC";
        if (dir == 2) {
            direccion = "DESC";
        }
        final StringBuilder sql = new StringBuilder("select c from ").append(typeClass.getSimpleName()).append(" c order by ").append(order).append(" ").append(direccion);
        final Query query = em.createQuery(
                sql.toString());
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);

        return (List<T>) query.getResultList();
    }
}
