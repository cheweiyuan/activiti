package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 启动一个流程
 */
public class ActivitiRuntime {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取runTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //根据流程定义key来启动流程
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("holiday4");
        System.out.println("流程定义id:" + processInstance.getProcessDefinitionId());
        System.out.println("当前活动id:" + processInstance.getActivityId());
    }
}
