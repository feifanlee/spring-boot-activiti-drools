package com.xc.util;

import java.util.Map;

/**
 * Created by lifeifan on 2017/12/22.
 */
public class MapUtil {
    public static <T> T forceGet(Map<String,T> map, String key){
        T result;
        result = map.get(key);
        if(result!=null){
            return result;
        }
        result = map.get(key.toLowerCase());
        if(result!=null){
            return result;
        }
        result = map.get(key.toUpperCase());
        return result;
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass)
            throws Exception {
        if (map == null) {
            return null;
        }
        Object obj = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }
}
