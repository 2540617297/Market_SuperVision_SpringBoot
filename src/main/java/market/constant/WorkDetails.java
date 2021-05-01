package market.constant;

public class WorkDetails {
    private String workId;
    private String workName;
    private String workDetails;
    private String addWho;
    private String workWho;
    private String workEndTime;
    private String solveDetails;
    private String workStatus;
    private String addWhoName;
    private String workWhoName;
    private String search;
    private PageInfo pageInfo;

    @Override
    public String toString() {
        return "WorkDetails{" +
                "workId='" + workId + '\'' +
                ", workName='" + workName + '\'' +
                ", workDetails='" + workDetails + '\'' +
                ", addWho='" + addWho + '\'' +
                ", workWho='" + workWho + '\'' +
                ", workEndTime='" + workEndTime + '\'' +
                ", solveDetails='" + solveDetails + '\'' +
                ", workStatus='" + workStatus + '\'' +
                ", addWhoName='" + addWhoName + '\'' +
                ", workWhoName='" + workWhoName + '\'' +
                ", search='" + search + '\'' +
                ", pageInfo=" + pageInfo +
                '}';
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

    public String getAddWhoName() {
        return addWhoName;
    }

    public void setAddWhoName(String addWhoName) {
        this.addWhoName = addWhoName;
    }

    public String getWorkWhoName() {
        return workWhoName;
    }

    public void setWorkWhoName(String workWhoName) {
        this.workWhoName = workWhoName;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkDetails() {
        return workDetails;
    }

    public void setWorkDetails(String workDetails) {
        this.workDetails = workDetails;
    }

    public String getAddWho() {
        return addWho;
    }

    public void setAddWho(String addWho) {
        this.addWho = addWho;
    }

    public String getWorkWho() {
        return workWho;
    }

    public void setWorkWho(String workWho) {
        this.workWho = workWho;
    }

    public String getWorkEndTime() {
        return workEndTime;
    }

    public void setWorkEndTime(String workEndTime) {
        this.workEndTime = workEndTime;
    }

    public String getSolveDetails() {
        return solveDetails;
    }

    public void setSolveDetails(String solveDetails) {
        this.solveDetails = solveDetails;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

}
