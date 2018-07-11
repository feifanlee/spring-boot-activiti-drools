package com.xc.rule.adapter;

import java.util.List;
import java.util.Map;

/**
 * Created by lifeifan on 2017/11/6.
 */
public class LastInAdapter extends BaseRuleAdapter {

    @Override
    public Object adapt(List<Object> in, Map<String,Object> global) {
        return in.get(in.size()-1);
    }
}
