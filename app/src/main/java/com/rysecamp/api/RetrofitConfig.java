package com.rysecamp.api;

import com.rysecamp.utils.RxErrorHandlingCallAdapterFactory;
import com.rysecamp.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sahil on 10/10/18.
 */

public class RetrofitConfig {

//    public static EgApi api;
//
//    public static EgApi apiLoc;
//
//    public static EgApi getInstance() {
//        if (api == null) {
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            // set your desired log level
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//            // add your other interceptors …
//
//            // add logging as last interceptor
//            httpClient.addInterceptor(logging);
//            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
//                    .baseUrl(BuildConfig.HOST)
//                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create());
//            if (BuildConfig.DEBUG)
//                retrofitBuilder.client(httpClient.build());
//            Retrofit retrofit = retrofitBuilder.build();
//            api = retrofit.create(EgApi.class);
//        }
//        return api;
//    }
//
//    public static EgApi getGeoInstance() {
//        if (apiLoc == null) {
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            // set your desired log level
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//            // add your other interceptors …
//
//          //  String BASE_URL = ApiUrls.BASEURL;
//            // add logging as last interceptor
//            httpClient.addInterceptor(logging);
//            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
//                    .baseUrl("https://api.rysecamp.com/")
//                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create());
//            if (BuildConfig.DEBUG)
//                retrofitBuilder.client(httpClient.build());
//            Retrofit retrofit = retrofitBuilder.build();
//            apiLoc = retrofit.create(EgApi.class);
//        }
//        return apiLoc;
//    }

}
