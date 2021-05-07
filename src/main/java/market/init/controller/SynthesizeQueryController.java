package market.init.controller;

import market.constant.CheckInfo;
import market.init.service.SynthesizeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/synQuery")
public class SynthesizeQueryController {
    @Autowired
    private SynthesizeQueryService synthesizeQueryService;

    @RequestMapping("/getCheck")
    @ResponseBody
    public List<CheckInfo> getCheck(){
        List<CheckInfo> checkInfos=synthesizeQueryService.getCheck();
        for (CheckInfo c :
                checkInfos) {
            System.out.println(c);
        }
        return checkInfos;
    }

}