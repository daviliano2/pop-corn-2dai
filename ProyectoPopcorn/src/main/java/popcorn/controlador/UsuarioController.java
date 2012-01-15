/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.controlador;

import com.google.appengine.api.datastore.KeyFactory;
import java.util.Collection;
import popcorn.persistence.Usuario;
import popcorn.service.UsuarioService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import popcorn.dao.UsuarioDAO;
import popcorn.persistence.Rol;
import popcorn.service.PeliculaService;
import popcorn.service.RolService;

@Controller
@SessionAttributes({"usuario"})
public class UsuarioController {

    private UsuarioService usuarioService;
    private RolService rolService;
    private AuthenticationManager authenticationManager;
    private PeliculaService peliculaService;
    private UsuarioDAO usuarioDAO;

    @Autowired
    @Required
    public void setUsuarioDAO(final UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Autowired
    @Required
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    @Required
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    @Required
    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsuarioConectado", headers = "Accept=application/json")
    public @ResponseBody
    Usuario getUser() {
        return usuarioService.getCurrentUser();
    }

    @RequestMapping(value = "/ir_ver_login", method = RequestMethod.GET)
    public String irLogin(Model model) {
        final Collection<Rol> roles = rolService.getAllRoles();
        for (Rol rol : roles) {
            if (rol.getNombre().compareTo("ROLE_USER") == 0) {
                model.addAttribute("roles", rol);
            }
            if (rol.getNombre().compareTo("ROLE_ADMIN") == 0) {
                model.addAttribute("roles1", rol);
            }
        }
        return "/login";
    }

    @RequestMapping(value = "/ir_registrar_usuario", method = RequestMethod.GET)
    public String verRegistrarUsuario(Model model) {
        final Collection<Rol> roles = rolService.getAllRoles();
        for (Rol rol : roles) {
            if (rol.getNombre().compareTo("ROLE_USER") == 0) {
                model.addAttribute("roles", rol);
                //model.addAttribute("valido", valido);
            }
        }
        return "/registrar_usuario";
    }
    
    @RequestMapping(value = "/ir_comprobar_usuario", method = RequestMethod.GET)
    public @ResponseBody String comprobarUsuario(@RequestParam("user") String nombreUsuario, @RequestParam("pass") String password,
                                                 @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
                                                 @RequestParam("idRol") String idRol) {
        String valido = "ok";
        if (usuarioDAO.findByString(nombreUsuario) != null) {
            valido = null;
        }
        /*System.out.println("Aqui comprobarUsuario 1 username, password, nombre, apellido, idRol: " + 
                nombreUsuario + password + nombre + apellido + idRol );*/
        if (valido != null) {
            return crearUsuario(nombreUsuario, password, nombre, apellido, idRol);
        } else {
            return valido;
        }
    }

    @RequestMapping(value = "/crear_usuario", method = RequestMethod.GET)
    public String crearUsuario(@RequestParam("user") String nombreUsuario, @RequestParam("pass") String password,
                               @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
                               @RequestParam("idRol") String idRol) {
        usuarioService.create(nombreUsuario, password, nombre, apellido, KeyFactory.stringToKey(idRol));
        /*System.out.println("Aqui crearUsuario 1 username, password, nombre, apellido, idRol: " + 
               nombreUsuario + password + nombre + apellido + idRol);*/
        return "redirect:/inicio";
    }

    @RequestMapping(value = "/ir_conectar_usuario", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody String conectar(@RequestParam String username,
                                         @RequestParam String password, Model model) {
        String rdo = null;
        try {
            //System.out.println("AQUI 1 UserController login ");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //System.out.println("AQUI 2 UserController login ");
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            //System.out.println("AQUI 3 UserController login ");
            authToken.setDetails(authentication.getDetails());
            //System.out.println("AQUI 4 UserController login authenticationManager="+authenticationManager);
            Authentication newAuth = authenticationManager.authenticate(authToken);
            //System.out.println("AQUI 5 UserController login ");
            SecurityContextHolder.getContext().setAuthentication(newAuth);
            //System.out.println("AQUI 6 UserController login newAuth.getName(): " + newAuth.getName());
            if (newAuth.isAuthenticated()) {
                //System.out.println("AQUI conectar usuario: " + usuarioService.getCurrentUser());
                model.addAttribute("usuario", usuarioService.getCurrentUser());
                //System.out.println("UsuarioController conectar se esta conectando " + username);
                //rdo = "Estas conectado como " + username;
            } else {
                rdo = "Error al conectar. Comprueba usuario y contraseña";
            }
            //System.out.println("AQUI 7 UserController login ");
        } catch (Exception unfe) {
            rdo = " ERROR " + unfe.getMessage();
            //unfe.printStackTrace();
        }
        return rdo/*"redirect:/inicio"*/;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession sesion, SessionStatus sessionStatus, @ModelAttribute("usuario") Usuario usuario) throws java.io.IOException {
        System.out.println("UsuarioController logout se está desconectando " + usuario.getUsername());
        SecurityContextHolder.getContext().setAuthentication(null);
        Usuario u = (Usuario) sesion.getAttribute("usuario");
        //System.out.println("UsuarioController logout se está desconectando2 " + usuario.getUsername());
        sessionStatus.setComplete();
        //Otra forma: sesion.invalidate();

        return "redirect:index.html";
    }
}
