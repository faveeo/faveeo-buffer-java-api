package com.faveeo.publishing.buffer;

import com.faveeo.publishing.buffer.api.BufferGateway;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BufferApiTest {

    @Test
    public void testBuilder() {
        BufferGateway bufferGateway = BufferApi.builder()
            .withEndpoint("https://buffer.com/api/")
            .withCallTimeout(10)
            .withConnectTimeout(10)
            .withReadtimeout(10)
            .build();

        Assertions.assertThat(bufferGateway).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuilder_with_invalid_endpoint() {
        BufferGateway bufferGateway = BufferApi.builder()
            .withEndpoint("https://buffer.com/api")
            .build();

        Assertions.assertThat(bufferGateway).isNotNull();
    }

}
