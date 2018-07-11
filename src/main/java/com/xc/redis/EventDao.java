package com.xc.redis;

import com.xc.config.ConstMap;
import com.xc.config.Event;
import com.xc.config.EventB;

import java.util.List;

/**
 * Created by lifeifan on 2018/2/2.
 */
public interface EventDao {
    void saveEvent(Event event);
    Event getEvent(String eventName);

    void saveEventB(EventB event);
    EventB getEventB(String eventName);

    List<ConstMap> getConsts(String eventName);
    Boolean removeEvent(String eventName);

    void save(String key, Object value);
    Object get(String key);
}
