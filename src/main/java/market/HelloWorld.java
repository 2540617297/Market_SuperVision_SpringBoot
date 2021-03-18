package market;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("nihao");
        return "hell world";
    }
}
