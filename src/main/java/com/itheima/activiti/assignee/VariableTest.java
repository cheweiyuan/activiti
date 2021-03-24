package com.itheima.activiti.assignee;

import com.cwy.beans.Holiday;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;

/**
 * 流程变量的测试
 */
public class VariableTest {

    //新的请假流程定义的部署
//    public static void main(String[] args) {
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//
//        Deployment deploy = repositoryService.createDeployment()
//                .addClasspathResource("activiti/holiday4.bpmn")
//                .addClasspathResource("activiti/holiday4.png")
//                .name("请假流程-流程变量")
//                .deploy();
//
//        System.out.println(deploy.getId());
//        System.out.println(deploy.getName());
//    }

    //启动流程实例 同时还要设置流程变量的值
//    public static void main(String[] args) {
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//
//        //流程定义的key问题  holiday4
//        String key = "holiday4";
//        HashMap<String, Object> map = new HashMap<>();
//        Holiday holiday = new Holiday();
//        holiday.setNum(5F);
//        map.put("holiday",holiday);
//        //启动流程实例 并且设置流程变量的值
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
//
//        //输出实例信息
//        System.out.println(processInstance.getName());
//        System.out.println(processInstance.getProcessDefinitionId());
//
//    }

    //完成任务  -----lisi----判断流程变量的请假天数-----人事经理存档
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        //查询当前用户是否有任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday4")
                .taskAssignee("zhaoliu")
                .singleResult();

        if (task != null) {
            taskService.complete(task.getId());
            System.out.println("任务执行完毕");
        }

    }
}
