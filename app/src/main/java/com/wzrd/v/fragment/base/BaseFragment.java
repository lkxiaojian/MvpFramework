package com.wzrd.v.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.wzrd.p.impl.AbsPresenter;
import com.wzrd.v.view.ResultView;


/**
 * Created by lk on 2017/9/29.
 */

public abstract class BaseFragment<V extends ResultView, P extends AbsPresenter> extends Fragment implements ResultView {
    public P presenter;

    public Context context;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.presenter == null) {
            this.presenter = bindPresenter();
        }
        this.presenter.attach(bindView());


    }



    public abstract P bindPresenter();

    public abstract V bindView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.delattach();
    }
}
