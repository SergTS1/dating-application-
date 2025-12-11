package com.date.datingapp.infra.logger;

public interface Logger {

    void error(String message, Throwable throwable);

    void error(String message);

    void info(String message);

    void error(String message, Throwable throwable, Object... args);

    void info(String message, Object... args);

    void debug(String message, Object... args);

    void warn(String message, Object... args);
}
