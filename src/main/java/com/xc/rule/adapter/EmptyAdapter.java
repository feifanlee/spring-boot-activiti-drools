package com.xc.rule.adapter;

import java.util.List;
import java.util.Map;

/**
 * Created by lifeifan on 2017/11/16.
 */
public class EmptyAdapter extends BaseRuleAdapter {
    @Override
    public Object adapt(List<Object> in, Map<String, Object> global) {
        return null;
    }
}
