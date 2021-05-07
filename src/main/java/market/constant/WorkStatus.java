package market.constant;

public class WorkStatus {
    private String statusId;
    private String workStatus;

    @Override
    public String toString() {
        return "WorkStatus{" +
                "statusId='" + statusId + '\'' +
                ", workStatus='" + workStatus + '\'' +
                '}';
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }
}
