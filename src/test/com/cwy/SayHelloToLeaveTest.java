package com.cwy;

import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.util.HashMap;

public class SayHelloToLeaveTest {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Test
    public void testStartProcess() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addClasspathResource("activiti/leave.bpmn");
        builder.deploy();

        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .singleResult();
        System.err.println("进入");
        System.err.println(processDefinition);


    }

    //启动流程
    @Test
    public void startProcess(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("leave");
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("applyUser","employeel");
        variables.put("days",3);
    }

    //查看任务
    @Test
    public void queryProcess(){
        TaskService taskService = processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        Task taskOfLeader = taskQuery.taskCandidateGroup("deptLeader").singleResult();
        System.out.println(taskOfLeader);
    }


}




























