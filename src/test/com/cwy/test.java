package com.cwy;

import com.cwy.beans.User;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class test {

    //创表
    @Test
    public void createTables() {
        //创建ProcessEngineProcessEngine 参数：配置文件名称
        ProcessEngineConfiguration processEngineConfigurationFromResource
                = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //创建processEngine对象
        ProcessEngine processEngine = processEngineConfigurationFromResource.buildProcessEngine();
        System.out.println(processEngine);


    }

    @Test
    public void testGenTable(){
        //条件：1.activiti配置文件名称：activiti.cfg.xml   2.bean的id="processEngineConfiguration"
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }




}
