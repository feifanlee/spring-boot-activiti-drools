package com.xc.controller;

import com.xc.config.*;
import com.xc.redis.EventDao;
import com.xc.rule.TextRule;
import com.xc.rule.in.BaseIn;
import com.xc.rule.in.InFactory;
import com.xc.rule.out.BaseOut;
import com.xc.rule.out.OutFactory;
import com.xc.util.PropertyUtil;
import com.xc.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.*;

//@Controller
public class EventController {

//    @Autowired
    EventDao eventDao;

//    @Autowired
    TextRule textRule;

//    @ResponseBody
//    @RequestMapping("event/{ename}/old")
//    public String oldaddEvent(@PathVariable(value="ename") String ename, @RequestParam(value="dname") String[] dname){
//        List<ConstMap> constMaps = new ArrayList<>();
//        List<FieldMap> fieldMaps = new ArrayList<>();
//        List<DroolsMap> drools = new ArrayList<>();
//        for (String name : dname) {
//            String boms = PropertyUtil.get("drools." + name + ".boms");
//            for (String bom : boms.split(",")) {
//                fieldMaps.addAll(ReflectUtil.getFieldMapByClassName(bom));
//            }
//            String consts = PropertyUtil.get("drools." + name + ".glbs");
//            for (String global : consts.split(",")) {
//                ConstMap constMap = new ConstMap(global,-99);
//                constMaps.add(constMap);
//            }
//            DroolsMap droolsMap = PropertyUtil.getDroolsInfo(name);
//            drools.add(droolsMap);
//        }
//        Event event = new Event();
//        event.setEventName(ename);
//    //        event.setFieldMaps(fieldMaps);
//        event.setConstMaps(constMaps);
//        event.setDrools(drools);
//        try {
//            eventDao.saveEvent(event);
//            return "true";
//        } catch (Exception e){
//            e.printStackTrace();
//            return "false";
//        }
//    }

    @ResponseBody
    @RequestMapping("event/{ename}")
    public Response addEvent(@PathVariable(value = "ename") String ename, @RequestParam(value = "dname") String[] dname) {
        List<ConstMap> constMaps = new ArrayList<>();
        Map fieldMaps = new HashMap();
        List<DroolsMap> drools = new ArrayList<>();
        for (String name : dname) {
            DroolsMap droolsMap = new DroolsMap(name);
            String boms = droolsMap.getBomsStr()+","+droolsMap.getBomsInt();
            for (String bom : boms.split(",")) {
                fieldMaps.put(bom, null);
            }
            String consts = droolsMap.getGlbs();
            for (String global : consts.split(",")) {
                ConstMap constMap = new ConstMap(global, -99);
                constMaps.add(constMap);
            }
            drools.add(droolsMap);
        }
        EventB event = new EventB(ename,drools,fieldMaps,null,constMaps);
        try {
            eventDao.saveEventB(event);
            return Response.succ();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.err();
        }
    }

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

    /**
     * 指定event中的bom里的字段映射input
     * http://localhost:8087/event/input?ename=event1&bom=boma,bomb&bomField=a,b&input=ccca,cccb&inputField=d,h 测试连接
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("event/{ename}/inputName")
    public Response<Map<String, Object>> fiedMappingInput(@PathVariable("ename") String ename, String[] bom, String[] bomField, String[] input, String[] inputField) {
        List<FieldMap> fieldMaps = new ArrayList<>();
        for (int i = 0; i < bom.length; i++) {
            FieldMap fieldMap = new FieldMap(bom[i], bomField[i], input[i], inputField[i]);
            fieldMaps.add(fieldMap);
        }
        //获取到event对象
        Event event = eventDao.getEvent(ename);
        if (event == null) {
            return Response.err("event不存在");
        }
        event.setFieldMaps(fieldMaps);
        eventDao.saveEvent(event);

        Event e1 = eventDao.getEvent(ename);
        for (FieldMap fieldMap : e1.getFieldMaps()) {
            System.out.println(fieldMap.getBom() + "," + fieldMap.getBomField() + "," + fieldMap.getInput() + "," + fieldMap.getInputField());
        }
        return Response.succ();
    }

    /**
     * 删除bom后的输入字段映射
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("event/{ename}/inputNames")
    public Response<Map<String, Object>> fiedMappingInputs(@PathVariable("ename") String ename, String[] input, String[] inputField, String[] globals, Integer[] globalValues) {
        Map fieldMap = new HashMap<String, Object>();
        for (int i = 0; i < input.length; i++) {
            fieldMap.put(input[i], inputField[i]);
        }

        //获取到event对象
        EventB event = eventDao.getEventB(ename);
        if (event == null) {
            return Response.err("event不存在");
        }
        event.setFieldMaps(fieldMap);
        if (globals != null && globalValues != null){
            List<ConstMap> list = event.getConstMaps();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < globals.length; j++) {
                    if (list.get(i).getConstName().equals(globals[j])){
                        list.get(i).setValue(globalValues[j]);
                    }
                }
            }
            event.setConstMaps(list);
        }

        eventDao.saveEventB(event);

        EventB e1 = eventDao.getEventB(ename);
        System.out.println(e1.getFieldMaps());
        return Response.succ();
    }

    /**
     * 指定 输出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("event/{ename}/outputNames")
    public Response<Map<String, Object>> filedMappingOutput(@PathVariable("ename") String ename, String[] output, String[] outputField) {
        Map outputMaps = new HashMap<String, Object>();
        for (int i = 0; i < output.length; i++) {
            outputMaps.put(output[i], outputField[i]);
        }

        //获取到event对象
        EventB event = eventDao.getEventB(ename);
        if (event == null) {
            return Response.err("event不存在");
        }
        event.setOutputMaps(outputMaps);
        eventDao.saveEventB(event);

        EventB e1 = eventDao.getEventB(ename);
        System.out.println(e1.getOutputMaps());
        return Response.succ();
    }

//    @ResponseBody
//    @RequestMapping("event/{ename}/oldrun")
//    public String oldtextRun(@PathVariable("ename") String eventName, String in) {
////        in =  " {\"com.xc.bom.BomA\": {\"APPID\": \"A1\", \"APP_PRODUCT\": \"A001\", \"BIRTHDAY\": 1517898916163, \"ID_NUM\": \"a00001\", \"IF_APPLY\": \"true\", \"IF_DECLINED_6MTH\": \"true\", \"IF_HAS\": \"true\", \"JOB_STATUS\": \"true\"},\n" +
////                " \"com.xc.bom.BomA\": {\"APPID\": \"A2\", \"APP_PRODUCT\": \"A002\", \"BIRTHDAY\": 1517898916163, \"ID_NUM\": \"a00002\", \"IF_APPLY\": \"true\", \"IF_DECLINED_6MTH\": \"true\", \"IF_HAS\": \"true\", \"JOB_STATUS\": \"true\"}}\n";
////        eventName = "event1";
////        Map<String, Object> globals = globalUtil.getGlobal(eventName);
//        if(in != null){
//            in = in.replaceAll("%7B","{").replaceAll("%7D","}");
//        }
//        try {
//            Date startTime = new Date();
//            Event event = eventDao.getEvent(eventName);
//            List<DroolsMap> drools = event.getDrools();
//            List<String> paths = new ArrayList<>();
//            for (DroolsMap drool : drools) {
//                paths.add(drool.getPath());
//            }
//            textRule.init(paths);
//            List<Object> rows = JsonUtil.transform(event.getFieldMaps(),in);
//            Object op = textRule.calc(rows,eventName);
//            textRule.close();
//            Date endTime = new Date();
//            System.out.println("Rule start time is: "+startTime );
//            System.out.println("Rule  end  time is: "+endTime );
//            return JsonUtil.toJson(op);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "false";
//    }


    @ResponseBody
    @RequestMapping("event/{ename}/run")
    public Response textRun(@PathVariable("ename") String eventName, String in) {
        if (in != null) {
            in = in.replaceAll("%7B", "{").replaceAll("%7D", "}");
        }
        try {
            Date startTime = new Date();
            EventB event = eventDao.getEventB(eventName);
            List<DroolsMap> drools = event.getDrools();
            Collections.sort(drools);
            BaseIn baseIn = InFactory.get();
            BaseOut baseOut = OutFactory.get();
            Map<String,Object> rows =  baseIn.readmap(in, event.getFieldMaps());
            for (DroolsMap drool : drools) {
                textRule.init(new File(drool.getPath()));
//                textRule.calc(rows, eventDao.getConsts(eventName), drool);
                textRule.close();
            }

            Date endTime = new Date();
            System.out.println("Rule start time is: " + startTime);
            System.out.println("Rule  end  time is: " + endTime);
            return Response.succ(baseOut.output(rows, event.getOutputMaps()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.err();
    }

    /**
     * 删除event
     *
     * @param ename
     * @return
     */
    @RequestMapping("event/{ename}/del")
    @ResponseBody
    public boolean delEvent(@PathVariable("ename") String ename) {
        if ("".equals(ename)) {
            return false;
        } else {
            return eventDao.removeEvent(ename);
        }
    }

}
