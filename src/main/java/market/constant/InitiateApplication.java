package market.constant;

public class InitiateApplication {
    private String IAId;
    private String IAName;
    private String IATime;
    private String IANational;
    private String IAIDCard;
    private String IAIdentity;
    private String IAMatter;
    private String IACourt;
    private Integer RecordId;
    private String IAReason;
    private IAMatterClassify iaMatterClassify;
    private String search;
    private PageInfo pageInfo;

    @Override
    public String toString() {
        return "InitiateApplication{" +
                "IAId='" + IAId + '\'' +
                ", IAName='" + IAName + '\'' +
                ", IATime='" + IATime + '\'' +
                ", IANational='" + IANational + '\'' +
                ", IAIDCard='" + IAIDCard + '\'' +
                ", IAIdentity='" + IAIdentity + '\'' +
                ", IAMatter='" + IAMatter + '\'' +
                ", IACourt='" + IACourt + '\'' +
                ", RecordId='" + RecordId + '\'' +
                ", IAReason='" + IAReason + '\'' +
                ", iaMatterClassify=" + iaMatterClassify +
                ", search='" + search + '\'' +
                ", pageInfo=" + pageInfo +
                '}';
    }

    public IAMatterClassify getIaMatterClassify() {
        return iaMatterClassify;
    }

    public void setIaMatterClassify(IAMatterClassify iaMatterClassify) {
        this.iaMatterClassify = iaMatterClassify;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getIAId() {
        return IAId;
    }

    public void setIAId(String IAId) {
        this.IAId = IAId;
    }

    public String getIAName() {
        return IAName;
    }

    public void setIAName(String IAName) {
        this.IAName = IAName;
    }

    public String getIATime() {
        return IATime;
    }

    public void setIATime(String IATime) {
        this.IATime = IATime;
    }

    public String getIANational() {
        return IANational;
    }

    public void setIANational(String IANational) {
        this.IANational = IANational;
    }

    public String getIAIDCard() {
        return IAIDCard;
    }

    public void setIAIDCard(String IAIDCard) {
        this.IAIDCard = IAIDCard;
    }

    public String getIAIdentity() {
        return IAIdentity;
    }

    public void setIAIdentity(String IAIdentity) {
        this.IAIdentity = IAIdentity;
    }

    public String getIAMatter() {
        return IAMatter;
    }

    public void setIAMatter(String IAMatter) {
        this.IAMatter = IAMatter;
    }

    public String getIACourt() {
        return IACourt;
    }

    public void setIACourt(String IACourt) {
        this.IACourt = IACourt;
    }

    public Integer getRecordId() {
        return RecordId;
    }

    public void setRecordId(Integer recordId) {
        RecordId = recordId;
    }

    public String getIAReason() {
        return IAReason;
    }

    public void setIAReason(String IAReason) {
        this.IAReason = IAReason;
    }
}
