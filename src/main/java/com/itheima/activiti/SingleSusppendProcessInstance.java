package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 实现流程实例挂起与激活
 */
public class SingleSusppendProcessInstance {

    public static void main(String[] args) {

        String instanceId = "7501";

        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RuntimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //3.得到当前流程定义
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();

        //4.得到当前流程定义的实例是否被挂起
        boolean suspended = processInstance.isSuspended();

        String processInstanceId = processInstance.getId();
        //5.判断
        if (suspended) {
            //如果被挂起  则激活，这里将流程定义的所有流程实例全部激活
            runtimeService.activateProcessInstanceById(processInstanceId);
            System.out.println("流程定义：" + processInstanceId + "激活");
        } else {
            //如果激活则挂起 这里将流程定义下的所有流程实力全部挂起
            runtimeService.suspendProcessInstanceById(processInstanceId);
            System.out.println("流程定义：" + processInstanceId + "挂起");
        }

    }

}
