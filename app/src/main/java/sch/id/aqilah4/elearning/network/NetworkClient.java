package sch.id.aqilah4.elearning.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sch.id.aqilah4.elearning.core.Const;


public class NetworkClient {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit() {
        if (retrofit == null){
            Gson gson   = new GsonBuilder()
                    .setLenient()
                    .create();

            OkHttpClient.Builder builder    = new OkHttpClient.Builder();
            OkHttpClient okHttpClient   = builder.build();
            retrofit    = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
