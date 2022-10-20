package com.cose444.pingpong.pingpong.service;

import com.cose444.pingpong.pingpong.config.Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class Service {

    private final Properties properties;

    private final RestTemplate restTemplate;

    public String ping() {
        String result = restTemplate.getForObject(properties.getRemote() + "pong", String.class);
        String format = String.format(
                "[%s] ---ping--> [%s] %s",
                properties.getSource().getPosition(),
                properties.getTarget().getPosition(),
                result
        );
        log.warn(format);
        return format;
    }

    public String pong() {
        String result = "Hi Hi Hi";
        String format = String.format(
                "[%s] <--pong--- [%s] %s",
                properties.getTarget().getPosition(),
                properties.getSource().getPosition(),
                result
        );
        log.warn(format);
        return result;
    }

}
