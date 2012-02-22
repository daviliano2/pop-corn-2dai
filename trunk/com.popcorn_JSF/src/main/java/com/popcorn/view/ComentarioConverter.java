package com.popcorn.view;

import com.google.appengine.api.datastore.Key;
import com.popcorn.persistence.Comentario;
import com.popcorn.service.ComentarioService;
import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;
/**
 *
 * @author david
 */
@Component
public class ComentarioConverter implements javax.faces.convert.Converter {
    
    private ComentarioService comentarioService;

    private ComentarioService getComentarioService(FacesContext context){
        return (ComentarioService)FacesContextUtils.getWebApplicationContext(context).getBean("comentarioService");
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        System.out.println("AQUI FechaConverter getAsString o="+o);
        String rdo = null;
        if (o instanceof Key) {
            Key idComentario = (Key) o;
            Comentario comentario = getComentarioService(FacesContext.getCurrentInstance()).getComentario(idComentario);
            System.out.println("AQUI FechaConverter getAsString ifecha="+comentario);
            if (comentario != null) {
                rdo = new SimpleDateFormat("yyyy/MM/dd").format(comentario.getFecha());
            }

        }
        return rdo;
    }
}

