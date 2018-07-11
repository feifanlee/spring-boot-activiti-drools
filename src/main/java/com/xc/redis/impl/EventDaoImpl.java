package com.xc.redis.impl;

import com.xc.config.*;
import com.xc.redis.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lifeifan on 2018/2/2.
 */
@Repository("eventDao")
public class EventDaoImpl implements EventDao {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveEvent(Event event) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        List<DroolsMap> drools = event.getDrools();
        List<FieldMap> fieldMap = event.getFieldMaps();
        List<ConstMap> constMaps = event.getConstMaps();
        operations.set("event_drools_" + event.getEventName(), drools);
        operations.set("event_field_" + event.getEventName(), fieldMap);
        operations.set("event_const_" + event.getEventName(), constMaps);
    }

    @Override
    public Event getEvent(String eventName) {
        List<FieldMap> fieldMaps = null;
        List<ConstMap> constMaps = null;
        List<DroolsMap> drools = null;
        Event event = new Event();
        try {
            fieldMaps = (List<FieldMap>) redisTemplate.opsForValue().get("event_field_"+eventName);
            constMaps = (List<ConstMap>) redisTemplate.opsForValue().get("event_const_"+eventName);
            drools = (List<DroolsMap>) redisTemplate.opsForValue().get("event_drools_"+eventName);
            if (fieldMaps == null && constMaps == null && drools == null){
                return null;
            }
            event.setConstMaps(constMaps);
            event.setFieldMaps(fieldMaps);
            event.setEventName(eventName);
            event.setDrools(drools);
            return event;
        }catch (ClassCastException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public void saveEventB(EventB event) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        List<DroolsMap> drools = event.getDrools();
        Map fieldMap = event.getFieldMaps();
        Map outputMaps = event.getOutputMaps();
        List<ConstMap> constMaps = event.getConstMaps();
        operations.set("event_outputfield_" + event.getEventName(), outputMaps);
        operations.set("event_drools_" + event.getEventName(), drools);
        operations.set("event_field_" + event.getEventName(), fieldMap);
        operations.set("event_const_" + event.getEventName(), constMaps);
    }

    @Override
    public EventB getEventB(String eventName) {
        Map fieldMaps = null;
        Map outputMaps = null;
        List<ConstMap> constMaps = null;
        List<DroolsMap> drools = null;
        EventB event = new EventB();
        try {
            outputMaps = (Map) redisTemplate.opsForValue().get("event_outputfield_"+eventName);
            fieldMaps = (Map) redisTemplate.opsForValue().get("event_field_"+eventName);
            constMaps = (List<ConstMap>) redisTemplate.opsForValue().get("event_const_"+eventName);
            drools = (List<DroolsMap>) redisTemplate.opsForValue().get("event_drools_"+eventName);
            if (fieldMaps == null && constMaps == null && drools == null){
                return null;
            }
            event.setConstMaps(constMaps);
            event.setFieldMaps(fieldMaps);
            event.setOutputMaps(outputMaps);
            event.setEventName(eventName);
            event.setDrools(drools);
            return event;
        }catch (ClassCastException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public Boolean removeEvent(String eventName){
        redisTemplate.delete("event_outputfield_"+eventName);
        redisTemplate.delete("event_field_"+eventName);
        redisTemplate.delete("event_const_"+eventName);
        redisTemplate.delete("event_drools_"+eventName);
        return getEvent(eventName)==null;
    }

    @Override
    public void save(String key, Object value) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }

    @Override
    public Object get(String key){
        Object o = redisTemplate.opsForValue().get(key);
        return o;
    }

    @Override
    public List<ConstMap> getConsts(String eventName){
        List<ConstMap> constMaps = null;
        try {
            Object a = redisTemplate.opsForValue().get("event_const_"+eventName);
            constMaps = (List<ConstMap>) a;
        }catch (Exception e){
            e.printStackTrace();
        }
        return constMaps;
    }

}
