package com.xc.config;

/**
 * Created by lifeifan on 2018/2/2.
 */
public class ConstMap {
    String constName;
    Integer value;

    public ConstMap() {
    }

    public ConstMap(String _constName, Integer _value){
        constName = _constName;
        value = _value;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName = constName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    //    String[] toArray(){
//        return new String[]{constName,value.toString()};
//    }
}
