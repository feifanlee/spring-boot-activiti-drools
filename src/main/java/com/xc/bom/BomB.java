package com.xc.bom;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lucyf
 * Date: 2018-02-05
 * Time: 17:55
 */
public class BomB {

    private String APPID;
    private Float LST_24_AMT;
    private int OD_CNT;

    public BomB() {
    }

    public BomB(String APPID, Float LST_24_AMT, int OD_CNT) {
        this.APPID = APPID;
        this.LST_24_AMT = LST_24_AMT;
        this.OD_CNT = OD_CNT;
    }

    public String getAPPID() {
        return APPID;
    }

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }

    public Float getLST_24_AMT() {
        return LST_24_AMT;
    }

    public void setLST_24_AMT(Float LST_24_AMT) {
        this.LST_24_AMT = LST_24_AMT;
    }

    public int getOD_CNT() {
        return OD_CNT;
    }

    public void setOD_CNT(int OD_CNT) {
        this.OD_CNT = OD_CNT;
    }
}
