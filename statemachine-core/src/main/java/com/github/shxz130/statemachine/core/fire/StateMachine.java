package com.github.shxz130.statemachine.core.fire;

import com.github.shxz130.statemachine.core.config.BaseStateMachineKey;
import com.github.shxz130.statemachine.core.config.Handler;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jetty on 2019/7/31.
 */
@Slf4j
public class StateMachine<S, E,H extends Handler> {

    private final StateMachineConfig<S, E,H> stateMachineConfig;


    public StateMachine( StateMachineConfig<S, E,H> config) {
        this.stateMachineConfig = config;
    }

    /**
     * 触发
     *
     * @param event 触发器
     * @param context 上下文
     */
    public void fire(E event, TransactionContext context) {
        S mainStateEnum=(S)context.getData(BaseStateMachineKey.CURRENT_STATE);
        if(null==mainStateEnum){
            throw new RuntimeException("未找到下一个状态，状态机无法继续");
        }
        H handle=stateMachineConfig.getHandle(mainStateEnum, event);
        if(handle==null){
            throw new RuntimeException(String.format("状态和指令不匹配,state=[%s],event=[%s]",mainStateEnum,event));
        }
        S nextState=stateMachineConfig.getNextState(mainStateEnum, event);
        context.setData(BaseStateMachineKey.CURRENT_STATE,nextState);
        log.info("[StateMachine] runing currentState=[{}], event=[{}], handle=[{}], nextState=[{}]",mainStateEnum,event,handle.getClass().getSimpleName(),nextState);
        handle.handle(context,this);
    }
}
