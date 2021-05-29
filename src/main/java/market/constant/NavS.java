package market.constant;

public class NavS {
    private int navsid;
    private String navsname;
    private int navsfid;
    private String navsherf;
    private NavF navF;

    public NavF getNavF() {
        return navF;
    }

    public void setNavF(NavF navF) {
        this.navF = navF;
    }

    public int getNavsfid() {
        return navsfid;
    }

    public void setNavsfid(int navsfid) {
        this.navsfid = navsfid;
    }


    public int getNavsid() {
        return navsid;
    }

    public void setNavsid(int navsid) {
        this.navsid = navsid;
    }

    public String getNavsname() {
        return navsname;
    }

    public void setNavsname(String navsname) {
        this.navsname = navsname;
    }

    public int getNavfid() {
        return navsfid;
    }

    public void setNavfid(int navsfid) {
        this.navsfid = navsfid;
    }


    public String getNavsherf() {
        return navsherf;
    }

    public void setNavsherf(String navsherf) {
        this.navsherf = navsherf;
    }

    @Override
    public String toString() {
        return "NavS{" +
                "navsid=" + navsid +
                ", navsname='" + navsname + '\'' +
                ", navsfid=" + navsfid +
                ", navsherf='" + navsherf + '\'' +
                ", navF=" + navF +
                '}';
    }
}
