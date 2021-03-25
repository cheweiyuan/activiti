package com.cwy;
/**
 * 组任务的测试
 * @author admin
 *
 */

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class GroupTest {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deployHoliday() {
		
		RepositoryService repositoryService = processEngine.getRepositoryService();
		
		Deployment deploy = repositoryService.createDeployment()
		.addClasspathResource("activiti/holiday5.bpmn")
		.addClasspathResource("activiti/holiday5.png")
		.name("请假申请流程单")
		.deploy();
		System.out.println(deploy.getId());
		System.out.println(deploy.getName());
	}
	
	 //删除已经部署成功的流程定义
    @Test
    public void deleteDeployment() {
        //流程部署id
        String deploymentId = "72505";
        //通过流程引擎获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //删除流程定义，如果该流程定义已有流程实例启动则删除时出错
//        repositoryService.deleteDeployment(deploymentId);
        //设置true级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级联删除方式
        repositoryService.deleteDeployment(deploymentId, true);
    }
}
