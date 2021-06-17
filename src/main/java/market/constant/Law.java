package market.constant;

public class Law {

    private int lawId;
    private String lawName;
    private String lawKind;
    private String lawSubKind;
    private String lawContent;
    private PageInfo pageInfo;
    private String search;

    @Override
    public String toString() {
        return "Law{" +
                "lawId=" + lawId +
                ", lawName='" + lawName + '\'' +
                ", lawKind='" + lawKind + '\'' +
                ", lawSubKind='" + lawSubKind + '\'' +
                ", lawContent='" + lawContent + '\'' +
                ", pageInfo=" + pageInfo +
                ", search='" + search + '\'' +
                '}';
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

    public String getLawSubKind() {
        return lawSubKind;
    }

    public void setLawSubKind(String lawSubKind) {
        this.lawSubKind = lawSubKind;
    }

    public int getLawId() {
        return lawId;
    }

    public void setLawId(int lawId) {
        this.lawId = lawId;
    }

    public String getLawName() {
        return lawName;
    }

    public void setLawName(String lawName) {
        this.lawName = lawName;
    }

    public String getLawKind() {
        return lawKind;
    }

    public void setLawKind(String lawKind) {
        this.lawKind = lawKind;
    }

    public String getLawContent() {
        return lawContent;
    }

    public void setLawContent(String lawContent) {
        this.lawContent = lawContent;
    }
}
