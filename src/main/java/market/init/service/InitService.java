package market.init.service;

import market.constant.UserInfo;
import market.init.dao.InitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitService {
    @Autowired
    private InitDao initDao;

    public UserInfo login(){
        return initDao.login();
    }
}
