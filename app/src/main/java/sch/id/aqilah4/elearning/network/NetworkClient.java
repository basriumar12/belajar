package sch.id.aqilah4.elearning.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sch.id.aqilah4.elearning.BuildConfig;
import sch.id.aqilah4.elearning.core.Const;


public class NetworkClient {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit() {
        if (retrofit == null){
            Gson gson   = new GsonBuilder()
                    .setLenient()
                    .create();

//
            //URL Server API
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(interceptor);
            }
            OkHttpClient okHttpClient = builder
                    .readTimeout(15, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build();

            retrofit= new Retrofit.Builder().baseUrl(Const.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

//            OkHttpClient.Builder builder    = new OkHttpClient.Builder();
//            OkHttpClient okHttpClient   = builder.build();
//            retrofit    = new Retrofit.Builder()
//                    .baseUrl(Const.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .client(okHttpClient)
//                    .build();
        }
        return retrofit;
    }


}
