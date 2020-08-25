package com.cwy;

import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class DeployTest {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程
    @Test
    public void deployTest() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addClasspathResource("activiti/demo.bpmn");
        builder.deploy();
    }

    //启动流程
    @Test
    public void startProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("test_1");
    }

    //查看任务节点
    @Test
    public void queryProcess() {
        TaskService taskService = processEngine.getTaskService();
        String assigne = "user";
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assigne).list();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.err.println(task);
        }

        for (Task task : tasks) {
            System.err.println("taskId=" + "流程任务节点信息ID：" + task.getId() +
                    ",taskName:" + "流程任务节点名称ID：" + task.getName() +
                    ",assignee:" + "流程任务节点接受人：" + task.getAssignee() +
                    ",createTime:" + "流程任务节点创建时间：" + task.getCreateTime());

        }
    }

    //查询流程明细定义明细







}
