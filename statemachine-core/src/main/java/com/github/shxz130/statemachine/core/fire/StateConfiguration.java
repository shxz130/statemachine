package com.github.shxz130.statemachine.core.fire;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jetty on 2019/7/31.
 */

public class StateConfiguration<S, E,H> {

    @Getter
    private S currentState;

    private Map<E,H> eventHandleMap;

    private Map<E, S> nextStateMap;

    public StateConfiguration(S state) {
        this.currentState = state;
        eventHandleMap = new HashMap<E, H>(8);
        nextStateMap = new HashMap<E, S>(8);
    }

    public void configEventHandle(E e,H h){
        eventHandleMap.put(e,h);
    }

    public void configEventNextState(E e,S s){
        nextStateMap.put(e,s);
    }

    public H getHandle(E e){
        return eventHandleMap.get(e);
    }

    public S getNextState(E e){
        return nextStateMap.get(e);
    }
}
