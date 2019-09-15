package com.alltrustnetworks.smartcheck;

import android.content.Context;
import android.util.Log;

import com.alltrustnetworks.smartcheck.models.Check;
import com.alltrustnetworks.smartcheck.models.misnap.Transaction;
import com.alltrustnetworks.smartcheck.util.Constant;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.Okio;
import java.io.File;

/**
 * MisnapApi API
 * @author
 * @version 1.0
 */
public class MisnapApi {
    private String url;
    private String username;
    private String password;
    private static MisnapApi instance = null;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Transaction> transactionAdapter = moshi.adapter(Transaction.class);

    public MisnapApi(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        MisnapApi.instance = this;
    }

    public static MisnapApi getInstance() {
        return instance;
    }

    public Transaction depositCheck(Check check) throws IOException {
        OkHttpClient httpClient =  this.createAuthenticatedClient(username, password);

        Transaction transaction = null;

        try {
            JSONObject json = new JSONObject();
            json.put("AccountNickName", "Mobile");
            json.put("AmountInCents", 100000);
            // Production
            json.put("PhoneUserName", BuildConfig.phoneUserName);
            // UAT
//            json.put("PhoneUserName", "mobileuser");
            RequestBody frontImage = RequestBody.create(MediaType.parse("image/jpeg"), Check.frontOriginalImage);
            RequestBody rearImage = RequestBody.create(MediaType.parse("image/jpeg"), Check.rearOriginalImage);
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("Json", json.toString())
                    .addFormDataPart("FrontImage", "front.jpg", frontImage)
                    .addFormDataPart("RearImage", "rear.jpg", rearImage)
                    .build();

            Request request = new Request.Builder()
                    .url(this.url + "/transactions")
                    .post(body)
                    .build();

            Response response = httpClient.newCall(request).execute();
            return transactionAdapter.fromJson(response.body().string());
        }
        catch(JSONException e) {
            Log.e("SmartCheck", e.getMessage());
        }

        return transaction;
    }

    public File getCheckImage(Context context,  int transactionId, String side) throws IOException {
        OkHttpClient httpClient =  this.createAuthenticatedClient(username, password);
        Request request = new Request.Builder()
                .url(this.url + "/transactions/" + transactionId + "/image?side=" + side + "&type=tiff")
                .get()
                .build();
        Response response = httpClient.newCall(request).execute();
        File file = new File(context.getCacheDir(), "check" + side + ".tiff");
        BufferedSink sink = Okio.buffer(Okio.sink(file));
        sink.writeAll(response.body().source());
        sink.close();
        return file;
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.NONE);
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    private static OkHttpClient createAuthenticatedClient(final String username, final String password) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Interceptor tokenInterceptor = chain -> {
            Request original = chain.request();
            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                    .addHeader("Authorization", Credentials.basic(username,password))
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

    private String getCredentials() {
        return Credentials.basic(username, password);
    }
}
