package market.init.service;

import market.constant.CheckInfo;
import market.constant.EnterPriseInfo;
import market.init.dao.SynthesizeQueryDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynthesizeQueryService {

    @Autowired
    private SynthesizeQueryDao synthesizeQueryDao;

    public List<CheckInfo> getCheck(CheckInfo checkInfo){
        return synthesizeQueryDao.getCheck(checkInfo);
    }

    public int allNum(String search){
        return synthesizeQueryDao.allNum(search);
    }

    public CheckInfo getCheckPost(String checkId){
        return synthesizeQueryDao.getCheckPost(checkId);
    }

    public List<EnterPriseInfo> getEPList(EnterPriseInfo enterPriseInfo){
        return synthesizeQueryDao.getEPList(enterPriseInfo);
    }

    public int EPallNum(String search){
        return synthesizeQueryDao.EPallNum(search);
    }

    public EnterPriseInfo getEPPost(String epId){
        return synthesizeQueryDao.getEPPost(epId);
    }

    public int updateEnterPrise(EnterPriseInfo enterPriseInfo){
        return synthesizeQueryDao.updateEnterPrise(enterPriseInfo);
    }

    public int saveEnterprise(EnterPriseInfo enterPriseInfo){
        return synthesizeQueryDao.saveEnterprise(enterPriseInfo);
    }
}
