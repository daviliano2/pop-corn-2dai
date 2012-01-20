package com.popcorn.view.utils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class MessageProvider implements Serializable {

    

    public static ResourceBundle getBundle() {
        return getBundle(false);
    }

    public static ResourceBundle getBundle(boolean obtenerDeNuevo) {
        //Ineficiente pero lo otro no funciona porque ResourceBundle no se puede guardar en la sesión al no ser serializable
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getResourceBundle(context, "msg");
        /*HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("msg");
        System.out.print("AQUI bundle="+bundle);
        if (obtenerDeNuevo || bundle == null) {
            bundle = context.getApplication().getResourceBundle(context, "msg");
            session.setAttribute("msg",bundle);
        }        
        return bundle;*/
    }

    public static String getValue(String key, Object... params) {

        String result = null;
        try {
            //System.out.print("AQUI 4 getBundle()="+getBundle());
            result = getBundle().getString(key);
            if (params != null && params.length > 0) {
                MessageFormat mf = new MessageFormat(result);
                result = mf.format(params, new StringBuffer(), null).toString();
            }

        } catch (MissingResourceException e) {
            result = "???" + key + "??? not found";
        }
        return result;
    }
}
