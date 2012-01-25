package com.popcorn.dao;

import com.google.appengine.api.datastore.Key;


import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.popcorn.persistence.Tema;

@Repository
public class TemaDAOImpl extends GenericPopDAOImpl<Tema, Key> implements TemaDAO {

    @Override
    public List<Tema> getTemas(String username) {
        String sql = "SELECT c FROM Tema c WHERE c.autor='" + username + "' ORDER by fecha";
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
}