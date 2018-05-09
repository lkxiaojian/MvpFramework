package com.wzrd.p;

import android.support.v7.widget.RecyclerView;

import com.wzrd.m.ry.RecycleMoudle;
import com.wzrd.p.impl.AbsRyPresenter;
import com.wzrd.v.view.RecycleResult;

/**
 * Created by lk on 2017/11/6.
 */

public class Rypresenter extends AbsRyPresenter<RecycleResult> {
    private RecycleMoudle recycleMoudle;
    private RecycleResult recycleResult;

    public Rypresenter(RecycleResult view) {
        this.recycleMoudle = new RecycleMoudle();
        this.recycleResult=view;
    }

    public void getlast(RecyclerView recyclerView,String message){
        this.recycleMoudle.getlast(recyclerView,message,recycleResult);

    }
}
