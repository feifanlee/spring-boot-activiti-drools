package com.xc.rule.out;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/3/1
 * Time: 18:01
 */
public interface BaseOut {

    public Object output(Map<String, Object> line, Map<String, String> filter);
}
