package com.xc.rule.adapter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lifeifan on 2017/11/6.
 */
public abstract class BaseRuleAdapter implements Serializable {
    public abstract Object adapt(List<Object> in, Map<String,Object> global);
}
