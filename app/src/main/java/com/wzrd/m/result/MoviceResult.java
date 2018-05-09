package com.wzrd.m.result;

import com.wzrd.m.HttpMoulde;
import com.wzrd.m.been.MoviceBeen;
import com.wzrd.v.view.ResultView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lk on 2017/11/1.
 */

public class MoviceResult {
    private ResultView view;

    public void getmovice(final ResultView view, final String message, String id){
        this.view=view;
        HttpMoulde.getInstance().getmovice(id, new Observer<MoviceBeen>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MoviceBeen moviceBeen) {
                if(view!=null){
                    view.Result(moviceBeen,message);
                }

            }

            @Override
            public void onError(Throwable e) {
                if(view!=null) {
                    view.Errar(e);
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
