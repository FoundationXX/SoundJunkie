package com.foundationXX.prometheus.soundjunkie.service;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HealthCheckService{
    private static final Logger logger = LoggerFactory.getLogger(HealthCheckService.class);
    private Configurations configs = new Configurations();
    private Configuration config;

    public Configuration loadConfig(String path){
        try
        {
            this.config = configs.properties(path);
        }
        catch(ConfigurationException cex)
        {
            logger.error("Exception occurred when retrieving system properties: {}", cex);
        }
        return config;
    }


}

