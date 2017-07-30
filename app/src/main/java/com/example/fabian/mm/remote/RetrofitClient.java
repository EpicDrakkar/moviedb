package com.example.fabian.mm.remote;

/**
 * Created by fabian on 7/26/17.
 */

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    public static final int DEFAULT_TIMEOUT = 20;

    private static okhttp3.OkHttpClient.Builder getHttpClientBuilder() {
        okhttp3.OkHttpClient.Builder httpClient = new okhttp3.OkHttpClient.Builder();
        httpClient.protocols(Collections.singletonList(okhttp3.Protocol.HTTP_1_1));
        httpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        return httpClient;
    }

    private static HttpLoggingInterceptor getLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public static Retrofit getClient(String baseUrl) {
        okhttp3.OkHttpClient.Builder httpClient = getHttpClientBuilder();

        httpClient.addInterceptor(getLogInterceptor());
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
