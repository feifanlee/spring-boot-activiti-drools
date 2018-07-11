package com.xc.bom;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lucyf
 * Date: 2018-02-05
 * Time: 17:51
 */
public class BomA {

    private String APPID;
    private java.util.Date BIRTHDAY;
    private java.lang.String ID_NUM;
    private java.lang.String INDUSTRY;
    private java.lang.String JOB_STATUS;
    private java.lang.String APP_PRODUCT;
    private java.lang.String IF_DECLINED_6MTH;
    private java.lang.String IF_HAS;
    private java.lang.String IF_APPLY;
    private java.lang.String IF_HAS_EXCLUSIVE;

    public BomA() {
    }

    public BomA(String APPID, Date BIRTHDAY, String ID_NUM, String INDUSTRY, String JOB_STATUS, String APP_PRODUCT, String IF_DECLINED_6MTH, String IF_HAS, String IF_APPLY, String IF_HAS_EXCLUSIVE) {
        this.APPID = APPID;
        this.BIRTHDAY = BIRTHDAY;
        this.ID_NUM = ID_NUM;
        this.INDUSTRY = INDUSTRY;
        this.JOB_STATUS = JOB_STATUS;
        this.APP_PRODUCT = APP_PRODUCT;
        this.IF_DECLINED_6MTH = IF_DECLINED_6MTH;
        this.IF_HAS = IF_HAS;
        this.IF_APPLY = IF_APPLY;
        this.IF_HAS_EXCLUSIVE = IF_HAS_EXCLUSIVE;
    }

    public String getAPPID() {
        return APPID;
    }

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }

    public Date getBIRTHDAY() {
        return BIRTHDAY;
    }

    public void setBIRTHDAY(Date BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }

    public String getID_NUM() {
        return ID_NUM;
    }

    public void setID_NUM(String ID_NUM) {
        this.ID_NUM = ID_NUM;
    }

    public String getINDUSTRY() {
        return INDUSTRY;
    }

    public void setINDUSTRY(String INDUSTRY) {
        this.INDUSTRY = INDUSTRY;
    }

    public String getJOB_STATUS() {
        return JOB_STATUS;
    }

    public void setJOB_STATUS(String JOB_STATUS) {
        this.JOB_STATUS = JOB_STATUS;
    }

    public String getAPP_PRODUCT() {
        return APP_PRODUCT;
    }

    public void setAPP_PRODUCT(String APP_PRODUCT) {
        this.APP_PRODUCT = APP_PRODUCT;
    }

    public String getIF_DECLINED_6MTH() {
        return IF_DECLINED_6MTH;
    }

    public void setIF_DECLINED_6MTH(String IF_DECLINED_6MTH) {
        this.IF_DECLINED_6MTH = IF_DECLINED_6MTH;
    }

    public String getIF_HAS() {
        return IF_HAS;
    }

    public void setIF_HAS(String IF_HAS) {
        this.IF_HAS = IF_HAS;
    }

    public String getIF_APPLY() {
        return IF_APPLY;
    }

    public void setIF_APPLY(String IF_APPLY) {
        this.IF_APPLY = IF_APPLY;
    }

    public String getIF_HAS_EXCLUSIVE() {
        return IF_HAS_EXCLUSIVE;
    }

    public void setIF_HAS_EXCLUSIVE(String IF_HAS_EXCLUSIVE) {
        this.IF_HAS_EXCLUSIVE = IF_HAS_EXCLUSIVE;
    }
}
