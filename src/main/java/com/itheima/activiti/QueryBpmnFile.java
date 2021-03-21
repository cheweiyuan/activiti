package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 1.从activiti的act_ge_bytearray表中读取两个资源文件
 * 2.将两个资源文件保存到一个路径下
 * <p>
 * 技术方案：
 * 1.第一种方式  使用activiti的api来实现
 * 2.第二种方式  其实就是原理层面，可以使用jdbc的对blob类型数据的读取，并保存
 * 3.IO流转换，最好commons-io.jar包可以轻松解决IO操作
 * <p>
 * 真实的应用场景：用户想查看请假流程具体有哪些步骤要走？
 */
public class QueryBpmnFile {

    public static void main(String[] args) throws IOException {
        //1.得到ProcessEngines对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.得到RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3.得到查询器 ProcessDefinitionQuery对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        //4.设置查询条件
        processDefinitionQuery.processDefinitionKey("holiday2_2");
        //5.执行查询操作，查询出我们想要的流程定义
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        //6.通过流程定义信息，得到部署id
        String deploymentId = processDefinition.getDeploymentId();
        //7.通过repositoryService的方法，实现读取图片信息 及 BPMN文件信息（输入流）
        //getResourceAsStream()第一个参数代表部署id  第二个参数代表资源名称
        // processDefinition.getDiagramResourceName()代表获取png图片资源的名称
        // processDefinition.getResourceName() 代表获取bpmn文件的名称
        InputStream pngIs = repositoryService.getResourceAsStream(deploymentId, processDefinition.getDiagramResourceName());
        InputStream bpmnIs = repositoryService.getResourceAsStream(deploymentId, processDefinition.getResourceName());
        //8.构造出OutputStream流
        FileOutputStream pngOs = new FileOutputStream("D:\\tempFiles\\" + processDefinition.getDiagramResourceName());
        FileOutputStream bpmnOs = new FileOutputStream("D:\\tempFiles\\" + processDefinition.getResourceName());
        //9.输入流，输出流的转换 commons-io-xx.jar的方法
        IOUtils.copy(pngIs, pngOs);
        IOUtils.copy(bpmnIs, bpmnOs);

        //10.关闭流
        pngOs.close();
        bpmnOs.close();
        pngIs.close();
        bpmnOs.close();
    }

}
