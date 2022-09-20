package com.syuez.springbootcustomconfigurationfromyaml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootCustomConfigurationFromYamlApplicationTests {
    @Autowired
    private CustomConfig config;

    @Autowired
    private CustomConfig2 config2;

    @Test
    void contextLoads() {
    }

    @Test
    public void configTest() {
        System.out.println(config.toString());
        System.out.println(config2.toString());
    }

}
