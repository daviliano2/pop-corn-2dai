package com.popcorn.dao;

import com.google.appengine.api.datastore.Key;


import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.popcorn.persistence.Comentario;

@Repository
public class ComentarioDAOImpl extends GenericPopDAOImpl<Comentario, Key> implements ComentarioDAO {

    @Override
    public List<Key> getComentariosTema(String tema) {
        //System.out.println("AQUI comentarioDAOImpl antes de la query tema: " + tema);
        String sql = "SELECT c FROM Comentario c WHERE c.tema='" + tema + "' ORDER by fecha";
        Query query = em.createQuery(sql);
       // System.out.println("AQUI comentarioDAOImpl despues de la query tema: " + tema);
        return query.getResultList();
    }
}