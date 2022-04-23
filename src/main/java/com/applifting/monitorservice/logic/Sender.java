package com.applifting.monitorservice.logic;

import com.applifting.monitorservice.service.ResultService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class Sender extends Thread {

    private final ResultService resultService;

    private Integer delay;
    private String url;

    public Sender(ResultService resultService, String endpointName, Integer delay, String url) {
        super(endpointName);
        this.resultService = resultService;
        this.delay = delay * 1000;
        this.url = url;
    }

    @Override
    public void run() {
        HttpURLConnection connection = null;
        try {
            URL u = new URL(url);
            while (true) {
                connection = (HttpURLConnection) u.openConnection();
                connection.setRequestMethod("HEAD");
                int code = connection.getResponseCode();
                String message = connection.getResponseMessage();
                resultService.saveMonitoringResult(getName(), code, message);
                Thread.sleep(delay);
            }
        } catch (NullPointerException e) {
            interrupt();
            log.info("Thread with \"{}\" endpoint was stopped", getName());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
