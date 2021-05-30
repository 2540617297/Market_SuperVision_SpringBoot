package market.init.service;

import market.constant.NavF;
import market.constant.PostInformation;
import market.init.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;
    public List<NavF> findNavf(){
        return adminDao.findNavf();

    }
    public List findNavs(int navfid){
        return adminDao.findNavs(navfid);
    }

    public List<List<NavF>> findAll(){
        List<NavF> listNavf=findNavf();
        List<List<NavF>> lists=new ArrayList<>();
        for (NavF listAll:listNavf) {
            lists.add(findNavs(listAll.getNavfid()));
        }
        return lists;
    }

    public int serviceAdd(PostInformation postInformation){
        return adminDao.serviceAdd(postInformation);
    }
}
