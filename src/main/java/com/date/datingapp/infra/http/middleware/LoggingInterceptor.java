package com.date.datingapp.infra.http.middleware;

import com.date.datingapp.infra.logger.LogKeys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final String REQUEST_START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler)
            throws Exception {

        var requestId = MDC.get(LogKeys.REQUEST_ID);

        long startTime = System.currentTimeMillis();
        request.setAttribute(REQUEST_START_TIME, startTime);

        var msg = String.format(
                "[%s] Incoming request: %s %s from %s",
                requestId,
                request.getMethod(),
                request.getRequestURL(),
                request.getRemoteAddr());

        log.info(msg);

        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            @Nullable Exception ex)
            throws Exception {

        var requestId = MDC.get(LogKeys.REQUEST_ID);
        var startTime = (Long) request.getAttribute(REQUEST_START_TIME);

        if (startTime == null) {
            startTime = System.currentTimeMillis();
        }

        var duration = System.currentTimeMillis() - startTime;

        var msg = String.format(
                "[%s] Completed request: %s %s with status %d in %d ms",
                requestId,
                request.getMethod(),
                request.getRequestURL(),
                response.getStatus(),
                duration);
        log.info(msg);
    }
}
