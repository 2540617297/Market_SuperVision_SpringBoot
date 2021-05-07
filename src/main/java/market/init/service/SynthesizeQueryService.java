package market.init.service;

import market.constant.CheckInfo;
import market.init.dao.SynthesizeQueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynthesizeQueryService {

    @Autowired
    private SynthesizeQueryDao synthesizeQueryDao;

    public List<CheckInfo> getCheck(){
        return synthesizeQueryDao.getCheck();
    }
}
