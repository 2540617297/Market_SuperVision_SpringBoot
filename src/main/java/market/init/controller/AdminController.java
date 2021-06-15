package market.init.controller;

import market.constant.*;
import market.init.service.*;
import market.util.Calpage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private SynthesizeQueryService synthesizeQueryService;
    @Autowired
    private MobileLawService mobileLawService;


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
            session.setAttribute("userId",resultUserInfo.getUserId());
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

    @RequestMapping("/index/backlog")
    public String backLog(Model model, @RequestParam(value = "search", required = false) String search, HttpServletRequest request,
                          @RequestParam(value = "getnowpage", required = false) Integer getNowPage, HttpSession httpSession){
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
        model.addAttribute("userId",httpSession.getAttribute("userId"));
        System.out.println(httpSession.getAttribute("userId"));
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("admin","admin");
        return "/AdminBackLog";
    }

    @RequestMapping("/index/noticeWrite")
    public String noticeWrite(HttpSession httpSession,Model model){
        List<List<NavF>> lists = adminService.findAll();
        model.addAttribute("lists",lists);
        model.addAttribute("userId",httpSession.getAttribute("userId"));
        return "/AdminNoticeWrite";
    }

    @RequestMapping("/index/serviceAdd")
    @ResponseBody
    public Map<String,String> serviceAdd(HttpSession httpSession, String servicetitle, String servicecontent, Integer serviceuser) {
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
        Map<String,String> status=new HashMap<String,String>();
        status.put("status","1");
        return status;
    }

    @RequestMapping("/index/deleteBackLog")
    @ResponseBody
    public Map<String,String> deleteBackLog(String workId){
        HashMap<String,String> hashMap=new HashMap<>();
        int deleteNum=adminService.deleteBackLog(workId);

        if(deleteNum>0){
            hashMap.put("data","删除成功");
        }else{
            hashMap.put("data","删除失败");
        }
        return hashMap;
    }

    @RequestMapping("/index/userList")
    public String userList(HttpSession httpSession,Model model,@RequestParam(value = "getnowpage", required = false) Integer getNowPage,
                           @RequestParam(value = "search", required = false) String search){
        List<List<NavF>> lists = adminService.findAll();
        model.addAttribute("lists",lists);
        UserInfo userInfo=new UserInfo();
        userInfo.setSearch(search);
        //分页查询
        int allNum=adminService.searchUserNum(userInfo);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        userInfo.setPageInfo(pageInfo);
        //返回查询数据
        List<UserInfo> userInfos=adminService.searchUser(userInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("userInfos",userInfos);
        model.addAttribute("userId",httpSession.getAttribute("userId"));
        return "AdminUserList";
    }

    @RequestMapping("/index/deleteUser")
    @ResponseBody
    public Map<String,String> deleteUser(String userId){
        HashMap<String,String> hashMap=new HashMap<>();
        int deleteNum=adminService.deleteUser(userId);

        if(deleteNum>0){
            hashMap.put("data","删除成功");
        }else{
            hashMap.put("data","删除失败");
        }
        return hashMap;
    }

    @RequestMapping("/index/enterPriseList")
    public String enterPriseList(HttpSession httpSession,Model model,@RequestParam(value = "getnowpage", required = false) Integer getNowPage,
                           @RequestParam(value = "search", required = false) String search){
        List<List<NavF>> lists = adminService.findAll();
        model.addAttribute("lists",lists);
        EnterPriseInfo enterPriseInfo=new EnterPriseInfo();
        enterPriseInfo.setSearch(search);
        //分页查询
        int allNum=synthesizeQueryService.EPallNum(search);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        enterPriseInfo.setPageInfo(pageInfo);

        List<EnterPriseInfo> enterPriseInfos=synthesizeQueryService.getEPList(enterPriseInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("enterPriseInfos",enterPriseInfos);
        model.addAttribute("userId",httpSession.getAttribute("userId"));
        model.addAttribute("admin","admin");
        return "AdminEnterPriseList";
    }

    @RequestMapping("/index/deleteEnterprise")
    @ResponseBody
    public Map<String,String> deleteEnterprise(String epId){
        HashMap<String,String> hashMap=new HashMap<>();
        int deleteNum=adminService.deleteEnterprise(epId);

        if(deleteNum>0){
            hashMap.put("data","删除成功");
        }else{
            hashMap.put("data","删除失败");
        }
        return hashMap;
    }

    @RequestMapping("/index/patrolRecord")
    public String patrolRecord(HttpSession session,Model model,@RequestParam(value = "getnowpage", required = false) Integer getNowPage,
                               @RequestParam(value = "search", required = false) String search){
        //菜单
        List<List<NavF>> lists = adminService.findAll();
        model.addAttribute("lists",lists);

        RouteInfo routeInfo=new RouteInfo();
        routeInfo.setSearch(search);
        //分页查询
        int allNum=mobileLawService.getRouteNum(routeInfo);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        routeInfo.setPageInfo(pageInfo);

        List<RouteInfo> routeInfos=mobileLawService.getRouteInfo(routeInfo);
        model.addAttribute("routeInfos",routeInfos);
        System.out.println(routeInfos);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("admin","admin");
        return "AdminPatrolRecord";
    }

    @RequestMapping("/index/deletePatrolRecord")
    @ResponseBody
    public Map<String,String> deletePatrolRecord(String routeId){
        HashMap<String,String> hashMap=new HashMap<>();
        int deleteNum=adminService.deletePatrolRecord(routeId);

        if(deleteNum>0){
            hashMap.put("data","删除成功");
        }else{
            hashMap.put("data","删除失败");
        }
        return hashMap;
    }

    @RequestMapping("/index/checkInfoList")
    public String checkInfoList(HttpSession session,Model model,@RequestParam(value = "getnowpage", required = false) Integer getNowPage,
                                @RequestParam(value = "search", required = false) String search){
        //菜单
        List<List<NavF>> lists = adminService.findAll();
        model.addAttribute("lists",lists);

        CheckInfo checkInfo=new CheckInfo();
        checkInfo.setSearch(search);
        //分页查询
        int allNum=synthesizeQueryService.allNum(search);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        checkInfo.setPageInfo(pageInfo);

        List<CheckInfo> checkInfos=synthesizeQueryService.getCheck(checkInfo);
        model.addAttribute("admin","admin");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("checkInfos",checkInfos);
        return "AdminCheckList";
    }

    @RequestMapping("/index/serviceList")
    public String serviceList(HttpSession session,Model model,@RequestParam(value = "getnowpage", required = false) Integer getNowPage,
                              @RequestParam(value = "search", required = false) String search){
        List<List<NavF>> lists = adminService.findAll();
        model.addAttribute("lists",lists);

        PostInformation postInformation=new PostInformation();
        postInformation.setSearch(search);

        //分页查询
        int allNum=adminService.getServiceNum(postInformation);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        postInformation.setPageInfo(pageInfo);

        List<PostInformation> postInformations=adminService.getService(postInformation);

        model.addAttribute("admin","admin");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("postInformations",postInformations);
        return "AdminServiceList";
    }

    @RequestMapping("/index/deleteService")
    @ResponseBody
    public Map<String,String> deleteService(String service_id){
        HashMap<String,String> hashMap=new HashMap<>();
        int deleteNum=adminService.deleteService(service_id);

        if(deleteNum>0){
            hashMap.put("data","删除成功");
        }else{
            hashMap.put("data","删除失败");
        }
        return hashMap;
    }

    @RequestMapping("/postShowList")
    public String postShowList(Model model){

        return "PostShowList";
    }

    @RequestMapping("/postShow")
    public String postShow(){
        return "PostShow";
    }
}
