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
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import popcorn.persistence.Rol;
import popcorn.service.RolService;

@Controller
@SessionAttributes({"usuario"})
public class UsuarioController {

    private UsuarioService usuarioService;
    private RolService rolService;
    private AuthenticationManager authenticationManager;

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

    @RequestMapping(method = RequestMethod.GET, value = "/getLoginURL")
    public String getLoginURL() {
        return "/login";
    }
    
    @RequestMapping(value = "/ir_registrar_usuario", method = RequestMethod.GET)
    public String verRegistrarUsuario(Model model,Boolean valido) {
        final Collection<Rol> roles = rolService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("valido", valido);
        return "/registrar_usuario";
    }
    
    @RequestMapping(value = "/comprobar_usuario", method = RequestMethod.GET)
    public String comprobarUsuario(@RequestParam("username") String nombreUsuario,
                               @RequestParam("password") String password,@RequestParam("idRol") String idRol) {
        Boolean valido = true;
        final Rol rol = rolService.getRol(KeyFactory.stringToKey(idRol));
        if(rol.getUsuarios() != null) {
            for(Usuario usuario : rol.getUsuarios()) {
                if(usuario.getUsername().compareTo(nombreUsuario) == 0) {
                    valido = false;
                }
            }
            if(valido == true) {                
                System.out.println("id 1: " + idRol);
                return "redirect:/crear_usuario?username=" + nombreUsuario + "&password=" + password + "&idRol=" + idRol;
            } else {
                return "redirect:/ir_registrar_usuario?valido=" + valido;
            }
        } else {
            System.out.println("id 2: " + idRol);
            return "redirect:/crear_usuario?username=" + nombreUsuario + "&password=" + password + "&idRol=" + idRol;
        }
    }
    
    @RequestMapping(value = "/crear_usuario", method = RequestMethod.GET)
    public String crearUsuario(@RequestParam("username") String nombreUsuario,
                               @RequestParam("password") String password, @RequestParam("idRol") String idRol) {
        usuarioService.create(nombreUsuario, password, KeyFactory.stringToKey(idRol));        
        return "/inicio";
    }
     
    /*@RequestMapping(value= "/ir_ver_usuarios", method = RequestMethod.GET)
    public String verUsuarios(Model model) {
        final Collection<Rol> roles = rolService.getAllRoles();
        
        final Collection<Usuario> usuarios = usuarioService.getAllUsuarios(KeyFactory.stringToKey(idRol));        
        model.addAttribute("usuarios", usuarios);
        return "/ver_usuarios";
    }*/
    
    /*@RequestMapping(value = "/ir_seleccionar_peliculas", method = RequestMethod.GET)
    public String doIrPeliculas(Model model) {
        final Collection<Pelicula> peliculas = peliculaService.getAllPeliculas();
        model.addAttribute("peliculas",peliculas);
        return "/seleccionar_pelicula";
    }*/
    
    @RequestMapping(method = RequestMethod.GET, value = "/login", headers = "Accept=application/json")
    public @ResponseBody
    String login(@RequestParam String username, @RequestParam String password, Model model) {
        String rdo = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            authToken.setDetails(authentication.getDetails());
            Authentication newAuth = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(newAuth);
            if (newAuth.isAuthenticated()) {
                model.addAttribute("usuario", usuarioService.getCurrentUser());
            } else {
                rdo = "Error al conectar. Comprueba usuario y contraseña";
            }

        } catch (Exception unfe) {
            rdo = " ERROR " + unfe.getMessage();
            unfe.printStackTrace();
        }
        return rdo;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logout(HttpSession sesion, SessionStatus sessionStatus, @ModelAttribute("usuario") Usuario usuario) throws java.io.IOException {
        System.out.println("UsuarioController logout se está desconectando " + usuario.getUsername());
        SecurityContextHolder.getContext().setAuthentication(null);
        Usuario u = (Usuario) sesion.getAttribute("usuario");
        System.out.println("UsuarioController logout se está desconectando2 " + usuario.getUsername());
        sessionStatus.setComplete();
        //Otra forma: sesion.invalidate();

        return "redirect:index.html";
    }
}
