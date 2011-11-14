package popcorn.controlador;

import com.google.appengine.api.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InicioController {

    private UserService userService;

    @Autowired
    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/inicio", method = RequestMethod.GET)
    public String doIrInicio() {

        return "/inicio";

    }

    
}
