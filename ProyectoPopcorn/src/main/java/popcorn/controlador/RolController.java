/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.controlador;

import com.google.appengine.api.datastore.KeyFactory;
import java.util.ArrayList;
import java.util.Collection;
import popcorn.persistence.Rol;
import popcorn.service.RolService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RolController {
    
    private RolService rolService;
    
    @Autowired
    @Required
    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }
    
    @RequestMapping(value = "/ir_seleccionar_roles", method = RequestMethod.GET)
    public String irRoles(Model model) {
        final Collection<Rol> roles = rolService.getAllRoles();
        model.addAttribute("rol", roles);
        return "/ver_usuarios";
    }
    
    @RequestMapping(value = "/ir_ver_rol", method = RequestMethod.GET)
    public String verRol(@RequestParam("idRol") String idRol) {
        final Rol rol = rolService.getRol(idRol);
    }
}
