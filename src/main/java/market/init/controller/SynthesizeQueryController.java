package market.init.controller;

import market.constant.*;
import market.init.service.InitService;
import market.init.service.SynthesizeQueryService;
import market.util.Calpage;
import market.util.CreateWord;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/synQuery")
public class SynthesizeQueryController {
    @Autowired
    private SynthesizeQueryService synthesizeQueryService;
    @Autowired
    private InitService initService;

    @RequestMapping("/getCheck")
    public String getCheck(Model model,@RequestParam(required = false,value = "search")String search,
                           @RequestParam(value = "getnowpage", required = false) Integer getNowPage){
        CheckInfo checkInfo=new CheckInfo();
        checkInfo.setSearch(search);
        //分页查询
        int allNum=synthesizeQueryService.allNum(search);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        checkInfo.setPageInfo(pageInfo);

        List<CheckInfo> checkInfos=synthesizeQueryService.getCheck(checkInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("checkInfos",checkInfos);
        return "/CheckInfoList";
    }

    @RequestMapping("/checkPost")
    public String checkPost(Model model,String checkId){
        CheckInfo checkInfo=synthesizeQueryService.getCheckPost(checkId);
        System.out.println(checkInfo);
        model.addAttribute("checkInfo",checkInfo);
        return "/CheckPost";
    }

    @RequestMapping("/downloadCheck")
    public void downloadCheck(String checkId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        CheckInfo checkInfo=synthesizeQueryService.getCheckPost(checkId);
        String serverpath = ResourceUtils.getURL("static").getPath();
        String templatePath = "D:\\IDEA\\IDEA-WorkSpace\\Practice\\Market_SuperVision_SpringBoot\\src\\main\\resources\\static"+"/fileTemplate/CheckListFile.doc";
        System.out.println(templatePath);
        InputStream is = new FileInputStream(templatePath);
        HWPFDocument doc = new HWPFDocument(is);
        Range range = doc.getRange();
        //把range范围内的${reportDate}替换为当前的日期
        range.replaceText("${epName}", checkInfo.getEnterPriseInfo().getEpName());
        range.replaceText("${epAddress}", checkInfo.getEnterPriseInfo().getEpAddress());
        range.replaceText("${epArea}", checkInfo.getEnterPriseInfo().getEpArea());
        range.replaceText("${epCredit}", checkInfo.getEnterPriseInfo().getEpCredit());
        range.replaceText("${epKind}", checkInfo.getEnterPriseInfo().getEpKind());
        range.replaceText("${epLegalName}", checkInfo.getEnterPriseInfo().getEpLegalName());
        range.replaceText("${epRegisterAssets}", checkInfo.getEnterPriseInfo().getEpRegisterAssets());
        range.replaceText("${epBusinessScop}", checkInfo.getEnterPriseInfo().getEpBusinessScop());
        range.replaceText("${checkKind}", checkInfo.getCheckKind());
        range.replaceText("${checkCase}", checkInfo.getCheckCase());
        range.replaceText("${checkOpion}", checkInfo.getCheckOpion());
        range.replaceText("${checkUser}", checkInfo.getUserInfo().getUserNameCN());
        range.replaceText("${checkTime}", checkInfo.getCheckTime().substring(0,10));
        OutputStream os =  response.getOutputStream();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=downloadCheck.doc");
        //把doc输出到输出流中
        doc.write(os);
        os.close();
        is.close();
    }

    @RequestMapping("/enterPriseList")
    public String enterPriseList(Model model,@RequestParam(required = false,value = "search")String search,
                                 @RequestParam(value = "getnowpage", required = false) Integer getNowPage,
                                 String userId){
        EnterPriseInfo enterPriseInfo=new EnterPriseInfo();
        enterPriseInfo.setSearch(search);
        //分页查询
        int allNum=synthesizeQueryService.EPallNum(search);
        Calpage calpage = new Calpage();
        PageInfo pageInfo=calpage.getPageInfo(allNum,getNowPage);
        enterPriseInfo.setPageInfo(pageInfo);

        List<EnterPriseInfo> enterPriseInfos=synthesizeQueryService.getEPList(enterPriseInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("userId",userId);
        model.addAttribute("enterPriseInfos",enterPriseInfos);
        return "/EnterPrise";
    }

    @RequestMapping("/enterPrisePost")
    public String enterPrisePost(Model model,String epId,String userId,@RequestParam(required = false,value = "updateStatus")String updateStatus){
        //查询用户信息
        UserInfo userInfo=initService.getUserInfo(userId);
        String power=userInfo.getRoleId();
        EnterPriseInfo enterPriseInfo=synthesizeQueryService.getEPPost(epId);
        System.out.println(enterPriseInfo);
        System.out.println("updateStr"+updateStatus);
        if(updateStatus!=null){
            if(Integer.valueOf(updateStatus)>0){
                model.addAttribute("status","更新成功");
            }
        }
        model.addAttribute("enterPriseInfo",enterPriseInfo);
        model.addAttribute("power",power);
        model.addAttribute("userId",userInfo.getUserId());
        model.addAttribute("method","get");
        return "/EnterPrisePost";
    }

    @RequestMapping("/updateEnterPrise")
    @ResponseBody
    public String updateEnterPrise(EnterPriseInfo enterPriseInfo, String userId,HttpServletRequest request){
        System.out.println(enterPriseInfo);
        System.out.println(userId);
        int updateStatus=synthesizeQueryService.updateEnterPrise(enterPriseInfo);
        request.setAttribute("updateStatus",updateStatus);
        request.setAttribute("userId",userId);
        request.setAttribute("epId",enterPriseInfo.getEpId());
        return "更新信息成功！";
    }

    @RequestMapping("/saveEnterprise")
    @ResponseBody
    public HashMap<String,String> saveEnterprise(EnterPriseInfo enterPriseInfo){
        int saveNum=synthesizeQueryService.saveEnterprise(enterPriseInfo);
        HashMap<String,String> hashMap=new HashMap<>();
        if(saveNum>0){
            hashMap.put("data","保存成功");
        }else{
            hashMap.put("data","保存失败");
        }
        return hashMap;
    }
}