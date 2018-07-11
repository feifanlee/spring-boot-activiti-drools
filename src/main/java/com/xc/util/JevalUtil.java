package com.xc.util;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JevalUtil {
    private static Logger logger = LoggerFactory.getLogger(JevalUtil.class);

    public static HashMap<String,Object> evaluate(String exprID, Map<String,Object> parmets) {
        PropertyUtil.setPath("D:\\Workspace\\IdeaProjects\\Beta\\ruleengine\\src\\main\\resources\\config\\expression.properties",PropertyKeys.FORMULA_PROPERTIES_TYPE);
        String expr=PropertyUtil.get(exprID, PropertyKeys.FORMULA_PROPERTIES_TYPE);
        System.out.println(expr);
        Evaluator eva = new Evaluator();
        for (Map.Entry<String, Object> parme : parmets.entrySet()) {
            eva.putVariable(parme.getKey(), String.valueOf(parme.getValue()));
        }
        HashMap result=new HashMap<String,Object>();
        try {
            result.put("result",eva.evaluate(expr));
        } catch (EvaluationException e) {
            e.printStackTrace();
            logger.warn("无法执行公式，缺少对应参数:"+e.getMessage());
            return null;
        }
        return result;
    }

    public static void main(String[] args) throws EvaluationException {
        // 我们的游戏公式 ahit-(blv-alv)*(6*beva/100)+32
        Map parmets=new HashMap<String,Object>();
        parmets.put("ahit", "33");
        parmets.put("blv", "10");
        parmets.put("alv", "10");
        parmets.put("beva", "5");
        System.out.print(JevalUtil.evaluate("expr1",parmets));
      /*  Evaluator eva = new Evaluator();
        try {
            *//** * 添加变量到 Evaluator 类实例. *//*
            eva.putVariable("ahit", "33");
            eva.putVariable("blv", "10");
            eva.putVariable("alv", "10");
            eva.putVariable("beva", "5");
            *//** * 简单输出变量. *//*
            System.out.println(eva.evaluate("#{ahit}"));
            System.out.println(eva.evaluate("#{blv}"));
            System.out.println(eva.evaluate("#{alv}"));
            System.out.println(eva.evaluate("#{beva}"));
            //公式计算
            //公式字符串
            String exp="#{ahit}-(#{blv}-#{alv})*(6*#{beva}/100)+32";
            System.out.println(eva.evaluate(exp));
        } catch (EvaluationException e) {
            e.printStackTrace();
        }*/

        System.out.println(PropertyUtil.get("drools.modules",PropertyKeys.DEFALUT_PROPERTIES_TYPE));
        System.out.println(PropertyUtil.get("expr2",PropertyKeys.FORMULA_PROPERTIES_TYPE));
    }
}
