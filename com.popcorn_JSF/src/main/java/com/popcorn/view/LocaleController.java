package com.popcorn.view;

import com.popcorn.view.utils.MessageProvider;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

public class LocaleController {

    public static final String LOCALE_SESSION_ATTRIBUTE_NAME = SessionLocaleResolver.class.getName() + ".LOCALE";

    private static final Map<String, Locale> paises;
    private SessionLocaleResolver localeResolver;


    
    //No se utiliza
    public void setLocaleResolver(SessionLocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
        
    }

    static {
        paises = new LinkedHashMap<String, Locale>();
        paises.put("en", Locale.ENGLISH);
        paises.put("es", new Locale("ES", "es"));
    }

   


    public void cambiarIdioma(String idioma) {
        System.out.println("AQUI LocaleController cambiarIdioma idioma=" + idioma);

        Locale locale = paises.get(idioma);

        if (locale != null) {
            HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
            WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            MessageProvider.getBundle(true); //Volvemos a obtener el fichero de mensajes para que cambien de idioma.
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, MessageProvider.getValue("idioma_cambiado"), MessageProvider.getValue("idioma_cambiado"));
            FacesContext.getCurrentInstance().addMessage(null, msg);

            //localeResolver.setDefaultLocale(locale);
        }
    }




}
