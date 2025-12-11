package com.date.datingapp.infra.logger.impl;

import java.util.Set;

import com.date.datingapp.infra.logger.Logger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuditLogger implements Logger {

    private static final Set<String> USER_STATE_CHANGES = Set.of(
            "user created",
            "user cancelled",
            "user confirmed");

    @Override
    public void info(String message) {
        if (shouldAudit(message)) {
            auditEvent(message, null);
        }
    }

    @Override
    public void error(String message, Throwable throwable) {
        if (shouldAudit(message)) {
            auditEvent(message, throwable);
        }
    }

    @Override
    public void error(String message) {
        if (shouldAudit(message)) {
            auditEvent(message, null);
        }
    }

    @Override
    public void info(String message, Object... args) {
        String formatted = formatMessage(message, args);
        if (shouldAudit(formatted)) {
            auditEvent(formatted, null);
        }
    }

    @Override
    public void error(String message, Throwable throwable, Object... args) {
        String formatted = formatMessage(message, args);
        if (shouldAudit(formatted)) {
            auditEvent(formatted, throwable);
        }
    }

    private boolean shouldAudit(String message) {
        String lowerMessage = message.toLowerCase();
        return USER_STATE_CHANGES.stream()
                .anyMatch(lowerMessage::contains);
    }

    private String formatMessage(String message, Object... args) {
        if (args == null || args.length == 0) {
            return message;
        }
        String result = message;
        for (Object arg : args) {
            result = result.replaceFirst("\\{\\}", String.valueOf(arg));
        }
        return result;
    }

    @Override
    public void debug(String message, Object... args) {
        // AuditLogger doesn't audit debug messages
    }

    @Override
    public void warn(String message, Object... args) {
        // AuditLogger doesn't audit warn messages
    }

    private void auditEvent(String message, Throwable throwable) {
        log.info("[AUDIT] - {}", message);

        if (throwable != null) {
            log.error("[AUDIT] Exception details:", throwable);
        }
    }
}