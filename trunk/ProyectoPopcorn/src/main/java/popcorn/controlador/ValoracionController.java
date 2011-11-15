package popcorn.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.Collection;
import popcorn.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import popcorn.persistence.Valoracion;

@Controller
public class ValoracionController {
    
    private ValoracionService valoracionService;
    
    @Autowired
    @Required
    public void setValoracionService(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }
       
    @RequestMapping(value = "/valorar", method = RequestMethod.GET)
    public String doCrearValoracion(@RequestParam ("valoracion") int val, 
                            @RequestParam ("idPelicula") String idStringPelicula) {
        valoracionService.create(val,KeyFactory.stringToKey(idStringPelicula));
        return "redirect:ir_ver_pelicula";
    }
    
    @RequestMapping(value = "/ir_ver_valoraciones", method = RequestMethod.GET)   
    public String doVerValoracion(@RequestParam ("idPelicula") Key idPelicula, Model model) {           
        final Collection<Valoracion> valoraciones = valoracionService.getValoraciones(idPelicula);
        model.addAttribute("valoraciones", valoraciones);               
        return "/ver_valoraciones";    
    }
}
