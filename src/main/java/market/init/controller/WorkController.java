package market.init.controller;

import com.alibaba.fastjson.JSON;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import market.constant.CommonCVal;
import market.constant.PageInfo;
import market.constant.UserInfo;
import market.constant.WorkDetails;
import market.init.service.InitService;
import market.init.service.WorkService;
import market.util.Calpage;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/work")
public class WorkController {
    @Autowired
    private WorkService workService;
    @Autowired
    private InitService initService;

    @RequestMapping("/BackLog")
    public String backLog(Model model, @RequestParam(value = "search", required = false) String search, HttpServletRequest request,
                          @RequestParam(value = "getnowpage", required = false) Integer getNowPage, HttpSession httpSession){
//        List<WorkDetails> backLogs=workService.getBackLog(new WorkDetails());

        WorkDetails workDetails=new WorkDetails();
        workDetails.setSearch(search);
        //分页查询
        int allNum=workService.allNum(workDetails);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        workDetails.setPageInfo(pageInfo);
        //返回查询数据
        List<WorkDetails> backLogs=workService.searchBackLog(workDetails);
        model.addAttribute("backLogs",backLogs);
        model.addAttribute("pageInfo",pageInfo);
//        System.out.println("BackLoghttpsessions"+ JSON.toJSONString(request.getSession()));
//        System.out.println("BackLoghttpsession:"+(UserInfo)httpSession.getAttribute("userInfo"));
        return "BackLog";
    }

    @RequestMapping("/WorkDetail")
    public String workDetails(Model model, String workId, HttpSession httpSession,
                              @RequestParam(value = "power", required = false) String power){
        System.out.println(workId);
        WorkDetails workDetail=new WorkDetails();
        workDetail.setWorkId(workId);
        List<WorkDetails> backLogs=workService.getBackLog(workDetail);
        if(!backLogs.isEmpty()){
            workDetail=backLogs.get(0);
        }
        model.addAttribute("workDetail",workDetail);
        System.out.println(power);
        model.addAttribute("power",power);
        System.out.println(backLogs);
        System.out.println("WorkDetailhttpsession:"+(UserInfo)httpSession.getAttribute("userInfo"));
        return "WorkDetail";
    }

    @RequestMapping("/taskDistribution")
    public String taskDistribution(HttpSession httpSession){

        return "TaskDistribution";
    }
}
