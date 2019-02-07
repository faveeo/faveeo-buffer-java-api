package com.faveeo.publishing.buffer.api;

import com.faveeo.publishing.buffer.api.representations.response.BufferErrorRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferUpdateResponseRepresentation;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;

@Slf4j
public class BufferRetrofitCallback implements Callback<BufferUpdateResponseRepresentation> {

    private static final String METER_NAME_BUFFER_UPDATES_OK = "publishing.buffer.updates.ok"; //NON-NLS
    private static final String METER_NAME_BUFFER_UPDATES_ERROR = "publishing.buffer.updates.error"; //NON-NLS
    private final Timer.Sample start;
    private final BufferCallback<BufferUpdateResponseRepresentation> callback;
    private final Timer okMeter;
    private final Timer errorMeter;

    public BufferRetrofitCallback(final BufferCallback<BufferUpdateResponseRepresentation> bufferCallback) {
        this.callback = bufferCallback;
        this.okMeter = Metrics.timer(METER_NAME_BUFFER_UPDATES_OK);
        this.errorMeter = Metrics.timer(METER_NAME_BUFFER_UPDATES_ERROR);
        start = Timer.start();
    }

    @Override
    public final void onResponse(final Call<BufferUpdateResponseRepresentation> call,
                                 final Response<BufferUpdateResponseRepresentation> response) {
        if (log.isDebugEnabled()) {
            final Request request = call.request();
            final HttpUrl url = request.url();
            final URI uri = url.uri();
            log.debug(BufferGatewayImpl.resourceBundle.getString("buffer.rest.call.with.success.response"), uri, response.code(), response);
        }
        if (response.isSuccessful()) {
            start.stop(okMeter);
            callback.onResponse(call, response);
        } else {
            onError(call, response);
        }
    }

    public void onError(final Call<BufferUpdateResponseRepresentation> call,
                        final Response<BufferUpdateResponseRepresentation> response) {
        BufferErrorRepresentation bufferErrorRepresentation = null;

        try {
            final ResponseBody responseBody = response.errorBody();
            if (responseBody != null) {
                bufferErrorRepresentation = parsingErrorRepresentation(responseBody.charStream());
            }

            if (bufferErrorRepresentation == null) {
                log.error(BufferGatewayImpl.resourceBundle.getString("error.creating.buffer.update.http.error"),
                        response.code());
            } else {
                log.error(BufferGatewayImpl.resourceBundle.getString("error.creating.buffer.update.http.error.buffer.error"),
                        response.code(),
                        bufferErrorRepresentation.code, bufferErrorRepresentation.message);
            }
        } finally {
            start.stop(errorMeter);
            callback.onError(bufferErrorRepresentation, response);
        }

    }

    private BufferErrorRepresentation parsingErrorRepresentation(final Reader charStream) {
        BufferErrorRepresentation bufferErrorRepresentation = null;
        try {
            bufferErrorRepresentation = BufferJacksonConfigFactory.getObjectMapper().readValue(charStream,
                    BufferErrorRepresentation.class);
        } catch (final IOException e) {
            log.error(BufferGatewayImpl.resourceBundle.getString("could.not.parse.the.buffer.error.representation"), e);
        }
        return bufferErrorRepresentation;
    }

    @Override
    public final void onFailure(final Call<BufferUpdateResponseRepresentation> call, final Throwable exception) {
        log.error(BufferGatewayImpl.resourceBundle.getString("error.creating.buffer.update.http.error"), -1, exception);
        start.stop(errorMeter);
        callback.onFailure(null, exception);
    }
}

