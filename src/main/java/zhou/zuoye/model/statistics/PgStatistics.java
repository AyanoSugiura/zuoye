package zhou.zuoye.model.statistics;

public class PgStatistics {
    private Integer isPg;
    private Integer noPg;
    private Integer noSub;

    public PgStatistics(Integer isPg, Integer noPg, Integer noSub) {
        this.isPg = isPg;
        this.noPg = noPg;
        this.noSub = noSub;
    }

    public PgStatistics(){}

    public Integer getIsPg() {
        return isPg;
    }

    public void setIsPg(Integer isPg) {
        this.isPg = isPg;
    }

    public Integer getNoPg() {
        return noPg;
    }

    public void setNoPg(Integer noPg) {
        this.noPg = noPg;
    }

    public Integer getNoSub() {
        return noSub;
    }

    public void setNoSub(Integer noSub) {
        this.noSub = noSub;
    }
}
