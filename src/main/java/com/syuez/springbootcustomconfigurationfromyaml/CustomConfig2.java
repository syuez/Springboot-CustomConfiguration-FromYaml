package com.syuez.springbootcustomconfigurationfromyaml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "client")
@PropertySource(value = "classpath:custom.yml", factory = YamlPropertySourceFactory.class)
public class CustomConfig2 {
    private Map<String,String> network;
    private List<String> switches;

    public Map<String, String> getNetwork() {
        return new HashMap<>(network);
    }

    public void setNetwork(Map<String, String> network) {
        this.network = new HashMap<>(network);
    }

    public List<String> getSwitches() {
        return new ArrayList<>(switches);
    }

    public void setSwitches(List<String> switches) {
        this.switches = new ArrayList<>(switches);
    }

    @Override
    public String toString() {
        return "CustomConfig2{" +
                "network=" + network +
                ", switches=" + switches +
                '}';
    }
}
