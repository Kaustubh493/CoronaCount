package com.kale.gaurav.coronacount.Utils;

import com.kale.gaurav.coronacount.Models.Example;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class BloggerAPI {
    public static final String url = "https://bing.com/";

    public static PostService postService = null;

    private static OkHttpClient okClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();
    }

    public static PostService getService() {
        if (postService == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(okClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService {

        @GET("covid/data?IG=643D94B857844E85BC18C4478A4D4BE1")
        Call<Example> getWorldData();

    }
}
