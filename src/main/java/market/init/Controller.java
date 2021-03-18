package market.init;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class Controller {

    @RequestMapping("/login")
    public String login(){
        return "";
    }
}
