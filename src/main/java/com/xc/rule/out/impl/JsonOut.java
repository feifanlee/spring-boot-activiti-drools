package com.xc.rule.out.impl;

import com.xc.rule.out.BaseOut;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/3/1
 * Time: 18:05
 * .
 */
public class JsonOut  implements BaseOut{

    @Override
    public Object output(Map<String, Object> line, Map<String, String> filter) {
        if (filter == null){
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Object> objectEntry : line.entrySet()) {
            if (filter.containsKey(objectEntry.getKey()) && filter.get(objectEntry.getKey()) != null){
                result.put(filter.get(objectEntry.getKey()), objectEntry.getValue());
            }
        }
        return result;
    }
}
