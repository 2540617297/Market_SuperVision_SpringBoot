package market.constant;

public class FileChoose {
    private String id;
    private String obj;
    private String userNameCN;
    private String dateTime;
    private String fileUrl;

    @Override
    public String toString() {
        return "FileChoose{" +
                "id='" + id + '\'' +
                ", obj='" + obj + '\'' +
                ", userNameCN='" + userNameCN + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getUserNameCN() {
        return userNameCN;
    }

    public void setUserNameCN(String userNameCN) {
        this.userNameCN = userNameCN;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
