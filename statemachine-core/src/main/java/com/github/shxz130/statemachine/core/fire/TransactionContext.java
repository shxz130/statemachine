package com.github.shxz130.statemachine.core.fire;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jetty on 2019/7/31.
 */
public class TransactionContext {

    private Map<String, Object> dataMap;

    public TransactionContext() {
        this.dataMap = new HashMap<String, Object>();
    }

    public Object getData(String key){
        return dataMap.get(key);
    }

    public Object setData(String key,Object value){
        return dataMap.put(key,value);
    }
}
