package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * 完成任务
 */
public class ActivitiCompleteTask {

    public static void main(String[] args) {
//        1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
//        3.处理任务，结合当前用户任务列表查询操作的话，
        //根据流程定义的key，负责人assignee来实现当前用户的任务列表查询
        Task taskList = taskService.createTaskQuery()
                .processDefinitionKey("holiday3")
                .taskAssignee("wangwu")
                .singleResult();
            System.out.println("--------------" + taskList.getId());
            taskService.complete(taskList.getId());
    }
}
