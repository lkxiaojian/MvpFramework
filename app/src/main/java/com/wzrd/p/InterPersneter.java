package com.wzrd.p;


import com.wzrd.v.view.ResultView;

/**
 * Created by lk on 2017/9/18.
 */

public interface InterPersneter<V extends ResultView> {
    void attach(V view);
    void delattach();
}
