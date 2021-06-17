package market.init.service;

import market.constant.*;
import market.init.dao.MobileLawDao;
import org.apache.ibatis.annotations.Param;
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

    public List<RouteInfo> getLatLng(String routeId){
        return  mobileLawDao.getLatLng(routeId);
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

    public int saveRecordQUestion(RecordQuestionInfo questionInfo){
        return mobileLawDao.saveRecordQUestion(questionInfo);
    }

    public List<RecordQuestionInfo> getRecordQuestions(RecordQuestionInfo recordQuestionInfo){
        return mobileLawDao.getRecordQuestions(recordQuestionInfo);
    }

    public int getRecordQuestionNum(RecordQuestionInfo recordQuestionInfo){
        return mobileLawDao.getRecordQuestionNum(recordQuestionInfo);
    }

    public RecordQuestionInfo getRecordQuestion(String recordId){
        return mobileLawDao.getRecordQuestion(recordId);
    }

    public int updateRecordQuestion(RecordQuestionInfo recordQuestionInfo){
        return mobileLawDao.updateRecordQuestion(recordQuestionInfo);
    }

    public List<IAMatterClassify> getIAMatterClassify(){
        return mobileLawDao.getIAMatterClassify();
    }

    public int saveSpotNotice(SpotNoticeInfo spotNoticeInfo){
        return mobileLawDao.saveSpotNotice( spotNoticeInfo);
    }

    public SpotNoticeInfo getSpotNotice(String noticeId){
        return mobileLawDao.getSpotNotice(noticeId);
    }
}
