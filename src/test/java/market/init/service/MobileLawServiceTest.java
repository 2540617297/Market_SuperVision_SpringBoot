package market.init.service;

import junit.framework.TestCase;
import market.constant.EnterPriseInfo;
import market.init.dao.MobileLawDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class MobileLawServiceTest extends TestCase {

    private MobileLawService mobileLawService=new MobileLawService();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    /**
     * 获取企业信息
     */
    public void testGetEnterpriseInfo() {
        String epName="合肥学院";
        EnterPriseInfo enterPriseInfo=mobileLawService.getEnterpriseInfo(epName);
    }

    public void testSaveCheckList() {
    }

    public void testSaveRoutInfo() {
    }

    public void testGetLatLng() {
    }

    public void testRemoveRouteSame() {
    }

    public void testGetRouteInfo() {
    }

    public void testGetRouteNum() {
    }

    public void testSaveRecordQUestion() {
    }

    public void testGetRecordQuestions() {
    }

    public void testGetRecordQuestionNum() {
    }

    public void testGetRecordQuestion() {
    }

    public void testUpdateRecordQuestion() {
    }

    public void testGetIAMatterClassify() {
    }
}