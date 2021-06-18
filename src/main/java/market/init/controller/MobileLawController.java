package market.init.controller;

import market.constant.*;
import market.init.dao.InitDao;
import market.init.service.InitService;
import market.init.service.MobileLawService;
import market.util.Calpage;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.resource.cci.Record;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mobileLaw")
public class MobileLawController {

    @Autowired
    private MobileLawService mobileLawService;
    @Autowired
    private InitService initService;
    @Autowired
    private ServletContext context;

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
    public String saveCheckList(CheckInfo checkInfo,@RequestParam(required = false,value = "image") MultipartFile image,
                                @RequestParam(required = false,value = "vedio") MultipartFile vedio
                                ) throws IOException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = dateFormat.format(date);
        checkInfo.setCheckTime(time);
        SimpleDateFormat checkIddateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String checkId = checkIddateFormat.format(date);
        checkInfo.setCheckId(checkId);
        System.out.println(checkInfo);
        int saveInfo=mobileLawService.saveCheckList(checkInfo);
        String uploadDir = "C:\\Users\\Admin\\Pictures\\android\\" + checkId.substring(0, 8);
        if(image!=null) {
            System.out.println("image"+image.getSize());
            System.out.println("image"+image.isEmpty());
            String imageS = image.getOriginalFilename();
            System.out.println(uploadDir);
            File dirFile = new File(uploadDir);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            File imageFile = new File(uploadDir + "/" + checkId + "." + imageS.substring(imageS.lastIndexOf(".") + 1));
            image.transferTo(imageFile);
        }
        if(vedio!=null){
            System.out.println("vedio"+vedio.getSize());
            System.out.println("vedio"+vedio.isEmpty());
            String vedioS = vedio.getOriginalFilename();
            File dirFile = new File(uploadDir);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            File vedioFile = new File(uploadDir + "/" + checkId + "." + vedioS.substring(vedioS.lastIndexOf(".") + 1));
            vedio.transferTo(vedioFile);
        }

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

    @RequestMapping("/getLatLng")
    @ResponseBody
    public List<RouteInfo> getLatLng(String routeId){
        List<RouteInfo> routeInfos=mobileLawService.getLatLng(routeId);
        for (RouteInfo routeInfo:
             routeInfos) {
            System.out.println(routeInfo);
        }
        return routeInfos;
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

    @RequestMapping("/recordQuestionWrite")
    public String recordQuestionWrite(@RequestParam(required = false,value = "userId") String userId,
                                      @RequestParam(required = false,value = "method") String method,
                                      @RequestParam(required = false,value = "recordId")String recordId,
                                      Model model){
        RecordQuestionInfo recordQuestionInfo=null;
        System.out.println(recordId);
        if(recordId!=null) {
            recordQuestionInfo = mobileLawService.getRecordQuestion(recordId);
        }
        model.addAttribute("recordQuestionInfo",recordQuestionInfo);
        System.out.println(recordQuestionInfo);
        model.addAttribute("method",method);
        UserInfo userInfo=initService.getUserInfo(userId);
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("userId",userId);
        return "/RecordQuestion";
    }

    @RequestMapping("/saveRecordQuestion")
    @ResponseBody
    public String saveRecordQuestion(RecordQuestionInfo recordQuestionInfo, @RequestParam(value = "recordName")String recordName){
        recordName=recordName.substring(1);
        Integer userId=Integer.valueOf(recordName);
        recordQuestionInfo.setRecordName(userId);
        System.out.println("save"+recordQuestionInfo);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String recordId = dateFormat.format(date);
        recordQuestionInfo.setRecordId(recordId);
        int saveStatus=mobileLawService.saveRecordQUestion(recordQuestionInfo);
        System.out.println("saveRecordQuestion:"+saveStatus);
        return "保存成功";
    }

    @RequestMapping("/updateRecordQuestion")
    @ResponseBody
    public String updateRecordQuestion(RecordQuestionInfo recordQuestionInfo){
        System.out.println("save"+recordQuestionInfo);
        int saveStatus=mobileLawService.updateRecordQuestion(recordQuestionInfo);
        System.out.println("saveRecordQuestion:"+saveStatus);
        return "更新成功";
    }

    @RequestMapping("/recordQuestionList")
    public String recordQuestionList(Model model,@RequestParam(required = false,value = "search")String search,
                                     String userId,
                                     @RequestParam(value = "getnowpage", required = false) Integer getNowPage){
        RecordQuestionInfo recordQuestionInfo=new RecordQuestionInfo();
        recordQuestionInfo.setSearch(search);
        //分页查询
        int allNum=mobileLawService.getRecordQuestionNum(recordQuestionInfo);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        recordQuestionInfo.setPageInfo(pageInfo);

        List<RecordQuestionInfo> recordQuestionInfos=mobileLawService.getRecordQuestions(recordQuestionInfo);
        model.addAttribute("recordQuestionInfos",recordQuestionInfos);
        System.out.println(recordQuestionInfos);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("userId",userId);
        return "/RecordQuestionList";
    }



    @RequestMapping("/downloadRecord")
    public void downloadCheck(String recordId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        RecordQuestionInfo recordQuestionInfo=mobileLawService.getRecordQuestion(recordId);
        String serverpath = ResourceUtils.getURL("static").getPath();
        String templatePath = "D:\\IDEA\\IDEA-WorkSpace\\Practice\\Market_SuperVision_SpringBoot\\src\\main\\resources\\static"+"/fileTemplate/recordquestion.doc";
        System.out.println(templatePath);
        InputStream is = new FileInputStream(templatePath);
        HWPFDocument doc = new HWPFDocument(is);
        Range range = doc.getRange();
        //把range范围内的${reportDate}替换为当前的日期
        range.replaceText("${recordTime}", recordQuestionInfo.getRecordTime());
        range.replaceText("${recordAddress}", recordQuestionInfo.getRecordAddress());
        range.replaceText("${recordName}", recordQuestionInfo.getUserInfo().getUserNameCN());
        range.replaceText("${BRDName}", recordQuestionInfo.getBRDName());
        range.replaceText("${BRDSex}", recordQuestionInfo.getBRDSex());
        range.replaceText("${BRDPhone}", recordQuestionInfo.getBRDPhone());
        range.replaceText("${BRDIDCard}", recordQuestionInfo.getBRDIDCard());
        range.replaceText("${RQuestion1}", recordQuestionInfo.getRQuestion1());
        range.replaceText("${RAnswer1}", recordQuestionInfo.getRAnswer1());
        range.replaceText("${RQuestion2}", recordQuestionInfo.getRQuestion2());
        range.replaceText("${RAnswer2}", recordQuestionInfo.getRAnswer2());
        range.replaceText("${other}", recordQuestionInfo.getOther());
        OutputStream os =  response.getOutputStream();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=downloadRecord.doc");
        //把doc输出到输出流中
        doc.write(os);
        os.close();
        is.close();
    }

    @RequestMapping("/initiateApplication")
    public String iA(Model model,String userId){
        List<IAMatterClassify> iaMatterClassifies = mobileLawService.getIAMatterClassify();
        model.addAttribute("iaMatterClassifies",iaMatterClassifies);
        model.addAttribute("userId",userId);
        return "InitiateTheApplication";
    }

    @ResponseBody
    @RequestMapping("/saveInitiateApplication")
    public Map<String,String> saveIA(InitiateApplication initiateApplication){
        HashMap<String,String> hashMap=new HashMap<>();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String iaId = "IA"+simpleDateFormat.format(date);
        initiateApplication.setIAId(iaId);

        int saveNum=mobileLawService.saveIA(initiateApplication);
        if(saveNum>0){
            hashMap.put("data","保存成功");
        }else{
            hashMap.put("data","保存失败");
        }
        return hashMap;
    }

    @RequestMapping("/spotNotice")
    public String spotNotice(String userId,Model model){
        UserInfo userInfo=initService.getUserInfo(userId);
        model.addAttribute("userInfo",userInfo);
        return "SpotNotice";
    }

    @RequestMapping("/saveSpotNotice")
    @ResponseBody
    public Map<String,String> saveSpotNotice(SpotNoticeInfo spotNoticeInfo){
        HashMap<String,String> hashMap=new HashMap<>();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String noticeId = "SN"+simpleDateFormat.format(date);
        spotNoticeInfo.setNoticeId(noticeId);

        int saveNum=mobileLawService.saveSpotNotice(spotNoticeInfo);
        if(saveNum>0){
            hashMap.put("data","保存成功,通知编号:"+noticeId);
        }else{
            hashMap.put("data","保存失败");
        }
        return hashMap;
    }

    @RequestMapping("/downloadSpotNotice")
    public void downloadSpotNotice(String noticeId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        SpotNoticeInfo spotNoticeInfo=mobileLawService.getSpotNotice(noticeId);
        String serverpath = ResourceUtils.getURL("static").getPath();
        String templatePath = "D:\\IDEA\\IDEA-WorkSpace\\Practice\\Market_SuperVision_SpringBoot\\src\\main\\resources\\static"+"/fileTemplate/SpotNotice.doc";
        System.out.println(templatePath);
        InputStream is = new FileInputStream(templatePath);
        HWPFDocument doc = new HWPFDocument(is);
        Range range = doc.getRange();
        //把range范围内的${reportDate}替换为当前的日期
        range.replaceText("${noticeId}", spotNoticeInfo.getNoticeId());
        range.replaceText("${noticeEP}", spotNoticeInfo.getNoticeEP());
        range.replaceText("${noticeTime}", spotNoticeInfo.getNoticeTime());
        range.replaceText("${noticeMatter}", spotNoticeInfo.getNoticeMatter());
        range.replaceText("${noticeStipulate}", spotNoticeInfo.getNoticeStipulate());
        range.replaceText("${noticeLaw}", spotNoticeInfo.getNoticeLaw());
        range.replaceText("${noticeCorrectContent}", spotNoticeInfo.getNoticeCorrectContent());
        OutputStream os =  response.getOutputStream();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=downloadSpotNotice.doc");
        //把doc输出到输出流中
        doc.write(os);
        os.close();
        is.close();
    }

}
