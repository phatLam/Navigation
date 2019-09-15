package com.alltrustnetworks.smartcheck.di;

import com.alltrustnetworks.smartcheck.BuildConfig;
import com.alltrustnetworks.smartcheck.util.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public final class HttpDependence{

    private static HttpDependence instance;

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    private static OkHttpClient okHttpClient;

    public static HttpDependence getInstance() {
        if (instance == null){
            instance = new HttpDependence();
        }
        return instance;
    }
    public void initHttp(String apiToken){
        if (okHttpClient == null){
            okHttpClient = createAuthenticatedClient(apiToken);
        }
    }
    private HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.NONE);
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    private OkHttpClient createAuthenticatedClient(String apiToken) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Interceptor tokenInterceptor = chain -> {
            Request original = chain.request();
            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Authorization",Credentials.basic(apiToken, ""))
                    .addHeader("Content-Type", "application/json");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };

        builder.addInterceptor(tokenInterceptor)
                .addInterceptor(provideHttpLoggingInterceptor())
                .connectTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constant.TIMEOUT, TimeUnit.SECONDS);
        return builder.build();

    }
}
