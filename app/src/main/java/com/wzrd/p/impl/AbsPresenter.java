package com.wzrd.p.impl;


import com.wzrd.p.InterPersneter;
import com.wzrd.v.view.ResultView;

/**
 * Created by lk on 2017/9/18.
 */

public abstract class AbsPresenter<V extends ResultView>  implements InterPersneter<V> {
    private V view;

    @Override
    public void attach(V view) {
        this.view=view;

    }

    @Override
    public void delattach() {
        this.view=null;
    }


    public V getView() {
        return view;
    }
}
