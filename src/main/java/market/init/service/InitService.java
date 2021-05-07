package market.init.service;

import com.sun.istack.internal.Nullable;
import market.constant.UserInfo;
import market.init.dao.InitDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserInfo getUserInfo(String userId){
        return initDao.getUserInfo(userId);
    }

    public int updateUserInfo(UserInfo userInfo){
        return initDao.updateUserInfo(userInfo);
    }

    public List<UserInfo> findUser(String username){
        return initDao.findUser(username);
    }

    public List<UserInfo> searchUser(String search){
        return initDao.searchUser(search);
    }
}
