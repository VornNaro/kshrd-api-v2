package com.kshrd.kshrd_websiteapi.configuration.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // this code below is added to reject queries with malicious strings
        // todo: this is a temporary solution to help prevent the API from going down
        // todo: due to suspected attacks
        String queryString = request.getRequestURI() + "?" + request.getQueryString();
        if (containsMaliciousString(queryString)) {
            logInformation(request);
            response.setStatus(401);
            return false;
        }
        return true;
    }

    private boolean containsMaliciousString(String queryString) {
        return queryString.contains("%2e") || queryString.contains(";");
    }

    private void logInformation(HttpServletRequest request) {
        logger.info(
                "Suspicious Request: METHOD({}) URI({}) QUERY({})",
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString());
        logger.info("Has Remote Host: {}", request.getRemoteHost());
    }

    // @Override
    // public void afterCompletion(HttpServletRequest request, HttpServletResponse
    // response, Object handler, Exception ex) throws Exception {
    // // Log response details
    // logger.info("Sent response: {} {} with status {} and exception {}",
    // request.getMethod(), request.getRequestURI(), response.getStatus(), ex);
    // }
}