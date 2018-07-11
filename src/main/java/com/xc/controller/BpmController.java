package com.xc.controller;

import com.xc.config.DroolsMap;
import com.xc.config.EventB;
import com.xc.redis.BpmDao;
import com.xc.redis.EventDao;
import com.xc.rule.in.BaseIn;
import com.xc.rule.in.InFactory;
import com.xc.util.PropertyUtil;
import com.xc.vo.Response;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lifeifan on 2018/2/6.
 */
@RequestMapping("bpm")
@RestController
public class BpmController {
//    @Autowired
//    EventDao eventDao;

    @Autowired
    BpmDao bpmDao;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    /**
     * 展示所有规则集
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("drools")
    public Response<List<DroolsMap>> showAllDrolls() {
        return Response.succ(DroolsMap.getAllDroolsMap());
    }

    @RequestMapping("editMatching")
    public Response editMatching(String processName, String[] inputs, String[] inputMatching, String[] output, String[] outputMatching,
                                 String[] globals, String[] globalsValues){

        Map<String,String> inputMap = new HashMap<>();
        Map<String,String> outMap = new HashMap<>();
        Map<String,Object> globalMap = new HashMap<>();
        if (inputs != null && inputMatching != null){
            if (inputs.length != inputMatching.length) {
                return Response.err("请检查输入信息及匹配信息是否匹配");
            }
            for (int i = 0; i < inputs.length; i++) {
                inputMap.put(inputs[i], inputMatching[i]);
            }
        }
        if (output != null && outputMatching != null){
            if (output.length != outputMatching.length) {
                return Response.err("请检查输出信息及匹配信息是否匹配");
            }
            for (int i = 0; i < output.length; i++) {
                outMap.put(output[i], outputMatching[i]);
            }
        }
        if (globals != null && globalsValues != null){
            if (globals.length != globalsValues.length) {
                return Response.err("请检查Global及匹配信息是否匹配");
            }
            for (int i = 0; i < globals.length; i++) {
                globalMap.put(globals[i], globalsValues[i]);
            }
        }
        bpmDao.saveMatching(processName, inputMap, outMap, globalMap);
        return Response.succ();
    }


//    private Map<String,Object> initProccess(){
//        List<DroolsMap> droolsMaps = DroolsMap.getAllDroolsMap();
//
//    }

    @RequestMapping("startbpm")
    public Response startBpm(String processId, String processName, String in){
        if (in != null) {
            in = in.replaceAll("%7B", "{").replaceAll("%7D", "}");
        }
//        System.out.println(" 开始时间" + new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date()));
        BaseIn baseIn = InFactory.get();
        Map<String,String> inputMatching = bpmDao.getInputMatching(processName);
        Map<String, Object> rows = baseIn.readmap(in, inputMatching);
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processId, processName, rows);
        System.out.println(pi.getId());
        Object out = bpmDao.get("result");
//        System.out.println(" 结束时间" + new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date()));

        return Response.succ(out);
    }


}
