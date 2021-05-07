package market.init.service;

import market.constant.EnterPriseInfo;
import market.constant.WorkDetails;
import market.constant.WorkStatus;
import market.init.dao.WorkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService {

    @Autowired
    private WorkDao workDao;

    public List<WorkDetails> getBackLog(WorkDetails workDetails){
        return workDao.getBackLog(workDetails);
    }

    public List<WorkDetails> searchBackLog(WorkDetails workDetails) {
        return workDao.searchBackLog(workDetails);
    }

    public int allNum(WorkDetails workDetails){

        return workDao.allNum(workDetails);
    }

    public int saveTask(WorkDetails workDetails){
        return workDao.saveTask(workDetails);
    }

    public List<WorkStatus> getWorkStatus(){
        return workDao.getWorkStatus();
    }

    public int updateTask(WorkDetails workDetails){
        return workDao.updateTask(workDetails);
    }
}
