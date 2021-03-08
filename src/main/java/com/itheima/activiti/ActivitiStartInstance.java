package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 启动流程实例：前提是先已经完成流程定义的部署工作
 * act_hi_actinst       已完成的活动信息
 * act_hi_identitylink  参与者信息
 * act_hi_procinst      流程实例
 * act_hi_taskinst      任务实例
 * act_ru_execution     执行表
 * act_ru_identitylink  参与者信息
 * act_ru_task  任务
 */

public class ActivitiStartInstance {
    public static void main(String[] args) {
//        1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2.得到RunService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();
//        3.创建流程实例 流程定义的key需要知道
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday");
//        4.输出实例的相关信息
        System.out.println("流程部署id" + processInstance.getDeploymentId());
        System.out.println("流程实例id" + processInstance.getId());
        System.out.println("活动id" + processInstance.getActivityId());
    }
}
