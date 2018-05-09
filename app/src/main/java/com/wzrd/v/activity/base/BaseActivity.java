package com.wzrd.v.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wzrd.p.impl.AbsPresenter;
import com.wzrd.v.view.ResultView;


/**
 * Created by lk on 2017/9/18.
 */

public abstract class BaseActivity<V extends ResultView,P extends AbsPresenter> extends AppCompatActivity implements ResultView{

    public P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(this.presenter==null){
            this.presenter=bindPresenter();
        }
        this.presenter.attach(bindView());
    }




    public abstract P bindPresenter();
    public abstract V bindView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.delattach();
    }
}
