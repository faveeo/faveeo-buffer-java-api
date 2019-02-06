package com.faveeo.publishing.buffer.api;

import com.faveeo.publishing.buffer.api.representations.response.BufferErrorRepresentation;
import com.faveeo.publishing.buffer.api.representations.response.BufferUpdateResponseRepresentation;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Request;
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
            log.debug(BufferGatewayImpl.resourceBundle.getString("buffer.rest.call.with.success.response"), uri, response);
        }
        start.stop(okMeter);
        callback.onResponse(call, response);
    }

    @Override
    public final void onFailure(final Call<BufferUpdateResponseRepresentation> call, final Throwable exception) {
        final Response<BufferUpdateResponseRepresentation> execute;
        BufferErrorRepresentation bufferErrorRepresentation = null;
        try {
            execute = call.execute();
            final Reader charStream = execute.errorBody().charStream();
            bufferErrorRepresentation = parsingErrorRepresentation(charStream);

            if (bufferErrorRepresentation == null) {
                log.error(BufferGatewayImpl.resourceBundle.getString("error.creating.buffer.update.http.error"),
                    execute.code(), exception);
            } else {
                log.error(BufferGatewayImpl.resourceBundle.getString("error.creating.buffer.update.http.error.buffer.error"),
                    execute.code(),
                    bufferErrorRepresentation.code, bufferErrorRepresentation.message, exception);
            }
            start.stop(errorMeter);
        } catch (IOException e) {
            log.error("Cannot obtain the error response {}", e.getMessage(), e);
        }
        callback.onFailure(bufferErrorRepresentation, exception);
    }

    private BufferErrorRepresentation parsingErrorRepresentation(final Reader charStream)  {
        BufferErrorRepresentation bufferErrorRepresentation = null;
        try {
            bufferErrorRepresentation = BufferJacksonConfigFactory.getObjectMapper().readValue(charStream,
                BufferErrorRepresentation.class);
        } catch (final IOException e) {
            log.error(BufferGatewayImpl.resourceBundle.getString("could.not.parse.the.buffer.error.representation"), e);
        }
        return bufferErrorRepresentation;
    }
}
