package com.foundationXX.prometheus.soundjunkie.service;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;

import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class PropertyReloadingService {
    private PeriodicReloadingTrigger trigger;
    private String defaultPropertiesFile = "system.properties";

    private void setUp(String path){
        File propertiesFile = new File(path);
        ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(new Parameters().fileBased().setFile(propertiesFile));


        trigger = new PeriodicReloadingTrigger(
                builder.getReloadingController(),null, 5, TimeUnit.SECONDS);
    }

    public void init(){
        this.setUp(this.defaultPropertiesFile);
        this.trigger.start();
        System.out.println("Triggered started");
    }
}
