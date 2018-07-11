package com.xc.rule.out;

import com.xc.rule.out.impl.JsonOut;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/3/1
 * Time: 18:04
 */
public class OutFactory {
    public static BaseOut get(){
        return new JsonOut();
    }
}
