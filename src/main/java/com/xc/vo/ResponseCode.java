package com.xc.vo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/3/13
 * Time: 17:02
 */
public enum ResponseCode {

    COMMON_SUCCESS(1, "成功"),
    COMMON_FAILED(0, "失败"),

    SERVER_EXCEPTION(500,"服务器异常"),
    ;



    public static ResponseCode fromInt(int value) {
        for (ResponseCode type : ResponseCode.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return SERVER_EXCEPTION;
    }
    private ResponseCode(int value, String info) {
        this.value = value;
        this.info = info;
    }

    private int value;
    private String info;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
