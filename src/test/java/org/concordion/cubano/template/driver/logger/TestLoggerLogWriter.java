package org.concordion.cubano.template.driver.logger;

import org.concordion.cubano.driver.http.LogWriter;

/**
 * 
 * This class extends {@link LogWriter}. LogWriter is part of HttpEasy, and so provides an ability to
 * override the default logging mechanism
 *
 */
public class TestLoggerLogWriter extends LogWriter {

    private String request;
    private String response;

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }

    @Override
    public void info(String msg, Object... args) {
        // log any info messages

    }

    @Override
    public void request(String msg, Object... args) {
        request = msg;

    }

    @Override
    public void response(String msg, Object... args) {
        response = msg;
    }

    @Override
    public void error(String message, Throwable t) {
        // log any error messages
    }
}
