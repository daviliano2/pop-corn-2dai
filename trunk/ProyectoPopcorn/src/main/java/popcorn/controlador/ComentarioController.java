/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.controlador;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import popcorn.persistence.Comentario;
import popcorn.service.ComentarioService;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComentarioController {
    
    private ComentarioService comentarioService;
    private UserService userService;

    @Autowired
    @Required
    public void setComentarioService(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @Autowired
    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }    
       
    @RequestMapping(value = "/comentar", method = RequestMethod.POST)
    public String doCrearComentario(@RequestParam("content")String content,
                                    @RequestParam ("idPelicula") String idStringPelicula) {
        final User author = userService.getCurrentUser();
        final Date fecha = new Date();
        comentarioService.create(content,KeyFactory.stringToKey(idStringPelicula), fecha, author);
        return "redirect:ir_ver_pelicula";
    }
    
    @RequestMapping(value = "/ir_ver_comentario", method = RequestMethod.GET)
    public String doVerComentario(@RequestParam ("idPelicula") String idPelicula, Model model) {  
        final Collection<Comentario> comentarios = comentarioService.getAllComentarios(KeyFactory.stringToKey(idPelicula));        
        model.addAttribute("comentarios", comentarios);              
        return "/ver_comentarios";
    }        
}