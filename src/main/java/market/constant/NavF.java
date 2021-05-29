package market.constant;

public class NavF {
    private int navfid;
    private String navfname;
    private NavS navss;
    private int navfmark;

    public int getNavfmark() {
        return navfmark;
    }

    public void setNavfmark(int navfmark) {
        this.navfmark = navfmark;
    }

    public int getNavfid() {
        return navfid;
    }

    public void setNavfid(int navfid) {
        this.navfid = navfid;
    }

    public String getNavfname() {
        return navfname;
    }

    public void setNavfname(String navfname) {
        this.navfname = navfname;
    }

    public NavS getNavss() {
        return navss;
    }

    public void setNavss(NavS navss) {
        this.navss = navss;
    }

    @Override
    public String toString() {
        return "NavF{" +
                "navfid=" + navfid +
                ", navfname='" + navfname + '\'' +
                ", navss=" + navss +
                ", navfmark=" + navfmark +
                '}';
    }
}
