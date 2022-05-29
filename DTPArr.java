package KPP.lab5.Sec;

public class DTPArr {
    private String regName;
    private String district;
    private String COORD_L;
    private String COORD_W;

    public DTPArr(String regName, String district, String COORD_L, String COORD_W) {
        this.regName = regName;
        this.district = district;
        this.COORD_L = COORD_L;
        this.COORD_W = COORD_W;
    }

    public DTPArr(){}

    public String getRegName() {
        return regName;
    }

    public String getDistrict() {
        return district;
    }

    public String getCOORD_L() {
        return COORD_L;
    }

    public String getCOORD_W() {
        return COORD_W;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCOORD_L(String COORD_L) {
        this.COORD_L = COORD_L;
    }

    public void setCOORD_W(String COORD_W) {
        this.COORD_W = COORD_W;
    }

    @Override
    public String toString() {
        return  "\nDistrict: " + district + '\n' +
                "COORD_L: "  + COORD_L + '\n' +
                "COORD_: " + COORD_W;
    }
}
