package com.xc.config;

import com.xc.util.PropertyKeys;
import com.xc.util.PropertyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lucyf
 * Date: 2018-02-07
 * Time: 16:12
 */
public class  DroolsMap implements Comparable<DroolsMap>{

    private String name;
    private String path;
//    private String boms;
    private String bomsStr;
    private String bomsInt;
    private String glbs;
    private String output;
    private int num;

    public DroolsMap() {
    }

    public DroolsMap(String name){
        String[] keys = new String[]{
                "drools."+name+".name",
                "drools."+name+".path",
                "drools."+name+".glbs",
                "drools."+name+".boms.str",
                "drools."+name+".boms.int",
//                "drools."+name+".boms",
        };
        this.setName(PropertyUtil.get(keys[0], PropertyKeys.DEFALUT_PROPERTIES_TYPE));
        this.setPath(PropertyUtil.get(keys[1], PropertyKeys.DEFALUT_PROPERTIES_TYPE));
        this.setGlbs(PropertyUtil.get(keys[2], PropertyKeys.DEFALUT_PROPERTIES_TYPE));
        this.setBomsStr(PropertyUtil.get(keys[3], PropertyKeys.DEFALUT_PROPERTIES_TYPE));
        this.setBomsInt(PropertyUtil.get(keys[4], PropertyKeys.DEFALUT_PROPERTIES_TYPE));
//        this.setBoms(get(keys[5]));
    }

    public DroolsMap(String name, String path, String bomsStr, String bomsInt, String glbs, String output, int num) {
        this.name = name;
        this.path = path;
        this.bomsStr = bomsStr;
        this.bomsInt = bomsInt;
        this.glbs = glbs;
        this.output = output;
        this.num = num;
    }

    public String getBomsStr() {
        return bomsStr;
    }

    public void setBomsStr(String bomsStr) {
        this.bomsStr = bomsStr;
    }

    public String getBomsInt() {
        return bomsInt;
    }

    public void setBomsInt(String bomsInt) {
        this.bomsInt = bomsInt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGlbs() {
        return glbs;
    }

    public void setGlbs(String glbs) {
        this.glbs = glbs;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public int compareTo(DroolsMap o) {
        if (this.num >= o.getNum()){
            return 1;
        }
        return -1;
    }

    public static List<DroolsMap> getAllDroolsMap(){
        try {
            PropertyUtil.refresh(PropertyKeys.DEFALUT_PROPERTIES_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<DroolsMap> list = new ArrayList<>();
        String droolsModules = PropertyUtil.get("drools.modules", PropertyKeys.DEFALUT_PROPERTIES_TYPE);
        if (droolsModules == null) {
            return null;
        }
        for (String modules : droolsModules.split(",")) {
            DroolsMap droolsMap = new DroolsMap(modules);
            list.add(droolsMap);
        }
        return list;
    }
}
