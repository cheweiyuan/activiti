package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 启动流程实例，添加进businessKey
 */
public class BusinessKeyAdd {

    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RuntimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //3.启动流程实例 同时还要指定业务标识  businessKey  它本身就是请假单的id
        //第一个参数：是指流程定义的key  第二个参数：业务标识businessKey
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday2_2", "1001");

        //4.输出processInstance相关的属性
        System.out.println(processInstance.getBusinessKey());
    }

}
