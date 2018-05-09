package com.wzrd.m.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ExpandableListView;


import com.wzrd.m.been.MoviceBeen;
import com.wzrd.v.activity.CustomRyAdapter;
import com.wzrd.v.view.recycleview.RecycleViewDivider;
import com.wzrd.v.view.recycleview.YRecycleView;

import java.util.List;

/**
 * Created by lk on 2018/4/9.
 */

public class DataRecycleUtils {
    @BindingAdapter("customry")
    public static void setcustomryadapter(YRecycleView recyclerView, List<MoviceBeen.ItemListBean> data) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new RecycleViewDivider(recyclerView.getContext(), LinearLayoutManager.VERTICAL));
        CustomRyAdapter adapter = new CustomRyAdapter(recyclerView.getContext(), data);
        recyclerView.setAdapter(adapter);
    }


}
