package com.xc.redis;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/3/14
 * Time: 17:03
 */
public interface BpmDao {

    void saveMatching(String processName, Map<String, String> inputMatching, Map<String, String> outputMatching,
                      Map<String, Object> globals);

    Map<String,String> getInputMatching(String processName);

    Map<String,String> getOutputMatching(String processName);

    Map<String,Object> getGlobals(String processName);

    void save(String key, Object value);
    Object get(String key);
}
