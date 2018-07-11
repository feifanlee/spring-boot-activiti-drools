package com.xc.config;


import java.util.List;
import java.util.Map;

/**
 * Created by lifeifan on 2018/2/1.
 */
public class EventB {

    private String eventName;
    private List<DroolsMap> drools;
    private Map fieldMaps;
    private Map outputMaps;
    private List<ConstMap> constMaps;

    public EventB() {
    }

    public EventB(String eventName, List<DroolsMap> drools, Map fieldMaps, Map outputMaps, List<ConstMap> constMaps) {
        this.eventName = eventName;
        this.drools = drools;
        this.fieldMaps = fieldMaps;
        this.outputMaps = outputMaps;
        this.constMaps = constMaps;
    }

    public Map getFieldMaps() {
        return fieldMaps;
    }

    public void setFieldMaps(Map fieldMaps) {
        this.fieldMaps = fieldMaps;
    }

    public EventB(String name){
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

    public Map getOutputMaps() {
        return outputMaps;
    }

    public void setOutputMaps(Map outputMaps) {
        this.outputMaps = outputMaps;
    }
}
