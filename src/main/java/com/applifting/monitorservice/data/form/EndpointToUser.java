package com.applifting.monitorservice.data.form;

import lombok.Data;

@Data
public class EndpointToUser {
    private String endpointName;
    private String url;
    private Integer monitoredInterval;
}
