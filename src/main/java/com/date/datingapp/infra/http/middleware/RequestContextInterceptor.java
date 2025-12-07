package com.date.datingapp.infra.http.middleware;

import com.date.datingapp.infra.http.HttpHeaderConstants;
import com.date.datingapp.infra.logger.LogKeys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class RequestContextInterceptor implements Filter {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        var httpRequest = (HttpServletRequest) request;

        var requestId = httpRequest.getHeader(HttpHeaderConstants.REQUEST_ID_HEADER);
        if (requestId == null || requestId.isEmpty()) {
            requestId = UUID.randomUUID().toString();
        }

        MDC.put(LogKeys.REQUEST_ID, requestId);
        MDC.put(LogKeys.APP_NAME, appName);

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(LogKeys.REQUEST_ID);
            MDC.remove(LogKeys.APP_NAME);
        }
    }
}
