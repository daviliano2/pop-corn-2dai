/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.controlador;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import popcorn.persistence.Comentario;
import popcorn.service.ComentarioService;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import popcorn.dao.ComentarioDAO;
import popcorn.persistence.Usuario;

@Controller
@SessionAttributes({"usuario"})
public class ComentarioController {

    private ComentarioService comentarioService;
    private UsuarioController userController;
    private ComentarioDAO comentarioDAO;

    @Autowired
    @Required
    public void setComentarioDAO(final ComentarioDAO comentarioDao) {
        this.comentarioDAO = comentarioDao;
    }

    @Autowired
    @Required
    public void setUsuarioController(UsuarioController userController) {
        this.userController = userController;
    }

    @Autowired
    @Required
    public void setComentarioService(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @RequestMapping(value = "/comentar", method = RequestMethod.POST)
    public String doCrearComentario(@RequestParam("content") String content,
            @RequestParam("idPelicula") String idStringPelicula) {
        final Usuario usuario = userController.getUser();
        final Date fecha = new Date();
        final Comentario comentario;
        if (usuario == null) {
            comentario = new Comentario(content, fecha);
        } else {
            comentario = new Comentario(usuario, content, fecha);
        }
        comentarioService.create(comentario, KeyFactory.stringToKey(idStringPelicula));
        return "redirect:ir_ver_pelicula?idPelicula=" + idStringPelicula;
    }

    @RequestMapping(value = "/ir_ver_comentario", method = RequestMethod.GET)
    public String doVerComentario(@RequestParam("idPelicula") String idPelicula, Model model) {
        final Collection<Comentario> comentarios = comentarioService.getAllComentarios(KeyFactory.stringToKey(idPelicula));
        model.addAttribute("comentarios", comentarios);
        return "/ver_comentarios";
    }

    @RequestMapping(value = "/ir_num_comentarios", method = RequestMethod.GET)
    public @ResponseBody String verNumComentarios(Model model) {
        final Usuario usuario = userController.getUser();
        List<Comentario> comentarios = comentarioDAO.getComentarios(usuario.getUsername());       
        //model.addAttribute("numComentarios", comentarios.size()); 
        return numComentJson(comentarios, usuario).toString();
    }

    private JSONObject numComentJson(List<Comentario> comentarios, Usuario usuario) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("numComen", comentarios.size());
            if(comentarios.isEmpty()) {
                jsonObject.put("comentarios", "No has comentado");
            }
        } catch (JSONException ex) {
            Logger.getLogger(ComentarioController.class.getName());
        }
        System.out.println("AQUI verNumComentarios.numComentJson comentarios: " + comentarios.size());
        return jsonObject;
    }
}