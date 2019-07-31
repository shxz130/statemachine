package com.github.shxz130.statemachine.demo.main;

import com.github.shxz130.statemachine.core.fire.StateMachineFactory;
import com.github.shxz130.statemachine.core.fire.TransactionContext;
import com.github.shxz130.statemachine.demo.config.LeavePermitContextConstants;
import com.github.shxz130.statemachine.demo.config.LeavePermitEvent;
import com.github.shxz130.statemachine.demo.config.LeavePermitState;
import com.github.shxz130.statemachine.demo.config.StatemachineInit;
import com.github.shxz130.statemachine.demo.config.bean.LeavePermit;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jetty on 2019/7/31.
 */
@Slf4j
public class Main {

    public static void main(String[] args){
        StatemachineInit.init();

        log.info("创建假单");
        TransactionContext transactionContext=new TransactionContext();
        transactionContext.setData(LeavePermitContextConstants.CURRENT_STATE, LeavePermitState.SUBMIT_PERMIT);
        StateMachineFactory.getStateMachine("LEAVE_PERMIT").fire(LeavePermitEvent.SUBMIT_PERMIT, transactionContext);


        log.info("领导审批");
        LeavePermit leavePermit=(LeavePermit)transactionContext.getData(LeavePermitContextConstants.LEAVE_PERMIT);
        TransactionContext transactionContext2=new TransactionContext();
        transactionContext2.setData(LeavePermitContextConstants.LEAVE_PERMIT,leavePermit);
        transactionContext2.setData(LeavePermitContextConstants.CURRENT_STATE, LeavePermitState.LEADER_PERMIT);
        StateMachineFactory.getStateMachine("LEAVE_PERMIT").fire(LeavePermitEvent.LEADER_PERMIT_AGREE, transactionContext2);

        log.info("ceo审批");
        LeavePermit leavePermit2=(LeavePermit)transactionContext.getData(LeavePermitContextConstants.LEAVE_PERMIT);
        TransactionContext transactionContext3=new TransactionContext();
        transactionContext3.setData(LeavePermitContextConstants.LEAVE_PERMIT,leavePermit2);
        transactionContext3.setData(LeavePermitContextConstants.CURRENT_STATE, LeavePermitState.CEO_PERMIT);
        StateMachineFactory.getStateMachine("LEAVE_PERMIT").fire(LeavePermitEvent.CEO_PERMIT_AGREE, transactionContext3);
    }
}
