package com.applifting.monitorservice.logic;

import com.applifting.monitorservice.data.model.Endpoint;
import com.applifting.monitorservice.service.EndpointService;
import com.applifting.monitorservice.service.ResultService;
import lombok.extern.slf4j.Slf4j;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class Sender extends Thread {

    private final ResultService resultService;
    private final EndpointService endpointService;

    private Integer delay;
    private String url;
    private Endpoint endpoint;
    private final Integer endpointId;

    public Sender(ResultService resultService, EndpointService endpointService, String endpointName, Integer delay, String url) {
        super(endpointName);
        this.resultService = resultService;
        this.endpointService = endpointService;
        this.delay = delay;
        this.url = url;
        this.endpoint = endpointService.getEndpointByName(super.getName());
        endpointId = this.endpoint.getEndpointId();
    }

    @Override
    public void run() {
        HttpURLConnection connection = null;

        try {
            while (true) {
                endpoint = endpointService.getByEndpointId(endpointId);
                if (!super.getName().equals(endpoint.getName())) {
                    super.setName(endpoint.getName());
                }
                if (!endpoint.getUrl().equals(this.url)){
                    this.url = endpoint.getUrl();
                }
                URL u = new URL(url);
                connection = (HttpURLConnection) u.openConnection();
                connection.setRequestMethod("HEAD");
                int code = connection.getResponseCode();
                String message = connection.getResponseMessage();
                resultService.saveMonitoringResult(getName(), code, message);
                if (delay != endpoint.getMonitoredInterval()) {
                    this.delay = endpoint.getMonitoredInterval();
                }
                Thread.sleep(delay * 1000);
            }
        } catch (NullPointerException e) {
            interrupt();
            log.info("Thread with \"{}\" endpoint was stopped", getName());
        } catch (Exception e) {
            e.printStackTrace();
            run();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
