package com.kk2.user.net.client;


import com.kk2.user.net.retrfit.FastJsonConverterFactory;
import com.kk2.user.net.retrfit.HttpClientBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created on 17-8-1
 *
 * @author liuhuijie
 */

public class RetrofitManager {

    private static Retrofit mRetrofit;

    public static Retrofit build(String baseUrl) {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(HttpClientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .build();
        }

        return mRetrofit;
    }


}
