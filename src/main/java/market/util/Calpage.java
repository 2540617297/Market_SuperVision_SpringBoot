package market.util;

import market.constant.CommonCVal;
import market.constant.PageInfo;

public class Calpage {
    public Calpage() {
    }

    public int calpage(Integer getnowpage, int allpagenum) {
        int nowpage;
        if (getnowpage == null) {
            nowpage = 1;
        } else {
            nowpage = getnowpage;
        }
        if (nowpage < 1) {
            nowpage = 1;
        }
        if (nowpage > allpagenum) {
            nowpage = allpagenum;
        }
        if (allpagenum == 0) {
            nowpage = 1;
        }
        return nowpage;
    }

    /**
     *
     * @param allNum 数据库总数据数量
     * @param getNowPage 前端页面传输的当前页面数量
     * @return
     */
    public PageInfo getPageInfo(int allNum,Integer getNowPage){
        int nowPage=0;
        PageInfo pageInfo = new PageInfo();
        int allpagenum = (int) Math.ceil((double) allNum / (double) CommonCVal.PAGE);
        nowPage = calpage(getNowPage, allpagenum);
        pageInfo.setNowpage(nowPage);
        pageInfo.setStartpage(nowPage * CommonCVal.PAGE - CommonCVal.PAGE);
        pageInfo.setEndpage(CommonCVal.PAGE);
        pageInfo.setPage(CommonCVal.PAGE);
        pageInfo.setAllpage(allpagenum);
        return pageInfo;
    }
}
