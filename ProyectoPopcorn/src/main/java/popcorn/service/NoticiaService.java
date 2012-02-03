/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.service;

import com.google.appengine.api.datastore.Key;
import java.util.Collection;
import popcorn.persistence.Noticia;
/**
 *
 * @author david
 */
public interface NoticiaService {
    
    void create(final Noticia noticia);
    
    Noticia getNoticia(Key idNoticia);
    
    Collection<Noticia> getAllNoticias();
    
    Collection<Noticia> getOrderNoticias();

    int countAllNoticias();
    
}