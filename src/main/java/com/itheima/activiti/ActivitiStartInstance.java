package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import java.util.List;

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
//        2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();
//        3.根据流程定义的key，负责人assignee来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("holiday3")
                .taskAssignee("wangwu")
                .list();
        System.out.println("----------------" + taskList.size());
//        4.任务列表的展示
        for (Task task : taskList) {
            System.out.println("流程实例id " + task.getProcessInstanceId());
            System.out.println("任务id " + task.getId());
            System.out.println("任务负责人 " + task.getAssignee());
            System.out.println("任务名称 " + task.getName());
        }
    }
}
