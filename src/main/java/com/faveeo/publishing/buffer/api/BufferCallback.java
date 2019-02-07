package com.faveeo.publishing.buffer.api;

import com.faveeo.publishing.buffer.api.representations.response.BufferErrorRepresentation;
import retrofit2.Call;
import retrofit2.Response;

/**
 * This class defines the callback to be implemented when a Buffer API is performed.
 *
 * @param <T> the type of object returned when the response is valid
 */
public interface BufferCallback<T> {

    public default void onResponse(final Call<T> call, final Response<T> response) {
    }

    public default void onFailure(final Call<T> call, final Throwable t) {
    }

    public default void onError(BufferErrorRepresentation bufferErrorRepresentation, Response<T> response) {
    }
}
