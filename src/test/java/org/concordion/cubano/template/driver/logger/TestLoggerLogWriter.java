package org.concordion.cubano.template.driver.logger;

import org.concordion.cubano.driver.http.LogType;
import org.concordion.cubano.driver.http.LogWriter;

/**
 * 
 * This class extends {@link LogWriter}. LogWriter is part of HttpEasy, and so provides an ability to
 * override the default logging mechanism
 *
 */
public class TestLoggerLogWriter implements LogWriter {

    private String request;
    private String response;

    @Override
    public void info(String message, LogType logType) {
        
        if (LogType.REQUEST.equals(logType)) {
            request = message;
        } else {
            response = message;
        }
        
    }

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }
}
