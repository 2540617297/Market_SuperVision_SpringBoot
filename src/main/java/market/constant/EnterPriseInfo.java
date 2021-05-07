package market.constant;

public class EnterPriseInfo {
    private String epId;
    private String epName;
    private String epAddress;
    private String epArea;
    private String epCredit;
    private String epKind;
    private String epLegalName;
    private String epRegisterAssets;
    private String epBusinessScop;

    @Override
    public String toString() {
        return "EnterPriseInfo{" +
                "epId='" + epId + '\'' +
                ", epName='" + epName + '\'' +
                ", epAddress='" + epAddress + '\'' +
                ", epArea='" + epArea + '\'' +
                ", epCredit='" + epCredit + '\'' +
                ", epKind='" + epKind + '\'' +
                ", epLegalName='" + epLegalName + '\'' +
                ", epRegisterAssets='" + epRegisterAssets + '\'' +
                ", epBusinessScop='" + epBusinessScop + '\'' +
                '}';
    }

    public String getEpId() {
        return epId;
    }

    public void setEpId(String epId) {
        this.epId = epId;
    }

    public String getEpName() {
        return epName;
    }

    public void setEpName(String epName) {
        this.epName = epName;
    }

    public String getEpAddress() {
        return epAddress;
    }

    public void setEpAddress(String epAddress) {
        this.epAddress = epAddress;
    }

    public String getEpArea() {
        return epArea;
    }

    public void setEpArea(String epArea) {
        this.epArea = epArea;
    }

    public String getEpCredit() {
        return epCredit;
    }

    public void setEpCredit(String epCredit) {
        this.epCredit = epCredit;
    }

    public String getEpKind() {
        return epKind;
    }

    public void setEpKind(String epKind) {
        this.epKind = epKind;
    }

    public String getEpLegalName() {
        return epLegalName;
    }

    public void setEpLegalName(String epLegalName) {
        this.epLegalName = epLegalName;
    }

    public String getEpRegisterAssets() {
        return epRegisterAssets;
    }

    public void setEpRegisterAssets(String epRegisterAssets) {
        this.epRegisterAssets = epRegisterAssets;
    }

    public String getEpBusinessScop() {
        return epBusinessScop;
    }

    public void setEpBusinessScop(String epBusinessScop) {
        this.epBusinessScop = epBusinessScop;
    }
}
