package com.xc.bpm;

import com.xc.config.DroolsMap;
import com.xc.redis.BpmDao;
import com.xc.redis.EventDao;
import com.xc.rule.BaseRule;
import com.xc.rule.RuleFactory;
import com.xc.rule.out.BaseOut;
import com.xc.rule.out.OutFactory;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/3/6
 * Time: 15:58
 */
@Component("ruleTask")
public class RuleTask implements JavaDelegate {

    @Autowired
    BpmDao bpmDao;

    @Autowired
    RuleFactory ruleFactory;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Object> in = delegateExecution.getVariables();
        String processName = delegateExecution.getProcessBusinessKey();
        String droolsName = delegateExecution.getCurrentActivityName();
        if (!"FinishTask".equals(droolsName)) {

            DroolsMap drools =  new DroolsMap(droolsName);

            String initBegin = new SimpleDateFormat("yyyyMMddhhmmssSSS") .format(new Date());
            BaseRule textRule = ruleFactory.get(drools.getPath());
            String initOver = new SimpleDateFormat("yyyyMMddhhmmssSSS") .format(new Date());


            textRule.calc(in, bpmDao.getGlobals(processName), drools);
            String calcOver = new SimpleDateFormat("yyyyMMddhhmmssSSS") .format(new Date());


            //            delegateExecution.removeVariables();
            delegateExecution.setVariables(in);


            System.out.println("initBegin : " + initBegin);
            System.out.println("initOver : " + initOver);
            System.out.println("calcOver : " + calcOver);
        }else{
            BaseOut out = OutFactory.get();
            bpmDao.save("result", out.output(in,bpmDao.getOutputMatching(processName)));
        }
    }




}
