package market.init.service;

import market.constant.WorkDetails;
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




}
