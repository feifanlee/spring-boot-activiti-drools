package com.xc.redis.impl;

import com.xc.config.ConstMap;
import com.xc.config.DroolsMap;
import com.xc.config.FieldMap;
import com.xc.redis.BpmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/3/14
 * Time: 17:04
 */
@Repository("bpmDao")
public class BpmDaoImpl implements BpmDao {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveMatching(String processName, Map<String, String> inputMatching, Map<String, String> outputMatching,
                             Map<String, Object> globals) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set("process_inputMatching_" + processName, inputMatching);
        operations.set("process_outputMatching_" + processName, outputMatching);
        operations.set("process_global_" + processName, globals);
    }

    @Override
    public Map<String, String> getInputMatching(String processName) {
        return (Map<String, String>) redisTemplate.opsForValue().get("process_inputMatching_"+processName);
    }

    @Override
    public Map<String, String> getOutputMatching(String processName) {
        return (Map<String, String>) redisTemplate.opsForValue().get("process_outputMatching_"+processName);
    }

    @Override
    public Map<String, Object> getGlobals(String processName) {
        return (Map<String, Object>) redisTemplate.opsForValue().get("process_global_"+processName);
    }

    @Override
    public void save(String key, Object value) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }

    @Override
    public Object get(String key){
        Object o = redisTemplate.opsForValue().get(key);
        return o;
    }
}
