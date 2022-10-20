package com.cose444.pingpong.pingpong;

import com.cose444.pingpong.pingpong.config.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({Properties.class})
public class PingpongApplication {

    public static void main(String[] args) {
        SpringApplication.run(PingpongApplication.class, args);
    }

}
