package popcorn.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import java.util.Collection;
import java.util.logging.Logger;
import popcorn.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import popcorn.persistence.Valoracion;
import org.springframework.web.bind.annotation.SessionAttributes;
import popcorn.persistence.Usuario;

@Controller
@SessionAttributes({"usuario"})
public class ValoracionController {
    
    private ValoracionService valoracionService;
    private UsuarioController userController;
   
    @Autowired
    @Required
    public void setUsuarioController(UsuarioController userController) {
        this.userController = userController;
    }
    
    @Autowired
    @Required
    public void setValoracionService(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }
       
    @RequestMapping(value = "/valorar", method = RequestMethod.GET)
    public @ResponseBody String doCrearValoracion(@RequestParam ("valoracion") int val, 
                            @RequestParam ("idPelicula") String idStringPelicula) {
        final Usuario usuario = userController.getUser();        
        final Valoracion valoracion = new Valoracion(usuario, val);
        valoracionService.create(valoracion,KeyFactory.stringToKey(idStringPelicula));
        return datosValoracion(KeyFactory.stringToKey(idStringPelicula)).toString();
    }
        
    private JSONObject datosValoracion(Key idPelicula) {
        int numValoraciones = valoracionService.contarValoraciones(idPelicula);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idPelicula", idPelicula);
            jsonObject.put("valoracion", numValoraciones);
            if (numValoraciones == 0) {
                jsonObject.put("media", "Sin valorar");
            } else {
                jsonObject.put("media", valoracionService.mediaValoracion(idPelicula));
            }
        } catch (JSONException ex) {
            Logger.getLogger(ValoracionController.class.getName());
        }
        return jsonObject;
    }
    
    @RequestMapping(value = "/ir_ver_valoraciones", method = RequestMethod.GET)   
    public String doVerValoracion(@RequestParam ("idPelicula") String idPelicula, Model model) {           
        final Collection<Valoracion> valoraciones = valoracionService.getValoraciones(KeyFactory.stringToKey(idPelicula));
        final Double media = valoracionService.mediaValoracion(KeyFactory.stringToKey(idPelicula));
        final Usuario usuario = userController.getUser();    
        /*if(usuario != null) {
            for(Valoracion val : valoraciones) {
                if(val.getAutor().compareTo(usuario.getNombre()) == 0) {
                    model.addAttribute("valoracion", val.getValoracion());
                }
            }
        }      */
        model.addAttribute("media", media);
        return "/ver_valoraciones";    
    }
    
}
