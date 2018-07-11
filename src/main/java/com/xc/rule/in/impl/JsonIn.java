package com.xc.rule.in.impl;

import com.xc.rule.in.BaseIn;
import com.xc.util.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lucyf
 * Date: 2018-02-09
 * Time: 18:09
 */
public class JsonIn implements BaseIn {

    @Override
    public List<Object> read(String in) {
        try {
            Object obj =  JsonUtil.parseMap(in);
            List<Object> result = new ArrayList<Object>();
            result.add(obj);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Object> readmap(String in, Map<String, String> map) {
        try {
            Map<String,Object> inputs = JsonUtil.parseMap(in);
            if (map == null){
                return inputs;
            }
            Map<String, Object> temp = new HashMap();
            for (Map.Entry<String ,Object> o : inputs.entrySet()) {
                for (Map.Entry<String ,String> entry : map.entrySet()) {
                    if (o.getKey().equals(entry.getValue())) {
                        temp.put(entry.getKey(), o.getValue());
                    }else {
                        temp.put(o.getKey(), o.getValue());
                    }
                }
            }
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Object> read2map(String in) {
        try {
            return JsonUtil.parseMap(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
