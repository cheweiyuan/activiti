package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;

public class QueryProcessDefinition {

    public static void main(String[] args) {
        //1.得到RepositoryService对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.创建RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.得到一个processDefinitionQuery对象，可以认为它就是一个查询器
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        //4.设置条件，并查询出当前所有的流程定义，查询条件：流程定义的key=holiday
        //orderByProcessDefinitionVersion  设置排序方式，根据流程定义的版本号进行排序
        List<ProcessDefinition> list = processDefinitionQuery.processDefinitionKey("holiday2_2")
                .orderByProcessDefinitionVersion()
                .desc().list();
        //5.输出流程定义信息
        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程定义id:" + processDefinition.getId());
            System.out.println("流程定义:" + processDefinition.getName());
            System.out.println("流程定义的Key:" + processDefinition.getKey());
            System.out.println("流程定义的版本号:" + processDefinition.getVersion());
            System.out.println("流程部署的id:" + processDefinition.getDeploymentId());
        }
    }
}
