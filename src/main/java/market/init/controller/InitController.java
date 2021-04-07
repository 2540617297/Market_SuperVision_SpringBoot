package market.init.controller;

import market.constant.UserInfo;
import market.init.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitController {
    @Autowired
    private InitService initService;

    @RequestMapping("/login")
    public UserInfo login(){
        UserInfo userInfo=new UserInfo();
        userInfo=initService.login();
        return userInfo;
    }
}
