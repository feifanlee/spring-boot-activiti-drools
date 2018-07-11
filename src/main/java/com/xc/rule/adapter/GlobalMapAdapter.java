package com.xc.rule.adapter;

import java.util.List;
import java.util.Map;

/**
 * Created by lifeifan on 2017/12/21.
 */
public class GlobalMapAdapter extends BaseRuleAdapter {

    @Override
    public Object adapt(List<Object> in, Map<String, Object> global) {
        //todo kiesesion设置global需要重构
//        String glbs = PropertyUtil.get(PropertyKeys.ADAPTER_GLOBAL_RESULTS_MAP);
//        String glbs =
//        String[] keys = glbs.split(",");
//        Map<String,Object> result = new HashMap<String, Object>();
//        for(String key:keys){
//            result.put(key,global.get(key));
//        }
//
//        return result;
        return null;
    }
}
