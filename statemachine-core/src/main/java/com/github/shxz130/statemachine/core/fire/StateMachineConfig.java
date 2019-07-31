package com.github.shxz130.statemachine.core.fire;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jetty on 2019/7/31.
 */
public class StateMachineConfig<S, E, H> {

    private S tempCurrentState;
    private E tempEvent;
    private H tempHandler;
    private S tempNextState;

    private final Map<S, StateConfiguration<S, E,H>> stateConfigurationMap = new HashMap<S, StateConfiguration<S, E,H>>(8);

    public StateMachineConfig from(S s){
        this.tempCurrentState=s;
        return this;
    }
    public StateMachineConfig permit(E e){
        this.tempEvent=e;
        return this;
    }

    public StateMachineConfig handle(H h){
        this.tempHandler=h;
        return this;
    }


    public StateMachineConfig to(S s){
        this.tempNextState=s;
        return this;
    }

    public void build(){
        StateConfiguration stateConfiguration= createOrGetStateConfiguration(tempCurrentState);
        stateConfiguration.configEventHandle(tempEvent,tempHandler);
        stateConfiguration.configEventNextState(tempEvent,tempNextState);
        this.tempCurrentState=null;
        this.tempEvent=null;
        this.tempHandler=null;
        this.tempNextState=null;
    }


    private StateConfiguration createOrGetStateConfiguration(S s){
        if(stateConfigurationMap.get(s)==null){
            StateConfiguration stateConfiguration=new StateConfiguration(s);
            stateConfigurationMap.put(s,stateConfiguration);
        }
        return stateConfigurationMap.get(s);
    }

    public H getHandle(S s,E e){
        StateConfiguration stateConfiguration=stateConfigurationMap.get(s);
        if(stateConfiguration==null){
            return null;
        }
        return (H)stateConfiguration.getHandle(e);
    }

    public S getNextState(S s,E e){
        return stateConfigurationMap.get(s)==null?null: stateConfigurationMap.get(s).getNextState(e);
    }
}