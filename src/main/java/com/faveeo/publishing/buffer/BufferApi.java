package com.faveeo.publishing.buffer;

import com.faveeo.publishing.buffer.api.BufferGateway;
import com.faveeo.publishing.buffer.api.BufferGatewayImpl;
import com.faveeo.publishing.buffer.api.BufferRetrofit;
import com.faveeo.publishing.buffer.api.BufferRetrofitClientFactory;

public final class BufferApi {


    private final BufferRetrofitClientFactory bufferRetrofitClientFactory;

    private BufferApi() {
        bufferRetrofitClientFactory = new BufferRetrofitClientFactory();
    }

    /**
     * Entrypoint to initializes the buffer client api
     *
     * @return the buffer client api.
     */
    public static BufferApi builder() {
        return new BufferApi();
    }

    /**
     * Overrides the Buffer API endpoint
     *
     * @param endpoint the endpoint
     * @return the builder
     */
    public BufferApi withEndpoint(final String endpoint) {
        if (!endpoint.endsWith("/")) {
            throw new IllegalArgumentException("Buffer API Endpoint must end in /");
        }
        bufferRetrofitClientFactory.setBufferApiUrl(endpoint);
        return this;
    }

    /**
     * Overrides the Buffer API read timeout
     *
     * @param readTimeout the timeout value
     * @return the builder
     */
    public BufferApi withReadtimeout(final long readTimeout) {
        bufferRetrofitClientFactory.setReadTimeout(readTimeout);
        return this;
    }

    /**
     * Overrides the Buffer API connect timeout
     *
     * @param connectTimeout the timeout value
     * @return the builder
     */
    public BufferApi withConnectTimeout(final long connectTimeout) {
        bufferRetrofitClientFactory.setConnectTimeout(connectTimeout);
        return this;
    }

    /**
     * Overrides the call timeout
     *
     * @param timeout the timeout value
     * @return the builder
     */
    public BufferApi withCallTimeout(final long timeout) {
        bufferRetrofitClientFactory.setCallTimeout(timeout);
        return this;
    }

    public BufferGateway build() {
        final BufferRetrofit bufferRetrofit = bufferRetrofitClientFactory.newClient();
        return new BufferGatewayImpl(bufferRetrofit);
    }
}
