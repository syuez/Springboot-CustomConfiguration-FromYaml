package com.syuez.springbootcustomconfigurationfromyaml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "server")
@PropertySource(value = "classpath:custom.yml", factory = YamlPropertySourceFactory.class)
public class CustomConfig {
    private Map<String,String> machine;
    private List<String> switches;

    public Map<String, String> getMachine() {
        return machine;
    }

    public void setMachine(Map<String, String> machine) {
        this.machine = machine;
    }

    public List<String> getSwitches() {
        return switches;
    }

    public void setSwitches(List<String> switches) {
        this.switches = switches;
    }

    @Override
    public String toString() {
        return "CustomConfig{" +
                "machine=" + machine +
                ", switches=" + switches +
                '}';
    }
}
