/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popcorn.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author miguel
 */
@Controller
public class VistaController {
    
    @RequestMapping(value = "/ir_nueva_vista", method = RequestMethod.GET)
    public String doShowVerVista() {
        return "/nueva_vista";
    }
}
