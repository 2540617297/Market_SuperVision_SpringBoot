package market.init.controller;

import market.constant.*;
import market.init.service.AdminService;
import market.init.service.InitService;
import market.init.service.WorkService;
import market.util.Calpage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private WorkService workService;
    @Autowired
    private InitService initService;
    @Autowired
    private AdminService adminService;


    @RequestMapping("/begin")
    public String begin(){
        return "/AdminLogin";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,String> adminLogin(HttpSession session,UserInfo userInfo){
        System.out.println(userInfo);
        HashMap<String,String> hashMap=new HashMap<>();
        userInfo.setRoleId("1");
        UserInfo resultUserInfo=initService.adminLogin(userInfo);
        if(resultUserInfo!=null){
            session.setAttribute("userInfo",resultUserInfo);
            hashMap.put("login","1");
        }else{
            hashMap.put("login","0");
        }
        return hashMap;
    }


    @RequestMapping("/index")
    public String index(Model model){
        List<List<NavF>> lists = adminService.findAll();
        model.addAttribute("lists",lists);
        return "/Index";
    }

    @RequestMapping("/userList")
    public String userList(Model model){
        List<List<NavF>> lists = adminService.findAll();
        model.addAttribute("lists",lists);
        return "/AdminUserList";
    }

    @RequestMapping("/backlog")
    public String backLog(Model model, @RequestParam(value = "search", required = false) String search, HttpServletRequest request,
                          @RequestParam(value = "getnowpage", required = false) Integer getNowPage, String userId){
        WorkDetails workDetails=new WorkDetails();
        workDetails.setSearch(search);
        //分页查询
        int allNum=workService.allNum(workDetails);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        workDetails.setPageInfo(pageInfo);
        //返回查询数据
        List<WorkDetails> backLogs=workService.searchBackLog(workDetails);
        List<List<NavF>> lists = adminService.findAll();
        model.addAttribute("lists",lists);
        model.addAttribute("backLogs",backLogs);
        model.addAttribute("userId",userId);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("admin","admin");
        return "/AdminBackLog";
    }

    @RequestMapping("/noticeWrite")
    public String noticeWrite(){
        return "/AdminNoticeWrite";
    }

    @RequestMapping("/serviceAdd")
    public String serviceAdd(String username, String servicetitle, String servicecontent, int serviceuser) {
        //插入post数据
        Date nowtime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String servicetime = simpleDateFormat.format(nowtime);
        PostInformation postInformation = new PostInformation();
        postInformation.setPosttitle(servicetitle);
        postInformation.setPostcontent(servicecontent);
        postInformation.setPosttime(servicetime);
        postInformation.setPostuser(serviceuser);
        adminService.serviceAdd(postInformation);
        return "redirect:/adminController/serviceedit";
    }

}
