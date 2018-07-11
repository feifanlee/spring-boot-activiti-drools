package com.xc.config;


import java.util.List;

/**
 * Created by lifeifan on 2018/2/1.
 */
public class Event {

    private String eventName;
    private List<DroolsMap> drools;
    private List<FieldMap> fieldMaps;
    private List<ConstMap> constMaps;

    public Event() {
    }
 
    public Event(String eventName, List<DroolsMap> drools, List<FieldMap> fieldMaps, List<ConstMap> constMaps) {
        this.eventName = eventName;
        this.drools = drools;
        this.fieldMaps = fieldMaps;
        this.constMaps = constMaps;
    }

    public Event(String name){
        eventName = name;
    }

    public List<DroolsMap> getDrools() {
        return drools;
    }

    public void setDrools(List<DroolsMap> drools) {
        this.drools = drools;
    }


    public List<ConstMap> getConstMaps() {
        return constMaps;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }



    public void setConstMaps(List<ConstMap> constMaps) {
        this.constMaps = constMaps;
    }

    public List<FieldMap> getFieldMaps() {
        return fieldMaps;
    }

    public void setFieldMaps(List<FieldMap> fieldMaps) {
        this.fieldMaps = fieldMaps;
    }
}
