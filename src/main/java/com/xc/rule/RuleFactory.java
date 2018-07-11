package com.xc.rule;

import com.xc.util.PropertyKeys;
import com.xc.util.PropertyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifeifan on 2018/3/12.
 */
@Component
public class RuleFactory {

    private static Map<String, BaseRule> BASERULES = new HashMap<>();

//    @Cacheable("rules")
    public BaseRule get(String path) throws Exception{
        if (BASERULES.size()==0){
            BaseRule rule = new TextRule();
            rule.init(new File(path));
            BASERULES.put(path, rule);
            return rule;
        }else {
            BaseRule rule =BASERULES.get(path);
            if (rule == null){
                rule = new TextRule();
                rule.init(new File(path));
                BASERULES.put(path, rule);
                return rule;
            }else {
                return rule;
            }
        }
    }

//    @CachePut("rules")
    public BaseRule refresh(String path) throws Exception{
        return this.get(path);
    }
}
