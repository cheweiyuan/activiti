package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * 测试流程的激活与挂起
 */
public class TestSuspendProcessInstance {

    /**
     * 当前流程实例7501  已经处于挂起状态 如果此时要让该实例继续执行，问题是：是否可以成功？
     * 如果不能执行  是否会抛出异常？
     * @param args
     */
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday2")
                .taskAssignee("zhangsan")
                .singleResult();
        taskService.complete(task.getId());

        System.out.println("输出任务id：" + task.getId());

    }
}
