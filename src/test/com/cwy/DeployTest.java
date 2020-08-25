package com.cwy;

import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * 流程详细步骤
 * 部署流程 -> 启动流程 -> 查询用户id -> 用户完成任务 -> 流程结束
 */

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
    @Test
    public void queryProcdef() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.processDefinitionKey("test_1");
        List<ProcessDefinition> pds = query.list();
        System.err.println(">>>>>>>>>>>>>>>>>>");
        for (ProcessDefinition pd : pds) {
            System.err.println("ID:" + pd.getId() + ",NAME:" + pd.getName() +
                    ",KEY:" + pd.getKey() + ",VERSION:" + pd.getVersion() +
                    ",RESOURCE_NAME:" + pd.getResourceName() +
                    ",DGRM_RESOURCE_NAME:" + pd.getDiagramResourceName());

        }
    }

    //审核过程完成任务节点审批
    @Test
    public void startProcessApproval(){
        TaskService taskService = processEngine.getTaskService();
        //taskId 就是查询任务中的ID
        String taskId = "2505";
        //完成请假申请任务
        taskService.complete(taskId);
    }

}
