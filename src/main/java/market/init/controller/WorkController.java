package market.init.controller;

import com.alibaba.fastjson.JSON;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import market.constant.*;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                          @RequestParam(value = "getnowpage", required = false) Integer getNowPage, String userId){
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
        model.addAttribute("userId",userId);
        model.addAttribute("pageInfo",pageInfo);
        return "BackLog";
    }

    @RequestMapping(value="/WorkDetail",produces="text/html; charset=UTF-8")
    public String workDetails(Model model, String workId,
                              @RequestParam(required = false,value="saveStatus")String saveStatus,String userId){
        List<UserInfo> userInfos=initService.findUser(null);
        model.addAttribute("userInfos",userInfos);

        System.out.println(workId);
        WorkDetails workDetail=new WorkDetails();
        workDetail.setWorkId(workId);
        List<WorkDetails> backLogs=workService.getBackLog(workDetail);
        if(!backLogs.isEmpty()){
            workDetail=backLogs.get(0);
        }
        List<WorkStatus> workStatuses=workService.getWorkStatus();
        model.addAttribute("workStatuses",workStatuses);
        if(saveStatus!=null) {
            if (saveStatus.equals("1")) {
                model.addAttribute("saveStatus", CommonCVal.saveStatus1);
            }
            if (saveStatus.equals("2")) {
                model.addAttribute("saveStatus", CommonCVal.saveStatus2);
            }
            if (saveStatus.equals("3")) {
                model.addAttribute("saveStatus", CommonCVal.saveStatus3);
            }
        }
        model.addAttribute("workDetail",workDetail);
        model.addAttribute("userId",userId);
        System.out.println(backLogs);
        return "WorkDetail";
    }

    @RequestMapping(value="/taskDistribution",produces="text/plain;charset=UTF-8")
    public String taskDistribution(Model model,@RequestParam(required = false,value="userId")String userId,
                                   @RequestParam(required = false,value="saveStatus")String saveStatus,
                                   HttpServletRequest request) throws UnsupportedEncodingException {
        List<UserInfo> userInfos=initService.findUser(null);
        model.addAttribute("userInfos",userInfos);

        UserInfo userInfo=initService.getUserInfo(userId);
        List<WorkStatus> workStatuses=workService.getWorkStatus();
        model.addAttribute("workStatuses",workStatuses);
        model.addAttribute("userInfo",userInfo);
        System.out.println("taskUserInfo"+userInfo+":userId"+userId);
        if(saveStatus!=null) {
            if (saveStatus.equals("1")) {
                model.addAttribute("saveStatus", CommonCVal.saveStatus1);
            }
            if (saveStatus.equals("2")) {
                model.addAttribute("saveStatus", CommonCVal.saveStatus2);
            }
            if (saveStatus.equals("3")) {
                model.addAttribute("saveStatus", CommonCVal.saveStatus3);
            }
        }
        System.out.println("savestatus"+saveStatus);
        return "TaskDistribution";
    }

    @RequestMapping(value = "/saveTask",produces="text/plain;charset=UTF-8")
    public String saveTask(WorkDetails workDetails,String userId) throws UnsupportedEncodingException {
        String saveStatus=null;
        Integer status=null;
        System.out.println(workDetails);
        System.out.println(userId);
        UserInfo userInfo=initService.getUserInfo(userId);
        String pow=userInfo.getRoleId();
        if(workDetails.getWorkId()==null) {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String time = dateFormat.format(date);
            System.out.println(time);
            workDetails.setWorkId(time);
            status = workService.saveTask(workDetails);
            System.out.println(status);
            if(status!=null){
                saveStatus="3";
                System.out.println(saveStatus);
            }
            return "redirect:taskDistribution?userId="+workDetails.getAddWho()+"&saveStatus="+saveStatus;
        }else {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = dateFormat.format(date);
            workDetails.setWorkSaveTime(time);

            WorkDetails workDetail=new WorkDetails();
            workDetail.setWorkId(workDetails.getWorkId());
            List<WorkDetails> backLogs=workService.getBackLog(workDetail);
            if(!backLogs.isEmpty()){
                workDetail=backLogs.get(0);
            }
            if("3".equals(workDetail.getWorkStatus())||"4".equals(workDetail.getWorkStatus())){
                if("0".equals(pow)){
                    saveStatus="1";
                }else{
                    status=workService.updateTask(workDetails);
                    if(status!=null){
                        saveStatus="2";
                    }
                }
            }else{
                status=workService.updateTask(workDetails);
                if(status!=null){
                    saveStatus="2";
                }
            }
            return "redirect:WorkDetail?workId="+workDetails.getWorkId()+"&saveStatus="+saveStatus;
        }

    }
}
