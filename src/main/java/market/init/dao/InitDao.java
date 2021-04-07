package market.init.dao;

import market.constant.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface InitDao {
    public UserInfo login();
}
