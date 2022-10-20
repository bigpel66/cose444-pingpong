package com.cose444.pingpong.pingpong.controller;

import com.cose444.pingpong.pingpong.config.Properties;
import com.cose444.pingpong.pingpong.exception.ForbiddenException;
import com.cose444.pingpong.pingpong.service.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

    private final Properties properties;

    private final Service service;

    @GetMapping("/")
    public String responseCall() {
        String format = String.format(
                "[%s] Hello, I am %s container! Nice to meet you!",
                properties.getSource().getPosition(),
                properties.getSource().getAlias());
        log.warn(format);
        return format;
    }

    @GetMapping("/ping")
    public String pingCall() {
        if (isNotCallable()) {
            throw new ForbiddenException("method is not callable");
        }
        try {
            return service.ping();
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return "pong error, nothing logged";
        }
    }

    @GetMapping("/pong")
    public String pongCall() {
        if (isNotCallable()) {
            throw new ForbiddenException("method is not callable");
        } else if (isNotKeyMatch()) {
            throw new ForbiddenException("key is not matched");
        } else {
            return service.pong();
        }
    }

    boolean isNotCallable() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return !request.getRequestURI().matches("/" + properties.getSource().getAlias());
    }

    boolean isNotKeyMatch() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return !Optional
                .ofNullable(request.getHeader("x-api-key"))
                .orElse("").
                matches(properties.getKey());
    }

}
