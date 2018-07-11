package com.xc.rule.in;

import com.xc.rule.in.impl.JsonIn;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: haihaoZhang
 * Date: 2018/2/26
 * Time: 11:05
 */
public class InFactory {

    public static BaseIn get(){
        return new JsonIn();
    }
}
