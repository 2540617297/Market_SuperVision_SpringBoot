package market.init.service;

import market.constant.CheckInfo;
import market.constant.EnterPriseInfo;
import market.constant.RouteInfo;
import market.init.dao.MobileLawDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileLawService {

    @Autowired
    private MobileLawDao mobileLawDao;

    public EnterPriseInfo getEnterpriseInfo(String epName){
        return mobileLawDao.getEnterpriseInfo(epName);
    }

    public int saveCheckList(CheckInfo checkInfo){
        return mobileLawDao.saveCheckList(checkInfo);

    }

    public int saveRoutInfo(RouteInfo routeInfo){
        return mobileLawDao.saveRouteInfo(routeInfo);
    }

    public RouteInfo removeRouteSame(RouteInfo routeInfo){
        return mobileLawDao.removeRouteSame(routeInfo);
    }

    public List<RouteInfo> getRouteInfo(RouteInfo routeInfo){
        return mobileLawDao.getRouteInfo(routeInfo);
    }

    public int getRouteNum(RouteInfo routeInfo){
        return mobileLawDao.getRouteNum(routeInfo);
    }

}
