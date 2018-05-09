package com.wzrd.v.view;

/**
 * Created by lk on 2017/10/30.
 */

public interface ResultView<T> {
    void Result(T result, String message);
    void Errar(T result);
    void Refresh(T refresh, String message);
}
