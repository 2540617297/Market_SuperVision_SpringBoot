package market.init.service;

import com.sun.istack.internal.Nullable;
import market.constant.UserInfo;
import market.init.dao.InitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitService {
    @Autowired
    private InitDao initDao;

    public UserInfo login(String username,@Nullable String password){
        return initDao.login(username,password);
    }

    public int register(UserInfo userInfo){
        return initDao.register(userInfo);
    }
}
