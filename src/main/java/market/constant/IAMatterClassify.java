package market.constant;

public class IAMatterClassify {

    private String MatterId;
    private String MatterName;

    @Override
    public String toString() {
        return "IAMatterClassify{" +
                "MatterId='" + MatterId + '\'' +
                ", MatterName='" + MatterName + '\'' +
                '}';
    }

    public String getMatterId() {
        return MatterId;
    }

    public void setMatterId(String matterId) {
        MatterId = matterId;
    }

    public String getMatterName() {
        return MatterName;
    }

    public void setMatterName(String matterName) {
        MatterName = matterName;
    }
}
