package market.init.controller;

import market.constant.*;
import market.init.service.*;
import market.util.Calpage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public String begin(HttpSession session){
        session.removeAttribute("userId");
        return "/AdminLogin";
    }
    @RequestMapping("/retrievePwd")
    public String retrievePwd(){
        return "/AdminRetrievePwd";
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
            session.setAttribute("roleId",resultUserInfo.getRoleId());
            session.setAttribute("userNameCN",resultUserInfo.getUserNameCN());
            hashMap.put("login","1");
        }else{
            hashMap.put("login","0");
        }
        return hashMap;
    }

    @RequestMapping("/resetPwd")
    @ResponseBody
    public Map<String,String> resetPwd(HttpSession session,String userName,String userPassword){
        int updateNum=initService.resetPwd(userName,userPassword);
        HashMap<String,String> hashMap=new HashMap<>();
        if(updateNum>0){
            UserInfo userInfo=new UserInfo();
            userInfo.setRoleId("1");
            userInfo.setUserName(userName);
            userInfo.setUserPassword(userPassword);
            UserInfo resultUserInfo=initService.adminLogin(userInfo);
            session.setAttribute("userInfo",resultUserInfo);
            session.setAttribute("userId",resultUserInfo.getUserId());
            session.setAttribute("roleId",resultUserInfo.getRoleId());
            session.setAttribute("userNameCN",resultUserInfo.getUserNameCN());
            hashMap.put("login","1");
        }else{
            hashMap.put("login","0");
        }
        return hashMap;
    }

    @RequestMapping("/verify")
    @ResponseBody
    public Map<String,String> verify(String userName,String phoneNo){
        Map<String,String> hashMap=new HashMap<>();
        int searchNum=initService.retrievePwd(userName,phoneNo);
        if(searchNum>0){
            hashMap.put("data","验证成功，请重新输入密码");
            hashMap.put("status","1");
        }else {
            hashMap.put("data","验证失败,请重新输入");
            hashMap.put("status","0");
        }
        return hashMap;
    }

    @RequestMapping("/adminExit")
    public String adminExit(HttpSession session){
        session.removeAttribute("userId");
        return "/admin/begin";
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
    public Map<String,String> serviceAdd(HttpSession httpSession, String servicetitle, String servicecontent, Integer serviceuser) throws IOException {
        //插入post数据
        Date nowtime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String servicetime = simpleDateFormat.format(nowtime);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("YYYYMMdd");
        String SAdir=simpleDateFormat1.format(nowtime);

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("YYYYMMddHHmmss");
        String SAfile=simpleDateFormat2.format(nowtime);

        PostInformation postInformation = new PostInformation();
        postInformation.setPosttitle(servicetitle);

        StringBuilder builderServiceContent=new StringBuilder(servicecontent);
        int indexStart=0;
        int indexEnd= 0;
        int index=0;
        Map<String,String> base64Img=new HashMap<>();
        while (true) {
            index=index+1;
            indexStart=builderServiceContent.indexOf("<img");
            indexEnd= builderServiceContent.indexOf("contenteditable=\"false\"/>");
            if(indexStart==-1){
                break;
            }
            base64Img.put("imageSA"+index,builderServiceContent.substring(indexStart,indexEnd+25));
            builderServiceContent.insert(indexEnd+25,"imageSA"+index);
            builderServiceContent.delete(indexStart,indexEnd+25);
        }
        if(base64Img.size()>0) {
            String dirURL = "C:\\Users\\Admin\\Pictures\\web\\SA" + SAdir;
            File dir = new File(dirURL);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileURL = dirURL + "\\" + SAfile + ".txt";

            File file = new File(fileURL);
            OutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(base64Img);
            objectOutputStream.close();
            System.out.println(builderServiceContent);
            postInformation.setPostfileurl(fileURL);
        }
        postInformation.setPostcontent(builderServiceContent+"");
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
        System.out.println(search);
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
        model.addAttribute("power",httpSession.getAttribute("roleId"));
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

        List<CheckInfo> checkInfos=synthesizeQueryService.searchCheck(checkInfo);
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
    public String postShowList(Model model) throws IOException, ClassNotFoundException {
        PostInformation postInformation=new PostInformation();
        PageInfo pageInfo=new PageInfo();
        pageInfo.setStartpage(0);
        pageInfo.setEndpage(5);
        postInformation.setPageInfo(pageInfo);
        List<PostInformation> postInformations=adminService.getService(postInformation);
        for (PostInformation p :
                postInformations) {
            if (p.getPostfileurl() == null) {
                p.setPostImage1(null);
            }else{
                File file=new File(p.getPostfileurl());
                InputStream inputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                HashMap<String,String> base64Img = (HashMap<String, String>) objectInputStream.readObject();
                p.setPostImage1(base64Img.get("imageSA1"));
                objectInputStream.close();
            }
        }
        model.addAttribute("postInformations",postInformations);
        return "PostShowList";
    }

    @ResponseBody
    @RequestMapping("/postShowListMore")
    public List<PostInformation> postShowListMore(String startpage,String endpage) throws IOException, ClassNotFoundException {
        PageInfo pageInfo=new PageInfo();
        pageInfo.setStartpage(Integer.parseInt(startpage));
        pageInfo.setEndpage(Integer.parseInt(endpage));
        PostInformation postInformation=new PostInformation();
        postInformation.setPageInfo(pageInfo);
        List<PostInformation> postInformations=adminService.getService(postInformation);
        for (PostInformation p :
                postInformations) {
            if (p.getPostfileurl() == null) {
                p.setPostImage1(null);
            }else{
                File file=new File(p.getPostfileurl());
                InputStream inputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                HashMap<String,String> base64Img = (HashMap<String, String>) objectInputStream.readObject();
                p.setPostImage1(base64Img.get("imageSA1"));
//                for(int i=1;i<base64Img.size();i++){
//                    String serviceContent=p.getPostcontent();
//                    serviceContent.replace("imageSA"+i,base64Img.get("imageSA"+i));
//                    p.setPostcontent(serviceContent);
//                }
                objectInputStream.close();
            }
        }

        return postInformations;
    }

    @RequestMapping("/postShow")
    public String postShow(Model model,String postid) throws IOException, ClassNotFoundException {
        PostInformation postInformation=adminService.getServiceById(Integer.parseInt(postid));
        System.out.println(postInformation);
        if(postInformation.getPostfileurl()!=null) {
            File file = new File(postInformation.getPostfileurl());
            InputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            HashMap<String, String> base64Img = (HashMap<String, String>) objectInputStream.readObject();
            StringBuilder builderServiceContent = new StringBuilder(postInformation.getPostcontent());
            System.out.println(base64Img.size());
            int indexStart = 0;
            for (int i = 1; i <= base64Img.size(); i++) {
//            serviceContent.replace("imageSA"+i,base64Img.get("imageSA"+i));
                String image = "imageSA" + i;
                indexStart = builderServiceContent.indexOf(image);
                builderServiceContent.insert(indexStart + image.length(), base64Img.get(image));
                builderServiceContent.delete(indexStart, indexStart + image.length());

            }
            postInformation.setPostcontent(builderServiceContent + "");
        }
        model.addAttribute("postInformation",postInformation);
        return "PostShow";
    }

    @RequestMapping("/index/lawList")
    public String lawEnter(HttpSession session,Model model,@RequestParam(value = "getnowpage", required = false) Integer getNowPage,
                           @RequestParam(value = "search", required = false) String search){

        List<List<NavF>> lists = adminService.findAll();
        model.addAttribute("lists",lists);

        Law law=new Law();
        law.setSearch(search);

        //分页查询
        int allNum=adminService.searchLawNum(law);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        law.setPageInfo(pageInfo);

        List<Law> laws=adminService.searchLaw(law);

        model.addAttribute("admin","admin");
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("laws",laws);
        return "AdminLawList";
    }

    @RequestMapping("/index/lawEnter")
    public String LawEnter(){
        return "LawEnter";
    }

    @RequestMapping("/index/insertLaw")
    @ResponseBody
    public Map<String,String > insertLaw(Law law){
        System.out.println(law);
        int insertNum=adminService.insertLaw(law);
        HashMap<String,String> hashMap=new HashMap<>();
        if(insertNum>0){
            hashMap.put("data","新增成功");

        }else{
            hashMap.put("data","保存失败");
        }
        return hashMap;
    }


}
