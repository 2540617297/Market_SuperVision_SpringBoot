package market.init.controller;

import com.alibaba.fastjson.JSON;
import market.constant.UserInfo;
import market.init.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


//@RestController
@Controller
@RequestMapping("/init")
public class InitController {
    @Autowired
    private InitService initService;

    @RequestMapping("/login")
//    public UserInfo login(HttpServletRequest request, HttpServletResponse response){
    @ResponseBody
    public HashMap login(Model model,String username, String password, HttpSession httpSession, HttpServletRequest request){
        UserInfo userInfo=new UserInfo();
        HashMap<String , Object> hashMap=new HashMap<String, Object>();
        System.out.println("loginSession"+JSON.toJSONString(request.getSession()));
        userInfo=initService.login(username,null);
        if(userInfo==null){
            hashMap.put("loginInfo","无当前用户，请重新输入！");
            return hashMap;
        }
        userInfo=initService.login(username,password);
        if(userInfo==null){
            hashMap.put("loginInfo","账号密码错误！");
            return hashMap;
        }
//        httpSession.setAttribute("userInfo",userInfo);
//        System.out.println((UserInfo)httpSession.getAttribute("userInfo"));
        hashMap.put("loginInfo","登录成功");
        hashMap.put("userInfo",userInfo);
        return hashMap;
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(UserInfo userInfo){
        System.out.println(userInfo);
        UserInfo userInfoQuery=initService.login(userInfo.getUserName(),null);
        if(userInfoQuery!=null){
            return "用户名已存在，请重新填写";
        }
        int insertNo=initService.register(userInfo);
        if(insertNo>0) {
            return "注册成功";
        }
        return "注册失败";
    }

}
