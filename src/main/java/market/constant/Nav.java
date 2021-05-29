package market.constant;

public class Nav {
    private int navid;
    private String navsname;
    private Integer navgrade;
    private String navfname;
    private int mark;
    private NavF navF;
    private NavS navS;

    public String getNavsname() {
        return navsname;
    }

    public void setNavsname(String navsname) {
        this.navsname = navsname;
    }

    public String getNavfname() {
        return navfname;
    }

    public void setNavfname(String navfname) {
        this.navfname = navfname;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getNavid() {
        return navid;
    }

    public void setNavid(int navid) {
        this.navid = navid;
    }

    public Integer getNavgrade() {
        return navgrade;
    }

    public void setNavgrade(Integer navgrade) {
        this.navgrade = navgrade;
    }

    public NavF getNavF() {
        return navF;
    }

    public void setNavF(NavF navF) {
        this.navF = navF;
    }

    public NavS getNavS() {
        return navS;
    }

    public void setNavS(NavS navS) {
        this.navS = navS;
    }

    @Override
    public String toString() {
        return "Nav{" +
                "navid=" + navid +
                ", navsname='" + navsname + '\'' +
                ", navgrade=" + navgrade +
                ", navfname='" + navfname + '\'' +
                ", mark=" + mark +
                ", navF=" + navF +
                ", navS=" + navS +
                '}';
    }
}
