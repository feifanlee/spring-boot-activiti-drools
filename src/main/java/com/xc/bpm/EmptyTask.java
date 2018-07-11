package com.xc.bpm;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/3/13
 * Time: 10:12
 */
@Component("emptyTask")
public class EmptyTask implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("is emtpy task");
        Thread.sleep(20);
    }
}
