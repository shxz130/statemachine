package com.github.shxz130.statemachine.core.config;

import com.github.shxz130.statemachine.core.fire.StateMachine;
import com.github.shxz130.statemachine.core.fire.TransactionContext;

/**
 * Created by jetty on 2019/7/31.
 */
public interface Handler {

    void handle(TransactionContext context, StateMachine stateMachine);
}
