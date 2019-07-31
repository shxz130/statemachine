package com.github.shxz130.statemachine.demo.config;

import com.github.shxz130.statemachine.core.config.Handler;
import com.github.shxz130.statemachine.core.fire.StateMachine;
import com.github.shxz130.statemachine.core.fire.StateMachineConfig;
import com.github.shxz130.statemachine.core.fire.StateMachineFactory;
import com.github.shxz130.statemachine.demo.config.handler.*;

/**
 * Created by jetty on 2019/7/31.
 */
public class StatemachineInit {

    //初始化状态机
    public static  void init(){
        //支持多状态机 这里以请假为例，可以支持多种
        StateMachineFactory.register("LEAVE_PERMIT",buildLeavePermitStateMachine());

    }

    private static StateMachine buildLeavePermitStateMachine() {
        StateMachineConfig<LeavePermitState,LeavePermitEvent,Handler> stateMachineConfig=new StateMachineConfig();

        stateMachineConfig.from(LeavePermitState.SUBMIT_PERMIT)//初始状态，提交假单
                .permit(LeavePermitEvent.SUBMIT_PERMIT) //拿假条
                .handle(new SubmitLeavePermitHandler())//填假条
                .to(LeavePermitState.LEADER_PERMIT) //填完之后，到领导审批
                 .build();

        stateMachineConfig.from(LeavePermitState.LEADER_PERMIT) //领导审批
                .permit(LeavePermitEvent.LEADER_PERMIT)  //待审批
                .handle(new LeaderPermitHandler())     //领导查阅
                .to(LeavePermitState.LEADER_PERMIT)    //查阅完，仍旧是领导审批状态
                .build();


        stateMachineConfig.from(LeavePermitState.LEADER_PERMIT) //领导审批
                .permit(LeavePermitEvent.LEADER_PERMIT_AGREE)   //领导同意
                .handle(new CeoPermitHandler())                 //领导同意之后CEO审批
                .to(LeavePermitState.CEO_PERMIT)                //ceo审批
                .build();

        stateMachineConfig.from(LeavePermitState.LEADER_PERMIT)  //领导审批
                .permit(LeavePermitEvent.LEADER_PERMIT_DISAGREE) //领导不同意
                .handle(new PermitFailHandler())                //假条失败
                .build();

        stateMachineConfig.from(LeavePermitState.CEO_PERMIT)   //CEO审批
                .permit(LeavePermitEvent.CEO_PERMIT_AGREE)      //ceo审批同意
                .handle(new PermitSuccessHandler())             //假条成功
                .build();


        stateMachineConfig.from(LeavePermitState.CEO_PERMIT)       //ceo审批
                .handle(new PermitFailHandler())                //ceo审批不通过
                .permit(LeavePermitEvent.CEO_PERMIT_DISAGREE)      //假条失败
                .build();

        return new StateMachine(stateMachineConfig);
    }

}
