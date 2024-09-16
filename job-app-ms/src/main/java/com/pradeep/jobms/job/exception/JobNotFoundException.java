package com.pradeep.jobms.job.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(String message) {
        super((message));
    }
}
