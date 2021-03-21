package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

/**
 * 删除已经部署的流程
 * 背后影响的表
 * act_ge_bytearray
 * act_ge_deployment
 * act_re_procdef
 */
public class DeleteProcessDefinition {
    /**
     * 注意事项
     *      1.当我们正在执行的这一套流程没有完全审批结束的时候，此时如果要删除流程定义信息就会失败
     *      2.如果公司层面要强制删除，可以使用repositoryService.deleteDeployment("7501",true);
     *      //参数为true代表级联删除，此时就会先删除没有完成的流程节点，最后就可以删除流程定义信息
     * @param args
     */

    public static void main(String[] args) {
        //1.得到RepositoryService对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.创建RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3执行删除流程定义 参数代表流程部署的id
        repositoryService.deleteDeployment("30001",true);

    }
}
