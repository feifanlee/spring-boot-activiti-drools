package com.xc.rule.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lifeifan on 2017/12/25.
 */
public class AllAdapter extends BaseRuleAdapter {
    @Override
    public Object adapt(List<Object> in, Map<String, Object> global) {
        Map<String,Object> all = new HashMap<String, Object>();
//        todo 待修改
//        all.put("s","test");
        return all;
    }
}
