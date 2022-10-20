package com.cose444.pingpong.pingpong.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "app")
public class Properties {

    private final String key;

    private final String remote;

    private final Container source;

    private final Container target;

    @Getter
    @RequiredArgsConstructor
    public static class Container {

        private final String position;

        private final String alias;

    }

}
