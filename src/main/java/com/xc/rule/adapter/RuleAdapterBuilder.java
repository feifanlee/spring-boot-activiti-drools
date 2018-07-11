package com.xc.rule.adapter;

/**
 * Created by lifeifan on 2017/11/6.
 */
public class RuleAdapterBuilder {


    public static BaseRuleAdapter getAdapter() {
//        if (PropertyUtil.get(PropertyKeys.ADAPTER_GLOBAL_RESULT_NAME) != null){
//            return new GlobalResultAdapter();
//        }else if(PropertyUtil.get(PropertyKeys.ADAPTER_GLOBAL_RESULTS_MAP)!=null){
//            return new GlobalMapAdapter();
//        }else
        //todo 有待修改
        if(1 == 1){
            return new AllAdapter();
        }
        return new EmptyAdapter();
    }
}
