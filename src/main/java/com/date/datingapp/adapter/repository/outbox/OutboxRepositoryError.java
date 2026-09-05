package com.date.datingapp.adapter.repository.outbox;

import com.date.datingapp.shared.exception.CodedException;

public class OutboxRepositoryError {

    public static final String ERR_EVENT_IS_REQUIRED = "011891fc94-001";

    private OutboxRepositoryError() {
    }

    public static CodedException errOutboxEventIsRequired() {
        var msg = "Event is required";
        return new CodedException(ERR_EVENT_IS_REQUIRED, msg);
    }
}
