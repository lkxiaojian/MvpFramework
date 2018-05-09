package com.wzrd.p.impl;

import com.wzrd.v.view.RecycleResult;

/**
 * Created by lk on 2017/11/6.
 */

public abstract class AbsRyPresenter<V extends RecycleResult> {

    private  V view;


    public V getView() {
        return view;
    }
}
