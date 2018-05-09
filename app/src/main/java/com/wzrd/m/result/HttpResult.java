package com.wzrd.m.result;


import android.util.Log;

import com.wzrd.m.HttpMoulde;
import com.wzrd.m.been.Loginbeen;
import com.wzrd.v.view.ResultView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lk on 2017/10/30.
 */

public class HttpResult<T> {
    ResultView view;
    T t;

    public void parse(final ResultView view,final String message,String loginname,String passwad) {
        this.view = view;


        HttpMoulde.getInstance().getJoke(loginname,passwad,new Observer<Loginbeen>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Loginbeen list) {
                if(view!=null){
                    view.Result(list,message);
                }


            }

            @Override
            public void onError(Throwable e) {
                Log.e(" e"," e---->"+e.getMessage());
                if(view!=null){
                    view.Errar(e);
                }

            }

            @Override
            public void onComplete() {

            }
        });



}


}
