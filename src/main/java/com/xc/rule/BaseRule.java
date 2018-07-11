package com.xc.rule;

import com.xc.config.ConstMap;
import com.xc.config.DroolsMap;
import com.xc.rule.adapter.BaseRuleAdapter;
import com.xc.rule.adapter.RuleAdapterBuilder;
import com.xc.util.PropertyKeys;
import com.xc.util.PropertyUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * Created by lifeifan on 2017/9/12.
 */
public abstract class BaseRule implements Serializable{
    static Logger LOGGER = LoggerFactory.getLogger(BaseRule.class);

    public final static Integer global_default_value = Integer.valueOf(PropertyUtil.get(PropertyKeys.GLOBAL_DEFAULT_VALUE, PropertyKeys.DEFALUT_PROPERTIES_TYPE).trim());

    KieSession kSession = null;
    BaseRuleAdapter adapter = null;
    Map<String, Object> global = null;

    public abstract void init(File in) throws Exception;

//    public Object calc(List<Object> in, String eventName, DroolsMap drool) {
//        String[] globals = drool.getGlbs().split(",");
//        if (global == null) {
//            global = getGlobal(eventName);
//            for (String key : global.keySet()) {
//                LOGGER.info("Setting global: {}, values: {}",key,global.get(key));
//                if (ArrayUtils.contains(globals,key)){
//                    kSession.setGlobal(key, global.get(key));
//                }
//
//            }
//        } else {
////            Map<String,Object> refresh = refreshGlobal();
//            Map<String,Object> refresh = getGlobal(eventName);
//            global.putAll(refresh);
//            for(String key:refresh.keySet()) {
//                LOGGER.info("Refreshing global:{} , values: {}",key,refresh.get(key));
//                if (ArrayUtils.contains(globals,key)){
//                    kSession.setGlobal(key, global.get(key));
//                }
//            }
//        }
//
//        for (Object bom : in) {
//            kSession.insert(bom);
//        }
//        int cnt = kSession.fireAllRules();
//        LOGGER.info(cnt + " rules triggered.");
//
//        setAdapter();
//        return adapter.adapt(in, global);
//    }

    public Object calc(Object in, Map<String, Object> globals, DroolsMap drool) {
        String[] droolGlobals = drool.getGlbs().split(",");

        global = getGlobal(globals,droolGlobals);
        for (Map.Entry<String, Object> entry : global.entrySet()) {
            kSession.setGlobal(entry.getKey(), entry.getValue());
        }

        kSession.insert(in);
        ArrayList arrayList = new ArrayList();
        arrayList.add(in);
        int cnt = kSession.fireAllRules();
        LOGGER.info(cnt + " rules triggered.");

        setAdapter();
        return adapter.adapt(arrayList, global);
    }

    public void setAdapter(BaseRuleAdapter adpter){
        this.adapter = adpter;
    }
    public void setAdapter(){
        if(this.adapter==null){
            this.adapter = RuleAdapterBuilder.getAdapter();
        }
    }

    public void close() {
        this.kSession.dispose();
    }

    public Map<String, Object> getGlobal(Map<String, Object> result, String[] droolGlobals ) {
        Map<String,Object> glb = new HashMap<>();
        try {
            for (String global : droolGlobals) {
                //如果global不赋值，统一赋值
                glb.put(global, result==null?global_default_value:result.get(global)==null?global_default_value:result.get(global)                                                                                                                                                                                                                                                                  );
            }
        } catch (Exception e) {
            LOGGER.error("ERROR: get global failed!");
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return glb;
    }
}
