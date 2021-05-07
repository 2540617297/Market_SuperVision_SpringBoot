package market.init.controller;

import market.constant.*;
import market.init.dao.InitDao;
import market.init.service.InitService;
import market.init.service.MobileLawService;
import market.util.Calpage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/mobileLaw")
public class MobileLawController {

    @Autowired
    private MobileLawService mobileLawService;
    @Autowired
    private InitService initService;

    @RequestMapping("/enterpriseInfo")
    @ResponseBody
    public EnterPriseInfo enterpriseInfo( HttpServletRequest request){
        String epName=request.getParameter("epName");
        System.out.println("epnaemfef:"+epName);
        return mobileLawService.getEnterpriseInfo(epName);
    }

    @RequestMapping("/checkList")
    public String checkList(@RequestParam(required = false,value = "userId") String userId,@RequestParam(required = false,value="saveInfo")Integer saveInfo,
                            Model model){
        UserInfo userInfo=initService.getUserInfo(userId);
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("saveInfo",saveInfo);
        System.out.println("saveInfo:"+saveInfo);
        System.out.println("checkList:"+userInfo);
        return "CheckList";
    }

    @RequestMapping("/saveCheckList")
    public String saveCheckList(CheckInfo checkInfo){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = dateFormat.format(date);
        checkInfo.setCheckTime(time);
        System.out.println(checkInfo);
        int saveInfo=mobileLawService.saveCheckList(checkInfo);
        return "forward:/mobileLaw/checkList?userId="+checkInfo.getCheckUser()+"&saveInfo="+saveInfo;
    }

    @RequestMapping("/saveRouteInfo")
    @ResponseBody
    public Integer saveRouteInfo(RouteInfo routeInfo){
        System.out.println(routeInfo);

        RouteInfo removeSame=mobileLawService.removeRouteSame(routeInfo);
        if(removeSame==null) {
            return mobileLawService.saveRoutInfo(routeInfo);
        }else{
            return 0;
        }

    }

    @RequestMapping("/patrolRecord")
    public String getRouteInfo(Model model,@RequestParam(required = false,value = "search")String search,
                               @RequestParam(value = "getnowpage", required = false) Integer getNowPage){
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
        return "/PatrolRecord";
    }
}
