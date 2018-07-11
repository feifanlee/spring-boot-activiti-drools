package com.xc.util;

import com.xc.config.FieldMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by lifeifan on 2017/11/14.
 */
public class ReflectUtil {
    static Logger LOGGER = LoggerFactory.getLogger(ReflectUtil.class);

    public static Object map2Class(Map<String, Object> map, String className) throws Exception {
        Class clz = null;
        Object bom = null;
        //internal bom class
        clz = Class.forName((String) className);
        bom = clz.newInstance();


        Field[] flds = clz.getDeclaredFields();
        for (Field fld : flds) {
            fld.setAccessible(true);
            try {
//                fld.set(bom, map.get(fld.getName().toLowerCase()));
                fld.set(bom, MapUtil.forceGet(map,fld.getName()));
            } catch (Exception e) {
                LOGGER.info("set field error:{}", fld.getName());
                LOGGER.debug(e.getMessage());
            }
        }
        return bom;
    }

    public static Map<String,Object> class2Map(Object obj) throws Exception{
        Map<String,Object> result = new HashMap<String, Object>();
        for(Field f: obj.getClass().getDeclaredFields()){
            String key = f.getName();
            Object val = f.get(obj);
            result.put(key,val);
        }
        return result;
    }


    public static Object[] class2KVList(Object obj) throws Exception{
        List<String> keys = new ArrayList<String>();
        List<Object> vals = new ArrayList<Object>();
        for(Field f: obj.getClass().getDeclaredFields()){
            keys.add(f.getName());
            vals.add(f.get(obj));
        }
        Object[] result = new Object[2];
        result[0] = keys;
        result[1] = vals;
        return result;
    }


    /**
     * 获取Object对象，所有成员变量属性值
     * @param obj
     */
    public static void getObjAttr(Object obj) {
        // 获取对象obj的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 对于每个属性，获取属性名
            String varName = field.getName();
            try {
                boolean access = field.isAccessible();
                if (!access) {
                    field.setAccessible(true);
                }
                //从obj中获取field变量
                Object o = field.get(obj);
                LOGGER.info("变量： " + varName + " = " + o);
                if (!access) {
                    field.setAccessible(false);
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getLocalizedMessage());
            }
        }
    }

    public static List<FieldMap> getFieldMapByClassName(String className){
        List<FieldMap> list = new ArrayList<>();
        try {
            Class c = Class.forName(className);
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                boolean access = field.isAccessible();
                if (!access) {
                    field.setAccessible(true);
                }
                FieldMap fieldMap = new FieldMap();
                fieldMap.setBom(c.getName());
                fieldMap.setBomField(field.getName());
                list.add(fieldMap);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object setDefault(Object model, Map<String,Object> map){
        // 获取实体类的所有属性，返回Field数组
        Field[] field = model.getClass().getDeclaredFields();
        try {
            // 遍历所有属性
            for (int j = 0; j < field.length; j++) {
                // 获取属性的名字
                String name = field[j].getName();
                // 将属性的首字符大写，方便构造get，set方法
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                // 获取属性的类型
                String type = field[j].getGenericType().toString();
                // 如果type是类类型，则前面包含"class "，后面跟类名
                if ("class java.lang.String".equals(type)) {
                    Method m = model.getClass().getMethod("get" + name);
                    // 调用getter方法获取属性值
                    String value = (String) m.invoke(model);
                    if (value == null) {
                        String val = (String) map.get(name);
                        m = model.getClass().getMethod("set"+name,String.class);
                        m.invoke(model, val==null?"":val);
                    }
                }
                if ("class java.lang.Integer".equals(type)) {
                    Method m = model.getClass().getMethod("get" + name);
                    Integer value = (Integer) m.invoke(model);
                    if (value == null) {
                        Integer val =  Integer.valueOf(map.get(name).toString());
                        m = model.getClass().getMethod("set"+name,Integer.class);
                        m.invoke(model, val==null?-99:val);
                    }
                }
                if ("class java.lang.Boolean".equals(type)) {
                    Method m = model.getClass().getMethod("get" + name);
                    Boolean value = (Boolean) m.invoke(model);
                    if (value == null) {
                        m = model.getClass().getMethod("set"+ name,Boolean.class);
                        m.invoke(model, false);
                    }
                }
                if ("class java.util.Date".equals(type)) {
                    Method m = model.getClass().getMethod("get" + name);
                    Date value = (Date) m.invoke(model);
                    if (value == null) {
                        m = model.getClass().getMethod("set"+name,Date.class);
                        m.invoke(model, new Date());
                    }
                }
                // 如果有需要,可以仿照上面继续进行扩充,再增加对其它类型的判断
            }
            return model;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
