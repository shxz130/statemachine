package com.github.shxz130.statemachine.core.fire;

import com.github.shxz130.statemachine.core.config.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jetty on 2019/7/31.
 */
public final class StateMachineFactory {

    private static Map<String, StateMachine<Object, Object, Handler>> stateMachineMap = new HashMap<String, StateMachine<Object, Object, Handler>>();


    private StateMachineFactory() {
    }


    public static void register(String key,StateMachine stateMachine){
        stateMachineMap.put(key,stateMachine);
    }

    public static StateMachine<Object,Object,Handler> getStateMachine(String key){
        return stateMachineMap.get(key);
    }
}