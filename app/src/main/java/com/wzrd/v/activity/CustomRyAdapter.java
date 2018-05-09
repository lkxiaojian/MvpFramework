package com.wzrd.v.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.wzrd.R;
import com.wzrd.m.been.MoviceBeen;
import com.wzrd.v.adapter.base.BaseDataRecycleAdapter;

import java.util.List;

/**
 * Created by lk on 2018/5/9.
 */

public class CustomRyAdapter extends BaseDataRecycleAdapter {
    private Context mContext;
    private List<MoviceBeen.ItemListBean> itemList;

    public CustomRyAdapter(Context mContext, List data) {
        super(mContext, data);
        this.mContext = mContext;
        this.itemList = data;
    }

    @Override
    public int getLayoutId() {
        return R.layout.custom_item_adapter;
    }

}
