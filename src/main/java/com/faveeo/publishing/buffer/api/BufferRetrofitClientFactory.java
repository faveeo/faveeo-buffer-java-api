package com.faveeo.publishing.buffer.api;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * This class is a factory to build the Buffer Retrofit Client.
 */
@Slf4j
@Data
public class BufferRetrofitClientFactory {

    @SuppressWarnings("MagicNumber")
    private long readTimeout = 10L;
    @SuppressWarnings("MagicNumber")
    private long connectTimeout = 10L;
    @SuppressWarnings("MagicNumber")
    private long callTimeout = 10L;
    private String bufferApiUrl = "https://api.bufferapp.com/1/"; //NON-NLS

    public BufferRetrofitClientFactory() { }

    /**
     * Returns a new Buffer API Client using Retrofit.
     *
     * @return the Buffer API Client.
     */
    public BufferRetrofit newClient() {

        final HttpLoggingInterceptor interceptor = initLoggingInterceptor();

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.readTimeout(readTimeout, TimeUnit.MINUTES);
        builder.connectTimeout(connectTimeout, TimeUnit.MINUTES);
        builder.callTimeout(callTimeout, TimeUnit.MINUTES);
        final OkHttpClient okHttpClient = builder.build();

        final Retrofit.Builder builder1 = new Retrofit.Builder();
        builder1.client(okHttpClient);
        builder1.baseUrl(bufferApiUrl);
        builder1.addConverterFactory(JacksonConverterFactory.create(BufferJacksonConfigFactory.getObjectMapper()));
        final Retrofit retrofit = builder1.build();

        return retrofit.create(BufferRetrofit.class);
    }

    /**
     * This class initializes the logging interceptor.
     * @return the logging interceptor.
     */
    private static HttpLoggingInterceptor initLoggingInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (log.isDebugEnabled()) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        return interceptor;
    }
}
