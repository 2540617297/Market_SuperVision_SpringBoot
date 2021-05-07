package market.constant;

public class CheckInfo {
    private String checkId;
    private Integer checkEnterprise;
    private String checkKind;
    private String checkCase;
    private String checkOpion;
    private String checkUser;
    private String checkTime;
    private EnterPriseInfo enterPriseInfo;

    public EnterPriseInfo getEnterPriseInfo() {
        return enterPriseInfo;
    }

    public void setEnterPriseInfo(EnterPriseInfo enterPriseInfo) {
        this.enterPriseInfo = enterPriseInfo;
    }

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
                '}';
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
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
}
