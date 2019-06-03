package com.faveeo.publishing.buffer.api;

import java.io.IOException;

public class BufferGatewayException extends RuntimeException {
    /**
     * Instantiates a new Buffer gateway exception.
     *
     * @param e the e
     */
    public BufferGatewayException(final Throwable e) {
        super(e.getMessage(), e);
    }
}
