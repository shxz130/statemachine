package com.github.shxz130.statemachine.demo.config;

import lombok.Data;

/**
 * Created by jetty on 2019/7/31.
 */
public enum LeavePermitEvent {
    SUBMIT_PERMIT,//提交假单
    LEADER_PERMIT,//领导审批
    LEADER_PERMIT_AGREE,//领导审批通过
    LEADER_PERMIT_DISAGREE,//领导审批不通过
    CEO_PERMIT_AGREE,//ceo审批通过
    CEO_PERMIT_DISAGREE,//ceo审批不通过
}
