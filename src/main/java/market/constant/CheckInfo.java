package market.constant;

public class CheckInfo {
    private String checkId;
    private Integer checkEnterprise;
    private String checkKind;
    private String checkCase;
    private String checkOpion;
    private String checkUser;
    private String checkTime;
    private UserInfo userInfo;
    private EnterPriseInfo enterPriseInfo;
    private PageInfo pageInfo;
    private String search;

    @Override
    public String toString() {
        return "CheckInfo{" +
                "checkId='" + checkId + '\'' +
                ", checkEnterprise=" + checkEnterprise +
                ", checkKind='" + checkKind + '\'' +
                ", checkCase='" + checkCase + '\'' +
                ", checkOpion='" + checkOpion + '\'' +
                ", checkUser='" + checkUser + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", userInfo=" + userInfo +
                ", enterPriseInfo=" + enterPriseInfo +
                ", pageInfo=" + pageInfo +
                ", search='" + search + '\'' +
                '}';
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public Integer getCheckEnterprise() {
        return checkEnterprise;
    }

    public void setCheckEnterprise(Integer checkEnterprise) {
        this.checkEnterprise = checkEnterprise;
    }

    public String getCheckKind() {
        return checkKind;
    }

    public void setCheckKind(String checkKind) {
        this.checkKind = checkKind;
    }

    public String getCheckCase() {
        return checkCase;
    }

    public void setCheckCase(String checkCase) {
        this.checkCase = checkCase;
    }

    public String getCheckOpion() {
        return checkOpion;
    }

    public void setCheckOpion(String checkOpion) {
        this.checkOpion = checkOpion;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public EnterPriseInfo getEnterPriseInfo() {
        return enterPriseInfo;
    }

    public void setEnterPriseInfo(EnterPriseInfo enterPriseInfo) {
        this.enterPriseInfo = enterPriseInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
