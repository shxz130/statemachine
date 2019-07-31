package com.github.shxz130.statemachine.demo.config.handler;

import com.github.shxz130.statemachine.core.config.Handler;
import com.github.shxz130.statemachine.core.fire.StateMachine;
import com.github.shxz130.statemachine.core.fire.TransactionContext;
import com.github.shxz130.statemachine.demo.config.LeavePermitContextConstants;
import com.github.shxz130.statemachine.demo.config.LeavePermitEvent;
import com.github.shxz130.statemachine.demo.config.bean.LeavePermit;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jetty on 2019/7/31.
 */
@Slf4j
public class LeaderPermitHandler implements Handler{

    public void handle(TransactionContext context, StateMachine stateMachine) {
       LeavePermit leavePermit=(LeavePermit)context.getData(LeavePermitContextConstants.LEAVE_PERMIT);
       leavePermit.setStatus("LEADER_PERMIT");
        log.info("[{}],permit=[{}]", this.getClass().getSimpleName(),leavePermit);
        String leaderSuggestion=(String)context.getData(LeavePermitContextConstants.LEADER_PERMIT_SUGGESTION);
        if(leaderSuggestion==null){
            log.info("等待领导审批");
            return;
        }
    }
}
