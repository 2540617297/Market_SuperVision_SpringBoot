package market.init.service;

import market.constant.CheckInfo;
import market.constant.EnterPriseInfo;
import market.constant.FileChoose;
import market.init.dao.SynthesizeQueryDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynthesizeQueryService {

    @Autowired
    private SynthesizeQueryDao synthesizeQueryDao;

    public List<CheckInfo> searchCheck(CheckInfo checkInfo){
        return synthesizeQueryDao.searchCheck(checkInfo);
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

    public List<FileChoose> getCheck( String search,String id){
        return synthesizeQueryDao.getCheck(search,id);
    }

    public List<FileChoose> getNotice( String search,String id){
        return synthesizeQueryDao.getNotice(search,id);
    }

    public List<FileChoose> getIA( String search,String id){
        return synthesizeQueryDao.getIA(search,id);
    }

    public List<FileChoose> getRecord( String search,String id){
        return synthesizeQueryDao.getRecord(search,id);
    }
    public int updateCheck(@Param("id")String id,@Param("fileUrl")String fileUrl){
        return synthesizeQueryDao.updateCheck(id,fileUrl);
    }

    public int updateNotice(@Param("id")String id,@Param("fileUrl")String fileUrl){
        return  synthesizeQueryDao.updateNotice(id, fileUrl);
    }

    public int updateIA(@Param("id")String id,@Param("fileUrl")String fileUrl){
        return synthesizeQueryDao.updateIA(id, fileUrl) ;
    }

    public int updateRecord(@Param("id")String id,@Param("fileUrl")String fileUrl){
        return  synthesizeQueryDao.updateRecord(id, fileUrl);
    }

    public List<FileChoose> getBacklog(@Param("search") String search,@Param("id") String id){
        return synthesizeQueryDao.getBacklog(search, id);
    }

    public int updateBacklog(@Param("id")String id,@Param("fileUrl")String fileUrl){
        return synthesizeQueryDao.updateBacklog(id, fileUrl);
    }



}
