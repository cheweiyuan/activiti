package com.cwy;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class HolidayTest {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //部署流程
    @Test
    public void deployTest() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment builder = repositoryService.createDeployment()
                .addClasspathResource("activiti/holiday2.bpmn")
                .name("请假测试")
                .deploy();
        System.out.println("流程部署id:" + builder.getId());
        System.out.println("流程部署名称:" + builder.getName());
    }

    //启动一个流程实例
    @Test
    public void startProcessInstance() {
        //获取runTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //根据流程定义key来启动流程
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("holiday2_2");
        System.out.println("流程定义id:" + processInstance.getProcessDefinitionId());
        System.out.println("当前活动id:" + processInstance.getActivityId());
    }

    //查询当前个人待执行任务
    @Test
    public void findPersonalTaskList() {
        //任务负责人
        String assingee = "lisi";
        //创建taskService
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("holiday2_2")
                .taskAssignee(assingee)
                .list();
        for (Task task : list) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务名称：" + task.getName());
        }
    }

    //完成任务
    @Test
    public void completeTask() {
        String taskId = "25002";
        //创建taskService
        TaskService taskService = processEngine.getTaskService();
        //完成任务
        taskService.complete(taskId);
        System.out.println("任务完成id=" + taskId);
    }

    //单个文件部署方式
    @Test
    public void deployProcess() {
        //获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //bpmn输入流
        InputStream inputStream_bpmn = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream("activiti/holiday.bpmn");
        //
        InputStream inputStream_png = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream("activiti/holiday.png");
        //流程部署对象
        Deployment deploy = repositoryService.createDeployment()
                .addInputStream("holiday.bpmn", inputStream_bpmn)
                .addInputStream("holiday.png", inputStream_png)
                .deploy();
        System.out.println("流程部署id:" + deploy.getId());
        System.out.println("流程部署名称:" + deploy.getName());
    }

    //流程定义查询
    @Test
    public void queryPorcee() {
        //流程定义key
        String processDefinitionKey = "holiday2";
        //获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //查询流程定义
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        //遍历查询结果
        List<ProcessDefinition> list = processDefinitionQuery
                .processDefinitionKey(processDefinitionKey)
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("----------------------");
            System.out.println("流程定义id:" + processDefinition.getDeploymentId());
            System.out.println("流程定义名称:" + processDefinition.getName());
            System.out.println("流程定义key:" + processDefinition.getKey());
            System.out.println("流程定义版本:" + processDefinition.getVersion());
        }
    }

    //删除已经部署成功的流程定义
    @Test
    public void deleteDeployment() {
        //流程部署id
        String deploymentId = "40001";
        //通过流程引擎获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //删除流程定义，如果该流程定义已有流程实例启动则删除时出错
//        repositoryService.deleteDeployment(deploymentId);
        //设置true级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级联删除方式
        repositoryService.deleteDeployment(deploymentId, true);
    }


    //流程历史信息的查看
    @Test
    public void testHistoric01() {
        HistoryService historyService = processEngine.getHistoryService();
        HistoricActivityInstanceQuery query =
                historyService.createHistoricActivityInstanceQuery();
        query.processInstanceId("87501");
        List<HistoricActivityInstance> list = query.list();
        for (HistoricActivityInstance historicActivityInstance : list) {
            System.err.println(historicActivityInstance.getActivityId());
            System.err.println(historicActivityInstance.getActivityName());
            System.err.println(historicActivityInstance.getDeleteReason());
            System.err.println(historicActivityInstance.getProcessInstanceId());
            System.err.println("=========================================");
        }

    }
}
