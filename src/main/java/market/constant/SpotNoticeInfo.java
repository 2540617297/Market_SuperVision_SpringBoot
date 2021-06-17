package market.constant;

public class SpotNoticeInfo {

    private String noticeId;
    private String noticeEP;
    private String noticeTime;
    private String noticeMatter;
    private String noticeLaw;
    private String noticeCorrectContent;
    private String noticeStipulate;
    private int addWho;
    private String search;
    private PageInfo pageInfo;

    @Override
    public String toString() {
        return "SpotNoticeInfo{" +
                "noticeId='" + noticeId + '\'' +
                ", noticeEP='" + noticeEP + '\'' +
                ", noticeTime='" + noticeTime + '\'' +
                ", noticeMatter='" + noticeMatter + '\'' +
                ", noticeLaw='" + noticeLaw + '\'' +
                ", noticeCorrectContent='" + noticeCorrectContent + '\'' +
                ", noticeStipulate='" + noticeStipulate + '\'' +
                ", addWho=" + addWho +
                ", search='" + search + '\'' +
                ", pageInfo=" + pageInfo +
                '}';
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeEP() {
        return noticeEP;
    }

    public void setNoticeEP(String noticeEP) {
        this.noticeEP = noticeEP;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getNoticeMatter() {
        return noticeMatter;
    }

    public void setNoticeMatter(String noticeMatter) {
        this.noticeMatter = noticeMatter;
    }

    public String getNoticeLaw() {
        return noticeLaw;
    }

    public void setNoticeLaw(String noticeLaw) {
        this.noticeLaw = noticeLaw;
    }

    public String getNoticeCorrectContent() {
        return noticeCorrectContent;
    }

    public void setNoticeCorrectContent(String noticeCorrectContent) {
        this.noticeCorrectContent = noticeCorrectContent;
    }

    public String getNoticeStipulate() {
        return noticeStipulate;
    }

    public void setNoticeStipulate(String noticeStipulate) {
        this.noticeStipulate = noticeStipulate;
    }

    public int getAddWho() {
        return addWho;
    }

    public void setAddWho(int addWho) {
        this.addWho = addWho;
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
}
