package com.axiom.fulfillment.api;

import android.content.Context;

import com.axiom.fulfillment.helper.UserSharedPreferences;

import org.apache.http.params.HttpConnectionParams;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.android.gms.common.util.CollectionUtils.listOf;

public class APIClient {
    private static Retrofit retrofit = null;
    private static Retrofit retrofittoken = null;
    private static Retrofit retrofitforinvoice = null;
    private static Retrofit retrofitforpod = null;
    private static Retrofit retrofittokenpod = null;
    private static UserSharedPreferences pref;
    private Context mContext;

    public APIClient(Context context) {
        mContext = context;
        pref = new UserSharedPreferences(mContext);
    }

    public static Retrofit gettoken() {
        if (retrofittoken == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofittoken = new Retrofit.Builder()
                    .baseUrl("https://ecommerce.axiomtelecom.com:555/fulfilment/live/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofittoken;
    }


    public static Retrofit gettokenforpod() {
        if (retrofittokenpod == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofittokenpod = new Retrofit.Builder()
                    .baseUrl("https://ecommerce.axiomtelecom.com:555/PlugFulfil/Prod/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofittokenpod;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .header("Authorization", "Bearer " + pref.getAccessToken())
                            .addHeader("Connection","keep-alive")
                            .build();
                    return chain.proceed(newRequest);
                }
            })      .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                  // .baseUrl("https://ecommerce.axiomtelecom.com:555/fulfilment/stagging/")
                    .baseUrl("https://ecommerce.axiomtelecom.com:555/fulfilment/live/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientforinvoice() {
        if (retrofitforinvoice == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofitforinvoice = new Retrofit.Builder()
                    .baseUrl("https://fulfilment.axiomtelecom.com/AxiomFulfillService.svc/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitforinvoice;
    }

    public static Retrofit getClientforPod() {
        if (retrofitforpod == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .header("Authorization", "Bearer " + pref.getAccessTokenPod())
                            .addHeader("Connection","keep-alive")
                            .build();
                    return chain.proceed(newRequest);
                }
            })      .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();
            retrofitforpod = new Retrofit.Builder()
                    .baseUrl("https://ecommerce.axiomtelecom.com:555/PlugFulfil/Prod/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitforpod;
    }
}
