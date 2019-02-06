package com.faveeo.publishing.buffer.api;

import java.io.IOException;

public class RestErrorException extends IOException {
    public RestErrorException(final String message) {
        super(message);
    }
}
