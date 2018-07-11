package com.xc.rule.in;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lucyf
 * Date: 2018-02-09
 * Time: 17:58
 */
public interface BaseIn {

    List<Object> read(String in);

    Map<String, Object> readmap(String in,Map<String,String> map);

    Map<String, Object> read2map(String in);
}
