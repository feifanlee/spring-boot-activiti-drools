package com.xc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xc.config.FieldMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lifeifan on 2017/11/10.
 */
public class JsonUtil {
    static Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    public static Object parseJson(String json, String className) throws Exception{
        return JSON.parseObject(json,Class.forName(className));
    }

    public static Map<String,Object> parseMap(String json) throws Exception{
        return parseMap(json,null);
    }

    public static Map<String,Object> parseMap(String json, Map<String,String> keyClzMap) throws Exception{
        if (json == null){
            return null;
        }
        Map<String,Object> map = (Map<String,Object>) JsonUtil.parseJson(json,"java.util.Map");
        for(String key:map.keySet()){
            if(keyClzMap!=null && keyClzMap.get(key)!=null){
                Object obj = JsonUtil.parseJson(map.get(key).toString(),keyClzMap.get(key));
                map.put(key,obj);
            }else{
                Object obj = map.get(key);
                if(obj instanceof JSONObject) {
                    map.put(key, map.get(key).toString());
                } else if (obj instanceof  String){
                    map.put(key, String.valueOf(obj));
                } else if (obj instanceof Integer) {
                    map.put(key, Integer.valueOf(obj.toString()));
                } else{
                    map.put(key,obj);
                }
            }
         }
        return map;
    }

    public static String toJson(Object obj){
        return JSON.toJSONString(obj);
    }

    public static HashMap<String, String> parseClzMap() {
        HashMap<String, String> keyClzMap = new HashMap<String, String>();
        String[] maps = PropertyUtil.get("key.clz.mapping", PropertyKeys.DEFALUT_PROPERTIES_TYPE).split(",");
        for (String map : maps) {
            String[] kv = map.split(":");
            if (kv.length == 2) {
                keyClzMap.put(kv[0], kv[1]);
            } else if (kv.length == 1) {
                keyClzMap.put("", kv[0]);
            } else {
                LOGGER.error("Wrong value for " + "key.clz.mapping" + " value: " + map);
            }
        }
        return keyClzMap;
    }

    public static List<Object> transform(List<FieldMap> fieldMaps, String json){
        Map<String, Object> map = JSONObject.parseObject(json, Map.class);
        if (map.isEmpty()) {
            return null;
        }
        List<Object> result = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object o = filter(fieldMaps, entry.getKey(),entry.getValue());
            result.add(o);
        }
        return result;
    }

    private static Object filter(List<FieldMap> fieldMaps, String inputName,Object maps) {
        List<FieldMap> filter = new ArrayList<>();
        Map<String, Object> inputfileds = JSONObject.parseObject(maps.toString(), Map.class);
        Map<String, Object> map = new HashMap<>();
        String className = "";
        Class c = null;
        try {
            for (FieldMap fieldMap : fieldMaps) {
                if (fieldMap.getInput().equals(inputName)){
                    className = fieldMap.getBom();
                    map.put(fieldMap.getBomField().substring(0, 1).toUpperCase() + fieldMap.getBomField().substring(1),inputfileds.get(fieldMap.getInputField()));
                }

            }
            c = Class.forName(className);
            Object o = c.newInstance();
            return ReflectUtil.setDefault(o,map);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object parseJson(String json) throws Exception {
        JSONObject object = JSON.parseObject(json);
        for (Object o : object.values()) {
            if (o instanceof JSONObject) {
                return o.toString();
            } else if (o instanceof String) {
                return String.valueOf(o);
            } else if (o instanceof Integer) {
                return Integer.valueOf(o.toString());
            } else {
                return o;
            }
        }
        return null;
    }



    public static void main(String[] args) throws Exception{

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("a",1);
        map.put("b","abc");
        String json = JsonUtil.toJson(map);
        System.out.println(json);

        Map<String,Object> m = JsonUtil.parseMap(json);
        System.out.println(m);
    }
}
