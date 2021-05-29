package market.constant;

import java.io.Serializable;

public class RecordQuestionInfo implements Serializable {
    private String recordId;
    private String recordTime;
    private String recordAddress;
    private String BRDName;
    private Integer recordName;
    private String RQuestion1;
    private String RAnswer1;
    private String RQuestion2;
    private String RAnswer2;
    private String BRDSex;
    private String BRDIDCard;
    private String BRDPhone;
    private String other;
    private String search;
    private UserInfo userInfo;
    private PageInfo pageInfo;

    @Override
    public String toString() {
        return "RecordQuestionInfo{" +
                "recordId='" + recordId + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", recordAddress='" + recordAddress + '\'' +
                ", BRDName='" + BRDName + '\'' +
                ", recordName='" + recordName + '\'' +
                ", RQuestion1='" + RQuestion1 + '\'' +
                ", RAnswer1='" + RAnswer1 + '\'' +
                ", RQuestion2='" + RQuestion2 + '\'' +
                ", RAnswer2='" + RAnswer2 + '\'' +
                ", BRDSex='" + BRDSex + '\'' +
                ", BRDIDCard='" + BRDIDCard + '\'' +
                ", BRDPhone='" + BRDPhone + '\'' +
                ", other='" + other + '\'' +
                ", search='" + search + '\'' +
                ", userInfo=" + userInfo +
                ", pageInfo=" + pageInfo +
                '}';
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordAddress() {
        return recordAddress;
    }

    public void setRecordAddress(String recordAddress) {
        this.recordAddress = recordAddress;
    }

    public String getBRDName() {
        return BRDName;
    }

    public void setBRDName(String BRDName) {
        this.BRDName = BRDName;
    }

    public Integer getRecordName() {
        return recordName;
    }

    public void setRecordName(Integer recordName) {
        this.recordName = recordName;
    }

    public String getRQuestion1() {
        return RQuestion1;
    }

    public void setRQuestion1(String RQuestion1) {
        this.RQuestion1 = RQuestion1;
    }

    public String getRAnswer1() {
        return RAnswer1;
    }

    public void setRAnswer1(String RAnswer1) {
        this.RAnswer1 = RAnswer1;
    }

    public String getRQuestion2() {
        return RQuestion2;
    }

    public void setRQuestion2(String RQuestion2) {
        this.RQuestion2 = RQuestion2;
    }

    public String getRAnswer2() {
        return RAnswer2;
    }

    public void setRAnswer2(String RAnswer2) {
        this.RAnswer2 = RAnswer2;
    }

    public String getBRDSex() {
        return BRDSex;
    }

    public void setBRDSex(String BRDSex) {
        this.BRDSex = BRDSex;
    }

    public String getBRDIDCard() {
        return BRDIDCard;
    }

    public void setBRDIDCard(String BRDIDCard) {
        this.BRDIDCard = BRDIDCard;
    }

    public String getBRDPhone() {
        return BRDPhone;
    }

    public void setBRDPhone(String BRDPhone) {
        this.BRDPhone = BRDPhone;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
