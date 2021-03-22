package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

/**
 * 实现流程实例挂起与激活
 */
public class SusppendProcessInstance {

    public static void main(String[] args) {

        String processDefinitionName = "holiday2";

        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.得到当前流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionName)
                .singleResult();

        //4.得到当前流程定义的实例是否被挂起
        boolean suspended = processDefinition.isSuspended();

        String processDefinitionId = processDefinition.getId();
        //5.判断
        if (suspended) {
            //如果被挂起  则激活，这里将流程定义的所有流程实例全部激活
            repositoryService.activateProcessDefinitionById(processDefinitionId,true,null);
            System.out.println("流程定义：" + processDefinitionId + "激活");
        } else {
            //如果激活则挂起 这里将流程定义下的所有流程实力全部挂起
            repositoryService.suspendProcessDefinitionById(processDefinitionId,true,null);
            System.out.println("流程定义：" + processDefinitionId + "挂起");
        }

    }

}
