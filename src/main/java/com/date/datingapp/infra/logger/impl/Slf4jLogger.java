package com.date.datingapp.infra.logger.impl;

import com.date.datingapp.infra.logger.Logger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Slf4jLogger implements Logger {

    @Override
    public void error(String message, Throwable throwable) {
        log.error(message, throwable);
    }

    @Override
    public void error(String message) {
        log.error(message);
    }

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void error(String message, Throwable throwable, Object... args) {
        log.error(message, args, throwable);
    }

    @Override
    public void info(String message, Object... args) {
        log.info(message, args);
    }

    @Override
    public void debug(String message, Object... args) {
        log.debug(message, args);
    }

    @Override
    public void warn(String message, Object... args) {
        log.warn(message, args);
    }
}
