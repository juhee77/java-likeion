package com.lahee.consumer.dto;

import lombok.Data;

@Data
public class JobPayload {
    private String jobId;
    private String filename;
    private String path;
}