package com.cwy;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class test {

    //创表
    @Test
    public void createTables() {
        //创建ProcessEngineProcessEngine
        ProcessEngineConfiguration processEngineConfigurationFromResource
                = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //创建processEngine
        ProcessEngine processEngine = processEngineConfigurationFromResource.buildProcessEngine();
        System.out.println(processEngine);


    }




}
