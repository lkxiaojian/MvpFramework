package com.wzrd.m;

import com.wzrd.m.been.Loginbeen;
import com.wzrd.m.been.MoviceBeen;
import com.wzrd.p.Apiservers;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lk on 2017/9/18.
 */

public class HttpMoulde<T> {


    private static final String BASE_URL = "http://baobab.kaiyanapp.com/api/" ;
    private static final int TIME_OUT = 4;
    private Retrofit retrofit;
    private Apiservers apiService;

    private HttpMoulde() {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(Apiservers.class);

    }


    private static class sinalInstance {
        public static final HttpMoulde instance = new HttpMoulde();
    }

    public static HttpMoulde getInstance() {
        return sinalInstance.instance;
    }

    public void getJoke(String loginname,String passwad,Observer<Loginbeen> observer) {
        apiService.getData(loginname,passwad,"cscec2bsafe")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void getmovice(String id , Observer<MoviceBeen> observable){
        apiService.getmovice(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);

    }






}
